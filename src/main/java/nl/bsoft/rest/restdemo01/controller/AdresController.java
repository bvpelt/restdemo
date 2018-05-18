package nl.bsoft.rest.restdemo01.controller;

import nl.bsoft.rest.restdemo01.domain.Adres;
import nl.bsoft.rest.restdemo01.domain.AdresList;
import nl.bsoft.rest.restdemo01.domain.Person;
import nl.bsoft.rest.restdemo01.domain.PersonList;
import nl.bsoft.rest.restdemo01.service.AdresService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.Resource;
import org.springframework.hateoas.ResourceSupport;
import org.springframework.hateoas.mvc.ControllerLinkBuilder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

@RestController
public class AdresController extends ResourceSupport {

    private static final Logger logger = LoggerFactory.getLogger(AdresController.class);

    @Autowired
    AdresService service;


    @RequestMapping(value = "/adresses/{id}", method = RequestMethod.GET)
    public ResponseEntity<Resource<Adres>> retrieveAdres(@PathVariable long id) {
        Optional<Adres> adres = service.findById(id);
        Resource<Adres> resource = null;

        if (!adres.isPresent()) {
            logger.error("Adres id: " + id + " not found");
            return new ResponseEntity<Resource<Adres>>(resource, HttpStatus.NOT_FOUND);
        } else {
            resource = new Resource<Adres>(adres.get());

            ControllerLinkBuilder linkTo = linkTo(methodOn(this.getClass()).getAdres());

            resource.add(linkTo.withRel("adresses"));
            logger.info("Adres id: " + id + " found");
        }
        return new ResponseEntity<Resource<Adres>>(resource, HttpStatus.OK);
    }

    @RequestMapping(value = "/adresses/", method = RequestMethod.GET)
    public ResponseEntity<AdresList> getAdres() {

        List<Adres> adresList = service.getAll();
        AdresList adresResult = new AdresList();

        for (Adres adres : adresList) {
            Link link = ControllerLinkBuilder.linkTo(methodOn(this.getClass()).getAdres())
                    .slash(adres.getAdresId())
                    .withRel("adresses");

            adres.add(link.withRel("adresses"));

            adresResult.getAdres().add(adres);
        }

        //Adding self link adresses collection resource
        Link selfLink = ControllerLinkBuilder
                .linkTo(ControllerLinkBuilder
                        .methodOn(AdresController.class).getAdres())
                .withSelfRel();
        adresResult.add(selfLink);

        return new ResponseEntity<AdresList>(adresResult, HttpStatus.OK);
    }

    @RequestMapping(value = "/adresses/", method = RequestMethod.POST)
    public ResponseEntity<Object> createAdres(@RequestBody Adres adres) {

        Adres savedAdres = service.create(adres);

        if (null == savedAdres) {
            return new ResponseEntity<Object>("Adres already exists", HttpStatus.NOT_MODIFIED);
        } else {
            URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                    .buildAndExpand(savedAdres.getAdresId()).toUri();

            logger.info("Adres id: " + savedAdres.getAdresId() + " saved");

            return ResponseEntity.created(location).build();
        }
    }

    @RequestMapping(value = "/adresses/{id}", method = RequestMethod.PUT)
    public ResponseEntity<String> updateAdres(@PathVariable Long id, @RequestBody Adres adres) {
        if (service.findById(id) == null) {
            return new ResponseEntity<String>("Adres not found", HttpStatus.NOT_FOUND);
        } else {
            service.update(adres);
            return new ResponseEntity<String>("Adres Updated Successfully", HttpStatus.OK);
        }
    }


    @RequestMapping(value = "/adresses/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<String> deleteAdres(@PathVariable Long id) {
        if (service.findById(id) == null) {
            return new ResponseEntity<String>("Adres not found", HttpStatus.NOT_FOUND);
        } else {
            service.delete(id);
            return new ResponseEntity<String>("Adres Deleted Successfully", HttpStatus.OK);
        }
    }

    @RequestMapping(value = "/adresses/{postCode}/{huisNummer}", method = RequestMethod.GET)
    public ResponseEntity<AdresList> getAdresAt(@PathVariable String postCode, @PathVariable int huisNummer) {

        List<Adres> adresList = service.getAdresAt(postCode, huisNummer);
        AdresList adresResult = new AdresList();

        for (Adres adres : adresList) {
            Link link = ControllerLinkBuilder.linkTo(methodOn(this.getClass()).getAdres())
                    .slash(adres.getAdresId())
                    .withSelfRel();

            adres.add(link);

            adresResult.getAdres().add(adres);
        }

        //Adding self link accounts collection resource
        Link selfLink = ControllerLinkBuilder
                .linkTo(ControllerLinkBuilder.methodOn(AdresController.class).getAdres())
                .withSelfRel();
        adresResult.add(selfLink);

        return new ResponseEntity<AdresList>(adresResult, HttpStatus.OK);
    }

    @RequestMapping(value = "/adresses/{postCode}/{huisNummer}/{huisNummerToevoeging}", method = RequestMethod.GET)
    public ResponseEntity<AdresList> getAdresAt(@PathVariable String postCode, @PathVariable int huisNummer, @PathVariable String huisNummerToevoeging) {

        List<Adres> adresList = service.getAdresAt(postCode, huisNummer, huisNummerToevoeging);
        AdresList adresResult = new AdresList();

        for (Adres adres : adresList) {
            Link link = ControllerLinkBuilder.linkTo(methodOn(this.getClass()).getAdres())
                    .slash(adres.getAdresId())
                    .withSelfRel();

            adres.add(link);

            adresResult.getAdres().add(adres);
        }

        //Adding self link accounts collection resource
        Link selfLink = ControllerLinkBuilder
                .linkTo(ControllerLinkBuilder.methodOn(AdresController.class).getAdres())
                .withSelfRel();
        adresResult.add(selfLink);

        return new ResponseEntity<AdresList>(adresResult, HttpStatus.OK);
    }

}
