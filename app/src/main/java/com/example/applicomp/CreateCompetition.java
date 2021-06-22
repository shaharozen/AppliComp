package com.example.applicomp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class CreateCompetition extends AppCompatActivity {
    Button btnContinue;
    Button btnBack;
    EditText compName;
    EditText teamName;
    Spinner ppw;
    Spinner ppd;
    String compNameStr;
    String teamNameStr;
    String ppwStr;
    String ppdStr;
    String[] points = {"0","1","2","3","4","5","6","7","8","9"};
    String username;
    TextView ppwViewer;
    TextView ppdViewer;
    


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_competition);
        Intent i = getIntent();
        username = i.getStringExtra("username");
        ppw = findViewById(R.id.ppw_spinner);
        ppd = findViewById(R.id.ppd_spinner);
        compName = findViewById(R.id.compName);
        teamName = findViewById(R.id.teamName);
        ppwViewer = findViewById(R.id.ppwViewer);
        ppdViewer = findViewById(R.id.ppdViewer);
        ArrayAdapter<String> ppwaa = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, points);
        ArrayAdapter<String> ppdaa = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, points);
        ppw.setAdapter(ppwaa);
        ppd.setAdapter(ppdaa);

        ppw.setOnItemSelectedListener(ppwListener);
        ppd.setOnItemSelectedListener(ppdListener);

    }
    private AdapterView.OnItemSelectedListener ppwListener = new AdapterView.OnItemSelectedListener() {
        @Override
        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
            ppwStr = points[position];
            ppwViewer.setText(ppwStr);

        }

        @Override
        public void onNothingSelected(AdapterView<?> parent) {
            ppwStr = "3";
            ppwViewer.setText(ppwStr);

        }
    };
    private AdapterView.OnItemSelectedListener ppdListener = new AdapterView.OnItemSelectedListener() {
        @Override
        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
            ppdStr = points[position];
            ppwViewer.setText(ppdStr);


        }

        @Override
        public void onNothingSelected(AdapterView<?> parent) {
            ppdStr = "1";
            ppwViewer.setText(ppwStr);

        }
    };

    public void toCompetitionManager(View view) {
        Intent i=new Intent(this, CompetitionManager.class);
        compNameStr = compName.getText().toString();
        Dal dal = new Dal(CreateCompetition.this);
        dal.generateFixture(compNameStr);
        i.putExtra("leagueName",compNameStr);
        i.putExtra("username", username);startActivity(i);

    }

    public void toMainActivity(View view) {
        Intent i=new Intent(this,MainActivity.class);
        i.putExtra("username", username);
        startActivity(i);
    }

    public void AddTeam(View view) {
        Dal dal = new Dal(CreateCompetition.this);
        compNameStr = compName.getText().toString();
        if (!dal.isLeagueExist(compNameStr))
        {
            dal.addLeague(compNameStr, ppwStr, ppdStr, username);
        }
        teamNameStr = teamName.getText().toString();
        dal.addTeam(teamNameStr, compNameStr);
        Toast.makeText(this, "Team added!", Toast.LENGTH_SHORT).show();
    }
}
