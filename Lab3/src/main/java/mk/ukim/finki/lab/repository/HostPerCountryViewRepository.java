package mk.ukim.finki.lab.repository;

import mk.ukim.finki.lab.model.views.HostPerCountryView;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface HostPerCountryViewRepository extends JpaRepository<HostPerCountryView,Long> {
    @Transactional
    @Modifying(clearAutomatically = true)
    @Query(value = "REFRESH MATERIALIZED VIEW hosts_per_country_view", nativeQuery = true)
    void refreshMaterializedView();


}
