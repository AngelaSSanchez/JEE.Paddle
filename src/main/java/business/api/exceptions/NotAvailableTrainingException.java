package business.api.exceptions;

public class NotAvailableTrainingException extends ApiException {

    private static final long serialVersionUID = -1344640670884805385L;

    public static final String DESCRIPTION = "No hay entrenamientos disponibles";

    public static final int CODE = 1;

    public NotAvailableTrainingException() {
        this("");
    }

    public NotAvailableTrainingException(String detail) {
        super(DESCRIPTION + ". " + detail, CODE);
    }
}
