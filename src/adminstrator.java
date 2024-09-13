import java.util.Collections;
import java.util.List;
import java.util.ArrayList;
class Administrator extends User {
    private static final String ADMIN_PASSWORD = "admin123";

    public Administrator(String email) {
        super(email, ADMIN_PASSWORD);
    }

    public void manageCourseCatalog(List<Course> courses) {
        // Logic for adding, removing, or viewing courses
    }

    public void assignProfessorToCourse(Professor professor, Course course) {
        course.setProfessor(professor);
        System.out.println("Assigned professor: " + professor.email + " to course: " + course.getTitle());
    }

    public void handleComplaints(List<Complaint> complaints) {
        // Logic to view and update complaint status
    }

    @Override
    public void displayMenu() {
        System.out.println("1. Manage Course Catalog\n2. Manage Student Records\n3. Assign Professors to Courses\n4. Handle Complaints\n5. Logout");
    }
}
