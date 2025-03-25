package mk.ukim.finki.lab1.repository;

import mk.ukim.finki.lab1.model.Accommodation;
import mk.ukim.finki.lab1.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface AccommodationRepository extends JpaRepository<Accommodation,Long> {
//    Optional<Accommodation> findByName(String name);
//    Optional<Accommodation> findByCategory(Category category);

}
