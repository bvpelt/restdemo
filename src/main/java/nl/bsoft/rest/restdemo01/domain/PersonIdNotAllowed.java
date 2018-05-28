package nl.bsoft.rest.restdemo01.domain;

public class PersonIdNotAllowed extends RuntimeException {

    public PersonIdNotAllowed(String exception) {
        super(exception);
    }
}
