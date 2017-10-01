package de.norbertspiess.spring.boot.data.enumeration;

import de.norbertspiess.spring.boot.data.Address;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RegularAddressRepository extends CrudRepository<Address, Long> {
}
