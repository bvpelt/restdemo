package nl.bsoft.rest.restdemo01.service;

import nl.bsoft.rest.restdemo01.domain.Account;
import nl.bsoft.rest.restdemo01.repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AccountService {

    @Autowired
    private AccountRepository repository;

    public Account findById(final Long id) {
        Account account = null;
        Optional<Account> result = repository.findById(id);
        if (result.isPresent()) {
            account = result.get();
        }
        return account;
    }

    public List<Account> getAll() {
        return repository.findAll();
    }

    public void create(final Account account) {
        repository.save(account);
    }

    public void update(final Account updatedAccount) {
        Account accountToBeUpdated = null;
        Optional<Account> result = repository.findById(updatedAccount.getId());

        if (result.isPresent()) {
            accountToBeUpdated = result.get();
            accountToBeUpdated.setName(updatedAccount.getName());
            accountToBeUpdated.setEmail(updatedAccount.getEmail());
            repository.save(accountToBeUpdated);
        } else {
            // not found
        }
    }

    public void delete(final Long id) {
        Account account = findById(id);
        if (account != null) {
            repository.delete(account);
        }
    }
}
