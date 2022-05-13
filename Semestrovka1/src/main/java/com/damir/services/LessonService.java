package com.damir.services;

import com.damir.dto.HomeworkDto;
import com.damir.models.Lesson;
import com.damir.models.Task;
import com.damir.models.User;
import org.springframework.web.multipart.MultipartFile;

public interface LessonService {

    Lesson getLesson(Long lessonId, User user);

    void createTask(Long lessonId, Task task);

    void passHomework(Long lessonId, HomeworkDto homeworkDto, MultipartFile[] multipart, User user);
}
