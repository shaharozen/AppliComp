package com.example.applicomp;


import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteStatement;
import android.util.Log;

import com.readystatesoftware.sqliteasset.SQLiteAssetHelper;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class Dal extends SQLiteAssetHelper {

    public Dal(Context context) {
        super(context, "compManagerDb.db", null, 1);
    }
    // User related functions
    public void addUser(String username, String mail, String password)
    {

        SQLiteDatabase db = getWritableDatabase();
        String sql_INSERT = "INSERT INTO user (name ,password ,mail) values(?,?,?)";
        SQLiteStatement statement = db.compileStatement(sql_INSERT);

        statement.bindString(1, username);
        statement.bindString(2, password);
        statement.bindString(3, mail);
        statement.execute();
    }
    public ArrayList<User> getAllUsers() {
        ArrayList<User> ary = new ArrayList<User>();

        String st = "select * from user";
        SQLiteDatabase db = getWritableDatabase();

        Cursor cursor = db.rawQuery(st, null);
        while (cursor.moveToNext()) {
            User u = new User();
            u.setUsername(cursor.getString(cursor.getColumnIndex("name")));


            ary.add(u);
        }
        return ary;

    }
    public Boolean isUserExist(String username) {


        String st = "select * from user";
        SQLiteDatabase db = getWritableDatabase();

        Cursor cursor = db.rawQuery(st, null);
        while (cursor.moveToNext()) {
            if((cursor.getString(cursor.getColumnIndex("name")).equals(username)))
                return true;

        }
        return false;

    }
    public Boolean isUserCorrect(String username, String password) {


        String st = "select * from user";
        SQLiteDatabase db = getWritableDatabase();

        Cursor cursor = db.rawQuery(st, null);
        while (cursor.moveToNext()) {

            if ((cursor.getString(cursor.getColumnIndex("name")).equals(username) && cursor.getString(cursor.getColumnIndex("password")).equals(password)))
                return true;

        }
        return false;

    }

    // Team related functions
    public ArrayList<Team> getAllTeamsByLeague(String leagueName) {
        ArrayList<Team> ary = new ArrayList<Team>();

        String st = "select * from team WHERE league ='" + leagueName + "'";
        SQLiteDatabase db = getWritableDatabase();

        Cursor cursor = db.rawQuery(st, null);
        while (cursor.moveToNext()) {
            Team t = new Team();
            t.setDraws(cursor.getString(cursor.getColumnIndex("draws")));
            t.setLosses(cursor.getString(cursor.getColumnIndex("losses")));
            t.setName(cursor.getString(cursor.getColumnIndex("name")));
            t.setPlayed(cursor.getString(cursor.getColumnIndex("played")));
            t.setPoints(cursor.getString(cursor.getColumnIndex("points")));
            t.setWins(cursor.getString(cursor.getColumnIndex("wins")));
            t.setLeague(leagueName);


            ary.add(t);
        }
        return ary;

    }
    public void calculateStats (int team1, int team2, String score1, String score2, String league) {

        ArrayList<Team> teams = getAllTeamsByLeague(league);
        int winPoints = getPpw(league);
        int drawPoints = getPpd(league);
        SQLiteDatabase db = getWritableDatabase();
        SQLiteStatement statement;
        SQLiteStatement statement2;
        SQLiteStatement statement3;
        SQLiteStatement statement4;
        SQLiteStatement statement5;
        SQLiteStatement statement6;
        String sql_INSERT_Win;
        String sql_INSERT_Lose;
        String sql_INSERT_Draw1;
        String sql_INSERT_Draw2;
        String sql_INSERT_Played1;
        String sql_INSERT_Played2;
        if (Integer.parseInt(score1) > Integer.parseInt(score2)) {
            sql_INSERT_Win = "UPDATE team SET wins='" + (Integer.parseInt(teams.get(team1).getWins()) + 1) + "', points='" + ((Integer.parseInt(teams.get(team1).getPoints())) + winPoints) + "' WHERE name='" + teams.get(team1).getName() + "' and league='" + league + "'";
            statement = db.compileStatement(sql_INSERT_Win);
            statement.execute();
            sql_INSERT_Lose = "UPDATE team SET losses='" + ((Integer.parseInt(teams.get(team2).getLosses()) + 1) + "' WHERE name='" + teams.get(team2).getName() + "' and league='" + league + "'");
            statement2 = db.compileStatement(sql_INSERT_Lose);
            statement2.execute();
        }
        else if (Integer.parseInt(score1) < Integer.parseInt(score2))
        {
            sql_INSERT_Win = "UPDATE team SET wins='" + (Integer.parseInt(teams.get(team2).getWins()) + 1) + "', points='" + (Integer.parseInt(teams.get(team2).getPoints()) + winPoints) + "' WHERE name='" + teams.get(team2).getName() + "' and league='" + league + "'";
            statement = db.compileStatement(sql_INSERT_Win);
            statement.execute();
            sql_INSERT_Lose = "UPDATE team SET losses='" + (Integer.parseInt(teams.get(team1).getLosses()) + 1) + "' WHERE name='" + teams.get(team1).getName() + "' and league='" + league + "'";
            statement2 = db.compileStatement(sql_INSERT_Lose);
            statement2.execute();
        }
        else
        {
            sql_INSERT_Draw1 = "UPDATE team SET draws='" + (Integer.parseInt(teams.get(team1).getDraws()) + 1) + "', points='" + (Integer.parseInt(teams.get(team1).getPoints()) + drawPoints) + "' WHERE name='" + teams.get(team1).getName() + "' and league='" + league + "'";
            statement3 = db.compileStatement(sql_INSERT_Draw1);
            statement3.execute();
            sql_INSERT_Draw2 = "UPDATE team SET draws='" + (Integer.parseInt(teams.get(team2).getDraws()) + 1) + "', points='" + (Integer.parseInt(teams.get(team2).getPoints()) + drawPoints) + "' WHERE name='" + teams.get(team2).getName() + "' and league='" + league + "'";
            statement4 = db.compileStatement(sql_INSERT_Draw2);
            statement4.execute();
        }
        sql_INSERT_Played1 = "UPDATE team SET played='" + (Integer.parseInt(teams.get(team1).getPlayed()) + 1) + "' WHERE name='" + teams.get(team1).getName() + "' and league='" + league + "'";
        statement5 = db.compileStatement(sql_INSERT_Played1);
        statement5.execute();
        sql_INSERT_Played2 = "UPDATE team SET played='" + (Integer.parseInt(teams.get(team2).getPlayed()) + 1) + "' WHERE name='" + teams.get(team2).getName() + "' and league='" + league + "'";
        statement6 = db.compileStatement(sql_INSERT_Played2);
        statement6.execute();
    }
    public void reduceMatch(int team1, int team2, String score1, String score2, String league)
    {
        ArrayList<Team> teams = getAllTeamsByLeague(league);
        int winPoints = getPpw(league);
        int drawPoints = getPpd(league);
        SQLiteDatabase db = getWritableDatabase();
        SQLiteStatement statement;
        SQLiteStatement statement2;
        SQLiteStatement statement3;
        SQLiteStatement statement4;
        SQLiteStatement statement5;
        SQLiteStatement statement6;
        String sql_INSERT_Win;
        String sql_INSERT_Lose;
        String sql_INSERT_Draw1;
        String sql_INSERT_Draw2;
        String sql_INSERT_Played1;
        String sql_INSERT_Played2;
        if (Integer.parseInt(score1) > Integer.parseInt(score2)) {
            sql_INSERT_Win = "UPDATE team SET wins='" + (Integer.parseInt(teams.get(team1).getWins()) - 1) + "', points='" + ((Integer.parseInt(teams.get(team1).getPoints())) - winPoints) + "' WHERE name='" + teams.get(team1).getName() + "' and league='" + league + "'";
            statement = db.compileStatement(sql_INSERT_Win);
            statement.execute();
            sql_INSERT_Lose = "UPDATE team SET losses='" + ((Integer.parseInt(teams.get(team2).getLosses()) - 1) + "' WHERE name='" + teams.get(team2).getName() + "' and league='" + league + "'");
            statement2 = db.compileStatement(sql_INSERT_Lose);
            statement2.execute();
        }
        else if (Integer.parseInt(score1) < Integer.parseInt(score2))
        {
            sql_INSERT_Win = "UPDATE team SET wins='" + (Integer.parseInt(teams.get(team2).getWins()) - 1) + "', points='" + (Integer.parseInt(teams.get(team2).getPoints()) - winPoints) + "' WHERE name='" + teams.get(team2).getName() + "' and league='" + league + "'";
            statement = db.compileStatement(sql_INSERT_Win);
            statement.execute();
            sql_INSERT_Lose = "UPDATE team SET losses='" + (Integer.parseInt(teams.get(team1).getLosses()) - 1) + "' WHERE name='" + teams.get(team1).getName() + "' and league='" + league + "'";
            statement2 = db.compileStatement(sql_INSERT_Lose);
            statement2.execute();
        }
        else
        {
            sql_INSERT_Draw1 = "UPDATE team SET draws='" + (Integer.parseInt(teams.get(team1).getDraws()) - 1) + "', points='" + (Integer.parseInt(teams.get(team1).getPoints()) - drawPoints) + "' WHERE name='" + teams.get(team1).getName() + "' and league='" + league + "'";
            statement3 = db.compileStatement(sql_INSERT_Draw1);
            statement3.execute();
            sql_INSERT_Draw2 = "UPDATE team SET draws='" + (Integer.parseInt(teams.get(team2).getDraws()) - 1) + "', points='" + (Integer.parseInt(teams.get(team2).getPoints()) - drawPoints) + "' WHERE name='" + teams.get(team2).getName() + "' and league='" + league + "'";
            statement4 = db.compileStatement(sql_INSERT_Draw2);
            statement4.execute();
        }
        sql_INSERT_Played1 = "UPDATE team SET played='" + (Integer.parseInt(teams.get(team1).getPlayed()) - 1) + "' WHERE name='" + teams.get(team1).getName() + "' and league='" + league + "'";
        statement5 = db.compileStatement(sql_INSERT_Played1);
        statement5.execute();
        sql_INSERT_Played2 = "UPDATE team SET played='" + (Integer.parseInt(teams.get(team2).getPlayed()) - 1) + "' WHERE name='" + teams.get(team2).getName() + "' and league='" + league + "'";
        statement6 = db.compileStatement(sql_INSERT_Played2);
        statement6.execute();
    }
    public void addTeam(String name, String leagueName)
    {

        SQLiteDatabase db = getWritableDatabase();
        String sql_INSERT = "INSERT INTO team (name ,wins ,losses ,points ,played ,draws ,league) values(?,?,?,?,?,?,?)";
        SQLiteStatement statement = db.compileStatement(sql_INSERT);

        statement.bindString(1, name);
        statement.bindString(2, "0");
        statement.bindString(3, "0");
        statement.bindString(4, "0");
        statement.bindString(5, "0");
        statement.bindString(6, "0");
        statement.bindString(7, leagueName);

        statement.execute();
    }
    public int getTeamIndex(String teamName, String league)
    {
        int id =0;
        String st = "select * from team WHERE league='" + league+ "'";
        SQLiteDatabase db = getWritableDatabase();

        Cursor cursor = db.rawQuery(st, null);
        cursor.moveToNext();
        while (!cursor.getString(cursor.getColumnIndex("name")).equals(teamName))
        {
            id++;
            cursor.moveToNext();

        }
        return id;
    }

    // Fixture related functions
    public ArrayList<Fixture> getAllFixtures(String leagueName) {
        ArrayList<Fixture> ary = new ArrayList<Fixture>();

        String st = "select * from fixtures WHERE league ='" + leagueName + "'";
        SQLiteDatabase db = getWritableDatabase();

        Cursor cursor = db.rawQuery(st, null);
        while (cursor.moveToNext()) {
            Fixture f = new Fixture();
            ArrayList<String> bothTeams = new ArrayList<String>();
            bothTeams.add(cursor.getString(cursor.getColumnIndex("first_team_name")));
            bothTeams.add(cursor.getString(cursor.getColumnIndex("second_team_name")));
            f.setTeams(bothTeams);
            f.setTeam1(cursor.getString(cursor.getColumnIndex("first_score")));
            f.setTeam2(cursor.getString(cursor.getColumnIndex("second_score")));
            f.setLeague(leagueName);

            ary.add(f);
        }
        return ary;

    }
    public void addFixture(String team1, String team2, String score1, String score2, String league)
    {

        SQLiteDatabase db = getWritableDatabase();
        String sql_INSERT = "INSERT INTO fixtures (first_team_name ,second_team_name ,first_score ,second_score ,league) values (?,?,?,?,?)";
        SQLiteStatement statement = db.compileStatement(sql_INSERT);
        statement.bindString(1, team1);
        statement.bindString(2, team2);
        statement.bindString(3, score1);
        statement.bindString(4, score2);
        statement.bindString(5, league);
        statement.execute();
    }
    public void updateFixture(String team1, String team2, String score1, String score2, String league)
    {
        SQLiteDatabase db = getWritableDatabase();
        SQLiteStatement statement1;
        String sql_Update_team1 = "UPDATE fixtures SET first_score='" + score1 + "', second_score='" + score2 + "' WHERE first_team_name='" + team1 + "' and league='" + league + "' and second_team_name='" + team2 + "'";
        statement1 = db.compileStatement(sql_Update_team1);
        statement1.execute();

    }
    public void generateFixture(String league)
    {
        ArrayList<Team> teams = new ArrayList<Team>();
        teams =getAllTeamsByLeague(league);
        int i =0;
        int j =0;
        for(i =0; i < teams.size(); i++)
        {
            for(j = i+1; j <teams.size(); j++)
            {
                addFixture(teams.get(i).getName(), teams.get(j).getName(), "-1","-1", league);
            }
        }
    }

    // League related functions
    public ArrayList<League> getAllLeagues(String username) {
        ArrayList<League> ary = new ArrayList<League>();

        String st = "select * from league WHERE creator='" + username + "'";
        SQLiteDatabase db = getWritableDatabase();

        Cursor cursor = db.rawQuery(st, null);
        while (cursor.moveToNext()) {
            League l = new League();
            l.setName(cursor.getString(cursor.getColumnIndex("name")));
            l.setCreator(cursor.getString(cursor.getColumnIndex("creator")));
            l.setPtsPerW(cursor.getString(cursor.getColumnIndex("points_per_win")));
            l.setPtsPerD(cursor.getString(cursor.getColumnIndex("points_per_draw")));

            ary.add(l);
        }
        return ary;

    }
    public void addLeague(String name, String ppw, String ppd, String creator)
    {

        SQLiteDatabase db = getWritableDatabase();
        String sql_INSERT = "INSERT INTO league (name ,creator ,points_per_win ,points_per_draw) values(?,?,?,?)";
        SQLiteStatement statement = db.compileStatement(sql_INSERT);

        statement.bindString(1, name);
        statement.bindString(2, creator);
        statement.bindString(3, ppw);
        statement.bindString(4, ppd);

        statement.execute();
    }
    public int getPpw(String leagueName)
    {
        int points =0;
        String st = "select * from league WHERE name='" + leagueName + "'";
        SQLiteDatabase db = getWritableDatabase();

        Cursor cursor = db.rawQuery(st, null);
        while (cursor.moveToNext()) {
            points = Integer.parseInt(cursor.getString(cursor.getColumnIndex("points_per_win")));
        }
        return points;
    }
    public int getPpd(String leagueName)
    {
        int points =0;
        String st = "select * from league WHERE name='" + leagueName + "'";
        SQLiteDatabase db = getWritableDatabase();

        Cursor cursor = db.rawQuery(st, null);
        while (cursor.moveToNext()) {
            points = Integer.parseInt(cursor.getString(cursor.getColumnIndex("points_per_draw")));
        }
        return points;
    }
    public Boolean isLeagueExist(String leagueName) {


        String st = "select * from league";
        SQLiteDatabase db = getWritableDatabase();

        Cursor cursor = db.rawQuery(st, null);
        while (cursor.moveToNext()) {
            if(cursor.getString(cursor.getColumnIndex("name")).equals(leagueName))
                return true;

        }
        return false;

    }


}
