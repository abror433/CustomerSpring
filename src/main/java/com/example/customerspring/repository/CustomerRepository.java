package com.example.customerspring.repository;
import com.example.customerspring.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
@Repository
public interface CustomerRepository extends JpaRepository<Customer,Integer>{
    boolean existsByPhone(String phone);
}