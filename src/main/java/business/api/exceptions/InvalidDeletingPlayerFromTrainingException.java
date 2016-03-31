package business.api.exceptions;

public class InvalidDeletingPlayerFromTrainingException extends ApiException {
	
    private static final long serialVersionUID = -1344640670884805385L;

    public static final String DESCRIPTION = "No se pudo eliminar al jugador del entrenamiento";

    public static final int CODE = 1;

    public InvalidDeletingPlayerFromTrainingException() {
        this("");
    }

    public InvalidDeletingPlayerFromTrainingException(String detail) {
        super(DESCRIPTION + ". " + detail, CODE);
    }

}
