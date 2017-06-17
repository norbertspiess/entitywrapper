package de.norbertspiess.spring.boot.entitywrapper.repo;

import de.norbertspiess.spring.boot.entitywrapper.entity.Address;
import de.norbertspiess.spring.boot.entitywrapper.entity.wrapper.AddressWrapper;
import org.springframework.data.repository.Repository;

public interface AddressRepository extends Repository<Address, Long> {
    AddressWrapper<Address> findOne(Long id);

    // somewhat ugly that you have to put the entity into the save method, but that's how it is...
    // probably a custom repository method "save(AddressWrapper)" with manual "entityManager.persist(wrapper.getEntity()) would be better
    AddressWrapper<Address> save(Address entity);
}
