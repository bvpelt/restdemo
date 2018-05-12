package nl.bsoft.rest.restdemo01.controller;

import nl.bsoft.rest.restdemo01.domain.Person;
import nl.bsoft.rest.restdemo01.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class PersonController {

    @Autowired
    PersonService service;

    @RequestMapping(value = "/persons/{id}", method = RequestMethod.GET)
    public ResponseEntity<Person> getPersonById(@PathVariable Long id) {
        return new ResponseEntity<Person>(service.findById(id), HttpStatus.OK);
    }

    @RequestMapping(value = "/persons/", method = RequestMethod.GET)
    public ResponseEntity<List<Person>> getPersons() {
        return new ResponseEntity<List<Person>>(service.getAll(), HttpStatus.OK);
    }

    @RequestMapping(value = "/persons/", method = RequestMethod.POST)
    public ResponseEntity<String> createPerson(@RequestBody Person person) {
        service.create(person);
        return new ResponseEntity<String>("Person Created Successfully", HttpStatus.CREATED);
    }

    @RequestMapping(value = "/persons/{id}", method = RequestMethod.PUT)
    public ResponseEntity<String> updatePerson(@PathVariable Long id, @RequestBody Person person) {
        if (service.findById(id) == null) {
            return new ResponseEntity<String>("Person not found", HttpStatus.NOT_FOUND);
        } else {
            service.update(person);
            return new ResponseEntity<String>("Person Updated Successfully", HttpStatus.OK);
        }
    }

    @RequestMapping(value = "/persons/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<String> deletePerson(@PathVariable Long id) {
        if (service.findById(id) == null) {
            return new ResponseEntity<String>("Person not found", HttpStatus.NOT_FOUND);
        } else {
            service.delete(id);
            return new ResponseEntity<String>("Person Deleted Successfully", HttpStatus.OK);
        }
    }

}
