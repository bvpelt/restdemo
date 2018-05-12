package nl.bsoft.rest.restdemo01.domain;

import org.springframework.hateoas.ResourceSupport;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class AccountList extends ResourceSupport implements Serializable {

    private static final long serialVersionUID = 4L;

    private List<Account> accounts = new ArrayList<Account>();

    public List<Account> getAccounts() {
        return accounts;
    }

    public void setAccounts(List<Account> accounts) {
        this.accounts = accounts;
    }

}
