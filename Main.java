import java.util.Scanner;

import com.i2i.sms.controller.AddressController;
import com.i2i.sms.controller.GradeController;
import com.i2i.sms.controller.RoleController;
import com.i2i.sms.controller.StudentController;


/**
 * This class represents the Main for all controllers managing school-related operations,
 * such as adding students, fetching data, searching for specific students or grades,
 * deleting student records, and handling roles associated with students.
 * This is an interface to interact with the user,
 * allowing them to choose different options like creating or fetching data, searching, deleting, or exiting the application.
 * Methods within this class handle user inputs, validate data, and delegate tasks to respective services.
 * It also contains methods for displaying student and grade data.
 */
public class Main {
    private static Scanner scanner = new Scanner(System.in);
    private StudentController studentController = new StudentController();
    private GradeController gradeController = new GradeController();
    private AddressController addressController = new AddressController();
    private RoleController roleController = new RoleController();

    public static void main(String args[]) {
        Main main = new Main();
        main.StartApplication(); 
    }

//TO-DO - COMMENT

    public void StartApplication() {
        boolean isAccess = true;
        while (isAccess) {
            System.out.println("Enter your choice :");
            System.out.println("Enter 1 to Create Student");
            System.out.println("Enter 2 to Get All Data");
            System.out.println("Enter 3 to Search");
            System.out.println("Enter 4 to Delete");
            System.out.println("Enter 5 to Exit -->");
            int choice = scanner.nextInt();
            int pick;
            switch (choice) {
                case 1:
                    studentController.createStudent();
                    break;
                case 2:
                    System.out.println("Enter 1 to Get All Grades...");
                    System.out.println("Enter 2 to Get All Students...");
                    pick = scanner.nextInt();
                    switch(pick) {
                        case 1:
                            gradeController.getAllGrades();
                            break;
                        case 2:
                            studentController.getAllStudents();
                            break;
                        default:
                            System.out.println("\n____Invalid Pick____");
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
                            studentController.getStudentById();
                            break;
                        case 2:
                            gradeController.getGradeById();
                            break;
                        case 3:
                            roleController.getRoleById();
                            break;
                        case 4:
                            addressController.getAddressById();
                            break;
                        default:
                            System.out.println("____Invalid Pick____");
                    }
                    break;
                case 4:
                    System.out.println("Enter 1 to delete student");
                    System.out.println("Enter 2 to delete grade");
                    pick = scanner.nextInt();
                    switch (pick) {
                        case 1:
                            studentController.deleteStudentById();
                            break;
                        case 2:
                            gradeController.deleteGradeById();
                            break;
                        default:
                            System.out.println("____Invalid Pick____");
                    }
                    break;
                case 5:
                    System.out.println("Exiting---->");
                    isAccess = false;
                    break;
                default:
                    System.out.println("\n_____Invalid Choice_____");
            }
        } 
    }
}
