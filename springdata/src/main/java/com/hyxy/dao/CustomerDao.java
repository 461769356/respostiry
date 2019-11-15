package com.hyxy.dao;

import com.hyxy.pojo.Customer;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Service;

import java.util.List;


public interface CustomerDao extends JpaRepository<Customer,Long>,JpaSpecificationExecutor<Customer>{
//    开启原生sql使用nativeQuery
    @Query(value = "select * from cst_customer",nativeQuery = true)
    List<Customer> findAllBySql();

    List<Customer> findAll(Sort sort);

    List<Customer> findAllByCustAddress(String address);

    List<Customer> findAllByCustAddressAndCustId(String address,Long id);

    List<Customer> findAllByCustIdLessThan(Long id);

    List<Customer> findAllByCustAddressOrderByCustIdDesc(String address);
}
