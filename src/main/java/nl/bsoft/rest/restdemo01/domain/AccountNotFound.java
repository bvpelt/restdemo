package nl.bsoft.rest.restdemo01.domain;

public class AccountNotFound extends RuntimeException {

    public AccountNotFound(String exception) {
        super(exception);
    }
}
