package com.example.applicomp;

public class Team {
    private String name;
    private String wins;
    private String losses;
    private String draws;
    private String points;
    private String played;
    private String league;


    public Team(String name, String wins, String losses, String draws, String points, String played, String league) {
        this.name = name;
        this.wins = wins;
        this.losses = losses;
        this.draws = draws;
        this.points = points;
        this.played = played;
        this.league = league;
    }
    public Team()
    {}

    public String getLeague() {
        return league;
    }

    public String getDraws() {
        return draws;
    }

    public String getName() {
        return name;
    }

    public String getLosses() {
        return losses;
    }

    public String getPlayed() {
        return played;
    }

    public String getPoints() {
        return points;
    }

    public String getWins() {
        return wins;
    }

    public void setLeague(String league) {
        this.league = league;
    }

    public void setDraws(String draws) {
        this.draws = draws;
    }

    public void setLosses(String losses) {
        this.losses = losses;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPlayed(String played) {
        this.played = played;
    }

    public void setPoints(String points) {
        this.points = points;
    }

    public void setWins(String wins) {
        this.wins = wins;
    }

    @Override
    public String toString() {
        return "Team{" +
                "name='" + name + '\'' +
                ", wins='" + wins + '\'' +
                ", losses='" + losses + '\'' +
                ", draws='" + draws + '\'' +
                ", points='" + points + '\'' +
                ", played='" + played + '\'' +
                '}';
    }
}
