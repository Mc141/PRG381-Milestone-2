package model;

/**
 * Enforces a contract for subclasses to implement getId method.
 */
public abstract class BaseModel {
    
    public abstract int getId();

    @Override
    public String toString() {
        return "Entity ID: " + getId();
    }
}