package com.example.applicomp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class ParticipateTournament extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_participate_tournament);
    }

    public void toParticipateList(View view) {
        Intent i=new Intent(this,ParticipateList.class);
        startActivity(i);
    }
}
