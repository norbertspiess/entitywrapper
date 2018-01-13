package de.norbertspiess.spring.boot.data.projection;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.inject.Inject;
import java.util.List;


@RestController
public class Controller {
    private ProjectionAddressRepository repo;

    @Inject
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

