package com.example.waterbillingsystem.Billing;


import org.springframework.data.repository.CrudRepository;

public interface ConsumerRepository extends CrudRepository<Consumer, Integer> {

    public Long countById(Integer id);

}
