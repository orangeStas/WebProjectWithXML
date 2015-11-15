package by.epam.task5.exception;

public class ProjectException extends Exception {
    private static final long serialVersionUID = 1L;
    private Exception hiddenException;

    public ProjectException(String message, Exception e) {
        super(message);
        hiddenException = e;
    }

    public ProjectException(String message) {
        super(message);
    }

    public Exception getHiddenException() {
        return hiddenException;
    }
}
