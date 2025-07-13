package model;

public class Feedback implements IIdentifiable {
    private int id;
    private Student student;
    private Counselor counselor;
    private Appointment appointment;
    private int rating;
    private String comments;

    public Feedback(int id, Student student, Counselor counselor, Appointment appointment, int rating, String comments) {
        this.id = id;
        this.student = student;
        this.counselor = counselor;
        this.appointment = appointment;
        this.rating = rating;
        this.comments = comments;
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

    public Appointment getAppointment() {
        return appointment;
    }

    public int getRating() {
        return rating;
    }

    public String getComments() {
        return comments;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public void setCounselor(Counselor counselor) {
        this.counselor = counselor;
    }

    public void setAppointment(Appointment appointment) {
        this.appointment = appointment;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }
}
