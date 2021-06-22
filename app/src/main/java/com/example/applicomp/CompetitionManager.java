package com.example.applicomp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Collections;

public class CompetitionManager extends AppCompatActivity {
    TextView pos1;
    TextView pos2;
    TextView pos3;
    TextView pos4;
    TextView pos5;
    TextView team1;
    TextView team2;
    TextView team3;
    TextView team4;
    TextView team5;
    TextView played1;
    TextView played2;
    TextView played3;
    TextView played4;
    TextView played5;
    TextView wins1;
    TextView wins2;
    TextView wins3;
    TextView wins4;
    TextView wins5;
    TextView draws1;
    TextView draws2;
    TextView draws3;
    TextView draws4;
    TextView draws5;
    TextView losses1;
    TextView losses2;
    TextView losses3;
    TextView losses4;
    TextView losses5;
    TextView points1;
    TextView points2;
    TextView points3;
    TextView points4;
    TextView points5;
    ArrayList<TextView> positions = new ArrayList<>();
    ArrayList<TextView> teams = new ArrayList<>();
    ArrayList<TextView> played = new ArrayList<>();
    ArrayList<TextView> wins = new ArrayList<>();
    ArrayList<TextView> draws = new ArrayList<>();
    ArrayList<TextView> losses = new ArrayList<>();
    ArrayList<TextView> points = new ArrayList<>();
    String leagueName;
    ArrayList<Team> currentLeague;
    String username;






    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_competition_manager);
        pos1 = (TextView)findViewById(R.id.pos1);
        pos2 = (TextView)findViewById(R.id.pos2);
        pos3 = (TextView)findViewById(R.id.pos3);
        pos4 = (TextView)findViewById(R.id.pos4);
        pos5 = (TextView)findViewById(R.id.pos5);
        team1 = (TextView)findViewById(R.id.team1);
        team2 = (TextView)findViewById(R.id.team2);
        team3 = (TextView)findViewById(R.id.team3);
        team4 = (TextView)findViewById(R.id.team4);
        team5 = (TextView)findViewById(R.id.team5);
        played1 = (TextView)findViewById(R.id.playeds1);
        played2 = (TextView)findViewById(R.id.playeds2);
        played3 = (TextView)findViewById(R.id.playeds3);
        played4 = (TextView)findViewById(R.id.playeds4);
        played5 = (TextView)findViewById(R.id.playeds5);
        wins1 = (TextView)findViewById(R.id.win1);
        wins2 = (TextView)findViewById(R.id.win2);
        wins3 = (TextView)findViewById(R.id.win3);
        wins4 = (TextView)findViewById(R.id.win4);
        wins5 = (TextView)findViewById(R.id.win5);
        draws1 = (TextView)findViewById(R.id.draw1);
        draws2 = (TextView)findViewById(R.id.draw2);
        draws3 = (TextView)findViewById(R.id.draw3);
        draws4 = (TextView)findViewById(R.id.draw4);
        draws5 = (TextView)findViewById(R.id.draw5);
        losses1 = (TextView)findViewById(R.id.lose1);
        losses2 = (TextView)findViewById(R.id.lose2);
        losses3 = (TextView)findViewById(R.id.lose3);
        losses4 = (TextView)findViewById(R.id.lose4);
        losses5 = (TextView)findViewById(R.id.lose5);
        points1 = (TextView)findViewById(R.id.pts1);
        points2 = (TextView)findViewById(R.id.pts2);
        points3 = (TextView)findViewById(R.id.pts3);
        points4 = (TextView)findViewById(R.id.pts4);
        points5 = (TextView)findViewById(R.id.pts5);
        setCells(positions, pos1, pos2, pos3, pos4, pos5);
        setCells(teams, team1, team2, team3, team4, team5);
        setCells(played, played1, played2, played3, played4, played5);
        setCells(wins, wins1, wins2, wins3, wins4, wins5);
        setCells(draws, draws1, draws2, draws3, draws4, draws5);
        setCells(losses, losses1, losses2, losses3, losses4, losses5);
        setCells(points, points1, points2, points3, points4, points5);

        Intent i = getIntent();
        leagueName = i.getStringExtra("leagueName");
        username = i.getStringExtra("username");
        Dal dal = new Dal(CompetitionManager.this);
        currentLeague = dal.getAllTeamsByLeague(leagueName);
        sortTable();
        fillTable();




    }
    public void fillTable (){
        int i = 0;
        for (i=0; i<currentLeague.size(); i++)
        {
            positions.get(i).setText("" +(i+1));
            teams.get(i).setText(currentLeague.get(i).getName());
            played.get(i).setText(currentLeague.get(i).getPlayed());
            wins.get(i).setText(currentLeague.get(i).getWins());
            draws.get(i).setText(currentLeague.get(i).getDraws());
            losses.get(i).setText(currentLeague.get(i).getLosses());
            points.get(i).setText(currentLeague.get(i).getPoints());
        }

    }

    public void sortTable (){
        int i = 0;
        int j = 0;
        for (i=0; i<currentLeague.size(); i++)
        {
            for (j=i+1; j<currentLeague.size(); j++)
            if (Integer.parseInt(currentLeague.get(i).getPoints()) < Integer.parseInt(currentLeague.get(j).getPoints())) {
                Collections.swap(currentLeague,i,j);
            }

        }
    }

    public void toLoadCompetition(View view) {
        Intent i=new Intent(this, LoadCompetition.class);
        i.putExtra("username", username);
        startActivity(i);
    }
    public void toFixturesManager(View view) {
        Intent i=new Intent(this, FixturesManager.class);
        i.putExtra("leagueName", leagueName);
        i.putExtra("username", username);
        startActivity(i);
    }
    public void setCells(ArrayList<TextView> cells, TextView cell1, TextView cell2, TextView cell3, TextView cell4, TextView cell5)
    {
        cells.add(cell1);
        cells.add(cell2);
        cells.add(cell3);
        cells.add(cell4);
        cells.add(cell5);


            }
}
