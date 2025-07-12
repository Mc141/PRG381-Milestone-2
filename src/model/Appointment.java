package model;

import java.time.LocalDate;
import java.time.LocalTime;

public class Appointment extends BaseModel {
    private int appointmentId;
    private String student;
    private String counselor;
    private LocalDate date;
    private LocalTime time;
    private String status;

    public Appointment() {}

    public Appointment(int appointmentId, String student, String counselor, LocalDate date, LocalTime time, String status) {
        this.appointmentId = appointmentId;
        this.student = student;
        this.counselor = counselor;
        this.date = date;
        this.time = time;
        this.status = status;
    }

    public Appointment(String student, String counselor, LocalDate date, LocalTime time, String status) {
        this.student = student;
        this.counselor = counselor;
        this.date = date;
        this.time = time;
        this.status = status;
    }

    @Override
    public int getId() {
        return appointmentId;
    }

    public int getAppointmentId() {
        return appointmentId;
    }

    public void setAppointmentId(int appointmentId) {
        this.appointmentId = appointmentId;
    }

    public String getStudent() {
        return student;
    }

    public void setStudent(String student) {
        this.student = student;
    }

    public String getCounselor() {
        return counselor;
    }

    public void setCounselor(String counselor) {
        this.counselor = counselor;
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

    @Override
    public String toString() {
        return "Appointment{" +
                "appointmentId=" + appointmentId +
                ", student='" + student + '\'' +
                ", counselor='" + counselor + '\'' +
                ", date=" + date +
                ", time=" + time +
                ", status='" + status + '\'' +
                '}';
    }
}
