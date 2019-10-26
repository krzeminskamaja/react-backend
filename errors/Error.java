package pw.react.backend.reactbackend.errors;

public class Error {
    private String message;
    private int errorCode;

    public Error(String message, int code) {
        this.message = message;
        this.errorCode = code;
    }


    public Error(int code) {
        this("", code);
    }


    public String getMessage() {
        return message;
    }

    public int getCode() {
        return errorCode;
    }

}