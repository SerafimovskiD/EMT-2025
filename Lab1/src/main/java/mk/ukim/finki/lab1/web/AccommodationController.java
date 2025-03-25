package mk.ukim.finki.lab1.web;

import mk.ukim.finki.lab1.exceptions.AccommodationException;
import mk.ukim.finki.lab1.model.Accommodation;
import mk.ukim.finki.lab1.model.Category;
import mk.ukim.finki.lab1.model.dto.AccommodationDto;
import mk.ukim.finki.lab1.service.AccommodationService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/accommodation")
public class AccommodationController {
    private final AccommodationService accommodationService;

    public AccommodationController(AccommodationService accommodationService) {
        this.accommodationService = accommodationService;
    }


    @GetMapping
    public List<Accommodation> findAll(){
        return accommodationService.findAll();
    }
    @GetMapping("{id}")
    public ResponseEntity<Accommodation>findById(@PathVariable Long id){
        return accommodationService.findById(id).
                map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }
    @PostMapping("/add")
    public ResponseEntity<Accommodation>save(@RequestBody AccommodationDto accommodationDto){
        return accommodationService.save(accommodationDto).map(ResponseEntity::ok).
                orElseGet(()->ResponseEntity.notFound().build());
    }
    @PutMapping("/edit/{id}")
    public ResponseEntity<Accommodation>update(@PathVariable Long id, @RequestBody AccommodationDto accommodationDto){
        return accommodationService.update(id,accommodationDto).
                map(ResponseEntity::ok).orElseGet(()->ResponseEntity.notFound().build());
    }
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> delete (@PathVariable Long id) throws AccommodationException {
        if(accommodationService.findById(id).isPresent()){
            accommodationService.deleteById(id);
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.notFound().build();
    }
    @PutMapping("/rent/{id}")
    public ResponseEntity<Accommodation> rent(@PathVariable Long id){
        return accommodationService.rent(id).map(ResponseEntity::ok).orElseGet(()->ResponseEntity.notFound().build());
    }
//    @PostMapping("/search-name")
//    public ResponseEntity<Accommodation> filterbyName(@RequestParam String name){
//        return accommodationService.findByName(name).map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
//    }
//
//    @PostMapping("/search-category")
//    public ResponseEntity<Accommodation> filterbyCategory(@RequestParam Category category){
//        return accommodationService.findByCategory(category).map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
//    }
}
