package com.example.applicomp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;

import java.util.ArrayList;

public class FixturesManager extends AppCompatActivity {

    ListView fixtureList;
    ArrayList<String> fixtures = new ArrayList<String>();
    ArrayList<Fixture> allFixturesFromDb;
    String leagueName;
    String username;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fixtures_manager);
        fixtureList = findViewById(R.id.fixtureViewer);
        Intent i = getIntent();
        leagueName = i.getStringExtra("leagueName");
        username = i.getStringExtra("username");
        setFixtures(leagueName);
        ArrayAdapter<String> fixturesAa = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, fixtures);
        fixtureList.setAdapter(fixturesAa);
        fixtureList.setOnItemClickListener(fixtureListener);

    }

    private void setFixtures(String leagueName) {
        Dal dal = new Dal(FixturesManager.this);
        allFixturesFromDb = dal.getAllFixtures(leagueName);
        int i =0;
        for (i=0; i<allFixturesFromDb.size(); i++) {
            fixtures.add(allFixturesFromDb.get(i).toString());
        }
    }


    private AdapterView.OnItemClickListener fixtureListener = new AdapterView.OnItemClickListener() {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            Intent editFixture = new Intent(FixturesManager.this, EditFixture.class);

            editFixture.putExtra("team1Name", fixtures.get(position).substring(0, fixtures.get(position).indexOf(" ")));
            editFixture.putExtra("team2Name", fixtures.get(position).substring(fixtures.get(position).indexOf("vs ") + 3, fixtures.get(position).length()));
            editFixture.putExtra("leagueName", leagueName);
            editFixture.putExtra("index", position);
            editFixture.putExtra("username", username);
            startActivity(editFixture);
        }
    };

    public void toCompetitionManager(View view) {
        Intent i=new Intent(this, CompetitionManager.class);
        i.putExtra("leagueName", leagueName);
        i.putExtra("username", username);
        startActivity(i);
    }

}
