package com.bridgelabz.bookstoreapp.repository;

import com.bridgelabz.bookstoreapp.model.OrderData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Map;
@Repository
public interface OrderRepository extends JpaRepository<OrderData, Long> {
    //getting data using SQL
}
