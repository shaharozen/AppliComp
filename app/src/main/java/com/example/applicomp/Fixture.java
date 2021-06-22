package com.example.applicomp;

import java.util.ArrayList;

public class Fixture {
    private ArrayList<String> teams;
    private String team1;
    private String team2;
    private String league;


    public Fixture(ArrayList<String> teams, String team1, String team2, String league, String score1, String score2) {
        this.teams = teams;
        this.team1 = team1;
        this.team2 = team2;

        this.league = league;
    }
    public Fixture()
    {

    }

    public ArrayList<String> getTeams() {
        return teams;
    }

    public String getTeam1() {
        return team1;
    }

    public String getTeam2() {
        return team2;
    }

    public String getLeague() {
        return league;
    }



    public void setTeams(ArrayList<String> teams) {
        this.teams = teams;
    }

    public void setTeam1(String team1) {
        this.team1 = team1;
    }

    public void setTeam2(String team2) {
        this.team2 = team2;
    }

    public void setLeague(String league) {
        this.league = league;
    }



    @Override
    public String toString() {
        return "" + teams.get(0) + " vs " + teams.get(1);

    }
}
