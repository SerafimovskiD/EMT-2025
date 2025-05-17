package mk.ukim.finki.lab.model.views;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
//@Subselect("SELECT * FROM public.accommodation_per_host")
@Table(name = "accommodations_per_host_view")
public class AccommodationsPerHostView {
    @Id
    private Long hostId;
    @Column
    private Long accommodationCount;


    public Long getHostId() {
        return hostId;
    }
    public Long getAccommodationCount() {
        return accommodationCount;
    }

}
