package de.norbertspiess.spring.boot.data.projection;

import de.norbertspiess.spring.boot.data.Address;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;


public interface ProjectionAddressRepository extends CrudRepository<Address, Long> {

	@Query("SELECT address.id AS id, address.street AS street FROM Address address")
	List<MinimalAddress> findAllInMinimalModel();

	List<MinimalAddress> findByStreet(String street);
}
