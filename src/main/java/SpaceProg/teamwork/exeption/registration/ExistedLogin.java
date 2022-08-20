package SpaceProg.teamwork.exeption.registration;

public class ExistedLogin extends RuntimeException{

    public ExistedLogin(String message) {
        super(message);
    }
}
