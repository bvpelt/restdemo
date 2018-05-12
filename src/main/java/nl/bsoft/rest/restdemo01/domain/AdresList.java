package nl.bsoft.rest.restdemo01.domain;

import org.springframework.hateoas.ResourceSupport;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class AdresList extends ResourceSupport implements Serializable {
    private static final long serialVersionUID = 7L;

    private List<Adres> adres = new ArrayList<Adres>();

    public List<Adres> getAdres() {
        return adres;
    }

    public void setAdres(List<Adres> adres) {
        this.adres = adres;
    }
}
