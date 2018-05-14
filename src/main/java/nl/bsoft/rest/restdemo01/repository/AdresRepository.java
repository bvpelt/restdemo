package nl.bsoft.rest.restdemo01.repository;

import nl.bsoft.rest.restdemo01.domain.Adres;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface AdresRepository extends JpaRepository<Adres, Long> {

    public final static String FIND_BY_FULL_ADDRESS_QUERY = "SELECT a " +
            "FROM Adres a " +
            "WHERE a.postCode = :postCode " +
            "AND a.huisNummer = :huisNummer " +
            "AND a.huisNummerToevoeging = :huisNummerToevoeging";

    public final static String FIND_BY_ADDRESS_QUERY = "SELECT a " +
            "FROM Adres a " +
            "WHERE a.postCode = :postCode " +
            "AND a.huisNummer = :huisNummer ";

    /**
     * Find adres by unique.
     */
    @Query(FIND_BY_FULL_ADDRESS_QUERY)
    public List<Adres> findByFullAddress(@Param("postCode") String postCode,
                                         @Param("huisNummer") int huisNummer,
                                         @Param("huisNummerToevoeging") String huisNummerToevoeging);

    @Query(FIND_BY_ADDRESS_QUERY)
    public List<Adres> findByAddress(@Param("postCode") String postCode,
                                     @Param("huisNummer") int huisNummer);
}
