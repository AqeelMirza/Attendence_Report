package com.example.itp.attendence_report;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
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

public class NewFaculty_Activity extends AppCompatActivity {
    private EditText username, fullname, password;
    private Button btnSave;
    private DatabaseReference mFirebaseDatabase;
    private FirebaseDatabase mFirebaseInstance;
    Faculty faculty;
    Student student;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.faculty_details);


        fullname = (EditText) findViewById(R.id.faculty_name);
        username = (EditText) findViewById(R.id.faculty_username);
        password = (EditText) findViewById(R.id.faculty_password);
        btnSave = (Button) findViewById(R.id.btn_save);

        mFirebaseInstance = FirebaseDatabase.getInstance();

        // get reference to 'users' node
        mFirebaseDatabase = mFirebaseInstance.getReference("users");

        // Save / update the user
        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String full_name = fullname.getText().toString();
                String user_name = username.getText().toString();
                String password_str = password.getText().toString().trim();


                createUser(full_name, user_name, password_str);

            }
        });
    }

    private void createUser(String name, String user_name, String password) {
        // TODO
        // In real apps this userId should be fetched
        // by implementing firebase auth
        student = new Student();
        HashMap<String, Student> studentHashMap = new HashMap<>();
        ArrayList<HashMap<String, Student>> hashMapArrayList = new ArrayList<>();

        hashMapArrayList.add(studentHashMap);

        HashMap<String, ArrayList<HashMap<String, Student>>> student_map = new HashMap();

        student_map.put("students", hashMapArrayList);
        faculty = new Faculty(name, user_name, password, student_map);

        mFirebaseDatabase.child(user_name).setValue(faculty);

        Toast.makeText(NewFaculty_Activity.this, "Faculty created successfully", Toast.LENGTH_SHORT).show();


        Intent in = new Intent(NewFaculty_Activity.this, Faculty_LoginActivity.class);

        startActivity(in);

        //  addUserChangeListener();
    }
}
