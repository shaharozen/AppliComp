package com.example.applicomp;

import java.util.ArrayList;

public class League {
    private String name;
    private String creator;
    private ArrayList<Team> teams;
    private String PtsPerW;
    private String PtsPerD;

    public League(String name, String creator, ArrayList<Team> teams, String ptsPerW, String ptsPerD) {
        this.name = name;
        this.creator = creator;
        this.teams = teams;
        this.PtsPerW =PtsPerW;
        this.PtsPerD =PtsPerD;
    }
    public League(){

    }

    public String getName() {
        return name;
    }

    public ArrayList<Team> getTeams() {
        return teams;
    }

    public String getCreator() {
        return creator;
    }

    public String getPtsPerD() {
        return PtsPerD;
    }

    public String getPtsPerW() {
        return PtsPerW;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setCreator(String creator) {
        this.creator = creator;
    }

    public void setTeams(ArrayList<Team> teams) {
        this.teams = teams;
    }

    public void setPtsPerD(String ptsPerD) {
        PtsPerD = ptsPerD;
    }

    public void setPtsPerW(String ptsPerW) {
        PtsPerW = ptsPerW;
    }

    @Override
    public String toString() {
        return "League{" +
                "name='" + name + '\'' +
                ", creator='" + creator + '\'' +
                ", teams=" + teams +
                '}';
    }
}
