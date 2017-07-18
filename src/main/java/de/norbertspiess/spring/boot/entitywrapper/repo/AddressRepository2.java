package de.norbertspiess.spring.boot.entitywrapper.repo;

import de.norbertspiess.spring.boot.entitywrapper.entity.Address;
import org.springframework.data.repository.CrudRepository;

/**
 * Created by norbert on 18.07.2017.
 */
public interface AddressRepository2 extends CrudRepository<Address, Long> {
}
