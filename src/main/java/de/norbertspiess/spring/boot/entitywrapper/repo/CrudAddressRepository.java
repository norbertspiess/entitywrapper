package de.norbertspiess.spring.boot.entitywrapper.repo;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import de.norbertspiess.spring.boot.entitywrapper.entity.Address;
import de.norbertspiess.spring.boot.entitywrapper.entity.MinimalAddress;


public interface CrudAddressRepository extends CrudRepository<Address, Long> {

	@Query("SELECT address.id AS id, address.street AS street FROM Address address")
	List<MinimalAddress> findAllInMinimalModel();
}
