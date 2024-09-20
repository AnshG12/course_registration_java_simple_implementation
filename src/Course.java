import java.util.List;
import java.util.ArrayList;
public class Course {
    private final String code;
    private final String courseName;
    private Professor professor;
    private List<Course> prerequisites;
    private int credits;
    private List<Student> enrolledStudents;
    private final int semester;
    private List<Integer> slot;
    private String syllabus= null;
    private int EnrollmentLimit=500;
    private static boolean drop=true;

    public Course(String code, String title, int credits ,List<Course> prerequisites,int semester) {
        this.code = code;
        this.courseName = title;
        this.credits = credits;
        this.enrolledStudents = new ArrayList<>();
        this.prerequisites = new ArrayList<>();
        this.semester = semester;
        this.prerequisites=prerequisites;
        if(credits==4){
            this.slot = new ArrayList<>(2);
            this.slot.add(0);
            this.slot.add(0);}
        else {this.slot=new ArrayList<>(1);this.slot.add(0);}
    }
    // Static getter for the drop variable
    public static boolean getDrop() {
        return drop;
    }
    // Static setter to update the drop variable
    public static void setDrop(boolean value) {
        drop = value;
    }
    public List<Integer> getSlot() {
        return slot;
    }
    public int getSlot1(){
        return slot.get(0);
    }
    public int getSlot2(){
        return slot.get(1);
    }
    public void setSlot1(int slot){
        this.slot.set(0, slot);
    }
    public void setSlot2(int slot){
        this.slot.set(1, slot);
    }
    public Professor getProfessor(){
        return this.professor;
    }
    public void setProfessor(Professor professor) {this.professor = professor;}
    public String getCourseName() {
        return courseName;
    }
    public String getCode() {
        return code;
    }

    public int getSemester() {
        return semester;
    }
    public int getCredit() {
        return credits;

    }
    public void setCredits(int credits) {
        this.credits = credits;
    }

    public List<Course> getPrerequisites() {
        return prerequisites;
    }


    public String getSyllabus() {
            return syllabus;
    }
    public void setSyllabus(String syllabus) {
        this.syllabus = syllabus;
    }
    public int getEnrollmentLimit() {
        return EnrollmentLimit;
    }

    public void setEnrollmentLimit(int enrollmentLimit) {
        this.EnrollmentLimit = enrollmentLimit;
    }

    public List<Student> getEnrolledStudents() {
        return enrolledStudents;
    }

    @Override
    public String toString() {
        return this.code + " - " + this.courseName + " (" + this.credits + " credits)";
    }
}
