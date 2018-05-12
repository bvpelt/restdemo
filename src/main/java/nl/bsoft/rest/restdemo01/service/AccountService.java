package nl.bsoft.rest.restdemo01.service;

import nl.bsoft.rest.restdemo01.domain.Account;
import nl.bsoft.rest.restdemo01.domain.AccountNotFound;
import nl.bsoft.rest.restdemo01.repository.AccountRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
public class AccountService {
    private static final Logger logger = LoggerFactory.getLogger(AccountService.class);

    @Autowired
    private AccountRepository repository;

    public Optional<Account> findById(final Long id) {
        Optional<Account> account = null;
        account = repository.findById(id);

        return account;
    }

    public List<Account> getAll() {

        return repository.findAll();
    }

    public Account create(final Account account) {
        Account savedAccount = repository.save(account);
        return savedAccount;
    }

    public void update(final Account updatedAccount) {
        Account accountToBeUpdated = null;
        Optional<Account> result = repository.findById(updatedAccount.getAccountId());

        if (result.isPresent()) {
            accountToBeUpdated = result.get();
            accountToBeUpdated.setName(updatedAccount.getName());
            accountToBeUpdated.setEmail(updatedAccount.getEmail());
            repository.save(accountToBeUpdated);
        } else {
            throw new AccountNotFound("id: " + updatedAccount.getAccountId());
        }
    }

    public void delete(final Long id) {
        Optional<Account> account = findById(id);
        if (account.isPresent()) {
            repository.delete(account.get());
        } else {
            logger.error("Account id: " + id + " not found");
        }
    }
}
