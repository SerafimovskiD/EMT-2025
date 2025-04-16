package mk.ukim.finki.lab1.web;

import io.swagger.v3.oas.annotations.tags.Tag;
import mk.ukim.finki.lab1.dto.CreateCountryDto;
import mk.ukim.finki.lab1.dto.DisplayCountryDto;
import mk.ukim.finki.lab1.model.exceptions.CountryException;
import mk.ukim.finki.lab1.service.Application.CountryApplicationService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import io.swagger.v3.oas.annotations.Operation;

import java.util.List;

@RestController
@RequestMapping("/api/country")
@Tag(name = "Country API", description = "Endpoints for managing countries")
public class CountryController {
    private final CountryApplicationService countryApplicationService;

    public CountryController(CountryApplicationService countryApplicationService) {
        this.countryApplicationService = countryApplicationService;
    }

    @Operation(summary = "Get all countries", description = "Retrieves a list of all available countries.")
    @GetMapping
    public List<DisplayCountryDto> findAll(){
        return countryApplicationService.findAll();
    }

    @Operation(summary = "Get country by ID", description = "Finds a country by its ID.")
    @GetMapping("{id}")
    public ResponseEntity<DisplayCountryDto> findById(@PathVariable Long id){
        return countryApplicationService.findById(id).map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }
    @Operation(
            summary = "Add a new country",
            description = "Creates a new product based on the given CountryDto."
    )

    @PostMapping("/add")
    public ResponseEntity<DisplayCountryDto> save(@RequestBody CreateCountryDto createCountryDto){
        return countryApplicationService.save(createCountryDto).map(ResponseEntity::ok).orElseGet(()->ResponseEntity.notFound().build());
    }
    @Operation(
            summary = "Update an existing country", description = "Updates a product by ID using countryDto."
    )
    @PutMapping("/edit/{id}")
    public ResponseEntity<DisplayCountryDto>update(@PathVariable Long id,@RequestBody CreateCountryDto createCountryDto){
        return countryApplicationService.update(id,createCountryDto).map(ResponseEntity::ok).orElseGet(()->ResponseEntity.notFound().build());
    }
    @Operation(summary = "Delete a country", description = "Deletes a country by its ID.")
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void>delete(@PathVariable Long id) throws CountryException {
        if(countryApplicationService.findById(id).isPresent()){
            countryApplicationService.deleteById(id);
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.notFound().build();
    }
}
