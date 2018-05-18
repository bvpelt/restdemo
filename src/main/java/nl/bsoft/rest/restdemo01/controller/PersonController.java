package nl.bsoft.rest.restdemo01.controller;

import nl.bsoft.rest.restdemo01.domain.Person;
import nl.bsoft.rest.restdemo01.domain.PersonList;
import nl.bsoft.rest.restdemo01.service.PersonService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.Resource;
import org.springframework.hateoas.mvc.ControllerLinkBuilder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

@RestController
public class PersonController {

    private static final Logger logger = LoggerFactory.getLogger(PersonController.class);

    @Autowired
    PersonService service;

    @RequestMapping(value = "/persons/{id}", method = RequestMethod.GET)
    public ResponseEntity<Resource<Person>> getPersonById(@PathVariable long id) {
        Optional<Person> person = service.findById(id);
        Resource<Person> resource = null;

        if (!person.isPresent()) {
            logger.error("Person id: " + id + " not found");
            return new ResponseEntity<Resource<Person>>(resource, HttpStatus.NOT_FOUND);
        } else {
            resource = new Resource<Person>(person.get());

            ControllerLinkBuilder linkTo = linkTo(methodOn(this.getClass()).getPersons());

            resource.add(linkTo.withRel("persons"));
            logger.info("Person id: " + id + " found");
        }
        return new ResponseEntity<Resource<Person>>(resource, HttpStatus.OK);
    }

    @RequestMapping(value = "/persons/", method = RequestMethod.GET)
    public ResponseEntity<PersonList> getPersons() {

        List<Person> personList = service.getAll();
        PersonList personResult = new PersonList();

        for (Person person : personList) {
            Link link = ControllerLinkBuilder.linkTo(methodOn(this.getClass()).getPersons())
                    .slash(person.getPersonId())
                    .withSelfRel();

            person.add(link.withRel("persons"));

            personResult.getPersons().add(person);
        }

        //Adding self link accounts collection resource
        Link selfLink = ControllerLinkBuilder
                .linkTo(ControllerLinkBuilder.methodOn(PersonController.class).getPersons())
                .withSelfRel();
        personResult.add(selfLink);

        return new ResponseEntity<PersonList>(personResult, HttpStatus.OK);
    }

    @RequestMapping(value = "/persons/adres/{postCode}/{huisNummer}", method = RequestMethod.GET)
    public ResponseEntity<PersonList> getPersonsAtAdres(@PathVariable String postCode, @PathVariable int huisNummer) {

        List<Person> personList = service.getPersonsAtAdres(postCode, huisNummer);
        PersonList personResult = new PersonList();

        for (Person person : personList) {
            Link link = ControllerLinkBuilder.linkTo(methodOn(this.getClass()).getPersons())
                    .slash(person.getPersonId())
                    .withSelfRel();

            person.add(link);

            personResult.getPersons().add(person);
        }

        //Adding self link accounts collection resource
        Link selfLink = ControllerLinkBuilder
                .linkTo(ControllerLinkBuilder.methodOn(PersonController.class).getPersons())
                .withSelfRel();
        personResult.add(selfLink);

        return new ResponseEntity<PersonList>(personResult, HttpStatus.OK);
    }


    @RequestMapping(value = "/persons/adres/{postCode}/{huisNummer}/{huisNummerToevoeging}", method = RequestMethod.GET)
    public ResponseEntity<PersonList> getPersonsAtAdres(@PathVariable String postCode, @PathVariable int huisNummer, @PathVariable String huisNummerToevoeging) {

        List<Person> personList = service.getPersonsAtAdres(postCode, huisNummer, huisNummerToevoeging);
        PersonList personResult = new PersonList();

        for (Person person : personList) {
            Link link = ControllerLinkBuilder.linkTo(PersonController.class)
                    .slash(person.getPersonId())
                    .withSelfRel();

            person.add(link);

            personResult.getPersons().add(person);
        }

        //Adding self link accounts collection resource
        Link selfLink = ControllerLinkBuilder
                .linkTo(ControllerLinkBuilder.methodOn(PersonController.class).getPersons())
                .withSelfRel();
        personResult.add(selfLink);

        return new ResponseEntity<PersonList>(personResult, HttpStatus.OK);
    }


    @RequestMapping(value = "/persons/", method = RequestMethod.POST)
    public ResponseEntity<Object> createPerson(@RequestBody Person person) {

        Person savedPerson = service.create(person);

        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(savedPerson.getPersonId())
                .toUri();

        logger.info("Person id: " + savedPerson.getPersonId() + " saved");

        return ResponseEntity.created(location).build();
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
