package com.i2i.sms.controller;

import java.util.Scanner;

import com.i2i.sms.exception.StudentManagementException;
import com.i2i.sms.models.Grade;
import com.i2i.sms.service.GradeService;

public class GradeController {

    public static Scanner scanner = new Scanner(System.in);
    public static GradeService gradeService = new GradeService();
 
    public void getAllGrades() {
        try {
            for (Grade grade : gradeService.getAllGrades()) {
                displayGrade(grade);
            }
        } catch (StudentManagementException e) {
            System.err.println(e.getMessage());
            e.printStackTrace();
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
            e.printStackTrace();
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
                System.out.println("**Grade with this Id:"+ gradeId +" not found to delete**");
            }
        } catch (StudentManagementException e) {
            System.err.println(e.getMessage());
            e.printStackTrace();
        }
    }
    /**
     * <p>
     * Display the grade data.   
     * </p>
     * @param Grade 
     *           Grade will have Standard, Section and gradeId.
     */
    public void displayGrade(Grade grade) {
        System.out.println("\n_ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _\n");
        System.out.println(grade);
        System.out.println("\n_ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _\n");
    }
}