package nl.bsoft.rest.restdemo01.domain;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "persoon")
public class Person implements Serializable {
    private static final long serialVersionUID = 3L;

    @Id
    @GeneratedValue
    @Column(name = "persoon_id")
    private Long id;

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

    @OneToOne
    @JoinColumn(name = "adres_id")
    private Adres adres;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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
