import java.util.Collections;
import java.util.List;
import java.util.ArrayList;
class Course {
    private String code;
    private String title;
    private Professor professor;
    private List<Course> prerequisites;
    private int credits;
    private List<Student> enrolledStudents;

    public Course(String code, String title, int credits) {
        this.code = code;
        this.title = title;
        this.credits = credits;
        this.enrolledStudents = new ArrayList<>();
        this.prerequisites = new ArrayList<>();
    }

    public String getTitle() {
        return title;
    }

    public List<Course> getPrerequisites() {
        return prerequisites;
    }

    public void setProfessor(Professor professor) {
        this.professor = professor;
    }

    public List<Student> getEnrolledStudents() {
        return enrolledStudents;
    }

    @Override
    public String toString() {
        return code + " - " + title + " (" + credits + " credits)";
    }
}
