package edu.swarthmore.cs.cs71.swatify.errors;

public class NotFoundError extends BaseError {
    public NotFoundError(String message) {
        super(message);
    }

    @Override
    public int getStatus() {
        return 404;
    }
}
