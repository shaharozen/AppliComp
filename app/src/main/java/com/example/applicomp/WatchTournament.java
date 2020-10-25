package com.example.applicomp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class WatchTournament extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_watch_tournament);
    }

    public void toWatchList(View view) {
        Intent i=new Intent(this,WatchList.class);
        startActivity(i);
    }
}
