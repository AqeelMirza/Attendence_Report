package com.example.itp.attendence_report;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

/**
 * Created by ITP on 4/21/2017.
 */

public class Add_or_Search_StudentActivity extends AppCompatActivity {
    String faculty_name, faculty_username, faculty_password;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.addorsearch_student);

        ImageView add_student_btn = (ImageView) findViewById(R.id.add_student_btn);
        final EditText search_et = (EditText) findViewById(R.id.search_student_et);
        final Button search_btn = (Button) findViewById(R.id.search_stu_btn);

        faculty_name = getIntent().getStringExtra("fullname");
        faculty_username = getIntent().getStringExtra("username");
        faculty_password = getIntent().getStringExtra("password");


        add_student_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent in = new Intent(Add_or_Search_StudentActivity.this, AddStudent_Activity.class);
                in.putExtra("fullname", faculty_name);
                in.putExtra("username", faculty_username);
                in.putExtra("password", faculty_password);
                startActivity(in);
            }
        });


        search_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String student_name = search_et.getText().toString().trim();
                if (student_name.isEmpty() || student_name == null) {
                    Toast.makeText(Add_or_Search_StudentActivity.this, "Please enter a name.", Toast.LENGTH_SHORT).show();
                } else {


                }
            }
        });


    }
}
