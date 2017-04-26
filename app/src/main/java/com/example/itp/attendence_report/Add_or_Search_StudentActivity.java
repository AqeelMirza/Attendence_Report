package com.example.itp.attendence_report;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonObjectRequest;
import com.example.itp.attendence_report.Models.Student;
import com.example.itp.attendence_report.Utils.AppController;

import org.json.JSONException;
import org.json.JSONObject;

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
                String rollNum = search_et.getText().toString().trim();
                if (rollNum.isEmpty() || rollNum == null) {
                    Toast.makeText(Add_or_Search_StudentActivity.this, "Please enter a Roll Number.", Toast.LENGTH_SHORT).show();
                } else {

                    getStudentDetails(rollNum);
                }
            }
        });
    }

    void getStudentDetails(String rollNumber) {
        //https://attendancereport-31594.firebaseio.com/users/aqeel/student_details
        String url = "https://attendancereport-31594.firebaseio.com/users/" + faculty_username + "/student_details/" + rollNumber + ".json";

        final ProgressDialog pDialog = new ProgressDialog(this);
        pDialog.setMessage("Loading...");
        pDialog.show();

        JsonObjectRequest jsonObjReq = new JsonObjectRequest(Request.Method.GET,
                url, null,
                new Response.Listener<JSONObject>() {

                    @Override
                    public void onResponse(JSONObject response) {
                        Log.d("", response.toString());
                        pDialog.hide();
                        if (response == null) {
                            Toast.makeText(Add_or_Search_StudentActivity.this, "Student does not exist,Please check your details", Toast.LENGTH_SHORT).show();
                        } else {
                            try {
                                JSONObject jsonObject = new JSONObject(response.toString());
                                Student student = new Student();

                                student.setStudent_rollnumber(jsonObject.getString("student_rollnumber"));
                                student.setStudent_name(jsonObject.getString("student_name"));
                                student.setStudent_branch(jsonObject.getString("student_branch"));
                                student.setStudent_month(jsonObject.getString("student_month"));
                                student.setStudent_month_attendance(jsonObject.getString("student_month_attendance"));
                                student.setStudent_year(jsonObject.getString("student_year"));
                                student.setStudent_year_sem(jsonObject.getString("student_year_sem"));
                                student.setStudent_phonenum(jsonObject.getString("student_phonenum"));
                                student.setStudent_year_sem_percentage(jsonObject.getString("student_year_sem_percentage"));

                                Intent in = new Intent(Add_or_Search_StudentActivity.this, AddStudent_Activity.class);
                                in.putExtra("student_details", student);
                                in.putExtra("edit", true);
                                startActivity(in);


                            } catch (JSONException e) {
                                Toast.makeText(Add_or_Search_StudentActivity.this, "some error occurred.", Toast.LENGTH_SHORT).show();
                                e.printStackTrace();
                            }

                        }
                    }
                }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                VolleyLog.d("", "Error: " + error.getMessage());
                // hide the progress dialog
                Toast.makeText(Add_or_Search_StudentActivity.this, "No records found.Please check entered values", Toast.LENGTH_SHORT).show();
                pDialog.hide();
            }
        });

// Adding request to request queue
        AppController.getInstance().addToRequestQueue(jsonObjReq);

    }


}
