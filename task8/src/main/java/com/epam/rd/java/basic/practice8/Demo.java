package com.epam.rd.java.basic.practice8;

import com.epam.rd.java.basic.practice8.db.DBManager;
import com.epam.rd.java.basic.practice8.db.entity.Team;
import com.epam.rd.java.basic.practice8.db.entity.User;

import java.util.List;

public class Demo {
    private static final String PETROV = "petrov";
    private static final String OBAMA = "obama";
    private static final String IVANOV = "ivanov";
    private static final String TEAM_A = "teamA";
    private static final String TEAM_B = "teamB";
    private static final String TEAM_C = "teamC";
    private static final String TEAM_X = "teamX";
    private static final String PARTS_DELIMITER = "===========================";
    private static final String DELIMITER = "~~~~~";

    private static void printList(List<?> list) {
        System.out.println(list);
    }

    public static void main(String[] args) {
        // users  ==> [ivanov]
        // teams ==> [teamA]

        DBManager dbManager = DBManager.getInstance();

        // Part 1
        dbManager.insertUser(User.createUser(PETROV));
        dbManager.insertUser(User.createUser(OBAMA));
        printList(dbManager.findAllUsers());
        // users  ==> [ivanov, petrov, obama]

        System.out.println(PARTS_DELIMITER);

        // Part 2
        dbManager.insertTeam(Team.createTeam(TEAM_B));
        dbManager.insertTeam(Team.createTeam(TEAM_C));

        printList(dbManager.findAllTeams());
        // teams ==> [teamA, teamB, teamC]

        System.out.println(PARTS_DELIMITER);

        // Part 3
        User userPetrov = dbManager.getUser(PETROV);
        User userIvanov = dbManager.getUser(IVANOV);
        User userObama = dbManager.getUser(OBAMA);

        Team teamA = dbManager.getTeam(TEAM_A);
        Team teamB = dbManager.getTeam(TEAM_B);
        Team teamC = dbManager.getTeam(TEAM_C);

        // method setTeamsForUser must implement transaction!
        dbManager.setTeamsForUser(userIvanov, teamA);
        dbManager.setTeamsForUser(userPetrov, teamA, teamB);
        dbManager.setTeamsForUser(userObama, teamA, teamB, teamC);

        for (User user : dbManager.findAllUsers()) {
            printList(dbManager.getUserTeams(user));
            System.out.println(DELIMITER);
        }
        // teamA
        // teamA teamB
        // teamA teamB teamC

        System.out.println(PARTS_DELIMITER);

        // Part 4

        // on delete cascade!
        dbManager.deleteTeam(teamA);

        // Part 5
        teamC.setName(TEAM_X);
        dbManager.updateTeam(teamC);
        printList(dbManager.findAllTeams());
        // teams ==> [teamB, teamX]

    }
}
