package com.example.namequizapp.database;

import android.net.Uri;

import androidx.annotation.IdRes;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "persons")
public class Person {

    @PrimaryKey(autoGenerate = true)
    private int id;

    private String name;
    private String picture;
    private String sex;

    public Person() {

    }

    public int getId() {return id;}

    public void setId(int id) {
        this.id = id;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }

    public Person(String name, String sex, String picture) {
        this.name = name;
        this.sex = sex;
        this.picture = picture;
    }



}
