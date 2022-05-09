package com.example.waterbillingsystem.Billing;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Query;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
public class ConsumerService {

    @Autowired
    private ConsumerRepository repo;

    public List<Consumer> listAll() {
        return (List<Consumer>) repo.findAll();

    }


    public void save(Consumer consumer) {

        BCryptPasswordEncoder encoder=new BCryptPasswordEncoder();
        String encodePassword=encoder.encode(consumer.getPassword());

        consumer.setPassword(encodePassword);
        double x, unit, rate, discount, tax;
        unit = consumer.getUnit();
        rate = consumer.getRate();
        discount = consumer.getDiscount();
        tax = (consumer.getTax() * (unit * rate) ) / 100;

        x = (unit*rate) - discount + tax;

        consumer.setTotal(x);

        repo.save(consumer);

    }


    public Consumer get(String name) throws ConsumerNotFoundException {
        Consumer result = repo.findByNam(name);
        return result;
    }



    public Consumer get(Integer id) throws ConsumerNotFoundException {
        Optional<Consumer> result = repo.findById(id);
        if (result.isPresent()) {
            return result.get();
        }
        throw new ConsumerNotFoundException("Could not find consumer with ID " + id);
    }


    public void delete(Integer id) throws ConsumerNotFoundException {

        Integer count = repo.countById(id);
        if (count == 0 || count == null) {
            throw new ConsumerNotFoundException("Could not find user with ID: " + id);
        }

        repo.deleteById(id);
    }




}


