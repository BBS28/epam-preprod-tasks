package com.epam.rd.java.basic.practice8.db.entity;

import java.util.Objects;

public class Team {

    private long id;
    private String name;

    public Team() {
    }

    public Team(String name) {
        this.name = name;
    }

    public static Team createTeam(String name) {
        return new Team(name);
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Team team = (Team) o;
        return Objects.equals(name, team.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name);
    }

    @Override
    public String toString() {
        return name;
    }
}
