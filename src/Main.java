import java.util.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        List<Course> course_list = new ArrayList<Course>();
        List<Complaint> complaints = new ArrayList<Complaint>();
        List<Student> EnrolledStudents = new ArrayList<Student>();
        List<Professor> professors = new ArrayList<Professor>();

        while (true) {
            System.out.println("Welcome to the University Course Registration System");
            System.out.println("1. Login as Student\n2. Login as Professor\n3. Login as Administrator\n4. Exit");
            int choice = scanner.nextInt();

                // Student login and menu
            if (choice == 1) {

                Student student = loginAsStudent(scanner, EnrolledStudents);
                if(student != null) {
                    studentMenu(student, course_list, complaints);
                }
            }

                // Professor login and menu

            else if (choice == 2) {
                Professor professor = loginAsProfessor(scanner, professors);
                professorMenu(professor, course_list);
            }

                // Admin login and menu

            else if (choice == 3) {
                Administrator admin = new Administrator("admin@university.com");
                adminMenu(admin, course_list, complaints, professors);
            }
                //EXIT

            else if (choice == 4) {
                System.out.println("Exiting the system. Goodbye!");
                break;
            }
        }
    }

    // LOGIN Function FOR STUDENT

    private static Student loginAsStudent(Scanner scanner, List<Student> EnrolledStudents) {
        while (true) {
            System.out.println("1. Log In\n2. Sign Up");
            int logInChoice = scanner.nextInt();
            scanner.nextLine();
// Login
            if (logInChoice == 1) {
                System.out.println("Enter your email: ");
                String studentEmail = scanner.nextLine();
                System.out.println("Enter your password: ");
                String studentPassword = scanner.nextLine();

                // Check if the student is already enrolled
                for (Student student : EnrolledStudents) {
                    if (student.getEmail().equals(studentEmail) && student.checkPassword(studentPassword)) {
                        System.out.println("Login successful!");
                        return student;  // Return the logged-in student
                    }
                }
                System.out.println("Invalid email or password. Please try again.");
            }

            // Sign-Up
            else if (logInChoice == 2) {
                System.out.println("Enter your email to sign up: ");
                String newStudentEmail = scanner.nextLine();
                boolean exists = false;
                for (Student student : EnrolledStudents) {
                    if (student.getEmail().equals(newStudentEmail)) {
                        exists = true;
                        System.out.println("Try with unregistered email id");
                        break;
                    }
                }
                // Check if the student already exists
                if (!exists) {
                    System.out.println("Sign-up successful! You can now log in.");
                } else {
                    System.out.println("This email is already registered. Try logging in.");
                    return null;
                }
                System.out.println("Create a password: ");
                String newStudentPassword = scanner.nextLine();

                Student newStudent = new Student(newStudentEmail, newStudentPassword);
                EnrolledStudents.add(newStudent);  // Add the new student to the list
                return newStudent;
            } else {
                System.out.println("Invalid choice. Please try again.");
                break;
            }
        }
        return null;

    }


    private static Professor loginAsProfessor(Scanner scanner, List<Professor> registeredProfessors) {
        while (true) {
            System.out.println("1. Log In\n2. Sign Up");
            int logInChoice = scanner.nextInt();
            scanner.nextLine();  // Consume newline

            if (logInChoice == 1) {  // Login
                System.out.println("Enter your email: ");
                String professorEmail = scanner.nextLine();
                System.out.println("Enter your password: ");
                String professorPassword = scanner.nextLine();

                // Check if the professor is already registered
                for (Professor professor : registeredProfessors) {
                    if (professor.getEmail().equals(professorEmail) && professor.checkPassword(professorPassword)) {
                        System.out.println("Login successful!");
                        return professor;  // Return the logged-in professor
                    }
                }
                System.out.println("Invalid email or password. Please try again.");
            } else if (logInChoice == 2) {  // Sign-Up
                System.out.println("Enter your email to sign up: ");
                String newProfessorEmail = scanner.nextLine();
                boolean exists = false;
                for (Professor professor : registeredProfessors) {
                    if (professor.getEmail().equals(newProfessorEmail)) {
                        exists = true;
                        System.out.println("This email is already registered. Try logging in with a different email.");
                        break;
                    }
                }
                // Check if the professor already exists
                if (!exists) {
                    System.out.println("Create a password: ");
                    String newProfessorPassword = scanner.nextLine();

                    // Create a new professor and add to the list
                    Professor newProfessor = new Professor(newProfessorEmail, newProfessorPassword);
                    registeredProfessors.add(newProfessor);  // Add the new professor to the list
                    System.out.println("Sign-up successful! You can now log in.");
                    return newProfessor;  // Return the newly signed-up professor
                }
            } else {
                System.out.println("Invalid choice. Please try again.");
                break;
            }
        }
        return null;
    }


    private static void studentMenu(Student student, List<Course> courses, List<Complaint> complaints) {
        student.displayMenu();
        // Handle student operations
    }

    private static void professorMenu(Professor professor, List<Course> courses) {
        professor.displayMenu();
        // Handle professor operations
    }

    private static void adminMenu(Administrator admin, List<Course> courses, List<Complaint> complaints, List<Professor> professors) {
        admin.displayMenu();
        // Handle admin operations
    }
}
