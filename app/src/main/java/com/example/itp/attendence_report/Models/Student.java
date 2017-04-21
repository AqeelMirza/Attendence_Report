package com.example.itp.attendence_report.Models;


public class Student {

    public String student_name;
    public String student_rollnumber;
    public String student_branch;
    public String student_year;
    public String student_year_sem;
    public String student_year_sem_percentage;
    public String student_month;
    public String student_month_attendance;

    public Student() {
    }

    public String getStudent_name() {
        return student_name;
    }

    public void setStudent_name(String student_name) {
        this.student_name = student_name;
    }

    public String getStudent_rollnumber() {
        return student_rollnumber;
    }

    public void setStudent_rollnumber(String student_rollnumber) {
        this.student_rollnumber = student_rollnumber;
    }

    public String getStudent_branch() {
        return student_branch;
    }

    public void setStudent_branch(String student_branch) {
        this.student_branch = student_branch;
    }

    public String getStudent_year() {
        return student_year;
    }

    public void setStudent_year(String student_year) {
        this.student_year = student_year;
    }

    public String getStudent_year_sem() {
        return student_year_sem;
    }

    public void setStudent_year_sem(String student_year_sem) {
        this.student_year_sem = student_year_sem;
    }

    public String getStudent_year_sem_percentage() {
        return student_year_sem_percentage;
    }

    public void setStudent_year_sem_percentage(String student_year_sem_percentage) {
        this.student_year_sem_percentage = student_year_sem_percentage;
    }

    public String getStudent_month() {
        return student_month;
    }

    public void setStudent_month(String student_month) {
        this.student_month = student_month;
    }

    public String getStudent_month_attendance() {
        return student_month_attendance;
    }

    public void setStudent_month_attendance(String student_month_attendance) {
        this.student_month_attendance = student_month_attendance;
    }

    public Student(String student_name, String student_rollnumber, String student_branch, String student_year, String student_year_sem, String student_year_sem_percentage, String student_month, String student_month_attendance) {
        this.student_name = student_name;
        this.student_rollnumber = student_rollnumber;
        this.student_branch = student_branch;
        this.student_year = student_year;
        this.student_year_sem = student_year_sem;
        this.student_year_sem_percentage = student_year_sem_percentage;
        this.student_month = student_month;
        this.student_month_attendance = student_month_attendance;
    }
}
