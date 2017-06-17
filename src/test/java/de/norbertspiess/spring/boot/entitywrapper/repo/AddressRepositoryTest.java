package de.norbertspiess.spring.boot.entitywrapper.repo;

import de.norbertspiess.spring.boot.entitywrapper.entity.Address;
import de.norbertspiess.spring.boot.entitywrapper.entity.wrapper.AddressWrapper;
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
        Address address = new Address();
        address.street = "street";
        Long id = repository.save(address).getId();

        AddressWrapper<Address> one = repository.findOne(id);

        assertEquals(address.street + " and stuff", one.getStreet());
    }

    @Test
    public void save() throws Exception {
        AddressWrapper input = new AddressWrapper();
        String street = "myStreet";
        input.setStreet(street);

        // somewhat ugly that you have to put the entity into the save method, but that's how it is...
        // probably a custom repository method "save(AddressWrapper)" with manual "entityManager.persist(wrapper.getEntity()) would be better
        AddressWrapper saved = repository.save(input.getEntity());

        // wrapper adds "fancy" in the setter and the getter adds " and stuff"
        assertEquals("fancy " + street + " and stuff", saved.getStreet());
        assertNotNull(saved.getId());
    }

}