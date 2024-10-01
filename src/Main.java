import java.util.*;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static class InvalidLoginException extends Exception {
        public InvalidLoginException(String message) {
            super(message);
        }
    }
    public static void main(String[] args) throws CourseFullException {
//        Scanner scanner = new Scanner(System.in);
//        List<Course> course_list = new ArrayList<Course>();
//        List<Complaint> complaints = new ArrayList<Complaint>();
//        List<Student> studentList = new ArrayList<Student>();
//        List<Professor> professorList = new ArrayList<Professor>();
//        List<Administrator> adminList = new ArrayList<Administrator>();
//        List<List<Course>> Schedule= new ArrayList<>();
//        while (true) {
//            System.out.println("Welcome to the University Course Registration System");
//            System.out.println("1. Login as Student\n2. Login as Professor\n3. Login as Administrator\n4. Exit");
//            int choice = scanner.nextInt();
//            scanner.nextLine();
//// Student login and menu
//            if (choice == 1) {
//                Student student = loginAsStudent(scanner, studentList);
//                if(student != null) {
//                    studentMenu(student, course_list, complaints);
//                }
//            }
//// Professor login and menu
//            else if (choice == 2) {
//                Professor professor = loginAsProfessor(scanner, professorList);
//                if(professor != null) {
//                    professorMenu(professor, course_list);
//                }
//            }
//// Admin login and menu
//            else if (choice == 3) {
//                Administrator admin= loginAsAdministrator(scanner, adminList);
//                adminMenu(admin, course_list, complaints, professorList,studentList,Schedule);
//            }
////EXIT
//            else if (choice == 4) {
//                System.out.println("Exiting the system. Goodbye!");
//                break;
//            }
//            else{
//                System.out.println("Please enter a valid choice");
//            }
//        }

            Scanner scanner = new Scanner(System.in);
            List<Course> course_list = new ArrayList<>();
            List<Complaint> complaints = new ArrayList<>();
            List<Student> studentList = new ArrayList<>();
            List<Professor> professorList = new ArrayList<>();
            List<Administrator> adminList = new ArrayList<>();
            List<List<Course>> Schedule = new ArrayList<>();
            while (true) {
                System.out.println("Welcome to the University Course Registration System");
                System.out.println("1. Login as Student\n2. Login as Professor\n3. Login as Administrator\n4. Exit");
                int choice = scanner.nextInt();
                scanner.nextLine();
                try {
                    if (choice == 1) {
                        Student student = loginAsStudent(scanner, studentList);
                        studentMenu(student, course_list, complaints);

                    } else if (choice == 2) {
                        Professor professor = loginAsProfessor(scanner, professorList);
                        if (professor != null) {
                            professorMenu(professor, course_list);
                        }
                    } else if (choice == 3) {
                        Administrator admin = loginAsAdministrator(scanner, adminList);
                        adminMenu(admin, course_list, complaints, professorList, studentList, Schedule);
                    } else if (choice == 4) {
                        System.out.println("Exiting the system. Goodbye!");
                        break;
                    } else {
                        System.out.println("Please enter a valid choice");
                    }
                } catch (InvalidLoginException e) {
                    System.out.println(e.getMessage());
                } catch (DropDeadlinePassedException e) {
                    throw new RuntimeException(e);
                }
            }
    }
// LOGIN Function FOR STUDENT
private static Student loginAsStudent(Scanner scanner, List<Student> EnrolledStudents) throws InvalidLoginException {
    while (true) {
        System.out.println("1. Log In\n2. Sign Up");
        int logInChoice = scanner.nextInt();
        scanner.nextLine(); // Consume newline

        if (logInChoice == 1) {
            // Log in logic
            System.out.println("Enter your email: ");
            String studentEmail = scanner.nextLine();
            System.out.println("Enter your password: ");
            String studentPassword = scanner.nextLine();

            for (Student student : EnrolledStudents) {
                if (student.getEmail().equals(studentEmail) && student.checkPassword(studentPassword)) {
                    System.out.println("Login successful!");
                    return student;
                }
            }

            throw new InvalidLoginException("Invalid email or password. Please try again.");

        } else if (logInChoice == 2) {
            // Sign up logic
            System.out.println("Enter your email to sign up: ");
            String newStudentEmail = scanner.nextLine();

            boolean exists = false;
            for (Student student : EnrolledStudents) {
                if (student.getEmail().equals(newStudentEmail)) {
                    exists = true;
                    break; // Email already exists
                }
            }

            if (exists) {
                System.out.println("This email is already registered. Try logging in.");
            } else {
                System.out.println("Create a password: ");
                String newStudentPassword = scanner.nextLine();
                Student newStudent = new Student(newStudentEmail, newStudentPassword);
                EnrolledStudents.add(newStudent);
                System.out.println("Sign-up successful! You can now log in.");
                return newStudent;
            }

        } else {
            // Handle invalid choices
            System.out.println("Invalid choice. Please enter 1 to log in or 2 to sign up.");
        }
    }
}

// LOGIN Function FOR Professor
private static Professor loginAsProfessor(Scanner scanner, List<Professor> registeredProfessors) throws InvalidLoginException {
    while (true) {
        System.out.println("1. Log In\n2. Sign Up");
        int logInChoice = scanner.nextInt();
        scanner.nextLine();
        if (logInChoice == 1) {
            System.out.println("Enter your email: ");
            String professorEmail = scanner.nextLine();
            System.out.println("Enter your password: ");
            String professorPassword = scanner.nextLine();
            for (Professor professor : registeredProfessors) {
                if (professor.getEmail().equals(professorEmail) && professor.checkPassword(professorPassword)) {
                    System.out.println("Login successful!");
                    return professor;
                }
            }
            throw new InvalidLoginException("Invalid email or password. Please try again.");
        } else if (logInChoice == 2) {
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
            if (!exists) {
                System.out.println("Create a password: ");
                String newProfessorPassword = scanner.nextLine();
                Professor newProfessor = new Professor(newProfessorEmail, newProfessorPassword);
                registeredProfessors.add(newProfessor);
                System.out.println("Sign-up successful! You can now log in.");
                return newProfessor;
            }
        } else {
            System.out.println("Invalid choice. Please try again.");
            break;
        }
    }
    return null;
}
// LOGIN Function FOR ADMIN
private static Administrator loginAsAdministrator(Scanner scanner, List<Administrator> registeredAdministrators) throws InvalidLoginException {
    System.out.println("Enter your email: ");
    String adminEmail = scanner.nextLine();
    System.out.println("Enter your password: ");
    String adminPassword = scanner.nextLine();

    // Loop through the list of registered administrators
    for (Administrator admin : registeredAdministrators) {
        if (admin.getEmail().equals(adminEmail)) {
            // Check password for the found administrator
            if (admin.checkPassword(adminPassword)) {
                System.out.println("Login successful!");
                return admin;
            } else {
                throw new InvalidLoginException("Invalid password. Please try again.");
            }
        }

    }
    Administrator admins = new Administrator(adminEmail);
    if (admins.checkPassword(adminPassword)) {
        registeredAdministrators.add(admins);
        System.out.println("Login successful!");
        // Return the logged-in admin
        return admins;
    }
    else{
        throw new InvalidLoginException("Invalid password. Please try again.");
    }
    // If the administrator doesn't exist, you can choose what to do:
    // Either prompt the user to register or throw an exception.

}


    private static void studentMenu(Student student, List<Course> courses, List<Complaint> complaints) throws CourseFullException, DropDeadlinePassedException {
        Scanner scanner = new Scanner(System.in);
        boolean run=true;
        while (run) {
            student.displayMenu();
            System.out.println("Enter your choice:");
            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline left-over
            switch (choice) {
                case 1:
                    System.out.println("Viewing Available Courses...");
// Implement the method to view available courses
                    student.view_courses(courses);
                    break;
                case 2:
                    System.out.println("Registration Page..");
// Implement the method to register for a course
                    student.register(courses);
                    break;
                case 3:
                    System.out.println("Dropping a Course...");
// Implement the method to drop a course
                    student.drop_course(courses);
                    break;
                case 4:
                    System.out.println("Tracking Academic Progress...");
// Implement the method to track academic progress
                    student.Academic_Progress();
                    break;
                case 5:
                    System.out.println("adding course feedback...");
// Implement the method to add course feedback
                    System.out.println("Enter course code for feedback: ");
                    String courseCode = scanner.nextLine();

                    System.out.println("Would you like to provide a rating (1-5) or a comment? (Enter 'rating' or 'comment')");
                    String feedbackType = scanner.nextLine();

                    if (feedbackType.equalsIgnoreCase("rating")) {
                        System.out.println("Enter your rating (1-5): ");
                        int rating = scanner.nextInt();
                        student.submitFeedback(courseCode, rating);

                    } else if (feedbackType.equalsIgnoreCase("comment")) {
                        System.out.println("Enter your comment: ");
                        scanner.nextLine();  // consume the newline character
                        String comment = scanner.nextLine();
                        student.submitFeedback(courseCode, comment);

                    }break;

                case 6:
                    System.out.println("Submitting a Complaint...");
// Implement the method to submit a complaint
                    student.Submit_complaint(complaints,courses);
                    break;
                case 7:
                    System.out.println("Viewing Complaint Box...");
// Implement the method to view complaints
                    student.view_complaint_status();
                    break;
                case 8:
                    System.out.println("Changing Password...\nEnter the new password below: ");
// Implement the method to change password
                    student.setPassword(scanner.nextLine());
                    break;
                case 9:
                    System.out.println("Logging Out...");
                    run = false;
                    break;
                default:
                    System.out.println("Invalid choice. Please select a valid number.");
                    break;
            }
        }
        System.out.println("You have successfully logged out. Have a good day!");
    }
    private static void professorMenu(Professor professor, List<Course> courses) {
        while (true) {
            professor.displayMenu();
            Scanner scanner = new Scanner(System.in);
            int choice = scanner.nextInt();
            scanner.nextLine();
            if (choice == 1) {
                professor.viewEnrolledStudents(courses);
            }
            else if (choice == 2) {
                professor.manageCourse(courses);
            }
            else if(choice==3){
                professor.grading(courses);
            }
            else if(choice==4){
                System.out.println("Logging Out...");break;
        }else
            System.out.println("Invalid choice. Please select a valid number.");
    }
    }
    private static void adminMenu(Administrator admin, List<Course> courses_list, List<Complaint> complaints_list, List<Professor> professors_list,List<Student>student_list,List<List<Course>> Schedule) {
        // Handle admin operations
        while(true){
        admin.displayMenu();
        Scanner scanner = new Scanner(System.in);
        int choice = scanner.nextInt();
            scanner.nextLine();
        if (choice == 1) {
            admin.course_catalog(scanner,courses_list, complaints_list,  professors_list,student_list);
        }
        else if (choice == 2) {
            admin.manage_student(scanner,courses_list, complaints_list,  professors_list,student_list);
        }
        else if (choice == 3) {
            admin.prof_allotment(scanner,courses_list, complaints_list,  professors_list,student_list);
        }
        else if (choice == 4) {
            System.out.println("current status for drop course");
            if(Course.getDrop()) System.out.println("window is open");
            else System.out.println("window is closed");
            System.out.println("want to change the status. Press Y/N");
            char c = scanner.next().charAt(0);
            if(c=='Y'||c=='y'){
                Course.setDrop(!Course.getDrop());
                System.out.println("Drop timeLine is updated.");
            }
        }
        else if (choice == 5) {
            for(Complaint complaint : complaints_list) {
                if(complaint.getDescription()!=null){
                    System.out.println("Compliant:-\n"+complaint.getDescription()+"\nIS RESOLVED NOW");
                    complaint.setMessage(complaint.getDescription()+".Resolved!");
                    complaint.setStatus("Resolved");
                }
                else{
                    complaint.getCourse().getProfessor().Clash_resolver(Schedule,complaint);
                }
                complaints_list.clear();
                }
            }
        else if(choice == 6) {
            break;
        }
        else System.out.println("Invalid choice. Please select a valid number.");
      }
    }
}
