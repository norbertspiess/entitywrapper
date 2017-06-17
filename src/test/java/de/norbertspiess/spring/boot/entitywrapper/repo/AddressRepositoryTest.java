package de.norbertspiess.spring.boot.entitywrapper.repo;

import de.norbertspiess.spring.boot.entitywrapper.entity.AddressEntity;
import de.norbertspiess.spring.boot.entitywrapper.entity.wrapper.Address;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

@RunWith(SpringRunner.class)
@DataJpaTest
public class AddressRepositoryTest {

    @Autowired
    private AddressRepository repository;

    @Test
    public void findOne() throws Exception {
        AddressEntity address = new AddressEntity();
        address.setStreet("street");
        Long id = repository.save(address).getId();

        Address<AddressEntity> one = repository.findOne(id);
        assertEquals(address.getStreet() + " and stuff", one.getStreet());
        System.out.println(one.getStreet());
    }

    @Test
    public void save() throws Exception {
        AddressEntity entity = new AddressEntity();
        entity.setStreet("foo");
        AddressEntity saved = repository.save(entity);
        assertEquals(entity.getStreet(), saved.getStreet());
        assertNotNull(saved.getId());
    }

}