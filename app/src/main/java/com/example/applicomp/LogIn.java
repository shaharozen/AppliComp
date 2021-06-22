package com.example.applicomp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class LogIn extends AppCompatActivity {
    EditText userNameHolder;
    EditText passwordHolder;
    String userNameStr;
    String passwordStr;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log_in);
        userNameHolder = findViewById(R.id.editTextTextPersonName);
        passwordHolder = findViewById(R.id.editTextTextPassword);


    }
    public Boolean checkUserValid()
    {
        Dal dal = new Dal(LogIn.this);
        userNameStr = userNameHolder.getText().toString();
        passwordStr = passwordHolder.getText().toString();
        if (userNameStr.equals("") || passwordStr.equals(""))
        {
            Toast.makeText(this, "All fields must be filled", Toast.LENGTH_LONG).show();
            return false;
        }
        else if (!dal.isUserCorrect(userNameStr, passwordStr))
        {
            Toast.makeText(this, "Incorrect username or password!", Toast.LENGTH_LONG).show();
            return false;
        }
        else
            return true;
    }

    public void toMainActivity(View view) {
        Intent i=new Intent(this,MainActivity.class);
        userNameStr = userNameHolder.getText().toString();
        passwordStr = passwordHolder.getText().toString();
        if(checkUserValid())
        {
            i.putExtra("username",userNameStr);
            startActivity(i);
        }


    }
    public void toSignIn(View view) {
        Intent i=new Intent(this,SignIn.class);
        startActivity(i);
    }
}
