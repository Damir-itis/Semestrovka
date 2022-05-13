package com.damir.dto;

import com.damir.models.Course;
import com.damir.models.Homework;
import com.damir.models.Lesson;
import com.damir.models.User;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface DtoMapper {

    UserDto userToUserDto(User user);

    Course courseDtoToCourse(CourseDto courseDto);
    CourseDto courseToCourseDto(Course course);

    Lesson lessonDtoToLesson(LessonDto lessonDto);
    LessonDto lessonToLessonDto(Lesson lesson);

    Homework homeworkDtoToHomework(HomeworkDto homeworkDto);
    HomeworkDto homeworkToHomeworkDto(Homework homework);

}
