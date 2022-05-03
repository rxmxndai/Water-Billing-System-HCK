package com.example.waterbillingsystem.Billing;


import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;


public interface ConsumerRepository extends CrudRepository<Consumer, Integer> {

    Long countById(Integer id);

//    @Query("SELECT p FROM Consumer p WHERE p.name LIKE %?1%")
//    public List<Consumer> findAll(String keyword);



}
