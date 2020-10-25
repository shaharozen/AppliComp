package com.example.applicomp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class ManageList extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manage_list);
    }

    public void toManageTournament(View view) {
        Intent i=new Intent(this,ManageTournament.class);
        startActivity(i);
    }

    public void toMainActivity(View view) {
        Intent i=new Intent(this,MainActivity.class);
        startActivity(i);
    }
}
