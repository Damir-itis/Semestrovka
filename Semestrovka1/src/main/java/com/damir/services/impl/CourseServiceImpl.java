package com.damir.services.impl;

import com.damir.dto.CourseDto;
import com.damir.dto.CourseInfoDto;
import com.damir.dto.DtoMapper;
import com.damir.dto.LessonDto;
import com.damir.models.*;
import com.damir.repositories.CourseRepository;
import com.damir.repositories.HomeworkRepository;
import com.damir.repositories.LessonRepository;
import com.damir.repositories.UserRepository;
import com.damir.services.CourseService;
import com.damir.services.FilesService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.Collections;
import java.util.List;

@RequiredArgsConstructor
@Service
public class CourseServiceImpl implements CourseService {

    private final CourseRepository courseRepository;
    private final UserRepository userRepository;
    private final DtoMapper dtoMapper;
    private final FilesService filesService;
    private final LessonRepository lessonRepository;
    private final HomeworkRepository homeworkRepository;

    @Override
    public Course createCourse(CourseDto courseDto, User user) {
        Course course = dtoMapper.courseDtoToCourse(courseDto);
        course.setCreator(user);
        course.setSubscribers(Collections.singletonList(user));
        return courseRepository.save(course);
    }

    @Override
    public Course getCourse(Long courseId) {
        return courseRepository.findById(courseId).orElseThrow();
    }

    @Override
    public List<Course> getMyCourses(User user) {
        return userRepository.findById(user.getId()).orElseThrow().getCourses();
    }

    @Override
    public void createLesson(Long courseId, LessonDto lessonDto, MultipartFile multipart, User user) {
        Course course = courseRepository.findById(courseId).orElseThrow();

        if (!course.getCreator().getId().equals(user.getId())) throw new RuntimeException();

        FileInfo video = filesService.upload(multipart);

        Lesson lesson = dtoMapper.lessonDtoToLesson(lessonDto);
        lesson.setCourse(course);
        lesson.setVideo(video);

        course.getLessons().add(lesson);

        courseRepository.save(course);
    }


    @Override
    public List<Course> getAllCourses() {
        return courseRepository.findAll();
    }

    @Override
    public void joinToCourse(Long courseId, User user) {
        Course course = courseRepository.findById(courseId).orElseThrow();
        if (course.getSubscribers().stream().anyMatch(x->x.getId().equals(user.getId()))) throw new RuntimeException();

        course.getSubscribers().add(user);

        courseRepository.save(course);
    }

    @Override
    public CourseInfoDto getCourseInfo(Long courseId, User user) {
        Course course = courseRepository.findById(courseId).orElseThrow();
        user = userRepository.findById(user.getId()).orElseThrow();
        boolean inCourse = user.getCourses().contains(course);
        boolean isCreator = course.getCreator().getId().equals(user.getId());
        int studentsCount = course.getSubscribers().size();
        return CourseInfoDto.builder()
                .inCourse(inCourse)
                .isCreator(isCreator)
                .studentsCount(studentsCount)
                .build();
    }

    @Override
    public void updateCourse(Long courseId, CourseDto courseDto, User user) {
        Course course = courseRepository.findById(courseId).orElseThrow();

        if (!course.getCreator().getId().equals(user.getId())) throw new RuntimeException();

        course.setTitle(courseDto.getTitle());
        course.setDescription(courseDto.getDescription());

        courseRepository.save(course);
    }

    @Override
    public void deleteCourse(Long courseId, User user) {
        Course course = courseRepository.findById(courseId).orElseThrow();

        if (!course.getCreator().getId().equals(user.getId())) throw new RuntimeException();


        courseRepository.deleteById(courseId);
    }

}
