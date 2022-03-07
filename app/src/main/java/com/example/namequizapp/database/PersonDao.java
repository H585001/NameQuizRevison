package com.example.namequizapp.database;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface PersonDao {

    @Insert
    void insertPerson(Person person);

    @Query("SELECT * FROM persons ORDER BY id ASC")
    List<Person> getAllPeople();

    @Query("DELETE FROM persons WHERE name = :name")
    void deletePerson(String name);

    @Query("SELECT * FROM persons WHERE id = :id")
    Person getPersonById(int id);

    @Query("SELECT * FROM persons WHERE name = :name")
    List<Person> getPersonByName(String name);
}
