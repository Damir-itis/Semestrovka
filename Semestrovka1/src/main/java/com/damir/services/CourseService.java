package com.damir.services;

import com.damir.dto.CourseDto;
import com.damir.dto.CourseInfoDto;
import com.damir.dto.LessonDto;
import com.damir.models.Course;
import com.damir.models.User;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface CourseService {

    Course createCourse(CourseDto courseDto, User user);

    Course getCourse(Long courseId);

    List<Course> getMyCourses(User user);



    void createLesson(Long courseId, LessonDto lessonDto, MultipartFile multipart, User user);

    List<Course> getAllCourses();

    void joinToCourse(Long courseId, User user);

    CourseInfoDto getCourseInfo(Long courseId, User user);

    void updateCourse(Long courseId, CourseDto course, User user);

    void deleteCourse(Long courseId, User user);
}
