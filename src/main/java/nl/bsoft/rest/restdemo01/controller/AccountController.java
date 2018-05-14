package nl.bsoft.rest.restdemo01.controller;

import nl.bsoft.rest.restdemo01.domain.Account;
import nl.bsoft.rest.restdemo01.domain.AccountList;
import nl.bsoft.rest.restdemo01.service.AccountService;
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
public class AccountController extends ResourceSupport {

    private static final Logger logger = LoggerFactory.getLogger(AccountController.class);

    @Autowired
    AccountService service;

    @RequestMapping(value = "/accounts/{id}", method = RequestMethod.GET)
    public ResponseEntity<Resource<Account>> getAccountById(@PathVariable long id) {
        Optional<Account> account = service.findById(id);
        Resource<Account> resource = null;

        if (!account.isPresent()) {
            logger.error("Account id: " + id + " not found");
            return new ResponseEntity<Resource<Account>>(resource, HttpStatus.NOT_FOUND);
        } else {
            resource = new Resource<Account>(account.get());

            ControllerLinkBuilder linkTo = linkTo(methodOn(this.getClass()).getAccounts());

            resource.add(linkTo.withRel("accounts"));
            logger.info("Account id: " + id + " found");
        }
        return new ResponseEntity<Resource<Account>>(resource, HttpStatus.OK);
    }

    @RequestMapping(value = "/accounts/", method = RequestMethod.GET)
    public ResponseEntity<AccountList> getAccounts() {

        List<Account> accountList = service.getAll();
        AccountList accountResult = new AccountList();

        for (Account account : accountList) {
            Link link = ControllerLinkBuilder.linkTo(AccountController.class)
                    .slash(account.getAccountId())
                    .withSelfRel();

            account.add(link);

            accountResult.getAccounts().add(account);
        }

        //Adding self link accounts collection resource
        Link selfLink = ControllerLinkBuilder
                .linkTo(ControllerLinkBuilder
                        .methodOn(AccountController.class).getAccounts())
                .withSelfRel();
        accountResult.add(selfLink);

        return new ResponseEntity<AccountList>(accountResult, HttpStatus.OK);
    }

    @RequestMapping(value = "/accounts/", method = RequestMethod.POST)
    public ResponseEntity<Object> createAccount(@RequestBody Account account) {

        Account savedAccount = service.create(account);

        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(savedAccount.getAccountId()).toUri();

        logger.info("Account id: " + savedAccount.getAccountId() + " saved");

        return ResponseEntity.created(location).build();
    }

    @RequestMapping(value = "/accounts/{id}", method = RequestMethod.PUT)
    public ResponseEntity<String> updateAccount(@PathVariable Long id, @RequestBody Account account) {
        if (service.findById(id) == null) {
            return new ResponseEntity<String>("Account not found", HttpStatus.NOT_FOUND);
        } else {
            service.update(account);
            return new ResponseEntity<String>("Account Updated Successfully", HttpStatus.OK);
        }
    }

    @RequestMapping(value = "/accounts/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<String> deleteAccount(@PathVariable Long id) {
        if (service.findById(id) == null) {
            return new ResponseEntity<String>("Account not found", HttpStatus.NOT_FOUND);
        } else {
            service.delete(id);
            return new ResponseEntity<String>("Account Deleted Successfully", HttpStatus.OK);
        }
    }


}
