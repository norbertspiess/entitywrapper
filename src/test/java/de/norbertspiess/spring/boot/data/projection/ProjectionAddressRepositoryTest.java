package de.norbertspiess.spring.boot.data.projection;

import de.norbertspiess.spring.boot.data.Address;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@DataJpaTest
public class ProjectionAddressRepositoryTest {
    @Autowired
    private ProjectionAddressRepository repo;

    @Test
    public void findByStreet() throws Exception {
        Address entity = new Address();
        entity.street = "street";
        repo.save(entity);

        List<MinimalAddress> addresses = repo.findByStreet("street");

        assertThat(addresses)
                .hasSize(1)
                .extracting(MinimalAddress::getStreet)
                .containsOnly("street");
    }

}