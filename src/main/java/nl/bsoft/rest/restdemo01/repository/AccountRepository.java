package nl.bsoft.rest.restdemo01.repository;

import nl.bsoft.rest.restdemo01.domain.Account;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AccountRepository extends JpaRepository<Account, Long> {
}
