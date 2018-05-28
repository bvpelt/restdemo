package nl.bsoft.rest.restdemo01.service;

import nl.bsoft.rest.restdemo01.domain.Person;
import nl.bsoft.rest.restdemo01.domain.PersonNotFound;
import nl.bsoft.rest.restdemo01.repository.AdresRepository;
import nl.bsoft.rest.restdemo01.repository.PersonRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Transactional
@Service
public class PersonService {
    private static final Logger logger = LoggerFactory.getLogger(PersonService.class);

    private AdresRepository adresRepository = null;
    private PersonRepository personRepository = null;

    @Autowired
    public PersonService(AdresRepository adresRepository, PersonRepository personRepository) {
        this.adresRepository = adresRepository;
        this.personRepository = personRepository;
    }

    public Optional<Person> findById(final Long id) {
        Optional<Person> person = null;
        person = personRepository.findById(id);

        return person;
    }

    public List<Person> getAll() {
        return personRepository.findAll();
    }

    public Person create(final Person person) {

        Person savedPerson = null;

        savedPerson = personRepository.save(person);

        return savedPerson;
    }

    public void update(final Person updatedPerson) {
        Person personToBeUpdated = null;
        Optional<Person> result = personRepository.findById(updatedPerson.getPersonId());

        if (result.isPresent()) {
            personToBeUpdated = result.get();
            personToBeUpdated.setAchterNaam(updatedPerson.getAchterNaam());
            personToBeUpdated.setEmailAdres(updatedPerson.getEmailAdres());
            personToBeUpdated.setGeboorteDatum(updatedPerson.getGeboorteDatum());
            personToBeUpdated.setKerkelijkeStaat(updatedPerson.getKerkelijkeStaat());
            personToBeUpdated.setMobielNummer(updatedPerson.getMobielNummer());
            personToBeUpdated.setRoepNaam(updatedPerson.getRoepNaam());
            personToBeUpdated.setTussenVoegsel(updatedPerson.getTussenVoegsel());
            personToBeUpdated.setVoorNamen(updatedPerson.getVoorNamen());
            personRepository.save(personToBeUpdated);
        } else {
            throw new PersonNotFound("id: " + updatedPerson.getPersonId());
        }
    }

    public void delete(final Long id) {
        Optional<Person> person = findById(id);
        if (person.isPresent()) {
            personRepository.delete(person.get());
        } else {
            logger.error("Person id: " + id + " not found");
        }
    }

    public List<Person> getPersonsAtAdres(String postCode, int huisNummer) {
        List<Person> personList = null;

        personList = personRepository.findByAddress(postCode, huisNummer);

        return personList;
    }


    public List<Person> getPersonsAtAdres(String postCode, int huisNummer, String huisNummerToevoeging) {
        List<Person> personList = null;

        personList = personRepository.findByFullAddress(postCode, huisNummer, huisNummerToevoeging);

        return personList;
    }
}
