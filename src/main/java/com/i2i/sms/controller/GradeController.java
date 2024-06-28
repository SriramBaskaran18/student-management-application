package com.i2i.sms.controller;

import java.util.Scanner;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.i2i.sms.exception.StudentManagementException;
import com.i2i.sms.model.Grade;
import com.i2i.sms.service.GradeService;

@Controller
public class GradeController {

    public static Scanner scanner = new Scanner(System.in);
    private final Logger logger = LoggerFactory.getLogger(GradeController.class);
    @Autowired
    public static GradeService gradeService;

    public void getAllGrades() {
        try {
            for (Grade grade : gradeService.getAllGrades()) {
                displayGrade(grade);
            }
        } catch (StudentManagementException e) {
            System.err.println(e.getMessage());
            logger.error(e.getMessage(), e);
        }
    }

    /**
     * <p>
     * retrieves the specific grade with associated students.
     * </p>
     */
    public void getGradeById() {
        try {
            System.out.println("Enter GradeId to Search Specific Grade With Associated Students :");
            int gradeId = scanner.nextInt();
            displayGrade(gradeService.getGradeById(gradeId));
        } catch (StudentManagementException e) {
            System.err.println(e.getMessage());
            logger.error(e.getMessage(), e);
        }
    }

    /**
     * <p>
     * Prompts User to enter the id of the grade to be removed,
     * it will display a success message if the specified grade removed or
     * else it will display a warning message with the corresponding grade id.
     * </p>
     */
    public void deleteGradeById() {
        try {
            System.out.println("Enter Grade ID to Delete Specific Grade :");
            int gradeId = scanner.nextInt();
            boolean isGradeDelete = gradeService.deleteGradeById(gradeId);
            if (isGradeDelete) {
                System.out.println("**Grade Deleted successfully**");
            } else {
                System.out.println("**Grade with this Id:" + gradeId + " not found to delete**");
                logger.warn("Grade with this Id: {} not found to delete", gradeId);
            }
        } catch (StudentManagementException e) {
            System.err.println(e.getMessage());
            logger.error(e.getMessage(), e);
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