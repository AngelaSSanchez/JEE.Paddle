package business.api.exceptions;

public class AllreadyExistTrainingException extends ApiException {

    private static final long serialVersionUID = -1344640670884805385L;

    public static final String DESCRIPTION = "Ya existe el entrenamiento";

    public static final int CODE = 1;

    public AllreadyExistTrainingException() {
        this("");
    }

    public AllreadyExistTrainingException(String detail) {
        super(DESCRIPTION + ". " + detail, CODE);
    }
}
