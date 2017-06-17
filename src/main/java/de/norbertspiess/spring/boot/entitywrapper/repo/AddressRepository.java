package de.norbertspiess.spring.boot.entitywrapper.repo;

import de.norbertspiess.spring.boot.entitywrapper.entity.AddressEntity;
import de.norbertspiess.spring.boot.entitywrapper.entity.wrapper.Address;
import org.springframework.data.repository.Repository;

public interface AddressRepository extends Repository<AddressEntity, Long> {
    Address<AddressEntity> findOne(Long id);

    AddressEntity save(AddressEntity entity);
}
