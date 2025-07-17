package model;

public class Feedback implements IIdentifiable {
    private int id;
    private int studentId;
    private int counselorId;
    private int appointmentId;
    private int rating;
    private String comments;

    public Feedback(int id, int studentId, int counselorId, int appointmentId, int rating, String comments) {
        this.id = id;
        this.studentId = studentId;
        this.counselorId = counselorId;
        this.appointmentId = appointmentId;
        this.rating = rating;
        this.comments = comments;
    }

    public Feedback() {}

    @Override
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getStudentId() {
        return studentId;
    }

    public void setStudentId(int studentId) {
        this.studentId = studentId;
    }

    public int getCounselorId() {
        return counselorId;
    }

    public void setCounselorId(int counselorId) {
        this.counselorId = counselorId;
    }

    public int getAppointmentId() {
        return appointmentId;
    }

    public void setAppointmentId(int appointmentId) {
        this.appointmentId = appointmentId;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }
}
