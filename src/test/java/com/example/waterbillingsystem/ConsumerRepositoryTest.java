package com.example.waterbillingsystem;

import com.example.waterbillingsystem.Billing.Consumer;
import com.example.waterbillingsystem.Billing.ConsumerRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;

import java.util.Optional;


@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Rollback(false)
public class ConsumerRepositoryTest {

    @Autowired private ConsumerRepository repo;


    @Test
    public void testAddNewConsumer() {

        Consumer user = new Consumer();

        user.setName("Anushriya Aryal");
        user.setContacts("9865321458");
        user.setEmail("anu322@gmail.com");
        user.setPassword("Anushriya Aryal");
        user.setProvince("Mahakali Kshetra");
        user.setUnit(10);
        user.setRate(100);
        user.setDiscount(10);
        user.setTax(13);
        user.setStatus(false);
        // SAVE DETAILS
        Consumer savedConsumer = repo.save(user);
        // DATABASE
        Assertions.assertThat(savedConsumer).isNotNull();
        // checks for duplicate ID
        Assertions.assertThat(savedConsumer.getId()).isGreaterThan(0);
    }


    @Test
    public void testListAllConsumer() {

        Iterable<Consumer> consumers = repo.findAll();

        Assertions.assertThat(consumers).hasSizeGreaterThan(0);

        for (Consumer consumer : consumers) {
            System.out.println(consumer);
        }
    }


    // test method for updating user
    @Test
    public void testUpdateConsumer() {

        Integer userId = 2;
        Optional<Consumer> tempConsumer = repo.findById(userId);
        Consumer consumer = tempConsumer.get();
        consumer.setPassword("Roman Karki");
        repo.save(consumer);

        Consumer updatedConsumer = repo.findById(userId).get();
        Assertions.assertThat(updatedConsumer.getPassword()).isEqualTo("Roman Karki");

    }

    @Test
    public void testGetConsumer() {
        Integer userId = 2;
        Optional<Consumer> optionalConsumer = repo.findById(userId);

        Assertions.assertThat(optionalConsumer).isPresent();
        System.out.println(optionalConsumer.get());
    }


    @Test
    public void testDeleteConsumer() {
        Integer userId = 2;
        repo.deleteById(userId);

        Optional<Consumer> optionalConsumer = repo.findById(userId);
        Assertions.assertThat(optionalConsumer).isNotPresent();
    }

}
