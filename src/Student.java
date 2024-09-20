import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Student extends User implements View{
    private int semester=1;
    private float cgpa;
    private final List<Course> registeredCourses;// we used final because no new instances or object would be made for them
    private final List<Integer> grades_registeredCourses;
    private final List<Complaint> registeredComplaints;




    public Student(String email, String password) {
        super(email, password);
        this.registeredCourses = new ArrayList<>();
        this.grades_registeredCourses=new ArrayList<>();
        this.registeredComplaints   = new ArrayList<>();

    }
    public float getCgpa(){
        return cgpa;
    }
    // Grade Management
    public void setGrade(String courseCode, int grade) {
        for(Course c : registeredCourses) {
            if(c.getCode().equals(courseCode)) {
                this.grades_registeredCourses.add(registeredCourses.indexOf(c), grade);
            }
        }
    }
    public Boolean check_if_eligible_for_sem_upg(){
        int count=0;
        for(Course c : registeredCourses) {
            if(c.getSemester()==this.semester) count++;
            if(c.getSemester()==this.semester&&grades_registeredCourses.get(registeredCourses.indexOf(c))<5){
                return false;
            }
        }
        return count > 0;
    }



    public void setSemester() {
        if(semester<=7){
        this.semester++;
            System.out.println("Semester updated successfully!");}
        else System.out.println("Student has Passed out of the college");
    }
    public void register(List<Course> courses) {
        int Tcredit=0;
        for(Course c : registeredCourses) {
            if(c.getSemester()==this.semester) Tcredit+=c.getCredit();
        }
        System.out.println("Enter the course code for registration:-");
        String courseCode=scanner.nextLine();
        for(Course c : courses){
            if(c.getCode().equals(courseCode)) {
                if(Tcredit+c.getCredit()<=20&&c.getEnrolledStudents().size()+1<=c.getEnrollmentLimit()){
                    int pre_count=0;
                   for(Course c1 : c.getPrerequisites()) {
                       for(Course c2: this.registeredCourses){
                           if(c1.getCode().equals(c2.getCode())&& grades_registeredCourses.get(registeredCourses.indexOf(c2))>4 ) {pre_count++;}
                       }
                   }
                   if(pre_count==c.getPrerequisites().size()){
                    this.registeredCourses.add(c);
                    this.grades_registeredCourses.add(0);
                    c.getEnrolledStudents().add(this);
                       System.out.println("Course Successfully Registered!");
                   }
                   else{
                       System.out.println("Prerequisites are not completed first complete them..");

                   }
                }
                else{
                    System.out.println("Course can't be registered...");
                }
                return;
            }
        }

    }
    public void drop_course(List<Course> courses){
        if(Course.getDrop()){
            String courseCode=scanner.nextLine();
            for(Course c : courses){
                if(c.getCode().equals(courseCode)) {
                    grades_registeredCourses.remove(registeredCourses.indexOf(c));
                    registeredCourses.remove(c);
                    System.out.println("Course is dropped...");

                    return;
                }
            }
        }
        else{
            System.out.println("Course can't be dropped...");
        }
    }
    public void Academic_Progress(){
        int cg=0;
        int tcredit=0;
        for(Course c : registeredCourses) {
            if(c.getSemester()==this.semester-1) {

                cg+=c.getCredit()*grades_registeredCourses.get(registeredCourses.indexOf(c));
                tcredit+=c.getCredit();
            }
        }
        this.cgpa = (float) cg / tcredit;
        if(cgpa==0) System.out.println("First semester is not completed yet.");
        else System.out.printf("CGPA: %.2f\n", cgpa);
        cg=0;
        for(Course c : registeredCourses) {
            if(c.getSemester()==this.semester) {
                if(grades_registeredCourses.get(registeredCourses.indexOf(c))==0){
                    System.out.println("Wait for the SGPA until the grades for all courses are finalized.");return;
                }
                cg+=c.getCredit()*grades_registeredCourses.get(registeredCourses.indexOf(c));
                tcredit+=c.getCredit();
            }
            //  private String studentName;
            float sgpa = (float) cg / tcredit;

            System.out.printf("SGPA: %.2f\n", sgpa);
            return;
        }
    }
    public void Submit_complaint(List<Complaint> complaints,List<Course>courses){
        System.out.println("1. General Complaint\n2. Complaint regarding clash");
        int choice = scanner.nextInt();
        scanner.nextLine();
        if(choice==1){
            System.out.println("Write down the complaint below:-");
            String des=scanner.nextLine();
            Complaint complaint=new Complaint(des);
            complaints.add(complaint);
            System.out.println("Complaint successfully submitted");
            registeredComplaints.add(complaint);

        }
        else if(choice==2){
            System.out.println("Write down the course code of the clashed course");
            String code=scanner.nextLine();
            System.out.println("Enter 1 if lecture 1 is clashing or 2 if lecture 2 is clashing(if 4credit course)");
            int slot = scanner.nextInt();
            scanner.nextLine();
            Complaint complaint=new Complaint(code,courses,slot);
            System.out.println("Complaint successfully submitted");
            registeredComplaints.add(complaint);


        }
    }
    public void view_complaint_status(){
        if(registeredComplaints.isEmpty()){
            System.out.println("No complaints found");return;
        }
        for(Complaint c : registeredComplaints) {
            System.out.println("Complaint:-\n"+c.getDescription());
            System.out.println("Status:-\n"+c.getStatus());
            System.out.println("Message:-\n"+c.getMessage());
            if(Objects.equals(c.getStatus(), "Resolved")){
                System.out.println("Now this complaint is getting removed from your complaint section");
                registeredComplaints.remove(c);
            }

        }
    }

    @Override
    public void view_courses(List<Course> courses_list) {
        for(Course c : courses_list){
            if(c.getSemester()==this.semester){
                System.out.println("Given below is the course details of " + c.getCourseName());
                System.out.println("Course code:- "+c.getCourseName());
                System.out.println("Course credit "+c.getCredit());
                if(c.getPrerequisites()==null){
                    System.out.println("No prerequisites for now");
                }
                else {System.out.println("List of prerequisites:-");
                int j = 1;
                for (Course prerequisite : c.getPrerequisites()) {
                    System.out.println(j + ". " + prerequisite.getCourseName());
                    j++;
                }
                if(c.getPrerequisites().isEmpty()){
                    System.out.println("NO Prerequisites req for this course.");
                }}
                if(c.getSyllabus()==null) {
                    System.out.println("Syllabus will be updated soon...");
                }
                else{
                System.out.println("Syllabus of the course:-");
                System.out.println(c.getSyllabus());}
                if(c.getSlot1()==0){
                    System.out.println("Slot/Slots will be updated soon...");
                }
                else {
                    if (c.getCredit() == 4)  System.out.println("Course Timings:-\n" + "timing for 1st lecture:- " + c.getSlot1() + "\ntiming for 2nd lecture:-" + c.getSlot2());

                    else System.out.println("Course Timings:-\n"+"timing for 1st lecture:- "+c.getSlot1());
                }
                if(c.getEnrollmentLimit()!=0)
                    System.out.println("Course enrolled students:"+c.getEnrollmentLimit());
                else System.out.println("NO course enrollment Limit");
                if(c.getProfessor()==null||c.getProfessor().getOfficeHour()==0)System.out.println("Office Hour still not decided");
                else System.out.println("Office Hour for this course:- "+c.getProfessor().getOfficeHour());

            }
            System.out.println("-".repeat(20));
        }
    }

    @Override
    public void displayMenu() {
        System.out.println("1. View Available Courses\n2. Register for Course\n3. Drop Course\n4. Track Academic Progress\n5. Submit Complaint\n6. View Complaint Box\n7. Change Password\n8. Logout");
    }
}
