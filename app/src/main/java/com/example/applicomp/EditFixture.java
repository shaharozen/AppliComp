package com.example.applicomp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.ArrayList;

public class EditFixture extends AppCompatActivity {
    Spinner spinner1;
    Spinner spinner2;
    String[] points = {"0","1","2","3","4","5","6","7","8","9"};
    TextView tv1;
    TextView tv2;
    String team1Name;
    String team2Name;
    String score1;
    String score2;
    String leagueName;
    TextView teamScore1;
    TextView teamScore2;
    String username;
    int index;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_fixture);

        spinner1 = findViewById(R.id.spn1);
        spinner2 = findViewById(R.id.spn2);
        tv1 = findViewById(R.id.team1tv);
        tv2 = findViewById(R.id.team2tv);
        teamScore1 = findViewById(R.id.result1);
        teamScore2 = findViewById(R.id.result2);
        Intent i = getIntent();
        team1Name = i.getStringExtra("team1Name");
        team2Name = i.getStringExtra("team2Name");
        leagueName = i.getStringExtra("leagueName");
        index = i.getIntExtra("index",0);
        username = i.getStringExtra("username");
        tv1.setText(team1Name);
        tv2.setText(team2Name);

        ArrayAdapter<String> spn1aa = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, points);
        ArrayAdapter<String> spn2aa = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, points);
        spinner1.setAdapter(spn1aa);
        spinner2.setAdapter(spn2aa);

        spinner1.setOnItemSelectedListener(spinner1Listener);
        spinner2.setOnItemSelectedListener(spinner2Listener);


    }
    private AdapterView.OnItemSelectedListener spinner1Listener = new AdapterView.OnItemSelectedListener() {
        @Override
        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
            score1 = points[position];
            teamScore1.setText(score1);

        }

        @Override
        public void onNothingSelected(AdapterView<?> parent) {

        }
    };
    private AdapterView.OnItemSelectedListener spinner2Listener = new AdapterView.OnItemSelectedListener() {
        @Override
        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
            score2 = points[position];
            teamScore2.setText(score2);


        }

        @Override
        public void onNothingSelected(AdapterView<?> parent) {

        }
    };


    public void returnToPrev(View view) {
        Dal dal = new Dal(EditFixture.this);
        ArrayList<Fixture> fixtures = dal.getAllFixtures(leagueName);
        if(!fixtures.get(index).getTeam1().equals("-1") || !(fixtures.get(index).getTeam2().equals("-1")))
        {
            dal.reduceMatch(dal.getTeamIndex(fixtures.get(index).getTeams().get(0), leagueName),dal.getTeamIndex(fixtures.get(index).getTeams().get(1), leagueName), fixtures.get(index).getTeam1(), fixtures.get(index).getTeam2(), leagueName);
        }

        dal.calculateStats(dal.getTeamIndex(team1Name, leagueName), dal.getTeamIndex(team2Name, leagueName), score1, score2, leagueName);
        dal.updateFixture(team1Name, team2Name, score1, score2, leagueName);


        Intent FixtureManagerRet = new Intent(this, FixturesManager.class);
        FixtureManagerRet.putExtra("leagueName", leagueName);
        FixtureManagerRet.putExtra("username", username);
        startActivity(FixtureManagerRet);
    }
}