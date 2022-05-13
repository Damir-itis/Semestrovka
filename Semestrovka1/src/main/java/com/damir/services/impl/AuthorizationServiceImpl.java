package com.damir.services.impl;

import com.damir.util.EmailUtil;
import com.damir.dto.SignUpDto;
import com.damir.models.User;
import com.damir.repositories.UserRepository;
import com.damir.services.AuthorizationService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

@RequiredArgsConstructor
@Service
public class AuthorizationServiceImpl implements AuthorizationService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final EmailUtil emailUtil;




    @Override
    public void registration(SignUpDto signUpDto) {
        Optional<User> optionalAccount = userRepository.findByUsername(signUpDto.getEmail());

        if (optionalAccount.isEmpty()) {
            User user = User.builder()
                    .firstName(signUpDto.getFirstName())
                    .lastName(signUpDto.getLastName())
                    .email(signUpDto.getEmail())
                    .passwordHash(passwordEncoder.encode(signUpDto.getPassword()))
                    .confirmCode(UUID.randomUUID().toString())
                    .status(User.Status.UNCONFIRMED)
                    .build();

            userRepository.save(user);

            Map<String, Object> data = new HashMap<>();
            data.put("firstName", user.getFirstName());
            data.put("confirmCode", user.getConfirmCode());

            emailUtil.sendMail(user.getEmail(), "confirm", "confirm_mail", data);


        } else {
            throw new RuntimeException("Пользователь уже существует");
        }


    }

    @Override
    public void oauth2Registration(SignUpDto signUpDto) {
        Optional<User> optionalAccount = userRepository.findByUsername(signUpDto.getEmail());

        if (optionalAccount.isEmpty()) {
            User user = User.builder()
                    .firstName(signUpDto.getFirstName())
                    .lastName(signUpDto.getLastName())
                    .email(signUpDto.getEmail())
                    .status(User.Status.ACTIVE)
                    .build();

            userRepository.save(user);

        }


    }


    @Override
    public void confirm(String confirmCode) {
        User user = userRepository.findByConfirmCode(confirmCode).orElseThrow(()-> new RuntimeException("Неверный код подтверждения"));
        user.setStatus(User.Status.ACTIVE);
        user.setConfirmCode(null);
        userRepository.save(user);
    }


}
