package nl.bsoft.rest.restdemo01.repository;

import nl.bsoft.rest.restdemo01.domain.Person;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PersonRepository extends JpaRepository<Person, Long> {
}
