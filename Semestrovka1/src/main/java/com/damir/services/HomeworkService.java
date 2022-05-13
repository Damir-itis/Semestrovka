package com.damir.services;


import com.damir.dto.HomeworkInfoDto;
import com.damir.models.Homework;
import com.damir.models.User;

public interface HomeworkService {

    Homework getHomework(Long homeworkId, User user);

    void checkHomework(Long homeworkId, Integer score);

    HomeworkInfoDto getHomeworkInfo(Long homeworkId, User user);
}
