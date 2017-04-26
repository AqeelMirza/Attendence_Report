package com.example.itp.attendence_report;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
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


public class Student_Activity extends AppCompatActivity {

    TextView name, rollnumber_tv, branch, year, year_sem, sem_per, month, month_att, phonenumber;
    LinearLayout details_layout;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.student_activity);

        final EditText search_et = (EditText) findViewById(R.id.search_student_et);
        ImageView search_btn = (ImageView) findViewById(R.id.search_stu_btn);
        final EditText fac_search_et = (EditText) findViewById(R.id.search_fac_username_et);

        details_layout = (LinearLayout) findViewById(R.id.studentinfo_layout);
        rollnumber_tv = (TextView) findViewById(R.id.roll_num);
        name = (TextView) findViewById(R.id.roll_name);
        phonenumber = (TextView) findViewById(R.id.roll_phonenumber);
        branch = (TextView) findViewById(R.id.roll_branch);
        year = (TextView) findViewById(R.id.roll_year);
        year_sem = (TextView) findViewById(R.id.roll_year_sem);
        sem_per = (TextView) findViewById(R.id.roll_sem_percentage);
        month = (TextView) findViewById(R.id.roll_month);
        month_att = (TextView) findViewById(R.id.roll_month_attendance);


        search_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String roll_search = search_et.getText().toString().trim();
                String fac_username = fac_search_et.getText().toString().trim();
                if (roll_search.isEmpty() || fac_username.isEmpty()) {
                    Toast.makeText(Student_Activity.this, "Please enter a roll number.", Toast.LENGTH_SHORT).show();
                } else {

                    InputMethodManager inputManager = (InputMethodManager)
                            getSystemService(Context.INPUT_METHOD_SERVICE);

                    inputManager.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(),
                            InputMethodManager.HIDE_NOT_ALWAYS);
                    getStudentDetails(fac_username, roll_search);
                }

            }
        });
    }

    void getStudentDetails(String faculty_username, final String rollNumber) {
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
                            Toast.makeText(Student_Activity.this, "Student does not exist,Please check your details", Toast.LENGTH_SHORT).show();
                        } else {
                            try {
                                JSONObject jsonObject = new JSONObject(response.toString());

                                details_layout.setVisibility(View.VISIBLE);
                                rollnumber_tv.setText("Roll Number - " + jsonObject.getString("student_rollnumber"));
                                name.setText("Student Name - " + jsonObject.getString("student_name"));
                                phonenumber.setText("Student PhoneNumber - " + jsonObject.getString("student_phonenum"));
                                branch.setText("Branch - " + jsonObject.getString("student_branch"));
                                month.setText("Last Month - " + jsonObject.getString("student_month"));
                                month_att.setText("Attendance(last month) - " + jsonObject.getString("student_month_attendance"));
                                year.setText("Year - " + jsonObject.getString("student_year"));
                                year_sem.setText("Semester - " + jsonObject.getString("student_year_sem"));
                                sem_per.setText("Semester % - " + jsonObject.getString("student_year_sem_percentage"));


                            } catch (JSONException e) {
                                e.printStackTrace();
                            }

                        }
                    }
                }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                VolleyLog.d("", "Error: " + error.getMessage());
                // hide the progress dialog
                Toast.makeText(Student_Activity.this, "No records found.Please check entered values", Toast.LENGTH_SHORT).show();
                pDialog.hide();
                details_layout.setVisibility(View.GONE);
            }
        });

// Adding request to request queue
        AppController.getInstance().addToRequestQueue(jsonObjReq);

    }


}
