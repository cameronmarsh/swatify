package edu.swarthmore.cs.cs71.swatify.errors;

public class ForbiddenError extends BaseError {
    public ForbiddenError(String message) {
        super(message);
    }

    @Override
    public int getStatus() {
        return 403;
    }
}
