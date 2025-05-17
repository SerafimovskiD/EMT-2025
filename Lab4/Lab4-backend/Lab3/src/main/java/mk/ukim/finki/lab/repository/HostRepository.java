package mk.ukim.finki.lab.repository;

import mk.ukim.finki.lab.model.domain.Host;
import mk.ukim.finki.lab.model.projections.HostNameProjection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface HostRepository extends JpaRepository<Host,Long> {
//    @Query("SELECT h.name AS name, h.surname AS surname FROM Host h")
    List<HostNameProjection> findAllBy();
}
