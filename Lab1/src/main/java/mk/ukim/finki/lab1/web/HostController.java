package mk.ukim.finki.lab1.web;

import mk.ukim.finki.lab1.exceptions.HostException;
import mk.ukim.finki.lab1.model.Host;
import mk.ukim.finki.lab1.model.dto.HostDto;
import mk.ukim.finki.lab1.service.HostService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/hosts")
public class HostController {
    private final HostService hostService;

    public HostController(HostService hostService) {
        this.hostService = hostService;
    }
    @GetMapping
    List<Host> findAll(){
        return hostService.findAll();
    }
    @GetMapping("{id}")
    public ResponseEntity<Host>findById(@PathVariable Long id){
        return hostService.findById(id).map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }
    @PostMapping("/add")
    public ResponseEntity<Host> save(@RequestBody HostDto hostDto){
        return hostService.save(hostDto).map(ResponseEntity::ok).orElseGet(()->ResponseEntity.notFound().build());
    }
    @PutMapping("/edit/{id}")
    public ResponseEntity<Host> update(@PathVariable Long id,@RequestBody HostDto hostDto){
        return hostService.update(id,hostDto).map(ResponseEntity::ok).orElseGet(()->ResponseEntity.notFound().build());
    }
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void>delete(@PathVariable Long id) throws HostException {
        if(hostService.findById(id).isPresent()){
            hostService.deleteById(id);
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.notFound().build();
    }

}
