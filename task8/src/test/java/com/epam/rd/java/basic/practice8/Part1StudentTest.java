package com.epam.rd.java.basic.practice8;

import com.epam.rd.java.basic.practice8.db.DBManager;
import com.epam.rd.java.basic.practice8.db.entity.User;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import java.io.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

public class Part1StudentTest {
    private static final String JDBC_DRIVER = "org.h2.Driver";
    private static final String DB_URL = "jdbc:h2:~/test";
    private static final String URL_CONNECTION = "jdbc:h2:~/test;user=bb;password=112233;";
    private static final String USER = "bb";
    private static final String PASS = "112233";
    private static final String SIDOROV = "sidorov";

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

        try (Connection con = DriverManager.getConnection(DB_URL, USER, PASS);
             Statement statement = con.createStatement()) {
            String sql = "CREATE TABLE users (\n" +
                    "  id int(11) NOT NULL AUTO_INCREMENT,\n" +
                    "  login varchar(16) NOT NULL,\n" +
                    "  PRIMARY KEY (id),\n" +
                    "  UNIQUE KEY login (login)\n" +
                    ");";

            statement.executeUpdate(sql);
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
    }

    @Test
    public void shouldInsertUser() {
        dbManager.insertUser(User.createUser(SIDOROV));
        User user = dbManager.findAllUsers().get(0);
        Assert.assertNotNull(user);
        Assert.assertEquals(SIDOROV, user.getLogin());
    }
}