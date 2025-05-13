package com.expensive.api.services;

import java.time.LocalDateTime;
import java.util.Optional;

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

    @Autowired 
    public UserService(UserRepository userRepository, EmailService emailService) {
        this.userRepository = userRepository;
        this.emailService = emailService;
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
}
