package com.example.itp.attendence_report.Models;

import java.util.ArrayList;
import java.util.HashMap;


public class Faculty {

    String faculty_fullname;
    String faculty_username;
    String faculty_password;
    Student student_details = new Student();

    public String getFaculty_fullname() {
        return faculty_fullname;
    }

    public void setFaculty_fullname(String faculty_fullname) {
        this.faculty_fullname = faculty_fullname;
    }

    public String getFaculty_username() {
        return faculty_username;
    }

    public void setFaculty_username(String faculty_username) {
        this.faculty_username = faculty_username;
    }

    public String getFaculty_password() {
        return faculty_password;
    }

    public void setFaculty_password(String faculty_password) {
        this.faculty_password = faculty_password;
    }


    public Faculty(String faculty_fullname, String faculty_username, String faculty_password, Student student_details) {

        this.faculty_fullname = faculty_fullname;
        this.faculty_username = faculty_username;
        this.faculty_password = faculty_password;
        this.student_details = student_details;
    }


}
