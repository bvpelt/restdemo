package nl.bsoft.rest.restdemo01.domain;


import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import org.springframework.hateoas.ResourceSupport;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "persoon")
//@JsonIdentityInfo(generator=ObjectIdGenerators.PropertyGenerator.class, property="personId")
public class Person extends ResourceSupport implements Serializable {
    private static final long serialVersionUID = 3L;

    @Id
    // @GeneratedValue(strategy = GenerationType.IDENTITY)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "persoonSequence")
    @SequenceGenerator(name = "persoonSequence", sequenceName = "persoon_seq")
    @Column(name = "persoon_id")
    private Long personId;

    @Column(name = "persoon_roepnaam")
    private String roepNaam;

    @Column(name = "persoon_voornamen")
    private String voorNamen;

    @Column(name = "persoon_tussenvoegsel")
    private String tussenVoegsel;

    @Column(name = "persoon_achternaam")
    private String achterNaam;

    @Temporal(TemporalType.DATE)
    @Column(name = "persoon_geboortedatum")
    private Date geboorteDatum;

    @Column(name = "persoon_kerkelijkestaat")
    private LidType kerkelijkeStaat;

    @Column(name = "persoon_email")
    private String emailAdres;

    @Column(name = "persoon_mobielnummer")
    private String mobielNummer;

    @ManyToOne
    @JoinColumn(name="adres_id")
    @JsonBackReference
    private Adres adres;

    public Long getPersonId() {
        return personId;
    }

    public void setPersonId(Long personId) {
        this.personId = personId;
    }

    public String getRoepNaam() {
        return roepNaam;
    }

    public void setRoepNaam(String roepNaam) {
        this.roepNaam = roepNaam;
    }

    public String getVoorNamen() {
        return voorNamen;
    }

    public void setVoorNamen(String voorNamen) {
        this.voorNamen = voorNamen;
    }

    public String getTussenVoegsel() {
        return tussenVoegsel;
    }

    public void setTussenVoegsel(String tussenVoegsel) {
        this.tussenVoegsel = tussenVoegsel;
    }

    public String getAchterNaam() {
        return achterNaam;
    }

    public void setAchterNaam(String achterNaam) {
        this.achterNaam = achterNaam;
    }

    public Date getGeboorteDatum() {
        return geboorteDatum;
    }

    public void setGeboorteDatum(Date geboorteDatum) {
        this.geboorteDatum = geboorteDatum;
    }

    public LidType getKerkelijkeStaat() {
        return kerkelijkeStaat;
    }

    public void setKerkelijkeStaat(LidType kerkelijkeStaat) {
        this.kerkelijkeStaat = kerkelijkeStaat;
    }

    public String getEmailAdres() {
        return emailAdres;
    }

    public void setEmailAdres(String emailAdres) {
        this.emailAdres = emailAdres;
    }

    public String getMobielNummer() {
        return mobielNummer;
    }

    public void setMobielNummer(String mobielNummer) {
        this.mobielNummer = mobielNummer;
    }

    public Adres getAdres() {
        return adres;
    }

    public void setAdres(Adres adres) {
        this.adres = adres;
    }
}
