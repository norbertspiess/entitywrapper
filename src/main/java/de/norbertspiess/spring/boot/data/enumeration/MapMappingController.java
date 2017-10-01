package de.norbertspiess.spring.boot.data.enumeration;

import de.norbertspiess.spring.boot.data.Address;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MapMappingController {

    private RegularAddressRepository repo;

    @Autowired
    public MapMappingController(RegularAddressRepository repository) {
        repo = repository;
    }

    @GetMapping("/map-mapping")
    public Iterable<Address> hello() {
        Address entity = new Address();
        entity.message.put(Locale.ENGLISH, "meh");
        entity.message.put(Locale.GERMAN, "yearh");
        repo.save(entity);
        return repo.findAll();
    }
}
