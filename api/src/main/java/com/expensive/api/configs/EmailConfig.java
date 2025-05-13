package com.expensive.api.configs;

import java.util.Properties;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;

@Configuration
public class EmailConfig {

    @Value("${spring.mail.username}")
    private String username;

    @Value("${spring.mail.host}")
    private String hostname;

    @Value("${spring.mail.password}")
    private String appPassword;

    @Value("${spring.mail.port}")
    private int port;
    
    @Value("${spring.mail.properties.mail.smtp.auth}")
    private boolean authprop;

    @Value("${spring.mail.properties.mail.smtp.starttls.enable}")
    private boolean ttlsprop;
    
    @Bean
    public JavaMailSender javaMailSender() {
        JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
        mailSender.setHost(hostname);
        mailSender.setPort(port);
        mailSender.setUsername(username);
        mailSender.setPassword(appPassword);
        
        Properties props = mailSender.getJavaMailProperties();
        props.put("mail.smtp.auth", authprop);
        props.put("mail.smtp.starttls.enable", ttlsprop);
        props.put("mail.debug", true);
        props.put("mail.transport.protocol", "smtp");

        return mailSender;
    }
}
