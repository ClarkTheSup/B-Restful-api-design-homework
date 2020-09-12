package com.thoughtworks.capability.gtb.restfulapidesign.service;

import com.thoughtworks.capability.gtb.restfulapidesign.domain.Student;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class StudentListSingletonFactory {
    private static List<Student> studentList = new ArrayList<Student>();
    private StudentListSingletonFactory() {}

    public static List<Student> getInstance() {
        return studentList;
    }
}
