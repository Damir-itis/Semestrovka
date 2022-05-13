package com.damir.services;

import com.damir.dto.SignUpDto;

public interface AuthorizationService {

    void registration(SignUpDto customerDto);

    void oauth2Registration(SignUpDto signUpDto);

    void confirm(String confirmCode);


}
