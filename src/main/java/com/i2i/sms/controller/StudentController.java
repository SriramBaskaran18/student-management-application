package com.i2i.sms.controller;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Scanner;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.i2i.sms.common.RoleEnum;
import com.i2i.sms.exception.StudentManagementException;
import com.i2i.sms.model.Address;
import com.i2i.sms.model.Role;
import com.i2i.sms.model.Student;
import com.i2i.sms.service.RoleService;
import com.i2i.sms.service.StudentService;
import com.i2i.sms.utils.DateUtils;
import com.i2i.sms.utils.StringValidationUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

/**
 * <p>
 * Methods within this class handle user inputs, validate data,
 * and delegate tasks to respective services.
 * It also contains methods for displaying student data.
 * </p>
 */
@Controller
public class StudentController {
    public static Scanner scanner = new Scanner(System.in);
    private final Logger logger = LoggerFactory.getLogger(StudentController.class);
    @Autowired
    public StudentService studentService;
    @Autowired
    public RoleService roleService;

    /**
     * <p>
     * Gather information from the user and validate like Name contains only Alphabets,
     * D.O.B in the yyyy-MM-dd format, standard, section and create a student.
     * </p>
     */
    public void createStudent() {
        try {
            String name;
            while (true) {
                System.out.println("Enter Your Name : ");
                name = scanner.next();
                if (!StringValidationUtil.isValidString(name)) {
                    System.out.println("Numbers are not allowed, Entered value is in invalid format");
                    continue;
                }
                break;
            }
            String dob;
            while (true) {
                System.out.println("Enter Your D.O.B Like yyyy-MM-dd : ");
                dob = scanner.next();
                if (!DateUtils.isValidDate(dob)) {
                    System.out.println("Entered D.O.B is in invalid format");
                    continue;
                }
                break;
            }
            System.out.println("Enter the Standard : ");
            int std = scanner.nextInt();
            String section;
            while (true) {
                System.out.println("Enter the Section : ");
                section = scanner.next();
                if (!StringValidationUtil.isValidString(section)) {
                    System.out.println("Numbers are not allowed, Entered value is in invalid format");
                    continue;
                }
                break;
            }
            System.out.println("Enter Your Address \n");
            System.out.println("Enter Your Door NO. :");
            String doorNumber = scanner.next();
            scanner.nextLine();
            System.out.println("Enter Your Street :");
            String street = scanner.nextLine();
            System.out.println("Enter Your City :");
            String city = scanner.nextLine();
            System.out.println("Enter Your State :");
            String state = scanner.nextLine();
            System.out.println("Enter Your Zipcode :");
            int zipcode = scanner.nextInt();
            scanner.nextLine();
            System.out.println("Enter Your Mobile Number :");
            String mobileNumber = scanner.nextLine();
            Address address = new Address(doorNumber, street, city, state, zipcode, mobileNumber);
            Set<Role> roles = addRole();
            Student insertedStudent = studentService.addStudent(name, dob, std, section, address, roles);
            if (null != insertedStudent) {
                displayStudent(insertedStudent);
                System.out.println(insertedStudent.getGrade());
                System.out.println("**Student Data Added to the Database**");
            } else {
                System.out.println("Unable to add Student");
                logger.warn("Unable to add Student");
            }
        } catch (StudentManagementException e) {
            System.err.println(e.getMessage());
            logger.error(e.getMessage(), e);
        }
    }

    /**
     * <p>
     * Allow Student to select roles from a predefined list and adds the selected roles to a set.
     * The student can select multiple roles and the method ensures that
     * each role is picked only once.
     * The method continues to prompt for role selection until the user chooses to exit.
     * </p>
     *
     * @return a set of roles selected by the user.
     */
    public Set<Role> addRole() {
        List<Integer> picks = new ArrayList<>();
        Set<Role> pickedRoles = new HashSet<>();
        try {
            while (true) {
                System.out.println("Choose The Role You Want");
                System.out.println("Pick 1 For Class Representative");
                System.out.println("Pick 2 For Board In-charge");
                System.out.println("Pick 3 For Cabinet In-charge");
                int pick = scanner.nextInt();
                if (!picks.contains(pick)) {
                    switch (pick) {
                        case 1:
                            pickedRoles.add(roleService.getRoleIfRoleExists(RoleEnum.CLASS_REPRESENTATIVE.getRole()));
                            break;
                        case 2:
                            pickedRoles.add(roleService.getRoleIfRoleExists(RoleEnum.BOARD_INCHARGE.getRole()));
                            break;
                        case 3:
                            pickedRoles.add(roleService.getRoleIfRoleExists(RoleEnum.CABINET_INCHARGE.getRole()));
                            break;
                        default:
                            System.out.println("\n____Invalid Pick____");
                    }
                    picks.add(pick);
                } else {
                    System.out.println("You Already Picked That Role");
                    logger.warn("You Already Picked That Role");
                }
                System.out.println("1--> Continue with another role "
                        + "\n press any number and Enter--> Exit....");
                int option = scanner.nextInt();
                if (option == 1) {
                    continue;
                }
                break;
            }
        } catch (StudentManagementException e) {
            System.err.println(e.getMessage());
            logger.error(e.getMessage(), e);
        }
        return pickedRoles;
    }

    /**
     * <p>
     * Retrieves All the student details and displays the student information to the user.
     * </p>
     */
    public void getAllStudents() {
        try {
            for (Student student : studentService.getAllStudents()) {
                displayStudent(student);
            }
        } catch (StudentManagementException e) {
            System.err.println(e.getMessage());
            logger.error(e.getMessage(), e);
        }
    }

    /**
     * <p>
     * Retrieves the corresponding student details and displays the
     * student information to the user.
     * </p>
     */
    public void getStudentById() {
        try {
            System.out.println("Enter StudentId to Search Specific Student : ");
            int searchId = scanner.nextInt();
            Student student = studentService.getStudentById(searchId);
            if (student != null) {
                displayStudent(student);
                System.out.println(student.getGrade() + "\n");
                System.out.println(student.getAddress() + "\n");
            } else {
                System.out.println("_ _ _ _ _ _ _ _ _ _ _ _ _");
                System.out.println("\n**No Student Exists**");
                System.out.println("_ _ _ _ _ _ _ _ _ _ _ _ _");
                logger.warn("No Student Exists");
            }
        } catch (StudentManagementException e) {
            System.err.println(e.getMessage());
            logger.error(e.getMessage(), e);
        }
    }

    /**
     * <p>
     * Prompts User to enter the id of the student to be removed,
     * it will display a success message if the specified student removed or
     * else it will display a warning message with the corresponding student id.
     * </p>
     */
    public void deleteStudentById() {
        try {
            System.out.println("Enter Student ID to Delete Specific Student :");
            int studentId = scanner.nextInt();
            boolean isStudentDelete = studentService.deleteStudentById(studentId);
            if (isStudentDelete) {
                System.out.println("**Student Deleted successfully**");
            } else {
                System.out.println("**Student Id:" + studentId + " not found to delete**");
                logger.warn("Student Id: {} not found to delete", studentId);
            }
        } catch (StudentManagementException e) {
            System.err.println(e.getMessage());
            logger.error(e.getMessage(), e);
        }
    }

    /**
     * <p>
     * Display the student data.
     * </p>
     *
     * @param student Student will have Name, D.O.B in the yyyy-MM-dd format, Age, and Grade.
     */
    public void displayStudent(Student student) {
        System.out.println("\n_ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _"
                + " _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _\n");
        System.out.println(student);
        System.out.println("\n_ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _"
                + " _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _\n");
    }

}