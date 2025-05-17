package mk.ukim.finki.lab.model.views;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "hosts_per_country_view")

public class HostPerCountryView {
    @Id
    private Long countryId;

    @Column(name="host_count")
    private Integer numHosts;

    public Long getCountryId() {
        return countryId;
    }

    public Integer getNumHosts() {
        return numHosts;
    }
}

