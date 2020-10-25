package com.example.applicomp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class WatchList extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_watch_list);
    }

    public void toWatchTournament(View view) {
        Intent i=new Intent(this,WatchTournament.class);
        startActivity(i);
    }

    public void toMainActivity(View view) {
        Intent i=new Intent(this,MainActivity.class);
        startActivity(i);
    }
}
