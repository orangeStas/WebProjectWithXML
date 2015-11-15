package by.epam.task5.exception;

public class XMLDaoException extends ProjectException {

    private static final long serialVersionUID = 1L;

    public XMLDaoException(String message, Exception e) {
        super(message, e);
    }

    public XMLDaoException(String message) {
        super(message);
    }
}
