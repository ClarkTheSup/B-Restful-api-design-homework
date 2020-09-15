package com.thoughtworks.capability.gtb.restfulapidesign.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.thoughtworks.capability.gtb.restfulapidesign.domain.Student;
import com.thoughtworks.capability.gtb.restfulapidesign.domain.Team;
import com.thoughtworks.capability.gtb.restfulapidesign.repository.StudentListSingletonFactory;
import com.thoughtworks.capability.gtb.restfulapidesign.repository.TeamListSingletonFactory;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class StudentService {
    public List<Student> getStudentList() {
        return StudentListSingletonFactory.getInstance();
    }

    public List<Team> getTeamList() {
        return TeamListSingletonFactory.getInstance();
    }

    public void groupStudents() {
        List<Student> studentList = this.getStudentList();
        List<Student> newStudentList = new ArrayList<>();
        ObjectMapper objectMapper = new ObjectMapper();
        studentList.forEach((student)->{
            try {
                Student newStudent = objectMapper.readValue(
                        objectMapper.writeValueAsString(student), Student.class);
                newStudentList.add(newStudent);
            } catch (JsonProcessingException e) {
                e.printStackTrace();
            }
        });

        List<Team> teamList = this.getTeamList();
        for(Team team: teamList) {
            team.getStudentList().clear();
        }
        Collections.shuffle(newStudentList);
        for(int i = 0; i < newStudentList.size(); i++) {
            teamList.get(i%6).getStudentList().add(newStudentList.get(i));
        }
    }

    public void addStudent(Student student) {
        List<Student> studentList = this.getStudentList();
        studentList.add(student);
    }

    public void changeTeamName(int index, String newName) {
        List<Team> teamList = this.getTeamList();
        teamList.forEach((team -> {
            if (team.getName().equals(newName)) {
                throw new RuntimeException();
            }
        }));
        teamList.get(index).setName(newName);
    }

    public void deleteStudent(Student student) {
        this.getStudentList().remove(student);
    }

    public List<Student> getStudentListByGender(String gender) {
        return this.getStudentList().stream().filter((
                student -> student.getGender().equals(gender))).collect(Collectors.toList());
    }

    public Student getStudentById(int id) {
        return this.getStudentList().stream().filter((
                student -> student.getId().equals(id))).findFirst().orElse(null);
    }

    public void updateStudentById(int id, Student student) {
        Student oldStudent = this.getStudentList().stream().filter(
                (std -> std.getId().equals(id))).findFirst().orElse(null);
        if (oldStudent != null) {
            if (student.getGender() != null)
                oldStudent.setGender(student.getGender());
            if (student.getName() != null)
                oldStudent.setName(student.getName());
            if (student.getNote() != null)
                oldStudent.setNote(student.getNote());
        }
    }

    public List<Student> getStudents(String gender) {
        if (gender == null) {
            return StudentListSingletonFactory.getInstance();
        } else {
            return getStudentListByGender(gender);
        }
    }
}
