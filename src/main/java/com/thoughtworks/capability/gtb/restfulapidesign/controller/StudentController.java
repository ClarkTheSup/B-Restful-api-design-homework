package com.thoughtworks.capability.gtb.restfulapidesign.controller;

import com.thoughtworks.capability.gtb.restfulapidesign.domain.Student;
import com.thoughtworks.capability.gtb.restfulapidesign.domain.Team;
import com.thoughtworks.capability.gtb.restfulapidesign.service.StudentService;
import com.thoughtworks.capability.gtb.restfulapidesign.repository.TeamListSingletonFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class StudentController {
    private final StudentService studentService;

    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @PostMapping("/students")
    @ResponseStatus(HttpStatus.CREATED)
    public void addStudent(@RequestBody Student student) {
        studentService.addStudent(student);
    }

    @DeleteMapping("/students")
    public void deleteStudent(@RequestBody Student student) {
        studentService.deleteStudent(student);
    }

    @GetMapping("/students")
    public List<Student> getStudentList(@RequestParam(required = false) String gender) {
        return studentService.getStudents(gender);
    }

    @GetMapping("/students/{id}")
    public Student getStudentById(@PathVariable int id) {
        return studentService.getStudentById(id);
    }

    @PatchMapping("/students/{id}")
    public void updateStudentById(@PathVariable int id, @RequestBody Student student) {
        studentService.updateStudentById(id, student);
    }

    @GetMapping("/teams")
    public List<Team> getTeamList() {
        return TeamListSingletonFactory.getInstance();
    }

    @PostMapping("/teams")
    @ResponseStatus(HttpStatus.CREATED)
    public void createTeamList() {
        studentService.groupStudents();
    }

    @PatchMapping("/teams/{index}/{name}")
    public void changeTeamName(@PathVariable int index, @PathVariable String name) {
        studentService.changeTeamName(index, name);
    }
}
