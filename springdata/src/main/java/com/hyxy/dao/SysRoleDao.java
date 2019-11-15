package com.hyxy.dao;

import com.hyxy.pojo.SysRole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;


public interface SysRoleDao extends JpaRepository<SysRole,Long>,JpaSpecificationExecutor<SysRole>{
   }
