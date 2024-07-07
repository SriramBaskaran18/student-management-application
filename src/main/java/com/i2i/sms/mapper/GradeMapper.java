package com.i2i.sms.mapper;

import com.i2i.sms.dto.*;
import com.i2i.sms.model.Grade;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class GradeMapper {
    /**
     * <p>
     * Maps a Grade entity to a ResponseGradeDto object.
     * This method takes a Grade entity as input and returns a ResponseGradeDto object
     * with the corresponding data.
     * </p>
     * @param grade the grade entity to be mapped
     * @return a ResponseGradeDto object with the mapped data
     */
    public ResponseGradeDto entityToResponseDto(Grade grade) {
        return ResponseGradeDto.builder().id(grade.getId()).
                standard(grade.getStandard()).section(grade.getSection()).build();
    }

    /**
     * <p>
     * Maps a CreateGradeDto entity to a Grade object.
     * This method takes a CreateGradeDto entity as input and returns a Grade object
     * with the corresponding data.
     * </p>
     * @param createGradeDto the createGradeDto entity to be mapped
     * @return a Grade object with the mapped data
     */
    public Grade requestDtoToEntity(CreateGradeDto createGradeDto) {
        return Grade.builder().standard(createGradeDto.getStandard()).
                section(createGradeDto.getSection()).build();
    }

    /**
     * <p>
     * Maps a Grade entity with its corresponding students to a GradeDto object.
     * This method takes a Grade entity with its corresponding students as input
     * and returns a GradeDto object with the corresponding data.
     * </p>
     * @param grade the grade entity to be mapped
     * @param students the corresponding students of the grade to be mapped.
     * @return a GradeDto object with the mapped data
     */
    public GradeDto entityToGradeDto(Grade grade, List<StudentDto> students) {
        return GradeDto.builder().id(grade.getId()).
                standard(grade.getStandard()).section(grade.getSection())
                .students(students).build();
    }
}
