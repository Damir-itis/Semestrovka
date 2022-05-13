package com.damir.services.impl;

import com.damir.dto.DtoMapper;
import com.damir.dto.SignUpDto;
import com.damir.dto.UserDto;
import com.damir.models.FileInfo;
import com.damir.models.User;
import com.damir.repositories.UserRepository;
import com.damir.services.ProfileService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class ProfileServiceImpl implements ProfileService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final DtoMapper dtoMapper;

    @Override
    public void editProfile(User user, SignUpDto userDto, FileInfo fileInfo) {
        if (!userDto.getFirstName().equals("")) user.setFirstName(userDto.getFirstName());
        if (!userDto.getLastName().equals("")) user.setLastName(userDto.getLastName());
        if (!userDto.getPassword().equals("")) user.setPasswordHash(passwordEncoder.encode(userDto.getPassword()));
        if (fileInfo !=null) user.setAvatar(fileInfo);
        userRepository.save(user);
    }

    @Override
    public List<User> getTeachers() {
        return null;
    }


    @Override
    public UserDto getUserById(Long userId) {
        return dtoMapper.userToUserDto(userRepository.findById(userId).orElseThrow());
    }

    @Override
    public UserDto getTeacherById(Long userId) {
        return null;
    }

}
