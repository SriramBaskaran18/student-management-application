# Student-Management-System :

## Feature of SMS :
- This application is used to manage the school application process from student registration.
- It covers four entities like student, grade, address, and role.
- In the student entity, it covers student details, including the address which the student 
   belongs, the grade to which the student belongs, and the role that is selected by the student.  
- The grade entity covers grade information and the students that are related to it. 
- The Address entity includes the student's city, mobile number, and state of residence.
- The Role object consists of three fixed roles. It lists the students assigned to each role,
   and the roles assigned to students are those that they select during the registration process.

## To Run the Application :

- Install Maven and set Classpath for System Variable.

## To confirm the Maven installation's success :

- open cmd  
- mvn -v 
- This displays the installed version of Maven.

## Launch Command Prompt and execute these commands:

- mvn clean install
- mvn compile
- mvn exec:java -Dexec.mainClass="Main" 