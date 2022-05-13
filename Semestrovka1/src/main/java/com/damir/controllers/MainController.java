package com.damir.controllers;


import com.damir.dto.SignUpDto;
import com.damir.models.Course;
import com.damir.models.FileInfo;
import com.damir.models.User;
import com.damir.security.details.UserSecurity;
import com.damir.services.CourseService;
import com.damir.services.FilesService;
import com.damir.services.ProfileService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@RequiredArgsConstructor
@Controller
@RequestMapping("/")
public class MainController {

    private final ProfileService profileService;
    private final FilesService filesService;
    private final CourseService courseService;


    @GetMapping("/")
    public String index(Model model) {
        List<Course> courses = courseService.getAllCourses();
        model.addAttribute("courses", courses);
        return "homepage";
    }

    @GetMapping("/registration")
    public String registration() {
        return "registration";
    }

    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @GetMapping("/profilepage")
    public String getProfile(){
        return "profilepage";
    }


    @GetMapping("/editpage")
    public String editpage() {
        return "editpage";
    }

    @PostMapping("/editpage")
    public String editProfile(SignUpDto signUpDto, @RequestParam("avatar") MultipartFile multipart, @AuthenticationPrincipal UserSecurity userSecurity) {
        User user = userSecurity.getUser();
        FileInfo fileInfo = null;
        if (!multipart.isEmpty()) fileInfo = filesService.upload(multipart);
        profileService.editProfile(user, signUpDto, fileInfo);
        return "redirect:/profilepage";
    }

    @ExceptionHandler({RuntimeException.class})
    public ModelAndView handleException(RuntimeException e) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("exception", e);

        modelAndView.setViewName("error");
        return modelAndView;
    }


}


