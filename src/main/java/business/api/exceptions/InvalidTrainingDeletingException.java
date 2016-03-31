package business.api.exceptions;

public class InvalidTrainingDeletingException extends ApiException {

    private static final long serialVersionUID = -1344640670884805385L;

    public static final String DESCRIPTION = "No se pudo eliminar el entrenamiento";

    public static final int CODE = 1;

    public InvalidTrainingDeletingException() {
        this("");
    }

    public InvalidTrainingDeletingException(String detail) {
        super(DESCRIPTION + ". " + detail, CODE);
    }
}
