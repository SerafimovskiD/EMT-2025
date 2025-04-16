package mk.ukim.finki.lab1.repository;

import mk.ukim.finki.lab1.model.domain.Country;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CountryRepository extends JpaRepository<Country,Long> {

}
