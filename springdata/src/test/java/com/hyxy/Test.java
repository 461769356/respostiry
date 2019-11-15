package com.hyxy;

import com.hyxy.dao.CustomerDao;
import com.hyxy.pojo.Customer;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:applicationContext.xml")
@Rollback(false)
public class Test {
    @Autowired
    CustomerDao customerDao;

    @org.junit.Test
    public void save(){
        Customer customer=new Customer();
        customer.setCustName("mmm");
        customerDao.save(customer);
    }
    @org.junit.Test
    public void update() {
        Customer customer = new Customer();
        customer.setCustId(5l);
        customer.setCustName("eee");
        customerDao.save(customer);
    }
    @org.junit.Test
    public void delete() {
        Customer customer = new Customer();
        customer.setCustId(7l);
        customerDao.delete(customer);
    }
    @org.junit.Test
    public void findOne() {
        Customer customer = customerDao.findOne(1l);
        System.out.println(customer);
    }
    @org.junit.Test
    public void jpqlselectAll() {
        Sort sort=new Sort(Sort.Direction.DESC,"custId");
        List<Customer> customers = customerDao.findAll(sort);
        for (Customer c:customers
              ) {
            System.out.println(c);
        }
    }
    @org.junit.Test
    public void jpqlselectByAddress() {
        List<Customer> customers = customerDao.findAllByCustAddress("111");
        for (Customer c:customers
                ) {
            System.out.println(c);
        }
    }
    @org.junit.Test
    public void jpqlselectBySql() {
        List<Customer> customers = customerDao.findAllBySql();
        for (Customer c:customers
                ) {
            System.out.println(c);
        }
    }
    @org.junit.Test
    public void findByCustomerName() {
        List<Customer> customers = customerDao.findAllByCustAddress("111");
        for (Customer c:customers
                ) {
            System.out.println(c);
        }
    }
    @org.junit.Test
    public void findByAddress() {
        List<Customer> customers = customerDao.findAllByCustAddress("111");
        for (Customer c:customers
                ) {
            System.out.println(c);
        }
    }
    @org.junit.Test
    public void findByAddressAndId() {
        List<Customer> customers = customerDao.findAllByCustAddressAndCustId("111",1l);
        for (Customer c:customers
                ) {
            System.out.println(c);
        }
    }
    @org.junit.Test
    public void findByIdLessThan() {
        List<Customer> customers = customerDao.findAllByCustIdLessThan(5l);
        for (Customer c:customers
                ) {
            System.out.println(c);
        }
    }
    @org.junit.Test
    public void findByPage() {
        Pageable pageable=new  PageRequest(0,3,Sort.Direction.DESC,"custId");
        Page<Customer> page = customerDao.findAll(pageable);
        for (Customer c:page.getContent()
                ) {
            System.out.println(c);
        }
    }
    @org.junit.Test
    public void findByAddressOrderById() {
        List<Customer> customers = customerDao.findAllByCustAddressOrderByCustIdDesc("111");
        for (Customer c:customers
                ) {
            System.out.println(c);
        }
    }
}
