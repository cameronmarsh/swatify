package edu.swarthmore.cs.cs71.swatify.errors;

public class UnauthorizedError extends BaseError {
    public UnauthorizedError(String message) {
        super(message);
    }

    @Override
    public int getStatus() {
        return 401;
    }
}

