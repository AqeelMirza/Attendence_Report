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
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonObjectRequest;
import com.example.itp.attendence_report.Utils.AppController;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import org.json.JSONException;
import org.json.JSONObject;


public class Faculty_LoginActivity extends AppCompatActivity {
    String TAG = "Login";
    FirebaseDatabase database;
    DatabaseReference myRef;
    EditText username_et, password_et;
    Button loginbtn, newFaculty_btn;
    public static String fac_username;
    public static String fac_password;
    public static String fac_name;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.faculty_login);
        // Write a message to the database
        database = FirebaseDatabase.getInstance();
        myRef = database.getReference("users");


        username_et = (EditText) findViewById(R.id.faculty_login_username);
        password_et = (EditText) findViewById(R.id.faculty_login_password);
        loginbtn = (Button) findViewById(R.id.login_fac_btn);
        newFaculty_btn = (Button) findViewById(R.id.faculty_login_newuser_btn);


        newFaculty_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in = new Intent(Faculty_LoginActivity.this, NewFaculty_Activity.class);
                startActivity(in);
            }
        });

        loginbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String username = username_et.getText().toString().trim();
                String password = password_et.getText().toString().trim();

                loginService(username, password);

            }
        });
    }

    void loginService(final String username, final String password) {


        String url = "https://attendancereport-31594.firebaseio.com/users/" + username + ".json";

        final ProgressDialog pDialog = new ProgressDialog(this);
        pDialog.setMessage("Loading...");
        pDialog.show();

        JsonObjectRequest jsonObjReq = new JsonObjectRequest(Request.Method.GET,
                url, null,
                new Response.Listener<JSONObject>() {

                    @Override
                    public void onResponse(JSONObject response) {
                        Log.d(TAG, response.toString());
                        pDialog.hide();
                        if (response == null) {
                            Toast.makeText(Faculty_LoginActivity.this, "Username does not exist,Please check your details", Toast.LENGTH_SHORT).show();
                        } else {
                            try {
                                JSONObject jsonObject = new JSONObject(response.toString());

                                String faculty_password = jsonObject.getString("faculty_password");
                                if (password.equalsIgnoreCase(faculty_password)) {
                                    Toast.makeText(Faculty_LoginActivity.this, "Login Successful", Toast.LENGTH_SHORT).show();
                                    String faculty_username = jsonObject.getString("faculty_username");
                                    String faculty_fullname = jsonObject.getString("faculty_fullname");

                                    fac_username = faculty_username;
                                    fac_password = password;
                                    fac_name = faculty_fullname;

                                    Intent i = new Intent(Faculty_LoginActivity.this, Add_or_Search_StudentActivity.class);
                                    i.putExtra("fullname", faculty_fullname);
                                    i.putExtra("username", faculty_username);
                                    i.putExtra("password", password);
                                    startActivity(i);


                                } else {
                                    Toast.makeText(Faculty_LoginActivity.this, "Password incorrect", Toast.LENGTH_SHORT).show();
                                }
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }

                        }
                    }
                }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                VolleyLog.d(TAG, "Error: " + error.getMessage());
                // hide the progress dialog
                Toast.makeText(Faculty_LoginActivity.this, "No records found.Please check entered values", Toast.LENGTH_SHORT).show();
                pDialog.hide();
            }
        });

// Adding request to request queue
        AppController.getInstance().addToRequestQueue(jsonObjReq);

    }

}
