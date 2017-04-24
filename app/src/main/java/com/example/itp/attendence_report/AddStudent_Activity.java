package com.example.itp.attendence_report;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.itp.attendence_report.Models.Faculty;
import com.example.itp.attendence_report.Models.Student;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by ITP on 4/21/2017.
 */

public class AddStudent_Activity extends AppCompatActivity {
    String faculty_name = Faculty_LoginActivity.fac_name;
    String faculty_username = Faculty_LoginActivity.fac_username;
    String faculty_password = Faculty_LoginActivity.fac_password;
    EditText rollnum_et, student_name_et, branch_et, sem_per_et, month_att_et;
    Spinner year_sp, year_sem_sp, month_sp;
    Button save_btn;
    Student student;
    String rollNum, studentName, branch, semester_percentage, month_attendance, year, year_sem, month;
    Faculty faculty;
    private DatabaseReference mFirebaseDatabase;
    private FirebaseDatabase mFirebaseInstance;
    Boolean edit;

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

        edit = getIntent().getBooleanExtra("edit", false);
        if (edit) {
            student = getIntent().getParcelableExtra("student_details");

            rollnum_et.setText(student.getStudent_rollnumber());
            student_name_et.setText(student.getStudent_name());
            branch_et.setText(student.getStudent_branch());
            save_btn.setText("Update");
        }

        mFirebaseInstance = FirebaseDatabase.getInstance();

        // get reference to 'users' node
        mFirebaseDatabase = mFirebaseInstance.getReference("users");

        save_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                student = new Student();

                rollNum = rollnum_et.getText().toString().trim();
                studentName = student_name_et.getText().toString().trim();
                branch = branch_et.getText().toString().trim();
                semester_percentage = sem_per_et.getText().toString().trim();
                month_attendance = month_att_et.getText().toString().trim();
                year = year_sp.getSelectedItem().toString();
                year_sem = year_sem_sp.getSelectedItem().toString();
                month = month_sp.getSelectedItem().toString();

                if (rollNum.isEmpty() || studentName.isEmpty() || branch.isEmpty() || semester_percentage.isEmpty() || month_attendance.isEmpty()
                        || year.isEmpty() || year_sem.isEmpty() || month.isEmpty()) {
                    Toast.makeText(AddStudent_Activity.this, "Please fill complete form.", Toast.LENGTH_SHORT).show();
                } else {

                    student = new Student(studentName, rollNum, branch, year, year_sem, semester_percentage, month, month_attendance);

                    mFirebaseDatabase.child(faculty_username).child("student_details").child(rollNum).setValue(student);

                    Toast.makeText(AddStudent_Activity.this, "Student created successfully", Toast.LENGTH_SHORT).show();

                    Intent in = new Intent(AddStudent_Activity.this, Add_or_Search_StudentActivity.class);
                    startActivity(in);
                    finish();
                }
            }
        });


    }
}
