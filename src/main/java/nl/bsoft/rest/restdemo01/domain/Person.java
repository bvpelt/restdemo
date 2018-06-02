package nl.bsoft.rest.restdemo01.domain;


import com.fasterxml.jackson.annotation.JsonBackReference;
import org.springframework.hateoas.ResourceSupport;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "persoon")
public class Person extends ResourceSupport implements Serializable {
    private static final long serialVersionUID = 3L;

    @Id
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
    @JoinColumn(name = "adres_id")
    @JsonBackReference
    private Adres adres;

    @Override
    public boolean equals(Object other) {
        if (!(other instanceof Person)) {
            return false;
        }

        Person that = (Person) other;

        // Custom equality check here.
        return this.roepNaam.equals(that.roepNaam)
                && this.voorNamen.equals(that.voorNamen)
                && this.tussenVoegsel.equals(that.tussenVoegsel)
                && this.achterNaam.equals(that.achterNaam)
                && this.geboorteDatum.equals(that.geboorteDatum)
                && this.kerkelijkeStaat.equals(that.kerkelijkeStaat)
                && this.emailAdres.equals(that.emailAdres)
                && this.mobielNummer.equals(that.mobielNummer)
                ;
    }

    @Override
    public int hashCode() {
        int hashCode = 17;

        hashCode = hashCode * 37 + this.roepNaam.hashCode();
        hashCode = hashCode * 37 + this.voorNamen.hashCode();
        hashCode = hashCode * 37 + this.tussenVoegsel.hashCode();
        hashCode = hashCode * 37 + this.achterNaam.hashCode();
        hashCode = hashCode * 37 + this.geboorteDatum.hashCode();
        hashCode = hashCode * 37 + this.kerkelijkeStaat.hashCode();
        hashCode = hashCode * 37 + this.emailAdres.hashCode();
        hashCode = hashCode * 37 + this.mobielNummer.hashCode();

        return hashCode;
    }

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
