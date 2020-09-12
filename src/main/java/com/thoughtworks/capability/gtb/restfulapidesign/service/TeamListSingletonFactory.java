package com.thoughtworks.capability.gtb.restfulapidesign.service;

import com.thoughtworks.capability.gtb.restfulapidesign.domain.Team;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class TeamListSingletonFactory {
    private static List<Team> teamList = new ArrayList<Team>();
    private TeamListSingletonFactory() {}

    static {
        for (int i=0; i<6; i++) {
            teamList.add(new Team(i, "Team " + (i + 1), ""));
        }
    }

    public static List<Team> getInstance() {
        return teamList;
    }
}
