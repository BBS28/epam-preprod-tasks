package com.epam.rd.java.basic.practice8.db;

import com.epam.rd.java.basic.practice8.db.entity.Team;
import com.epam.rd.java.basic.practice8.db.entity.User;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.*;
import java.util.*;

public class DBManager {
    private static DBManager instance;
    private static Connection connection;
    private static final String ID = "id";
    private static final String NAME = "name";
    private static final String LOGIN = "login";
    private static final String APP = "app.properties";
    private static final String CONNECTION_URL = "connection.url";
    private static final String INSERT_USER = "INSERT INTO USERS (login) VALUES(?)";
    private static final String INSERT_TEAM = "INSERT INTO TEAMS (name) VALUES(?)";
    private static final String FIND_ALL_USERS = "SELECT id, login FROM USERS";
    private static final String GET_USER = "SELECT id, login FROM USERS WHERE login = ?";
    private static final String GET_TEAM = "SELECT id, name FROM TEAMS WHERE name = ?";
    private static final String FIND_ALL_TEAMS = "SELECT id, name FROM TEAMS";
    private static final String INSERT_USER_TO_TEAM = "INSERT INTO users_teams VALUES (?, ?)";
    private static final String GET_USER_TEAMS = "SELECT t.id, t.name FROM users_teams ut"
            + " RIGHT JOIN teams t ON ut.team_id = t.id WHERE ut.user_id = ?";
    private static final String DELETE_TEAM = "DELETE FROM TEAMS WHERE name =?";
    private static final String UPDATE_TEAM = "UPDATE TEAMS SET name = ? WHERE id =?";

    private DBManager() {
    }

    public static DBManager getInstance() {
        if (instance == null) {
            instance = new DBManager();
            connection = getConnection();
        }
        return instance;
    }

    public static Connection getConnection() {
        try(InputStream is =new FileInputStream(APP)) {
            Properties prop = new Properties();
            prop.load(is);
            return DriverManager.getConnection(prop.getProperty(CONNECTION_URL));

        } catch (SQLException | IOException e) {
            System.err.println(e.getMessage());
        }
        return null;
    }

    public void insertUser(User user) {
        try (PreparedStatement ps = connection.prepareStatement(INSERT_USER, Statement.RETURN_GENERATED_KEYS)) {
            ps.setString(1, user.getLogin());
            if (ps.executeUpdate() > 0) {
                try (ResultSet rs = ps.getGeneratedKeys()) {
                    if (rs.next()) {
                        int userId = rs.getInt(1);
                        user.setId(userId);
                    }
                }
            }
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
    }

    public List<User> findAllUsers() {
        List<User> users = new ArrayList<>();
        try (Statement st = connection.createStatement();
             ResultSet rs = st.executeQuery(FIND_ALL_USERS)) {
            while (rs.next()) {
                User user = new User();
                user.setId(rs.getLong(ID));
                user.setLogin(rs.getString(LOGIN));
                users.add(user);
            }
        } catch (SQLException e) {
            System.err.println(e.getMessage());
            return Collections.emptyList();
        }
        return users;
    }

    public void insertTeam(Team team) {
        try (PreparedStatement ps = connection.prepareStatement(INSERT_TEAM, Statement.RETURN_GENERATED_KEYS)) {
            ps.setString(1, team.getName());
            if (ps.executeUpdate() > 0) {
                try (ResultSet rs = ps.getGeneratedKeys()) {
                    if (rs.next()) {
                        team.setId(rs.getLong(1));
                    }
                }
            }
        } catch (
                SQLException e) {
            System.err.println(e.getMessage());
        }
    }


    public List<Team> findAllTeams() {
        List<Team> teams = new ArrayList<>();
        try (Statement statement = connection.createStatement()) {
            try (ResultSet rs = statement.executeQuery(FIND_ALL_TEAMS)) {
                while (rs.next()) {
                    Team team = new Team();
                    team.setId(rs.getLong(ID));
                    team.setName(rs.getString(NAME));
                    teams.add(team);
                }
            }
        } catch (SQLException e) {
            System.err.println(e.getMessage());
            return Collections.emptyList();
        }
        return teams;
    }

    public User getUser(String login) {
        User user = new User();
        try (PreparedStatement ps = connection.prepareStatement(GET_USER)) {
            ps.setString(1, login);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    user.setId(rs.getLong(ID));
                    user.setLogin(rs.getString(LOGIN));
                }
            }
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
        return user;
    }

    public Team getTeam(String teamName) {
        Team team = new Team();
        try (PreparedStatement ps = connection.prepareStatement(GET_TEAM)) {
            ps.setString(1, teamName);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    team.setId(rs.getLong(ID));
                    team.setName(rs.getString(NAME));
                }
            }
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
        return team;
    }

    public void setTeamsForUser(User user, Team... teams) {
        try (PreparedStatement ps = connection.prepareStatement(INSERT_USER_TO_TEAM)) {
            processConnection();
            for (Team team : teams) {
                ps.setLong(1, user.getId());
                ps.setLong(2, team.getId());
                ps.addBatch();
            }
            ps.executeBatch();
            connection.commit();
        } catch (SQLException ex) {
            try {
                connection.rollback();
            } catch (SQLException e) {
                System.err.println(e.getMessage());
            }
        }
    }

    public List<Team> getUserTeams(User user) {
        List<Team> teams = new ArrayList<>();
        try {
            try (PreparedStatement st = connection.prepareStatement(GET_USER_TEAMS)) {
                st.setLong(1, user.getId());
                try (ResultSet rs = st.executeQuery()) {
                    while (rs.next()) {
                        Team team = new Team();
                        team.setId(rs.getLong(1));
                        team.setName(rs.getString(2));
                        teams.add(team);
                    }
                }
            }
        } catch (SQLException e) {
            System.err.println(e.getMessage());
            return Collections.emptyList();
        }
        return teams;
    }

    public void deleteTeam(Team team) {
        try (PreparedStatement ps = connection.prepareStatement(DELETE_TEAM)) {
            processConnection();
            ps.setString(1, team.getName());
            if (1 != ps.executeUpdate()) {
                return;
            }
            connection.commit();
        } catch (SQLException ex) {
            try {
                connection.rollback();
            } catch (SQLException e) {
                System.err.println(e.getMessage());
            }
        }
    }

    public void updateTeam(Team team) {
        try (PreparedStatement ps = connection.prepareStatement(UPDATE_TEAM)) {
            processConnection();
            ps.setString(1, team.getName());
            ps.setLong(2, team.getId());
            if (1 != ps.executeUpdate()) {
                return;
            }
            connection.commit();
        } catch (SQLException ex) {
            try {

                connection.rollback();
            } catch (SQLException e) {
                System.err.println(e.getMessage());
            }
        }
    }

    private void processConnection() throws SQLException {
        connection.setAutoCommit(false);
        connection.setTransactionIsolation(Connection.TRANSACTION_SERIALIZABLE);
    }
}
