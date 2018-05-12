package nl.bsoft.rest.restdemo01.service;

import nl.bsoft.rest.restdemo01.domain.Person;
import nl.bsoft.rest.restdemo01.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PersonService {

    @Autowired
    private PersonRepository repository;

    public Person findById(final Long id) {
        Person person = null;
        Optional<Person> result = repository.findById(id);
        if (result.isPresent()) {
            person = result.get();
        }
        return person;
    }

    public List<Person> getAll() {
        return repository.findAll();
    }

    public void create(final Person person) {
        repository.save(person);
    }

    public void update(final Person updatedPerson) {
        Person personToBeUpdated = null;
        Optional<Person> result = repository.findById(updatedPerson.getId());

        if (result.isPresent()) {
            personToBeUpdated = result.get();
            personToBeUpdated.setAdres(updatedPerson.getAdres());
            personToBeUpdated.setAchterNaam(updatedPerson.getAchterNaam());
            personToBeUpdated.setEmailAdres(updatedPerson.getEmailAdres());
            personToBeUpdated.setGeboorteDatum(updatedPerson.getGeboorteDatum());
            personToBeUpdated.setKerkelijkeStaat(updatedPerson.getKerkelijkeStaat());
            personToBeUpdated.setMobielNummer(updatedPerson.getMobielNummer());
            personToBeUpdated.setRoepNaam(updatedPerson.getRoepNaam());
            personToBeUpdated.setTussenVoegsel(updatedPerson.getTussenVoegsel());
            personToBeUpdated.setVoorNamen(updatedPerson.getVoorNamen());
            repository.save(personToBeUpdated);
        } else {
            // not found
        }
    }

    public void delete(final Long id) {
        Person person = findById(id);
        if (person != null) {
            repository.delete(person);
        }
    }
}
