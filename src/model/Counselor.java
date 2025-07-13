package model;

public class Counselor extends Person {
    private String specialisation;
    private boolean available;

    public Counselor(int id, String firstName, String lastName, String specialisation, boolean available) {
        super(id, firstName, lastName);
        this.specialisation = specialisation;
        this.available = available;
    }

    public String getSpecialisation() {
        return specialisation;
    }

    public void setSpecialisation(String specialisation) {
        this.specialisation = specialisation;
    }

    public boolean isAvailable() {
        return available;
    }

    public void setAvailable(boolean available) {
        this.available = available;
    }
}
