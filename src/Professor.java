import java.util.Collections;
import java.util.List;
import java.util.ArrayList;
class Professor extends User {
    private List<Course> courses;

    public Professor(String email, String password) {
        super(email, password);
        this.courses = new ArrayList<>();
    }

    public void viewEnrolledStudents(Course course) {
        System.out.println("Enrolled students for " + course.getTitle() + ":");
        for (Student student : course.getEnrolledStudents()) {
            System.out.println(student.email);
        }
    }

    public void manageCourse(Course course) {
        // Logic to update course details
    }

    @Override
    public void displayMenu() {
        System.out.println("1. View Enrolled Students\n2. Manage Course\n3. Logout");
    }
}
