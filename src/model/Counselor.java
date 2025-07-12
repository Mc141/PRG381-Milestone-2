package model;

public class Counselor extends BaseModel {
    private int counselorId;
    private String name;
    private String specialization;
    private boolean available;

    public Counselor() {}

    public Counselor(int counselorId, String name, String specialization, boolean available) {
        this.counselorId = counselorId;
        this.name = name;
        this.specialization = specialization;
        this.available = available;
    }

    public Counselor(String name, String specialization, boolean available) {
        this.name = name;
        this.specialization = specialization;
        this.available = available;
    }

    @Override
    public int getId() {
        return counselorId;
    }

    public int getCounselorId() {
        return counselorId;
    }

    public void setCounselorId(int counselorId) {
        this.counselorId = counselorId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSpecialization() {
        return specialization;
    }

    public void setSpecialization(String specialization) {
        this.specialization = specialization;
    }

    public boolean isAvailable() {
        return available;
    }

    public void setAvailable(boolean available) {
        this.available = available;
    }

    @Override
    public String toString() {
        return "Counselor{" +
                "counselorId=" + counselorId +
                ", name='" + name + '\'' +
                ", specialization='" + specialization + '\'' +
                ", available=" + available +
                '}';
    }
}
