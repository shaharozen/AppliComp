package com.example.applicomp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class SignIn extends AppCompatActivity {
    EditText userName;
    EditText password;
    EditText mail;
    EditText passwordAgain;
    String userNameStr;
    String passwordStr;
    String mailStr;
    String passwordAgainStr;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);
        userName = findViewById(R.id.editTextTextPersonName2);
        password = findViewById(R.id.editTextTextPassword2);
        mail = findViewById(R.id.editTextTextEmailAddress);
        passwordAgain = findViewById(R.id.editTextTextPassword3);


    }
    public Boolean checkUserValid(String username, String password, String passwordAgain, String mail)
    {

        Dal dal = new Dal(SignIn.this);
        if (!password.equals(passwordAgain))
        {
            Toast.makeText(this, "The passwords do not match!", Toast.LENGTH_LONG).show();
            return false;
        }
        else if(dal.isUserExist(username))
        {
            Toast.makeText(this, "This user already exists!", Toast.LENGTH_LONG).show();
            return false;
        }
        else{
            dal.addUser(username, mail, password);
            return true;
        }

    }

    public void toMainActivity(View view) {
        userNameStr = userName.getText().toString();
        passwordStr = password.getText().toString();
        mailStr = mail.getText().toString();
        passwordAgainStr = passwordAgain.getText().toString();
        Intent i=new Intent(this,MainActivity.class);
        if (checkUserValid(userNameStr, passwordStr, passwordAgainStr, mailStr))
        {
            i.putExtra("username",userNameStr);
            startActivity(i);}

    }
}
