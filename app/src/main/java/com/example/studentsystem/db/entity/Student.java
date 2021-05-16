package com.example.studentsystem.db.entity;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

import java.time.OffsetDateTime;

@Entity(tableName = "students")
public class Student {
    @ColumnInfo(name = "name")
    private String name;

    @ColumnInfo(name = "email")
    private String email;

    @ColumnInfo(name = "student_id")
    @PrimaryKey(autoGenerate = true)
    private long id;

    @ColumnInfo(name = "country")
    private String country;

    @ColumnInfo(name = "date")
    private String joinedDate;



    @Ignore
    public Student() {
    }

    public Student(String name, String email, long id, String country,String joinedDate) {
        this.name = name;
        this.email = email;
        this.id = id;
        this.country = country;
        this.joinedDate=joinedDate;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        country = country;
    }

    public String getJoinedDate() {
        return joinedDate;
    }

    public void setJoinedDate(String joinedDate) {
        this.joinedDate = joinedDate;
    }
}
