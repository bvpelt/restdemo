package nl.bsoft.rest.restdemo01.service;

import nl.bsoft.rest.restdemo01.domain.*;
import nl.bsoft.rest.restdemo01.repository.AdresRepository;
import nl.bsoft.rest.restdemo01.repository.PersonRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Transactional
@Service
public class AdresService {
    private static final Logger logger = LoggerFactory.getLogger(AdresService.class);

    private PersonRepository personRepository = null;
    private AdresRepository adresRepository = null;

    @Autowired
    public AdresService(final PersonRepository personRepository, final AdresRepository adresRepository) {
        this.adresRepository = adresRepository;
        this.personRepository = personRepository;
    }

    public Optional<Adres> findById(final Long id) {
        Optional<Adres> adres = null;
        adres = adresRepository.findById(id);

        return adres;
    }

    public List<Adres> getAll() {
        return adresRepository.findAll();
    }

    public Adres create(final Adres adres) throws AdresExists, PersonIdNotAllowed {
        Adres savedAdres = null;

        List<Adres> existingAdres = null;

        if (null == adres.getHuisNummerToevoeging()) {
            existingAdres = adresRepository.findByAddress(adres.getPostCode(), adres.getHuisNummer());
        } else {
            existingAdres = adresRepository.findByFullAddress(adres.getPostCode(), adres.getHuisNummer(), adres.getHuisNummerToevoeging());
        }

        if ((existingAdres != null) && (existingAdres.size() > 0)) {
            throw new AdresExists("Adres already exists with postcode: " +
                    existingAdres.get(0).getPostCode() +
                    " huisnummer: " + existingAdres.get(0).getHuisNummer() +
                    (existingAdres.get(0).getHuisNummerToevoeging() == null ? "" : " huisnummerToevoeging: " + existingAdres.get(0).getHuisNummerToevoeging())
            );
        } else {
            savedAdres = adresRepository.save(adres);

            if (savedAdres != null) {
                for (Person p : adres.getPersonen()) {
                    logger.info("Saving persoon: " + p.getPersonId() + " " + p.getRoepNaam() + " " + p.getTussenVoegsel() + " " + p.getAchterNaam());
                    if (p.getPersonId() == null) {
                        p.setAdres(savedAdres);
                        personRepository.save(p);
                    } else {
                        throw new PersonIdNotAllowed("Persoon id: " + p.getPersonId() + " ingevuld, maar niet toegestaan");
                    }
                }
            }
        }
        return savedAdres;
    }

    public void update(final Long id, final Adres updatedAdres) {
        Adres adresToBeUpdated = null;
        Optional<Adres> result = adresRepository.findById(id);

        if (result.isPresent()) {
            adresToBeUpdated = result.get();
            adresToBeUpdated.setHuisNummer(updatedAdres.getHuisNummer());
            adresToBeUpdated.setHuisNummerToevoeging(updatedAdres.getHuisNummerToevoeging());
            adresToBeUpdated.setPostCode(updatedAdres.getPostCode());
            adresToBeUpdated.setStraatNaam(updatedAdres.getStraatNaam());
            adresToBeUpdated.setTelefoonNummer(updatedAdres.getTelefoonNummer());
            adresToBeUpdated.setWoonPlaats(updatedAdres.getWoonPlaats());
            adresToBeUpdated.setPersonen(updatedAdres.getPersonen());
            Adres savedAdres = adresRepository.save(adresToBeUpdated);

            if (savedAdres != null) {
                for (Person p : updatedAdres.getPersonen()) {
                    logger.info("Saving persoon: " + p.getPersonId() + " " + p.getRoepNaam() + " " + p.getTussenVoegsel() + " " + p.getAchterNaam());
                    p.setAdres(savedAdres);
                    personRepository.save(p);
                }
            }
        } else {
            throw new AdresNotFound("Adres met id: " + updatedAdres.getAdresId() + " niet gevonden");
        }
    }

    public void delete(final Long id) {
        Optional<Adres> adres = findById(id);
        if (adres.isPresent()) {
            adresRepository.delete(adres.get());
        } else {
            logger.error("Adres id: " + id + " not found");
        }
    }

    public List<Adres> getAdresAt(String postCode, int huisNummer) {
        List<Adres> adresList = null;

        adresList = adresRepository.findByAddress(postCode, huisNummer);

        return adresList;
    }

    public List<Adres> getAdresAt(String postCode, int huisNummer, String huisNummerToevoeging) {
        List<Adres> adresList = null;

        adresList = adresRepository.findByFullAddress(postCode, huisNummer, huisNummerToevoeging);

        return adresList;
    }
}
