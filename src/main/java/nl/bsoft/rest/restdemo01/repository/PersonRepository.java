package nl.bsoft.rest.restdemo01.repository;

import nl.bsoft.rest.restdemo01.domain.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface PersonRepository extends JpaRepository<Person, Long> {

    public final static String FIND_PERSONS_BY_FULL_ADDRESS_QUERY = "SELECT p " +
            "FROM Adres a LEFT JOIN a.personen p " +
            "WHERE a.postCode = :postCode " +
            "AND a.huisNummer = :huisNummer " +
            "AND a.huisNummerToevoeging = :huisNummerToevoeging";

    public final static String FIND_PERSONS_BY_ADDRESS_QUERY = "SELECT p " +
            "FROM Adres a LEFT JOIN a.personen p " +
            "WHERE a.postCode = :postCode " +
            "AND a.huisNummer = :huisNummer";

    /**
     * Find adres by unique.
     */
    @Query(FIND_PERSONS_BY_FULL_ADDRESS_QUERY)
    public List<Person> findByFullAddress(@Param("postCode") String postCode,
                                          @Param("huisNummer") int huisNummer,
                                          @Param("huisNummerToevoeging") String huisNummerToevoeging);

    @Query(FIND_PERSONS_BY_ADDRESS_QUERY)
    public List<Person> findByAddress(@Param("postCode") String postCode,
                                      @Param("huisNummer") int huisNummer);
}
