package model;

import java.time.LocalDate;
import java.time.LocalTime;

public class Appointment implements IIdentifiable {
    private int id;
    private int student_id;
    private int counselor_id;
    private LocalDate date;
    private LocalTime time;
    private String status;

    // These are for display purposes only
    private String studentName;
    private String counselorName;

    public Appointment() {}

    public Appointment(int id, int student_id, int counselor_id, LocalDate date, LocalTime time, String status) {
        this.id = id;
        this.student_id = student_id;
        this.counselor_id = counselor_id;
        this.date = date;
        this.time = time;
        this.status = status;
    }

    @Override
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getStudentId() {
        return student_id;
    }

    public void setStudentId(int student_id) {
        this.student_id = student_id;
    }

    public int getCounselorId() {
        return counselor_id;
    }

    public void setCounselorId(int counselor_id) {
        this.counselor_id = counselor_id;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public LocalTime getTime() {
        return time;
    }

    public void setTime(LocalTime time) {
        this.time = time;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getStudentName() {
        return studentName;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }

    public String getCounselorName() {
        return counselorName;
    }

    public void setCounselorName(String counselorName) {
        this.counselorName = counselorName;
    }
}
