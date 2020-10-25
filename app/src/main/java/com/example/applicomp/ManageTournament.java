package com.example.applicomp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class ManageTournament extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manage_tournament);
    }

    public void toManageList(View view) {
        Intent i=new Intent(this,ManageList.class);
        startActivity(i);
    }
}
