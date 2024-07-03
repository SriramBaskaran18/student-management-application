package com.i2i.sms.service;

import com.i2i.sms.exception.StudentManagementException;
import com.i2i.sms.model.Grade;

import java.util.List;
import java.util.Optional;

public interface GradeService {
    Grade getGradeIfGradeExists(int standard, String section) throws StudentManagementException;

    Grade addGrade(int std, String section) throws StudentManagementException;

    Optional<Grade> getGradeById(int gradeId) throws StudentManagementException;

    List<Grade> getAllGrades() throws StudentManagementException;

    boolean deleteGradeById(int gradeId) throws StudentManagementException;
}
