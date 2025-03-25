package mk.ukim.finki.lab1.web;

import mk.ukim.finki.lab1.exceptions.CountryException;
import mk.ukim.finki.lab1.model.Country;
import mk.ukim.finki.lab1.service.CountryService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/countries")
public class CountryController {
    private final CountryService countryService;

    public CountryController(CountryService countryService) {
        this.countryService = countryService;
    }

    @GetMapping
    public List<Country> findAll(){
        return countryService.findAll();
    }
    @GetMapping("{id}")
    public ResponseEntity<Country> findById(@PathVariable Long id){
        return countryService.findById(id).map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }
    @PostMapping("/add")
    public ResponseEntity<Country> save(@RequestBody Country country){
        return countryService.save(country).map(ResponseEntity::ok).orElseGet(()->ResponseEntity.notFound().build());
    }
    @PutMapping("/edit/{id}")
    public ResponseEntity<Country>update(@PathVariable Long id,@RequestBody Country country){
        return countryService.update(id,country).map(ResponseEntity::ok).orElseGet(()->ResponseEntity.notFound().build());
    }
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void>delete(@PathVariable Long id) throws CountryException {
        if(countryService.findById(id).isPresent()){
            countryService.deleteById(id);
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.notFound().build();
    }
}
