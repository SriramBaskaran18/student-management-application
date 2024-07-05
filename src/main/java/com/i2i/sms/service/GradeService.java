package com.i2i.sms.service;

import com.i2i.sms.dto.CreateGradeDto;
import com.i2i.sms.dto.GradeDto;
import com.i2i.sms.dto.ResponseGradeDto;
import com.i2i.sms.exception.StudentManagementException;
import com.i2i.sms.mapper.GradeMapper;
import com.i2i.sms.model.Grade;

import java.util.List;
import java.util.Optional;

public interface GradeService {
    Grade getGradeIfGradeExists(CreateGradeDto gradeDto) throws StudentManagementException;

    Grade addGrade(CreateGradeDto gradeDto) throws StudentManagementException;

    GradeDto getGradeById(int gradeId) throws StudentManagementException;

    List<ResponseGradeDto> getAllGrades() throws StudentManagementException;

    boolean deleteGradeById(int gradeId) throws StudentManagementException;
}
