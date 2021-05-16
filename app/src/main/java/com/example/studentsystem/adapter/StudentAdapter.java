package com.example.studentsystem.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.studentsystem.R;
import com.example.studentsystem.db.entity.Student;

import java.util.ArrayList;

public class StudentAdapter extends RecyclerView.Adapter<StudentAdapter.StudentViewHolder> {

    private ArrayList<Student> studentArrayList = new ArrayList<>();

    @NonNull
    @Override
    public StudentViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new StudentViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_student, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull StudentViewHolder holder, int position) {
        holder.name.setText(studentArrayList.get(position).getName());
        holder.email.setText(studentArrayList.get(position).getEmail());
        holder.date.setText(studentArrayList.get(position).getJoinedDate());
        holder.country.setText(studentArrayList.get(position).getCountry());
    }

    @Override
    public int getItemCount() {
        if(studentArrayList!=null){
            return studentArrayList.size();
        }else {}
        return 0;
    }

    public void setStudents(ArrayList<Student> students) {
        this.studentArrayList = students;
        notifyDataSetChanged();
    }

    public class StudentViewHolder extends RecyclerView.ViewHolder {
        TextView name,email,country,date;
        public StudentViewHolder(@NonNull View itemView) {
            super(itemView);
            name=itemView.findViewById(R.id.student_item_name);
            email=itemView.findViewById(R.id.student_item_email);
            country=itemView.findViewById(R.id.student_item_country);
            date=itemView.findViewById(R.id.student_item_date);
        }
    }


}
