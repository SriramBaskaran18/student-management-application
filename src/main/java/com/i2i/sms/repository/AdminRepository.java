package com.i2i.sms.repository;

import com.i2i.sms.model.Admin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AdminRepository extends JpaRepository<Admin,Integer> {
    boolean findAdminByAdminNameAndAdminPassword(String name,String password);
}
