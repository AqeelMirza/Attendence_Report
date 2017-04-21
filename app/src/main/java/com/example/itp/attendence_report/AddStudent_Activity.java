package com.example.itp.attendence_report;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import com.example.itp.attendence_report.Models.Student;

/**
 * Created by ITP on 4/21/2017.
 */

public class AddStudent_Activity extends AppCompatActivity {
    String faculty_name, faculty_username, faculty_password;
    EditText rollnum_et, student_name_et, branch_et, sem_per_et, month_att_et;
    Spinner year_sp, year_sem_sp, month_sp;
    Button save_btn;
    Student student;
    String rollNum, studentName, branch, semester_percentage, month_attendance, year, year_sem, month;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.student_details);

        rollnum_et = (EditText) findViewById(R.id.student_rollnum);
        student_name_et = (EditText) findViewById(R.id.student_name);
        branch_et = (EditText) findViewById(R.id.student_branch);
        sem_per_et = (EditText) findViewById(R.id.student_Last_sem_result);
        month_att_et = (EditText) findViewById(R.id.student_Last_month_att);

        year_sp = (Spinner) findViewById(R.id.student_year);
        year_sem_sp = (Spinner) findViewById(R.id.student_year_sem);
        month_sp = (Spinner) findViewById(R.id.student_Last_month);

        save_btn = (Button) findViewById(R.id.student_btn_save);

        faculty_name = getIntent().getStringExtra("fullname");
        faculty_username = getIntent().getStringExtra("username");
        faculty_password = getIntent().getStringExtra("password");

        save_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                student = new Student();

                rollNum = rollnum_et.getText().toString().trim();
                studentName = student_name_et.getText().toString().trim();
                branch = branch_et.getText().toString().trim();



            }
        });


    }
}
