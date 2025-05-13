package com.expensive.api.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.expensive.api.dto.LoginReqDto;
import com.expensive.api.dto.LoginResDto;
import com.expensive.api.dto.RegisterReqDto;
import com.expensive.api.entities.User;
import com.expensive.api.repositories.UserRepository;

@Service
public class AuthenticationService {
    
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final EmailService emailService;
    private final JwtService jwtService;

    @Autowired
    public AuthenticationService(
        UserRepository userRepository,
        PasswordEncoder passwordEncoder,
        EmailService emailService,
        JwtService jwtService
    ) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.emailService = emailService;
        this.jwtService = jwtService;
    }

    public boolean registerUser(RegisterReqDto registerReqDto) {

        Optional<User> optionalUser = userRepository.findByEmail(registerReqDto.getEmail());
        if(optionalUser.isPresent()) 
            throw new RuntimeException("Email is already registerd."); 

        String encodedPassword = passwordEncoder.encode(registerReqDto.getPassword());

        User user = new User();
        user.setUsername(registerReqDto.getUsername());
        user.setEmail(registerReqDto.getEmail());
        user.setPassword(encodedPassword);

        userRepository.save(user);

        return true;
    }
    
    public LoginResDto loginUser(LoginReqDto loginReqDto) {
        
        Optional<User> optionalUser = userRepository.findByEmail(loginReqDto.getEmail());
        if(!optionalUser.isPresent())
            throw new RuntimeException("User is not registered.");
        else {
            User user = optionalUser.get();

            boolean passwordMatches = passwordEncoder.matches(loginReqDto.getPassword(), user.getPassword());

            if(!passwordMatches)
                throw new RuntimeException("Invalid password for given user.");
            
            String token = jwtService.generateToken(user.getId(), user.getUsername(), user.getEmail());
            LoginResDto loginResDto = new LoginResDto(
                token,
                user.getUsername(),
                user.getEmail(),
                "Successfully Loged In."
            );
            return loginResDto;   
        }

    }


}
