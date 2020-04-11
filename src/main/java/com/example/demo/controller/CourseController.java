package com.example.demo.controller;

import com.example.demo.dto.SubjectDTO;
import com.example.demo.entity.Account;
import com.example.demo.entity.Lecturer;
import com.example.demo.entity.Subject;
import com.example.demo.service.LecturerService;
import com.example.demo.service.SubjectService;
import com.example.demo.util.bean.SubjectBeanUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

@Controller
public class CourseController {
    @Autowired
    private SubjectService subjectService;
    @Autowired
    private SubjectBeanUtil subjectBeanUtil;
    @Autowired
    private LecturerService lecturerService;

    @GetMapping("/course-register")
    public String showCourseRegisterPage(Model model, HttpServletRequest request){
        HttpSession session = request.getSession();
        Account account = (Account) session.getAttribute("account");
        Lecturer lecturer = lecturerService.findById(account.getPerson().getId());
        Integer facultyId = lecturer.getFaculty().getId();
        List<Subject> subjects = subjectService.findByFacultyId(facultyId);
        List<SubjectDTO> subjectDTOS = new ArrayList<>();
        for(Subject subject: subjects){
            subjectDTOS.add(subjectBeanUtil.entity2DTO(subject));
        }
        model.addAttribute("subjects",subjectDTOS);
        return "course-register";
    }
}
