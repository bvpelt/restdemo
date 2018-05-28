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

@CrossOrigin(origins = "*")
@RestController
public class PersonController {

    private static final Logger logger = LoggerFactory.getLogger(PersonController.class);


    private PersonService personService;

    @Autowired
    public PersonController(final PersonService personService) {
        this.personService = personService;
    }

    @RequestMapping(value = "/persons/{id}", method = RequestMethod.GET)
    public ResponseEntity<Resource<Person>> getPersonById(@PathVariable final long id) {
        Optional<Person> person = personService.findById(id);
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

        List<Person> personList = personService.getAll();
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
    public ResponseEntity<PersonList> getPersonsAtAdres(@PathVariable final String postCode, @PathVariable final int huisNummer) {

        List<Person> personList = personService.getPersonsAtAdres(postCode, huisNummer);
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
    public ResponseEntity<PersonList> getPersonsAtAdres(@PathVariable final String postCode, @PathVariable final int huisNummer, @PathVariable final String huisNummerToevoeging) {

        List<Person> personList = personService.getPersonsAtAdres(postCode, huisNummer, huisNummerToevoeging);
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
    public ResponseEntity<Object> createPerson(@RequestBody final Person person) {

        Person savedPerson = personService.create(person);

        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(savedPerson.getPersonId())
                .toUri();

        logger.info("Person id: " + savedPerson.getPersonId() + " saved");

        return ResponseEntity.created(location).build();
    }

    @RequestMapping(value = "/persons/{id}", method = RequestMethod.PUT)
    public ResponseEntity<String> updatePerson(@PathVariable final Long id, @RequestBody final Person person) {
        if (personService.findById(id) == null) {
            return new ResponseEntity<String>("Person not found", HttpStatus.NOT_FOUND);
        } else {
            personService.update(person);
            return new ResponseEntity<String>("Person Updated Successfully", HttpStatus.OK);
        }
    }

    @RequestMapping(value = "/persons/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<String> deletePerson(@PathVariable final Long id) {
        if (personService.findById(id) == null) {
            return new ResponseEntity<String>("Person not found", HttpStatus.NOT_FOUND);
        } else {
            personService.delete(id);
            return new ResponseEntity<String>("Person Deleted Successfully", HttpStatus.OK);
        }
    }

}
