package com.example.namequizapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.namequizapp.database.Person;
import com.example.namequizapp.database.PersonDatabase;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        configureQuizButton();
        initData();
    }

    private void initData() {
//        PersonDatabase db = PersonDatabase.getDbInstance(this.getApplicationContext());
//        Uri image1 = Uri.parse("android.resource://com.example.namequizapp/" + R.drawable.mark);
//        getContentResolver().takePersistableUriPermission(image1, Intent.FLAG_GRANT_READ_URI_PERMISSION);
//        Uri image2 = Uri.parse("android.resource://com.example.namequizapp/" + R.drawable.chad);
//        getContentResolver().takePersistableUriPermission(image2, Intent.FLAG_GRANT_READ_URI_PERMISSION);
//        Uri image3 = Uri.parse("android.resource://com.example.namequizapp/" + R.drawable.brian);
//        getContentResolver().takePersistableUriPermission(image3, Intent.FLAG_GRANT_READ_URI_PERMISSION);
//
//        db.personDao().insertPerson(new Person("Mark", "male" ,image1.toString()));
//        db.personDao().insertPerson(new Person("Chad", "male" ,image2.toString()));
//        db.personDao().insertPerson(new Person("Brian","male" ,image3.toString()));
    }

    private void configureQuizButton() {
        Button quizbutton = findViewById(R.id.quizbutton);
        quizbutton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, QuizActivity.class));
            }
        });

        Button databaseButton = findViewById(R.id.databasebutton);
        databaseButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, DatabaseActivity.class));
            }
        });

    }
}