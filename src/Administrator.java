import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Scanner;

public class Administrator extends User implements View{
    private static final String ADMIN_PASSWORD = "admin123";

    public Administrator(String email) {
        super(email, ADMIN_PASSWORD);
    }

    @Override
    public void view_courses(List<Course> courses_list) {
        Scanner scanner = new Scanner(System.in);
        if (courses_list.isEmpty()) {
            System.out.println("NO COURSE FOUND");
        } else {
            int i = 1;
            for (Course course : courses_list) {
                System.out.println(i + ". " + course.getCourseName());
                i++;
            }
            while (true) {
                System.out.println("Would you like to view a detailed course description? Please select Y or N.");
                char c = scanner.next().charAt(0);
                if (c == 'Y') {
                    System.out.println("Enter course number: ");
                    int course_no = scanner.nextInt();
                    scanner.nextLine();  // Consume newline
                    if (course_no > 0 && course_no <= courses_list.size()) {
                        Course course = courses_list.get(course_no - 1);
                        System.out.println("Course Name: " + course.getCourseName());
                        System.out.println("Course Code: " + course.getCode());
                        System.out.println("Course Credit: " + course.getCredit());

                    } else {
                        System.out.println("Invalid course number. Please try again.");
                    }
                } else {
                    break;
                }
            }
        }
    }

    @Override
    public void displayMenu() {
        System.out.println("1. Manage Course Catalog\n2. Manage Student Records\n3. Assign Professors to Courses\n4. Status for drop courses\n5. Handle Complaints\n6. Logout");
    }
    public void course_catalog(Scanner scanner,List<Course> courses_list, List<Complaint> complaints_list, List<Professor> professors_list,List<Student>student_list) {
        while (true) {
            System.out.println("WELCOME TO COURSE SECTION\n");
            System.out.println("1. View Course Catalog\n2. Add Course\n3. Delete Course\n4. Exit");

            int choice_course_feature = scanner.nextInt();
            scanner.nextLine();  // Consume newline after number input
// View Course Catalog
            if (choice_course_feature == 1) {
                view_courses(courses_list);
            }
// Add Course
            else if (choice_course_feature == 2) {
                System.out.println("Enter Course Name: ");
                String courseName = scanner.nextLine();
                boolean check=true;
                for(Course c: courses_list) {
                    if(c.getCourseName().equals(courseName)) {
                        System.out.println("Enter an unique course name!!!!");
                        check=false;
                    }
                }
                if(!check) {
                    continue;
                }
                System.out.println("Enter Course Code: ");
                String courseCode = scanner.nextLine();
                for(Course c: courses_list) {
                    if(c.getCode().equals(courseCode)) {
                        System.out.println("Enter an unique course code!!!!");
                        check=false;
                    }
                }
                if(!check) {
                    continue;
                }
                System.out.println("Enter Course Credit (e.g., 2 or 4): ");
                int courseCredit = scanner.nextInt();
                scanner.nextLine();
                System.out.println("Enter Semester in which the Course is being offered");
                int sem = scanner.nextInt();
                scanner.nextLine();  // Consume newline


                // Handle prerequisites as an ArrayList
                List<Course> prerequisitesList = new ArrayList<>();
                System.out.println("Enter the prerequisites for this course (enter 'done' to finish): ");


                while (true) {
                    System.out.println("Enter course code for prerequisite: ");
                    String prerequisiteCourse = scanner.nextLine();

                    if (prerequisiteCourse.equalsIgnoreCase("done")) {
                        break;  // Exit the loop when done entering prerequisites
                    }

                    // Check if the prerequisite course is valid (exists in the courses_list)
                    boolean isValidCourse = false;
                    for (Course course : courses_list) {
                        if (course.getCode().equals(prerequisiteCourse)) {
                            prerequisitesList.add(course);
                            isValidCourse=true;
                            break;
                        }
                    }

                    if (!isValidCourse) {

                        System.out.println("No course has been offered with code: " + prerequisiteCourse + ". Enter a valid course.");
                    }
                }

                // Create a new Course object and add it to the course list
                Course newCourse = new Course(courseCode, courseName, courseCredit, prerequisitesList,sem);
                courses_list.add(newCourse);
                System.out.println("Course added successfully!");
            }
// Delete Course
            else if (choice_course_feature == 3) {
                while(true){
                    System.out.println("Would you like to delete course? Please select Y or N.");
                    char c = scanner.next().charAt(0);
                    if (c == 'Y'){
                        if (courses_list.isEmpty()) {
                            System.out.println("NO COURSE FOUND");
                        } else {
                            int i = 1;
                            for (Course course : courses_list) {
                                System.out.println(i + ". " + course.getCourseName());
                                i++;
                            }
                            System.out.println("Enter the number of the course you want to delete: ");
                            int course_no = scanner.nextInt();
                            scanner.nextLine();  // Consume newline
                            if (course_no > 0 && course_no <= courses_list.size()) {
                                courses_list.remove(course_no - 1);
                                System.out.println("Course deleted successfully!");
                            } else {
                                System.out.println("Invalid course number. Please try again.");
                            }
                        }
                    }
                    else{
                        break;
                    }

                }

            }
// Exit
            else if (choice_course_feature == 4) {
                System.out.println("Exiting Course Section...");
                break;  // Exit the loop
            } else {
                System.out.println("Invalid choice. Please try again.");
            }
        }

    }
    public void manage_student(Scanner scanner,List<Course> courses_list, List<Complaint> complaints_list, List<Professor> professors_list,List<Student>student_list){
        while (true) {
            System.out.println("1. View All Students\n2. Update Student Record\n3. Exit");
            int c = scanner.nextInt();
            scanner.nextLine();  // Consume newline

            if (c == 1) {
                // View All Students
                if (student_list.isEmpty()) {
                    System.out.println("No students are currently enrolled.");
                } else {
                    int i = 1;
                    for (Student student : student_list) {
                        System.out.println(i + ". " + student.getEmail());
                        i++;
                    }
                }

            }
            // Update Student Record
            else if (c == 2) {
                System.out.println("Enter the number(roll no.) of the student to update: ");
                int studentIndex = scanner.nextInt() - 1;
                scanner.nextLine();  // Consume newline

                if (studentIndex < 0 || studentIndex >= student_list.size()) {
                    System.out.println("Invalid selection.");
                    continue;
                }

                Student selectedStudent = student_list.get(studentIndex);

                System.out.println("1. Update Grades\n2. Update Personal Information");
                int updateChoice = scanner.nextInt();
                scanner.nextLine();  // Consume newline

                if (updateChoice == 1) {
                    // Update Grades
                    System.out.println("Enter the course code: ");
                    String courseCode = scanner.nextLine();
                    System.out.println("Enter the new grade: ");
                    int grade = scanner.nextInt();
                    scanner.nextLine();
                    selectedStudent.setGrade(courseCode, grade);
                    System.out.println("Grade updated successfully!");

                } else if (updateChoice == 2) {
                    // Update Personal Information
                    System.out.println("1. Update Email\n2. Update Semester");
                    int personalInfoChoice = scanner.nextInt();
                    scanner.nextLine();  // Consume newline

                    if (personalInfoChoice == 1) {
                        System.out.println("Enter new email: ");
                        String newEmail = scanner.nextLine();
                        selectedStudent.setEmail(newEmail);
                        System.out.println("Email updated successfully!");
                    }
                    else if (personalInfoChoice == 2) {
                        System.out.println("Checking if student has passed all the courses in this semester");
                        if(selectedStudent.check_if_eligible_for_sem_upg()){
                            selectedStudent.setSemester();
                        }
                    } else {
                        System.out.println("Invalid choice.");
                    }
                }
                else {
                    System.out.println("Invalid choice.");
                }
            }
            else if (c == 3) {
                // Exit
                break;
            }
            else {
                System.out.println("Invalid choice. Try again.");
            }
        }
    }
    public void prof_allotment(Scanner scanner,List<Course> courses_list, List<Complaint> complaints_list, List<Professor> professors_list,List<Student>student_list){
        System.out.println("Enter the course code:");
        String code = scanner.nextLine();

        System.out.println("Enter Professor email id: ");
        String professorName = scanner.nextLine();
boolean check2=false;
        for (Course course : courses_list){
            //System.out.println(code);
            if(Objects.equals(course.getCode(), code)){
                check2=true;
                while(true){
                    boolean check = false;
                    for (Professor prof : professors_list) {
                        if (prof.getEmail().equals(professorName)) {
                            course.setProfessor(prof);
                            prof.setAssigned_courseCode(course.getCode());
                            check=true;
                            System.out.println("Professor assigned to the course "+course.getCourseName());
                            break;
                        }
                    }
                    if (!check) {
                        System.out.println("No professor exist with email id " + professorName + ". Enter a valid professor email id.");
                        break;
                    }
                    break;
                }
            }
        }
        if(!check2) System.out.println("No course with this code exist...");
    }
}
