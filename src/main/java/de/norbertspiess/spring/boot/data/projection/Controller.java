package de.norbertspiess.spring.boot.data.projection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RestController
public class Controller {
    private ProjectionAddressRepository repo;

    @Autowired
    public Controller(ProjectionAddressRepository repo) {
        this.repo = repo;
    }

    @GetMapping("/minimal")
    public List<MinimalAddress> minimalData() {
        return repo.findAllInMinimalModel();
    }

    @GetMapping("/minimalByStreet")
    public List<MinimalAddress> findByStreet() {
        return repo.findByStreet("");
    }
}

