package com.example.namequizapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.namequizapp.database.Person;
import com.example.namequizapp.database.PersonDatabase;

import java.util.List;

public class DatabaseActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    Button addButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_database);

        createList();
        buttonSetup();
    }
    public void createList() {
        recyclerView = findViewById(R.id.recycler);

        PersonDatabase db = PersonDatabase.getDbInstance(this.getApplicationContext());
        List<Person> people = db.personDao().getAllPeople();
        StudentAdapter studentAdapter = new StudentAdapter(this, people);
        recyclerView.setAdapter(studentAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }

    @Override
    protected void onResume() {
        super.onResume();
        createList();
        buttonSetup();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        this.finish();
    }

    public void buttonSetup() {
        addButton = findViewById(R.id.addStudent);
        addButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                startActivity(new Intent(DatabaseActivity.this, AddPersonActivity.class));
            }
        });
    }

}