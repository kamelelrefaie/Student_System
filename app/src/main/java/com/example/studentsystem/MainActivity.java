package com.example.studentsystem;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;

import com.example.studentsystem.adapter.StudentAdapter;
import com.example.studentsystem.db.StudentAppDataBase;
import com.example.studentsystem.db.entity.Student;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class MainActivity extends AppCompatActivity {
    private StudentAdapter studentAdapter;
    private ArrayList<Student> studentArrayList = new ArrayList<>();
    private RecyclerView recyclerView;
    private StudentAppDataBase studentAppDataBase;

    RecyclerView getRecyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getRecyclerView = findViewById(R.id.main_activity_rv);


        //setting adapter

        getRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        getRecyclerView.setHasFixedSize(true);

        studentAdapter = new StudentAdapter();
        getRecyclerView.setAdapter(studentAdapter);
        studentAppDataBase = Room.databaseBuilder(getApplicationContext(), StudentAppDataBase.class, "studentDB").build();

        //getAllData
        new GetAllStudents().execute();
        //delete student
        getItemTouchHelper();

    }

    private void getItemTouchHelper() {
        new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT) {
            @Override
            public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
                Student studentToDelete = studentArrayList.get(viewHolder.getAdapterPosition());
                new DeleteStudentAsync().execute(studentToDelete);
            }
        }).attachToRecyclerView(getRecyclerView);

    }

    public void insert(View view) {
        startActivityForResult(new Intent(this, StudentRegister.class), 1);
    }

    private class GetAllStudents extends AsyncTask<Void, Void, Void> {

        @Override
        protected Void doInBackground(Void... voids) {
            studentArrayList = (ArrayList<Student>) (studentAppDataBase.getStudentDao().getStudents());
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            studentAdapter.setStudents(studentArrayList);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1 && resultCode == RESULT_OK) {
            String name = data.getStringExtra("NAME");
            String email = data.getStringExtra("EMAIL");
            String country = data.getStringExtra("COUNTRY");
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("EEE, MMM d, ''yy");
            String joinedDate = simpleDateFormat.format(new Date());

            new CreateStudentAsync().execute(new Student(name, email, 0, country, joinedDate));

        }
    }

    private class CreateStudentAsync extends AsyncTask<Student, Void, Void> {

        @Override
        protected Void doInBackground(Student... contacts) {
            studentAppDataBase.getStudentDao().addStudent(contacts[0]);
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            new GetAllStudents().execute();
        }
    }

    private class DeleteStudentAsync extends AsyncTask<Student, Void, Void> {

        @Override
        protected Void doInBackground(Student... contacts) {
            studentAppDataBase.getStudentDao().deleteStudent(contacts[0]);
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            new GetAllStudents().execute();
        }
    }
}