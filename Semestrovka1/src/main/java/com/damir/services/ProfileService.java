package com.damir.services;

import com.damir.dto.SignUpDto;
import com.damir.dto.UserDto;
import com.damir.models.FileInfo;
import com.damir.models.User;

import java.util.List;

public interface ProfileService {

    void editProfile(User user, SignUpDto userDto, FileInfo fileInfo);

    List<User> getTeachers();

    UserDto getUserById(Long userId);

    UserDto getTeacherById(Long userId);
}
