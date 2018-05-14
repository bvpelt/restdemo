package nl.bsoft.rest.restdemo01.domain;

import org.springframework.hateoas.ResourceSupport;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class PersonList extends ResourceSupport implements Serializable {

    private static final long serialVersionUID = 5L;

    private List<Person> persons = new ArrayList<Person>();

    public List<Person> getPersons() {
        return persons;
    }

    public void setPersons(List<Person> persons) {
        this.persons = persons;
    }

}
