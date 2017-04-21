package com.example.itp.attendence_report;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button faculty_btn = (Button) findViewById(R.id.faculty_btn);
        Button student_btn = (Button) findViewById(R.id.student_btn);


        faculty_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent in = new Intent(MainActivity.this, Faculty_LoginActivity.class);
                startActivity(in);
            }
        });

        student_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent in = new Intent(MainActivity.this, Student_Activity.class);
                startActivity(in);
            }
        });
    }
}
