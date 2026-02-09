package com.example.cursovik.service;

import com.example.cursovik.Role;
import com.example.cursovik.entity.User;
import com.example.cursovik.repository.UserRepository;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JavaMailSender mailSender;

    public UserService(UserRepository userRepository,
                       PasswordEncoder passwordEncoder,
                       JavaMailSender mailSender){
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.mailSender = mailSender;
    }

    public void registerTeacher(User user){
        String password = user.getPassword();
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setRole(Role.TEACHER);
        userRepository.save(user);
        sendEmail(password, user.getEmail());
    }

    public void registerParent(User user){
        String password = user.getPassword();
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setRole(Role.PARENT);
        userRepository.save(user);
        sendEmail(password, user.getEmail());
    }

    private void sendEmail(String password, String to){
        SimpleMailMessage msg = new SimpleMailMessage();
        msg.setFrom("zhurkovr@mail.ru");
        msg.setTo(to);
        msg.setSubject("Регистрация завершена");
        msg.setText("Вы были успешно зарегестрированы в системе \nВаш пароль: " + password);
        mailSender.send(msg);
    }
}
