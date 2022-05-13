package com.damir.controllers;

import com.damir.dto.CourseInfoDto;
import com.damir.dto.HomeworkDto;
import com.damir.dto.HomeworkInfoDto;
import com.damir.models.Homework;
import com.damir.models.Lesson;
import com.damir.models.Task;
import com.damir.security.details.UserSecurity;
import com.damir.services.CourseService;
import com.damir.services.FilesService;
import com.damir.services.HomeworkService;
import com.damir.services.LessonService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import java.util.Optional;

@RequiredArgsConstructor
@Controller
@RequestMapping("/lesson")
public class LessonController {

    private final FilesService filesService;
    private final LessonService lessonService;
    private final CourseService courseService;
    private final HomeworkService homeworkService;

    @GetMapping("/{lessonId}")
    public String getLesson(@AuthenticationPrincipal UserSecurity userSecurity,
                            Model model,
                            @PathVariable Long lessonId) {
        Lesson lesson = lessonService.getLesson(lessonId, userSecurity.getUser());
        CourseInfoDto courseInfo = courseService.getCourseInfo(lesson.getCourse().getId(), userSecurity.getUser());

        HomeworkInfoDto homeworkInfo;
        Optional<Homework> optionalHomework = lesson.getHomeworks().stream().filter(x -> x.getStudent().getId().equals(userSecurity.getUser().getId())).findFirst();
        if (optionalHomework.isEmpty())
            homeworkInfo = homeworkService.getHomeworkInfo(null, userSecurity.getUser());
        else
            homeworkInfo = homeworkService.getHomeworkInfo(optionalHomework.get().getId(), userSecurity.getUser());
        model.addAttribute("homeworkInfo", homeworkInfo);
        model.addAttribute("courseInfo", courseInfo);
        model.addAttribute("lesson", lesson);
        return "lesson";
    }

    @PostMapping("/{lessonId}/createTask")
    public String addHomework(@PathVariable Long lessonId, Task task) {
        lessonService.createTask(lessonId, task);
        return "redirect:/lesson/" + lessonId;
    }

    @PostMapping("/{lessonId}/passHomework")
    public String passHomework(@AuthenticationPrincipal UserSecurity userSecurity,
                               @PathVariable Long lessonId,
                               HomeworkDto homeworkDto,
                               @RequestParam("photo") MultipartFile[] multipart) {
        lessonService.passHomework(lessonId, homeworkDto, multipart, userSecurity.getUser());
        return "redirect:/lesson/" + lessonId;
    }

    @ExceptionHandler({RuntimeException.class})
    public ModelAndView handleException(RuntimeException e) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("exception", e);

        modelAndView.setViewName("error");
        return modelAndView;
    }

}
