package mk.ukim.finki.lab1.repository;

import mk.ukim.finki.lab1.model.domain.Accommodation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AccommodationRepository extends JpaRepository<Accommodation,Long> {
//    Optional<Accommodation> findByName(String name);
//    Optional<Accommodation> findByCategory(Category category);

}
