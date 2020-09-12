package com.thoughtworks.capability.gtb.restfulapidesign.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
public class Team {
    private int id;
    private String name;
    private String note;

    public Team(int id, String name, String note) {
        this.id = id;
        this.name = name;
        this.note = note;
    }

    private List<Student> studentList = new ArrayList<>();
}
