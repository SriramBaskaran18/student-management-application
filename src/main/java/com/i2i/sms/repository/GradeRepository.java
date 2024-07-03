package com.i2i.sms.repository;

import com.i2i.sms.model.Grade;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
@Repository
public interface GradeRepository extends JpaRepository<Grade,Integer> {
    Grade findByStandardAndSection(int standard,String section);
}
