package com.epam.rd.java.basic.practice8;

import com.epam.rd.java.basic.practice8.db.DBManager;
import com.epam.rd.java.basic.practice8.db.entity.Team;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.sql.SQLException;
import java.util.Properties;

public class Part5StudentTest {
    private static final String JDBC_DRIVER = "org.h2.Driver";
    private static final String DB_URL = "jdbc:h2:~/test";
    private static final String URL_CONNECTION = "jdbc:h2:~/test;user=bb;password=112233;";
    private static final String TEAM_D = "Team_D";
    private static final String TEAM_E = "Team_E";

    private static DBManager dbManager;

    @BeforeClass
    public static void beforeTest() throws SQLException, ClassNotFoundException {
        Class.forName(JDBC_DRIVER);

        try (OutputStream output = new FileOutputStream("app.properties")) {
            Properties prop = new Properties();
            prop.setProperty("connection.url", URL_CONNECTION);
            prop.store(output, null);
        } catch (IOException io) {
            System.err.println(io.getMessage());
        }

        dbManager = DBManager.getInstance();
    }

    @Test
    public void shouldDeleteTeam() {
        dbManager.insertTeam(Team.createTeam(TEAM_D));
        Team teamD = dbManager.getTeam(TEAM_D);
        teamD.setName(TEAM_E);
        dbManager.updateTeam(teamD);
        boolean b = false;
        for (Team t : dbManager.findAllTeams()) {
            if (TEAM_E.equals(t.getName())) {
                b = true;
                break;
            }
        }
        Assert.assertTrue(b);
    }
}