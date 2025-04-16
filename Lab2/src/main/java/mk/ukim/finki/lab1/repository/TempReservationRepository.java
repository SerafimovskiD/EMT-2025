package mk.ukim.finki.lab1.repository;

import mk.ukim.finki.lab1.model.domain.Accommodation;
import mk.ukim.finki.lab1.model.domain.ReservationStatus;
import mk.ukim.finki.lab1.model.domain.TempReservation;
import mk.ukim.finki.lab1.model.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;

public interface TempReservationRepository extends JpaRepository<TempReservation,Long> {
    Optional<TempReservation> findByUserAndStatus(User user, ReservationStatus reservationStatus);
}
