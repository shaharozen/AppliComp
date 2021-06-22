package com.example.applicomp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

public class LoadCompetition extends AppCompatActivity {


    ListView compList;
    ArrayList<String> comps = new ArrayList<String>();
    ArrayList<League> allCompsFromDb = new ArrayList<League>();
    String username = "";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_load_competition);
        compList = findViewById(R.id.compViewer);
        Intent i = getIntent();
        username = i.getStringExtra("username");
        setComps();
        ArrayAdapter<String> compAa = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, comps);
        compList.setAdapter(compAa);
        compList.setOnItemClickListener(compListener);
    }
    private AdapterView.OnItemClickListener compListener = new AdapterView.OnItemClickListener() {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            Intent LoadCompetition = new Intent(LoadCompetition.this, CompetitionManager.class);
            LoadCompetition.putExtra("leagueName", comps.get(position));
            LoadCompetition.putExtra("username", username);
            startActivity(LoadCompetition);
        }
    };
    private void setComps() {
        Dal dal = new Dal(LoadCompetition.this);
        allCompsFromDb = dal.getAllLeagues(username);
        int i =0;
        for (i=0; i<allCompsFromDb.size(); i++)
        {
            comps.add(allCompsFromDb.get(i).getName());
        }
    }

    public void toCompetitionManager(View view) {
        Intent i=new Intent(this, CompetitionManager.class);
        Log.w("myApp", "wtf??");
        i.putExtra("username", username);
        startActivity(i);
    }

    public void toMainActivity(View view) {
        Intent i=new Intent(this,MainActivity.class);
        i.putExtra("username", username);
        startActivity(i);
    }
}
