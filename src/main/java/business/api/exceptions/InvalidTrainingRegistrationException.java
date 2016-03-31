package business.api.exceptions;

public class InvalidTrainingRegistrationException extends ApiException {

    private static final long serialVersionUID = -1344640670884805385L;

    public static final String DESCRIPTION = "Reserva de pista inválida";

    public static final int CODE = 1;

    public InvalidTrainingRegistrationException() {
        this("");
    }

    public InvalidTrainingRegistrationException(String detail) {
        super(DESCRIPTION + ". " + detail, CODE);
    }
}
