package nl.bsoft.rest.restdemo01.repository;

import nl.bsoft.rest.restdemo01.domain.Adres;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AdresRepository extends JpaRepository<Adres, Long> {
}
