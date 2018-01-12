package edu.swarthmore.cs.cs71.swatify.errors;

public abstract class BaseError {
    private String message;

    public BaseError(String message) {
        this.message = message;
    }

    public String getMessage() {
        return this.message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public abstract int getStatus();
}
