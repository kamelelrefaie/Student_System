package com.example.studentsystem;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.studentsystem.adapter.StudentAdapter;
import com.example.studentsystem.db.StudentAppDataBase;
import com.example.studentsystem.db.entity.Student;

import java.util.ArrayList;

public class StudentRegister extends AppCompatActivity {
    EditText name, email, country;
    private StudentAppDataBase studentAppDataBase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_register);
        name = findViewById(R.id.student_register_activity_et_name);
        email = findViewById(R.id.student_register_activity_et_email);
        country = findViewById(R.id.student_register_activity_et_country);

    }

    public void submitBtn(View view) {
        if (TextUtils.isEmpty(name.getText()) && TextUtils.isEmpty(email.getText()) &&
                TextUtils.isEmpty(country.getText())
        ) {
            Toast.makeText(this, "please complete this fields", Toast.LENGTH_SHORT).show();
        } else {
            String nameString = name.getText().toString();
            String emailString = email.getText().toString();
            String countryString = country.getText().toString();

            Intent intent = new Intent();
            intent.putExtra("NAME", nameString);
            intent.putExtra("EMAIL", emailString);
            intent.putExtra("COUNTRY", countryString);
            setResult(RESULT_OK, intent);
            finish();

        }
    }


}