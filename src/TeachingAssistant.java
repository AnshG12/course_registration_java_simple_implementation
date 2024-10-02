import java.util.List;
import java.util.Scanner;

public class TeachingAssistant extends Student {
    private String assignedCourseCode;
    public TeachingAssistant(String email, String password) {
        super(email, password);
    }

    public void setAssignedCourseCode(String courseCode) {
        this.assignedCourseCode = courseCode;
    }

    public String getAssignedCourseCode() {
        return assignedCourseCode;
    }
    // Method to assist in grading students
    public void assistGrading(List<Course> courses) {
        for (Course course : courses) {
            if (course.getCode().equals(this.assignedCourseCode)) {
                System.out.println("Enter the number (roll no.) of the student to update: ");
                int studentIndex = scanner.nextInt() - 1;
                scanner.nextLine();  // Consume newline

                int i = 0;
                for (Student student : course.getEnrolledStudents()) {
                    if (studentIndex == i) {
                        System.out.println("Enter the grade the student has received:");
                        int grade = scanner.nextInt();
                        scanner.nextLine();
                        student.setGrade(this.assignedCourseCode, grade);
                        System.out.println("Grade updated successfully!");
                        return;
                    }
                    i++;
                }
                System.out.println("Invalid student number.");
                return;
            }
        }
        System.out.println("Course not found.");
    }
//    @Override
//    public TeachingAssistant clone() throws CloneNotSupportedException {
//        return (TeachingAssistant) super.clone();
//    }


    public void Menu() {
        System.out.println("1. View Available Courses\n2. Register for Course\n3. Drop Course\n4. Track Academic Progress\n5. Course Feedback\n6. Assist in Grading.\n7. Submit Complaint\n8. View Complaint Box\n9. Change Password\n10. Logout");
    }
}