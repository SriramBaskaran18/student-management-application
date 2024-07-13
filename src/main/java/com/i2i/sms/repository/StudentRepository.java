package com.i2i.sms.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.i2i.sms.model.Student;

@Repository
public interface StudentRepository extends JpaRepository<Student,UUID> {
}
