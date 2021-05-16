package com.example.studentsystem.db;

import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.studentsystem.db.entity.Student;

import java.util.List;

@androidx.room.Dao
public interface StudentDao {
    @Insert
    public void addStudent(Student student);

    @Delete
    public void deleteStudent(Student student);

    @Query("SELECT * from students")
    public List<Student> getStudents();


}
