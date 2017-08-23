package de.norbertspiess.spring.boot.entitywrapper.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import de.norbertspiess.spring.boot.entitywrapper.entity.Address;
import de.norbertspiess.spring.boot.entitywrapper.entity.Locale;
import de.norbertspiess.spring.boot.entitywrapper.entity.MinimalAddress;
import de.norbertspiess.spring.boot.entitywrapper.repo.CrudAddressRepository;


@RestController
public class Controller {
	@Autowired
	private CrudAddressRepository repo;

	@GetMapping("/hello")
	public Iterable<Address> hello() {
		Address entity = new Address();
		entity.message.put(Locale.ENGLISH, "meh");
		entity.message.put(Locale.GERMAN, "yearh");
		repo.save(entity);
		return repo.findAll();
	}

	@GetMapping("/minimal")
	public Iterable<MinimalAddress> minimalData() {
		return repo.findAllInMinimalModel();
	}
}

