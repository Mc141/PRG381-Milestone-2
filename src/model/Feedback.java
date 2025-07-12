package model;

public class Feedback extends BaseModel {
    private int feedbackId;
    private String student;
    private int rating;
    private String comments;

    public Feedback() {}

    public Feedback(int feedbackId, String student, int rating, String comments) {
        this.feedbackId = feedbackId;
        this.student = student;
        this.rating = rating;
        this.comments = comments;
    }

    public Feedback(String student, int rating, String comments) {
        this.student = student;
        this.rating = rating;
        this.comments = comments;
    }

    @Override
    public int getId() {
        return feedbackId;
    }

    public int getFeedbackId() {
        return feedbackId;
    }

    public void setFeedbackId(int feedbackId) {
        this.feedbackId = feedbackId;
    }

    public String getStudent() {
        return student;
    }

    public void setStudent(String student) {
        this.student = student;
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

    @Override
    public String toString() {
        return "Feedback{" +
                "feedbackId=" + feedbackId +
                ", student='" + student + '\'' +
                ", rating=" + rating +
                ", comments='" + comments + '\'' +
                '}';
    }
}
