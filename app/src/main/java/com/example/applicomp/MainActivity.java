package com.example.applicomp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void toLogIn(View view) {
        Intent i=new Intent(this,LogIn.class);
        startActivity(i);
    }

    public void toParticipateList(View view) {
        Intent i=new Intent(this,ParticipateList.class);
        startActivity(i);
    }

    public void toSettings(View view) {
        Intent i=new Intent(this,Settings.class);
        startActivity(i);
    }

    public void toWatchList(View view) {
        Intent i=new Intent(this,WatchList.class);
        startActivity(i);
    }

    public void toManageList(View view) {
        Intent i=new Intent(this,ManageList.class);
        startActivity(i);
    }
}
