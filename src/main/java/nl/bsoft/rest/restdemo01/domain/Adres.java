package nl.bsoft.rest.restdemo01.domain;


import com.fasterxml.jackson.annotation.JsonManagedReference;
import org.springframework.hateoas.ResourceSupport;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "adres",
        uniqueConstraints = {
                @UniqueConstraint(
                        columnNames = {
                                "adres_postcode",
                                "adres_huisnummer",
                                "adres_huisnummertoevoeging"
                        }
                )
        }
)

public class Adres extends ResourceSupport implements Serializable {

    private static final long serialVersionUID = 2L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "adresSequence")
    @SequenceGenerator(name = "adresSequence", sequenceName = "adres_seq")
    @Column(name = "adres_id")
    private Long adresId;

    @Column(name = "adres_straatnaam")
    private String straatNaam;

    @Column(name = "adres_huisnummer")
    private int huisNummer;

    @Column(name = "adres_huisnummertoevoeging")
    private String huisNummerToevoeging;

    @Column(name = "adres_postcode")
    private String postCode;

    @Column(name = "adres_woonplaats")
    private String woonPlaats;

    @Column(name = "adres_telefoonnummer")
    private String telefoonNummer;

    @OneToMany(orphanRemoval = true)
    @JoinColumn(name = "adres_id") // join column is in table for Order
    @JsonManagedReference
    private List<Person> personen = new ArrayList<Person>(0);

    public Long getAdresId() {
        return adresId;
    }

    public void setAdresId(Long id) {
        this.adresId = adresId;
    }

    public String getStraatNaam() {
        return straatNaam;
    }

    public void setStraatNaam(String straatNaam) {
        this.straatNaam = straatNaam;
    }

    public int getHuisNummer() {
        return huisNummer;
    }

    public void setHuisNummer(int huisNummer) {
        this.huisNummer = huisNummer;
    }

    public String getHuisNummerToevoeging() {
        return huisNummerToevoeging;
    }

    public void setHuisNummerToevoeging(String huisNummerToevoeging) {
        this.huisNummerToevoeging = huisNummerToevoeging;
    }

    public String getPostCode() {
        return postCode;
    }

    public void setPostCode(String postCode) {
        this.postCode = postCode;
    }

    public String getWoonPlaats() {
        return woonPlaats;
    }

    public void setWoonPlaats(String woonPlaats) {
        this.woonPlaats = woonPlaats;
    }

    public String getTelefoonNummer() {
        return telefoonNummer;
    }

    public void setTelefoonNummer(String telefoonNummer) {
        this.telefoonNummer = telefoonNummer;
    }

    public List<Person> getPersonen() {
        return personen;
    }

    public void setPersonen(List<Person> personen) {
        this.personen = personen;
    }
}
