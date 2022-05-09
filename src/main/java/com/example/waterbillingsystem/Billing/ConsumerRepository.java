package com.example.waterbillingsystem.Billing;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;


public interface ConsumerRepository extends JpaRepository<Consumer, Integer> {

    @Query("SELECT u FROM Consumer  u WHERE u.name=?1")
    Consumer findByName(String name);

    @Query("SELECT u FROM Consumer  u WHERE u.name=?1")
    Consumer findByNam(String name);


    Integer countById(Integer id);
}
