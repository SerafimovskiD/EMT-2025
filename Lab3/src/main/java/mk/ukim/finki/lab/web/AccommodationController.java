package mk.ukim.finki.lab.web;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import mk.ukim.finki.lab.dto.CreateAccommodationDto;
import mk.ukim.finki.lab.model.exceptions.AccommodationException;
import mk.ukim.finki.lab.dto.DisplayAccommodationDto;
import mk.ukim.finki.lab.model.views.AccommodationsPerHostView;
import mk.ukim.finki.lab.service.Application.AccommodationApplicationService;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/accommodation")
@Tag(name = "Accommodation API", description = "Endpoints for managing accommodations")
public class AccommodationController {
    private final AccommodationApplicationService accommodationApplicationService;
    public AccommodationController(AccommodationApplicationService accommodationApplicationService) {
        this.accommodationApplicationService = accommodationApplicationService;
    }

    @Operation(summary = "Get all accommodation", description = "Retrieves a list of all available accommodations.")
    @GetMapping
    public List<DisplayAccommodationDto> findAll(){
        return accommodationApplicationService.findAll();
    }

    @Operation(summary = "Get accommodation by ID", description = "Finds a accommodation by its ID.")
    @GetMapping("{id}")
    public ResponseEntity<DisplayAccommodationDto>findById(@PathVariable Long id){
        return accommodationApplicationService.findById(id).
                map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    @Operation(
            summary = "Add a new accommodation",
            description = "Creates a new product based on the given accommodationDto."
    )

    @PostMapping("/add")
    public ResponseEntity<DisplayAccommodationDto>save(@RequestBody CreateAccommodationDto createAccommodationDto){
        return accommodationApplicationService.save(createAccommodationDto).map(ResponseEntity::ok).
                orElseGet(()->ResponseEntity.notFound().build());
    }

    @PutMapping("/edit/{id}")
    public ResponseEntity<DisplayAccommodationDto>update(@PathVariable Long id, @RequestBody CreateAccommodationDto createAccommodationDto){
        return accommodationApplicationService.update(id,createAccommodationDto).
                map(ResponseEntity::ok).orElseGet(()->ResponseEntity.notFound().build());
    }
    @Operation(summary = "Delete a accommodation", description = "Deletes a accommodation by its ID.")
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> delete (@PathVariable Long id) throws AccommodationException {
        if(accommodationApplicationService.findById(id).isPresent()){
            accommodationApplicationService.deleteById(id);
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.notFound().build();
    }
    @PutMapping("/rent/{id}")
    public ResponseEntity<DisplayAccommodationDto> rent(@PathVariable Long id){
        return accommodationApplicationService.rent(id).map(ResponseEntity::ok).orElseGet(()->ResponseEntity.notFound().build());
    }


    @Operation(summary = "Get number of accommodations per host", description = "Returns the number of accommodations for each host.")
    @GetMapping("/by-host")
    @Transactional
    public List<AccommodationsPerHostView> getAccommodationsPerHost() {
        return accommodationApplicationService.getAccommodationsPerHost();
    }

}
