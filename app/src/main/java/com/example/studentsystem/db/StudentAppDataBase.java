package com.example.studentsystem.db;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.example.studentsystem.db.entity.Student;

@Database(entities = {Student.class}, version = 1)
public abstract class StudentAppDataBase extends RoomDatabase {
    public abstract StudentDao getStudentDao();

}
