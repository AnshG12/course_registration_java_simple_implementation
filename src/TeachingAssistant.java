import java.util.List;
import java.util.Scanner;

public class TeachingAssistant extends Student {
    public TeachingAssistant(String email, String password) {
        super(email, password);
    }

    // Method to assist in grading students
    public void assistGrading(List<Course> courses) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter the course code to assist in grading:");
        String courseCode = scanner.nextLine();

        for (Course course : courses) {
            if (course.getCode().equals(courseCode)) {
                System.out.println("Enter the number (roll no.) of the student to update: ");
                int studentIndex = scanner.nextInt() - 1;
                scanner.nextLine();  // Consume newline

                int i = 0;
                for (Student student : course.getEnrolledStudents()) {
                    if (studentIndex == i) {
                        System.out.println("Enter the grade the student has received:");
                        int grade = scanner.nextInt();
                        scanner.nextLine();
                        student.setGrade(courseCode, grade);
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

    @Override
    public void displayMenu() {
        super.displayMenu();
        System.out.println("10. Assist in Grading");
    }
}