package com.hyxy;

import com.hyxy.dao.CustomerDao;
import com.hyxy.dao.LinkManDao;
import com.hyxy.dao.SysRoleDao;
import com.hyxy.dao.SysUserDao;
import com.hyxy.pojo.Customer;

import com.hyxy.pojo.LinkMan;
import com.hyxy.pojo.SysRole;
import com.hyxy.pojo.SysUser;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:applicationContext.xml")
@Rollback(false)
public class Test01 {
    @Autowired
    CustomerDao customerDao;
    @Autowired
    LinkManDao linkManDao;
    @Test
    @Transactional
    public void test01(){
        Specification<Customer> specification=new Specification<Customer>() {
            public Predicate toPredicate(Root<Customer> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
                Predicate custName01 = criteriaBuilder.greaterThan(root.<Long>get("custId"), 2l);
                Predicate custName02 = criteriaBuilder.like(root.<String>get("custName"), "%c%");
                Predicate predicate=criteriaBuilder.and(custName01,custName02);
                return predicate;
            }
        };
        Pageable pageable=new PageRequest(0,5);
        Page<Customer> page = customerDao.findAll(specification, pageable);
        for (Customer c:page.getContent()
             ) {
            System.out.println(c);
        }
    }

    @Test
    @Transactional
    public void test02(){
        Customer c = new Customer();
        c.setCustName("TBD云集中心");
        c.setCustLevel("VIP客户");
        c.setCustSource("网络");
        c.setCustIndustry("商业办公");
        c.setCustAddress("昌平区北七家镇");
        c.setCustPhone("010-84389340");

        LinkMan l = new LinkMan();
        l.setLkmName("TBD联系人");
        l.setLkmGender("male");
        l.setLkmMobile("13811111111");
        l.setLkmPhone("010-34785348");
        l.setLkmEmail("98354834@qq.com");
        l.setLkmPosition("老师");
        l.setLkmMemo("还行吧");

        LinkMan l1 = new LinkMan();
        l1.setLkmName("TBD联系人");
        l1.setLkmGender("male");
        l1.setLkmMobile("13811111111");
        l1.setLkmPhone("010-34785348");
        l1.setLkmEmail("98354834@qq.com");
        l1.setLkmPosition("老师");
        l1.setLkmMemo("还行吧");


        Set<LinkMan> set=new HashSet<LinkMan>();
        set.add(l);
        set.add(l1);
        c.setLinkMans(set);
        l.setCustomer(c);
        l1.setCustomer(c);
        customerDao.save(c);
        linkManDao.save(l);
        linkManDao.save(l1);
    }
    @Test
    @Transactional
    public void findOne(){
        Customer customer = customerDao.findOne(21l);
        Set<LinkMan> linkMans = customer.getLinkMans();
        System.out.println(customer);
    }

    @Autowired
    SysRoleDao sysRoleDao;
    @Autowired
    SysUserDao sysUserDao;
    @Test
    @Transactional
    public void test03(){
        SysUser sysUser=new SysUser();
        SysRole sysRole=new SysRole();
        sysUser.setUserName("aaa");
        sysRole.setRoleName("AAA");
        sysRole.getUsers().add(sysUser);
        sysUser.getRoles().add(sysRole);

        sysUserDao.save(sysUser);
    }
}
