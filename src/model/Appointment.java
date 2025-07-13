package model;

import java.time.LocalDate;
import java.time.LocalTime;

public class Appointment implements IIdentifiable {
    private int id;
    private Student student;
    private Counselor counselor;
    private LocalDate date;
    private LocalTime time;
    private String status;

    public Appointment(int id, Student student, Counselor counselor, LocalDate date, LocalTime time, String status) {
        this.id = id;
        this.student = student;
        this.counselor = counselor;
        this.date = date;
        this.time = time;
        this.status = status;
    }

    @Override
    public int getId() {
        return id;
    }

    public Student getStudent() {
        return student;
    }

    public Counselor getCounselor() {
        return counselor;
    }

    public LocalDate getDate() {
        return date;
    }

    public LocalTime getTime() {
        return time;
    }

    public String getStatus() {
        return status;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public void setCounselor(Counselor counselor) {
        this.counselor = counselor;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public void setTime(LocalTime time) {
        this.time = time;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
