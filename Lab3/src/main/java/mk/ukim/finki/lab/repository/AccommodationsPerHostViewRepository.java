package mk.ukim.finki.lab.repository;

import mk.ukim.finki.lab.model.views.AccommodationsPerHostView;
//import mk.ukim.finki.lab1.model.views.AccommodationsPerHostView;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface AccommodationsPerHostViewRepository extends JpaRepository<AccommodationsPerHostView,Long> {
    @Transactional
    @Modifying(clearAutomatically = true)
    @Query(value = "REFRESH MATERIALIZED VIEW accommodations_per_host_view", nativeQuery = true)
    void refreshMaterializedView();

}
