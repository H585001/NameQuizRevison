package com.example.namequizapp.database;

import android.app.Application;

public class PersonRepository {

    private PersonDao personDao;

    public PersonRepository(Application application) {
        PersonDatabase db = PersonDatabase.getDbInstance(application);
        personDao = db.personDao();
    }
}
