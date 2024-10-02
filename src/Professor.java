import java.util.List;
import java.util.Scanner;

public class Professor extends User implements View{
    private String assigned_courseCode;
    private int OfficeHour=0;

    public Professor(String email, String password) {
        super(email, password);
    }
    public void setAssigned_courseCode(String assigned_courseCode) {
        this.assigned_courseCode = assigned_courseCode;
    }
    public void viewEnrolledStudents(List<Course> course) {
        for(Course c : course) {
            if(c.getCode().equals(assigned_courseCode)) {

                System.out.println("Enrolled students for " + c.getCourseName() + ":");
                for (Student student : c.getEnrolledStudents()) {
                    System.out.println(student.email);
                    System.out.println("CGPA:-"+student.getCgpa());
                }
                return;
            }
        }
        System.out.println("No Students enrolled for " + assigned_courseCode);
    }
    public void grading(List<Course> courses_list){
        Scanner scanner = new Scanner(System.in);

                System.out.println("Enter the number(roll no.) of the student to update: ");
                int studentIndex = scanner.nextInt() - 1;
                scanner.nextLine();  // Consume newline
        for(Course c : courses_list){
            if(c.getCode().equals(this.assigned_courseCode)) {
                int i = 0;
                for (Student s : c.getEnrolledStudents()) {
                    if (studentIndex == i) {
                        System.out.println("enter the grade student has received:-");
                        int grade = scanner.nextInt();
                        scanner.nextLine();
                        s.setGrade(this.assigned_courseCode, grade);
                        return;
                    }
                    else i++;
                }


                System.out.println("Invalid student no. ");
                return;

            }}
    }
    public int getOfficeHour() {
        return this.OfficeHour;
    }
    public void setOfficeHour(int officeHour) {
        this.OfficeHour = officeHour;
    }

    @Override
    public void view_courses(List<Course> courses_list){
        for(Course c : courses_list){
            if(c.getCode().equals(this.assigned_courseCode)){
                System.out.println("Given below is the course details of " + c.getCourseName());
                System.out.println("Course code:- "+c.getCourseName());
                System.out.println("Course credit "+c.getCredit());

                System.out.println("List of enrolled students:");
                int i = 1;
                for (Student student : c.getEnrolledStudents()) {
                    System.out.println(i + ". " + student.getEmail());
                    i++;
                }

                System.out.println("List of prerequisites:-");
                int j = 1;
                for (Course prerequisite : c.getPrerequisites()) {
                    System.out.println(j + ". " + prerequisite.getCourseName());
                    j++;
                }
                System.out.println("Syllabus of the course:-");
                System.out.println(c.getSyllabus());
                if(c.getCredit()==4) System.out.println("Course Timings:-\n"+"timing for 1st lecture:- "+c.getSlot1()+"\ntiming for 2nd lecture:- "+c.getSlot2());
                else System.out.println("Course Timings:-\n"+"timing for 1st lecture:- "+c.getSlot1());
                if(c.getEnrollmentLimit()!=0)
                System.out.println("Course enrolled students:"+c.getEnrollmentLimit());
                else System.out.println("NO course enrollment Limit");
                System.out.println("Office Hour for this course:- "+this.OfficeHour);

            }
        }
    }
    public void set_classTime(List<Course> courses_list){
        Scanner scanner = new Scanner(System.in);
        for(Course c : courses_list){
            if(c.getCode().equals(this.assigned_courseCode)){
                if(c.getCredit()==4){
                    System.out.println("Enter the slot no. from 1-6 for scheduling lecture no. 1");
                    int slot1=scanner.nextInt();
                    scanner.nextLine();
                    System.out.println("Enter the slot no. from 10-15 for scheduling lecture no. 2");
                    int slot2=scanner.nextInt();
                    scanner.nextLine();
                    c.setSlot1(slot1);
                    c.setSlot2(slot2);
                }
                else{
                    System.out.println("Enter the slot no. from 7-9 for scheduling lecture no. 1");
                    int slot1=scanner.nextInt();
                    scanner.nextLine();
                    c.setSlot1(slot1);
                }

            }
        }
    }
    public void Clash_resolver(List<List<Course>> Schedule, Complaint complaint){
    for (List<Course> dayCourses : Schedule) {
    boolean present=true;
        // Inner loop iterates over each course in the current day's course list
        for (Course course : dayCourses) {
            if(course.getSemester()==complaint.getCourse().getSemester()){
                present=false;
                break;
            }
        }
        if(present) {
            complaint.setSlot(Schedule.indexOf(dayCourses)+1);
            System.out.println("Clash has been resolved");
            complaint.setMessage("Check the schedule again for the course to see revised timings");
            complaint.setStatus("Resolved");
            return;
        }
    }
        System.out.println("We can't shift the course so You need to choose between one of them");
        complaint.setMessage("We can't shift the course so You need to choose between one of them");
        complaint.setStatus("Resolved");
    }
    public void manageCourse(List<Course> courses_list) {
       while(true) {
           System.out.println("1. To view the course\n2. To edit the details of the courses\n3. To edit the office hour\n4. To view the past feedback about the course.\n5. Exit");
           Scanner scanner = new Scanner(System.in);
           int choice = scanner.nextInt();
           scanner.nextLine();
           if (choice == 1) {
               view_courses(courses_list);
           }
           else if (choice == 2) {
               for (Course course : courses_list) {
                   if (course.getCode().equals(this.assigned_courseCode)) {
                       while (true) {
                           System.out.println("1. Edit the syllabus\n2. Set/Edit class timings\n3. Edit course credit\n4. Edit prerequisites\n5. Edit Enrollment Limit\n6. Edit Office Hours\n7. Exit");
                           int c = scanner.nextInt();
                           scanner.nextLine();
                           if (c == 1) {
                               System.out.println("Enter the syllabus for this course below:-");
                               String sy = scanner.nextLine();
                               course.setSyllabus(sy);
                               System.out.println("Syllabus changed");
                           } else if (c == 2) {
                               set_classTime(courses_list);
                               System.out.println("Class timings changed");
                           } else if (c == 3) {
                               System.out.println("Enter the course credit");
                               course.setCredits(scanner.nextInt());
                               scanner.nextLine();
                               System.out.println("Credit changed");
                           } else if (c == 4) {
                               System.out.println("1. For Adding the Prerequisite course\n2. For Deleting the Prerequisite course\n3. Exit");
                               int pre_choice = scanner.nextInt();
                               scanner.nextLine();
                               if (pre_choice == 1) {
                                   System.out.println("Enter the course code for adding the course:-");
                                   String pre_code = scanner.next();
                                   for (Course pre_course : courses_list) {
                                       if (pre_course.getCode().equals(pre_code)) {
                                           course.getPrerequisites().add(pre_course);
                                           System.out.println("Prerequisite added");
                                       }
                                   }
                               } else if (pre_choice == 2) {
                                   System.out.println("Enter the course code for deleting the course:-");
                                   String pre_code = scanner.nextLine();
                                   for (Course pre_course : course.getPrerequisites()) {
                                       if (pre_course.getCode().equals(pre_code)) {
                                           course.getPrerequisites().remove(pre_course);
                                           System.out.println("Prerequisite deleted");
                                       }
                                   }
                               } else if (pre_choice == 3) {
                                   System.out.println("Exiting this section");
                                   break;
                               }
                           } else if (c == 5) {
                               System.out.println("Enter the new Enrollment Limit");
                               int limit = scanner.nextInt();
                               scanner.nextLine();
                               course.setEnrollmentLimit(limit);
                               System.out.println("New Enrollment Limit is set");
                           }
                           else if(c==6){
                               System.out.println("Enter the new Office Hour");
                               this.OfficeHour= scanner.nextInt();
                               scanner.nextLine();
                           } else if (c==7) {
                               System.out.println("Exiting this section, bye:");
break;
                           }
                           else{
                               System.out.println("Invalid input retry....");
                           }

                       }
                   }
               }
           }
           else if(choice==3){
               System.out.println("Enter the new office hour..");
               setOfficeHour(scanner.nextInt());
               scanner.nextLine();
               System.out.println("Office hour updated..");
           } else if (choice == 4) {
               for (Course course : courses_list) {
                   if (course.getCode().equals(this.assigned_courseCode)) {
                       course.viewCourseFeedback();
                   }
               }

           } else if (choice == 5) {
                       System.out.println("Exiting manage course section....");
                       break;
           }
           else{
                   System.out.println("Invalid choice. retry....");
               }

       }
    }
    public void assignCourseToTA(List<TeachingAssistant> taList,List<Student> stu) {
        System.out.println("Enter Student roll no. who you want to make TA:");
        String taEmail = scanner.nextLine();
//
//        if(roll>stu.size()){
//            System.out.println("Invalid roll no. retry....");
//            return;
//        }
//        else{
//            try {
//                Student student=stu.get(roll-1);
//                TeachingAssistant newTA = (TeachingAssistant) student.clone();
//                taList.add(newTA);
//                System.out.println("TA assigned successfully.");
//            } catch (CloneNotSupportedException e) {
//                e.printStackTrace();
//            }
  //      }
        boolean taFound = false;
        for (TeachingAssistant ta : taList) {
            if (ta.getEmail().equals(taEmail)) {
                ta.setAssignedCourseCode(this.assigned_courseCode);
                System.out.println("Assigned course " + this.assigned_courseCode + " to TA " + ta.getEmail());
                taFound = true;
                break;
            }
        }
        if (!taFound) {
            System.out.println("TA with email " + taEmail + " not found.");
        }

    }
    @Override
    public void displayMenu() {
        System.out.println("1. View Enrolled Students\n2. Manage Course\n3. Grade the student\n4. Assign TA\n5. Logout");
    }
}
