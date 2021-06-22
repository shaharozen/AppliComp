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

public class MainActivity extends AppCompatActivity {

    String username;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Intent i = getIntent();
        username = i.getStringExtra("username");

    }

    public void toLogIn(View view) {
        Intent i=new Intent(this,LogIn.class);
        startActivity(i);
    }

    public void toCreateCompetition(View view) {
        Intent i=new Intent(this, CreateCompetition.class);
        i.putExtra("username", username);
        startActivity(i);
    }


    public void toLoadCompetition(View view) {
        Intent i=new Intent(this, LoadCompetition.class);
        i.putExtra("username", username);
        startActivity(i);
    }
}
