import java.util.Scanner;

import io.github.cdimascio.dotenv.Dotenv;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;

import com.i2i.sms.controller.AddressController;
import com.i2i.sms.controller.AdminController;
import com.i2i.sms.controller.GradeController;
import com.i2i.sms.controller.RoleController;
import com.i2i.sms.controller.StudentController;


/**
 * <p>
 * This class represents the Main for all controllers managing school-related operations,
 * such as adding students, fetching data, searching for specific students or grades,
 * deleting student records, and handling roles associated with students.
 * </p>
 */
@ComponentScan(basePackages = "com.i2i.sms")
public class Main {
    private static final Logger logger = LoggerFactory.getLogger(Main.class);
    private static Scanner scanner = new Scanner(System.in);
    @Autowired
    private AddressController addressController;
    @Autowired
    private AdminController adminController;
    @Autowired
    private GradeController gradeController;
    @Autowired
    private RoleController roleController;
    @Autowired
    private StudentController studentController;

    /**
     * <p>
     * This class is an interface to interact with the user,
     * allowing them to choose different options like creating or
     * searching, deleting, or exiting the application.
     * </p>
     */
    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(Main.class);
        Main main = context.getBean(Main.class);
        main.startApplication();
    }

    public void startApplication() {
        logger.debug("Application Started");
        boolean isAccess = true;
        while (isAccess) {
            System.out.println("Enter your choice :");
            System.out.println("Enter 1 to Create Student");
            System.out.println("Enter 2 to Get All Data");
            System.out.println("Enter 3 to Search");
            System.out.println("Enter 4 to Delete");
            System.out.println("Enter 5 to Edit Admin Data");
            System.out.println("Enter 6 to Exit -->");
            int choice = scanner.nextInt();
            int pick;
            switch (choice) {
                case 1:
                    studentController.createStudent();
                    break;
                case 2:
                    if (checkAdminPass()) {
                        System.out.println("Enter 1 to Get All Grades...");
                        System.out.println("Enter 2 to Get All Students...");
                        pick = scanner.nextInt();
                        switch (pick) {
                            case 1:
                                gradeController.getAllGrades();
                                break;
                            case 2:
                                studentController.getAllStudents();
                                break;
                            default:
                                System.out.println("\n____Invalid Pick____");
                        }
                    } else {
                        System.out.println("____Invalid AdminName and AdminPassword____");
                    }
                    break;
                case 3:
                    System.out.println("Enter 1 to Get Student");
                    System.out.println("Enter 2 to Get Grade");
                    System.out.println("Enter 3 to Get Role");
                    System.out.println("Enter 4 to Get Address");
                    pick = scanner.nextInt();
                    switch (pick) {
                        case 1:
                            //studentController.getStudentById();
                            break;
                        case 2:
                            //gradeController.getGradeById();
                            break;
                        case 3:
                            //roleController.getRoleById();
                            break;
                        case 4:
                            //addressController.getAddressById();
                            break;
                        default:
                            System.out.println("____Invalid Pick____");
                    }
                    break;
                case 4:
                    if (checkAdminPass()) {
                        System.out.println("Enter 1 to delete student");
                        System.out.println("Enter 2 to delete grade");
                        pick = scanner.nextInt();
                        switch (pick) {
                            case 1:
                                //studentController.deleteStudentById();
                                break;
                            case 2:
                                //gradeController.deleteGradeById();
                                break;
                            default:
                                System.out.println("____Invalid Pick____");
                        }
                    } else {
                        System.out.println("____Invalid AdminName and AdminPassword____");
                    }
                    break;
                case 5:
                    if (checkAdminPass()) {
                        System.out.println("Enter 1 to Add Admin");
                        System.out.println("Enter 2 to Delete Admin");
                        System.out.println("Enter 3 to Get All Admins");
                        pick = scanner.nextInt();
                        switch (pick) {
                            case 1:
                                //adminController.addAdmin();
                                break;
                            case 2:
                                //adminController.deleteAdminById();
                                break;
                            case 3:
                                //adminController.getAllAdmins();
                                break;
                            default:
                                System.out.println("____Invalid Pick____");
                        }
                    } else {
                        System.out.println("____Invalid UserName or Password____");
                    }
                    break;
                case 6:
                    System.out.println("Exiting---->");
                    isAccess = false;
                    logger.debug("Application Ended");
                    break;
                default:
                    System.out.println("\n_____Invalid Choice_____");
            }
        }
    }

    /**
     * <p>
     * checks the entered admin name, pass are right or wrong .
     * </p>
     *
     * @return true if the admin name, pass are right otherwise false.
     */
    public boolean checkAdminPass() {
        logger.debug("checking the Admin Name and Admin Password");
        Dotenv dotenv = Dotenv.load();
        System.out.println("Enter Admin Name :");
        String userName = scanner.next();
        System.out.println("Enter Admin password :");
        String password = scanner.next();
        return (userName.equals(dotenv.get("ADMIN_USER_NAME")) &&
                password.equals(dotenv.get("ADMIN_PASSWORD"))) ||
                adminController.isAdminExists(userName, password);
    }
}
