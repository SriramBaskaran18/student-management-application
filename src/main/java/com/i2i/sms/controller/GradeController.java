package com.i2i.sms.controller;

import java.util.Optional;
import java.util.Scanner;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.i2i.sms.exception.StudentManagementException;
import com.i2i.sms.model.Grade;
import com.i2i.sms.service.GradeService;

@RestController
@RequestMapping("sms/api/v1.0/grades")
public class GradeController {

    public static Scanner scanner = new Scanner(System.in);
    //private final Logger logger = LoggerFactory.getLogger(GradeController.class);
    @Autowired
    private GradeService gradeService;

    @GetMapping("find-all")
    public void getAllGrades() {
        try {
            for (Grade grade : gradeService.getAllGrades()) {
                displayGrade(grade);
            }
        } catch (StudentManagementException e) {
            System.err.println(e.getMessage());
            //logger.error(e.getMessage(), e);
        }
    }

    /**
     * <p>
     * retrieves the specific grade with associated students.
     * </p>
     */
    @GetMapping("find/{id}")
    public void getGradeById(@PathVariable("id") int gradeId) {
        try {
            //System.out.println("Enter GradeId to Search Specific Grade With Associated Students :");
            //int gradeId = scanner.nextInt();
            Optional<Grade> grade = gradeService.getGradeById(gradeId);
            if (grade.isPresent()) {
                Grade fetchedGrade = grade.get();
                displayGrade(fetchedGrade);
            }
        } catch (StudentManagementException e) {
            System.err.println(e.getMessage());
            //logger.error(e.getMessage(), e);
        }
    }

    /**
     * <p>
     * Prompts User to enter the id of the grade to be removed,
     * it will display a success message if the specified grade removed or
     * else it will display a warning message with the corresponding grade id.
     * </p>
     */
    @DeleteMapping("delete/{id}")
    public void deleteGradeById(@PathVariable("id") int gradeId) {
        try {
//            System.out.println("Enter Grade ID to Delete Specific Grade :");
//            int gradeId = scanner.nextInt();
            boolean isGradeDelete = gradeService.deleteGradeById(gradeId);
            if (isGradeDelete) {
                System.out.println("**Grade Deleted successfully**");
            } else {
                System.out.println("**Grade with this Id:" + gradeId + " not found to delete**");
                //logger.warn("Grade with this Id: {} not found to delete", gradeId);
            }
        } catch (StudentManagementException e) {
            System.err.println(e.getMessage());
//            logger.error(e.getMessage(), e);
        }
    }

    /**
     * <p>
     * Display the grade data.
     * </p>
     *
     * @param grade Grade will have Standard, Section and gradeId.
     */
    public void displayGrade(Grade grade) {
        System.out.println("\n_ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _\n");
        System.out.println(grade);
        System.out.println("\n_ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _\n");
    }
}