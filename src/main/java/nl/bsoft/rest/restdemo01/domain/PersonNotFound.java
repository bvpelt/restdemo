package nl.bsoft.rest.restdemo01.domain;

public class PersonNotFound extends RuntimeException {

    public PersonNotFound(String exception) {
        super(exception);
    }
}
