package mk.ukim.finki.lab.web;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import mk.ukim.finki.lab.dto.CreateHostDto;
import mk.ukim.finki.lab.dto.DisplayHostDto;
import mk.ukim.finki.lab.model.exceptions.HostException;
import mk.ukim.finki.lab.model.projections.HostNameProjection;
import mk.ukim.finki.lab.model.views.HostPerCountryView;
import mk.ukim.finki.lab.service.Application.HostApplicationService;
import mk.ukim.finki.lab.service.Domain.HostService;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;


import java.util.List;

@RestController
@RequestMapping("/api/host")
@Tag(name = "Host API", description = "Endpoints for managing hosts")
public class HostController {
    private final HostApplicationService hostApplicationService;
    private final HostService hostService;
    public HostController(HostApplicationService hostApplicationService, HostService hostService) {
        this.hostApplicationService = hostApplicationService;
        this.hostService = hostService;
    }

//    @Operation(summary = "Get host count by country", description = "Returns the number of hosts grouped by country using a materialized view.")
//    @GetMapping("/by-country")
//    public ResponseEntity<List<HostCountryStatsDto>> getHostCountByCountry() {
//        return ResponseEntity.ok(hostApplicationService.getHostsByCountry());
//    }

    @Operation(summary = "Get number of hosts per country", description = "Returns the number of accommodations for each host.")
    @GetMapping("/by-country")
    @Transactional
    public List<HostPerCountryView> getHostPerCountry() {
        return hostApplicationService.getHostPerCountry();
    }


    @Operation(summary = "Get names of all hosts", description = "Returns the name and surname of each host using a projection.")
    @GetMapping("/names")
    public ResponseEntity<List<HostNameProjection>> getAllHostNames() {
        List<HostNameProjection> names = hostService.getAllHostName();
        return ResponseEntity.ok(names);
    }



    @Operation(summary = "Get all hosts", description = "Retrieves a list of all available hosts.")
    @GetMapping
    List<DisplayHostDto> findAll(){
        return hostApplicationService.findAll();
    }
    @Operation(summary = "Get host by ID", description = "Finds a host by its ID.")
    @GetMapping("{id}")
    public ResponseEntity<DisplayHostDto>findById(@PathVariable Long id){
        return hostApplicationService.findById(id).map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }
    @Operation(
            summary = "Add a new host",
            description = "Creates a new product based on the given hostDto."
    )
    @PostMapping("/add")
    public ResponseEntity<DisplayHostDto> save(@RequestBody CreateHostDto createHostDto){
        return hostApplicationService.save(createHostDto).map(ResponseEntity::ok).orElseGet(()->ResponseEntity.notFound().build());
    }
    @Operation(
            summary = "Update an existing host", description = "Updates a product by ID using hostDto."
    )

    @PutMapping("/edit/{id}")
    public ResponseEntity<DisplayHostDto> update(@PathVariable Long id,@RequestBody CreateHostDto createHostDto){
        return hostApplicationService.update(id,createHostDto).map(ResponseEntity::ok).orElseGet(()->ResponseEntity.notFound().build());
    }
    @Operation(summary = "Delete a host", description = "Deletes a host by its ID.")
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void>delete(@PathVariable Long id) throws HostException {
        if(hostApplicationService.findById(id).isPresent()){
            hostApplicationService.deleteById(id);
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.notFound().build();
    }

}
