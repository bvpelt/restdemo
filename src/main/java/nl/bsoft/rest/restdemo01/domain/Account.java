package nl.bsoft.rest.restdemo01.domain;

import org.springframework.hateoas.ResourceSupport;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "account")
public class Account extends ResourceSupport implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    //@GeneratedValue(strategy = GenerationType.IDENTITY)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "accountSequence")
    @SequenceGenerator(name = "accountSequence", sequenceName = "account_seq")
    @Column(name = "account_id")
    private Long accountId;

    @Column(name = "account_name")
    private String name;

    @Column(name = "account_email")
    private String email;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getAccountId() {
        return accountId;
    }

    public void setAccountId(Long accountId) {
        this.accountId = accountId;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
