package ex03;

public class TransactionNotFoundException extends RuntimeException{
    public TransactionNotFoundException(String err) {
        super(err);
    }
}
