package team3.OneSubscribe.error;

public class DuplicatedLoginIDExcpetion extends RuntimeException {
    public DuplicatedLoginIDExcpetion(String message) {
        super(message);
    }
}
