package edu.swarthmore.cs.cs71.swatify.errors;

public class InternalServerError extends BaseError {
    public InternalServerError(String message) {
        super(message);
    }

    @Override
    public int getStatus() {
        return 500;
    }
}

