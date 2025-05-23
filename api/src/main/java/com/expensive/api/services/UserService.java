package com.expensive.api.services;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import com.expensive.api.dto.UserDto;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import com.expensive.api.dto.MessageDto;
import com.expensive.api.entities.User;
import com.expensive.api.repositories.UserRepository;

@Service
public class UserService {
    
    private final UserRepository userRepository;
    private final EmailService emailService;
    private final ModelMapper modelMapper;

    @Autowired 
    public UserService(UserRepository userRepository, EmailService emailService, ModelMapper modelMapper) {
        this.userRepository = userRepository;
        this.emailService = emailService;
        this.modelMapper = modelMapper;
    }

    public UserDetails loadByUserName(String email) {
        Optional<User> optionalUser = userRepository.findByEmail(email);
        if(optionalUser.isPresent()) 
            return optionalUser.get();
        else 
            throw new RuntimeException("No user found with given email."); 
        
    }

    public MessageDto verifyUser(String username) {
        User user = userRepository.findByUsername(username) 
                .orElseThrow(() -> new RuntimeException(username + " not found."));
        
        String verificationCode = generateRandomCode(6);
        user.setVerificationCode(verificationCode);
        user.setVerificationCodeExpired(LocalDateTime.now().plusHours(5));
        userRepository.save(user);
        System.out.println(user.getEmail());
        String subject = "Verification mail for S0ggy App.";
        String verificationLink = "http://localhost:8080/api/auth/enable?token=" + verificationCode + "&email=" + user.getEmail();
        String text = "Click on the verification link to verify your account: " + verificationLink;
        try {   
            emailService.sendVerificationMail(user.getEmail(), subject, text);
        } catch(Exception ex){
            throw new RuntimeException("Error sending mail");
        }

        return new MessageDto("Verification Code is sent to your email.");
    }

    public MessageDto enableUser(String email, String code) {
        
        User user = userRepository.findByEmail(email)
            .orElseThrow(() -> new RuntimeException("Email not found."));
        
        if(code.equals(user.getVerificationCode()) && LocalDateTime.now().isBefore(user.getVerificationCodeExpired())) {
            user.setEnabled(true);
            userRepository.save(user);
        } else {
            throw new RuntimeException("Verification Link is expired.");
        }

        return new MessageDto("The User is verified");
    }

    private String generateRandomCode(int length) {
        String characters = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
        StringBuilder code = new StringBuilder();
        for (int i = 0; i < length; i++) {
            int index = (int) (Math.random() * characters.length());
            code.append(characters.charAt(index));
        }
        return code.toString();
    }

    public List<UserDto> getAllUsers() {
        List<User> usersList = userRepository.findAll();
        List<UserDto> userDtosList = usersList
                .stream()
                .map(user -> modelMapper.map(user, UserDto.class))
                .toList();
        if(userDtosList.isEmpty()) {
            throw new RuntimeException("No Users are created.");
        }
        return userDtosList;
    }

    public UserDto getUserById(long id) {
        Optional<User> user = userRepository.findById(id);
        if(user.isEmpty()) {
            throw new RuntimeException("The User does not exist.");
        }
        return modelMapper.map(user.get(), UserDto.class);
    }

    public UserDto updateUser(long id, UserDto userDto) {
        Optional<User> user = userRepository.findById(id);
        if(user.isEmpty()) {
            throw new RuntimeException("The User does not exist.");
        }

        User userToUpdate = modelMapper.map(userDto, User.class);

        User updatedUser = userRepository.save(userToUpdate);
        return modelMapper.map(updatedUser, UserDto.class);
    }

    public UserDto deleteUser(long id) {
        Optional<User> user = userRepository.findById(id);
        if(user.isEmpty()) {
            throw new RuntimeException("The User does not exist.");
        }
        User userToDelete = user.get();
        userRepository.delete(userToDelete);
        return modelMapper.map(userToDelete, UserDto.class);
    }
}
