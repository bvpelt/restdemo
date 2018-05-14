package nl.bsoft.rest.restdemo01.service;

import nl.bsoft.rest.restdemo01.domain.Adres;
import nl.bsoft.rest.restdemo01.domain.AdresNotFound;
import nl.bsoft.rest.restdemo01.repository.AdresRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AdresService {
    private static final Logger logger = LoggerFactory.getLogger(AdresService.class);

    @Autowired
    private AdresRepository repository;

    public Optional<Adres> findById(final Long id) {
        Optional<Adres> adres = null;
        adres = repository.findById(id);

        return adres;
    }

    public List<Adres> getAll() {
        return repository.findAll();
    }

    public Adres create(final Adres adres) {
        Adres savedAdres = null;

        List<Adres> existingAdres = null;

        if (null == adres.getHuisNummerToevoeging()) {
            existingAdres = repository.findByAddress(adres.getPostCode(), adres.getHuisNummer());
        } else {
            existingAdres = repository.findByFullAddress(adres.getPostCode(), adres.getHuisNummer(), adres.getHuisNummerToevoeging());
        }

        if ((existingAdres != null) && (existingAdres.size() > 0)) {
            logger.info("Adres already exists with id: " + existingAdres.get(0).getAdresId());
        } else {
            savedAdres = repository.save(adres);
        }
        return savedAdres;
    }

    public void update(final Adres updatedAdres) {
        Adres adresToBeUpdated = null;
        Optional<Adres> result = repository.findById(updatedAdres.getAdresId());

        if (result.isPresent()) {
            adresToBeUpdated = result.get();
            adresToBeUpdated.setHuisNummer(updatedAdres.getHuisNummer());
            adresToBeUpdated.setHuisNummerToevoeging(updatedAdres.getHuisNummerToevoeging());
            adresToBeUpdated.setPostCode(updatedAdres.getPostCode());
            adresToBeUpdated.setStraatNaam(updatedAdres.getStraatNaam());
            adresToBeUpdated.setTelefoonNummer(updatedAdres.getTelefoonNummer());
            adresToBeUpdated.setWoonPlaats(updatedAdres.getWoonPlaats());
            repository.save(adresToBeUpdated);
        } else {
            throw new AdresNotFound("id: " + updatedAdres.getAdresId());
        }
    }

    public void delete(final Long id) {
        Optional<Adres> adres = findById(id);
        if (adres.isPresent()) {
            repository.delete(adres.get());
        } else {
            logger.error("Adres id: " + id + " not found");
        }
    }
}
