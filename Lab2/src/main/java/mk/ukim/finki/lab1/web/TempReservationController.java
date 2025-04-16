package mk.ukim.finki.lab1.web;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletRequest;
import mk.ukim.finki.lab1.dto.DisplayAccommodationDto;
import mk.ukim.finki.lab1.dto.TempReservationDto;
import mk.ukim.finki.lab1.model.domain.User;
import mk.ukim.finki.lab1.model.exceptions.AccommodationException;
import mk.ukim.finki.lab1.service.Application.TempReservationApplicationService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/reservations")
@Tag(name = "Reservation API", description = "Endpoints for managing reservation lists")
public class TempReservationController {
    private final TempReservationApplicationService tempReservationApplicationService;

    public TempReservationController(TempReservationApplicationService tempReservationApplicationService) {
        this.tempReservationApplicationService = tempReservationApplicationService;
    }
    @Operation(summary = "Get active reservation list", description = "Gets the current user's active reservation list")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Reservation list retrieved successfully"),
            @ApiResponse(responseCode = "404", description = "Reservation list not found")
    })

    @GetMapping
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<TempReservationDto> getActiveReservationList(HttpServletRequest req) {
        String username = req.getRemoteUser();
        return tempReservationApplicationService.getActiveTempReservation(username)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
    @Operation(summary = "List accommodations in reservation", description = "Lists all accommodations in a specific reservation list")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Accommodations listed successfully"),
            @ApiResponse(responseCode = "404", description = "Reservation list not found")
    })
    @GetMapping("/{id}/accommodations")
//    @PreAuthorize("hasRole('USER')")
    public List<DisplayAccommodationDto> listAccommodationsInReservation(@PathVariable Long id) {
        return this.tempReservationApplicationService.listAllAccommodationInTempReservation(id);
    }


    @Operation(summary = "Add accommodation to reservation", description = "Adds an accommodation to the user's active reservation list")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Accommodation added successfully"),
            @ApiResponse(responseCode = "400", description = "Accommodation already reserved"),
            @ApiResponse(responseCode = "404", description = "Accommodation or reservation list not found")
    })
    @PostMapping("/add-accommodation/{id}")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<TempReservationDto> addAccommodationToReservation(
            @PathVariable Long id,
            Authentication authentication
    ) {
        try {
            User user = (User) authentication.getPrincipal();
            return tempReservationApplicationService.addAccommodationToTempReservation(user.getUsername(), id)
                    .map(ResponseEntity::ok)
                    .orElse(ResponseEntity.notFound().build());
        } catch (RuntimeException exception) {
            return ResponseEntity.badRequest().build();
        } catch (AccommodationException e) {
            throw new RuntimeException(e);
        }
    }
    @Operation(summary = "Confirm reservation", description = "Confirms all accommodations in the reservation list as rented")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Reservation confirmed successfully"),
            @ApiResponse(responseCode = "404", description = "Reservation list not found")
    })
    @PostMapping("/{id}/confirm")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<TempReservationDto> confirmReservation(@PathVariable Long id) {
        return this.tempReservationApplicationService.confirmTempReservation(id)
                .map(reservationList -> ResponseEntity.ok(reservationList))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }
    @Operation(summary = "Clear reservation list", description = "Removes all accommodations from a reservation list")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Reservation list cleared successfully"),
            @ApiResponse(responseCode = "404", description = "Reservation list not found")
    })
    @PostMapping("/{id}/clear")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<Void> clearReservationList(@PathVariable Long id) {
        this.tempReservationApplicationService.clearTempReservationList(id);
        return ResponseEntity.ok().build();
    }
}
