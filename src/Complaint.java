import java.util.List;

public class Complaint {
    private String description= null;
    private String status;
    private Course clash_course1;
    private int slot;
    private String Message;


    public Complaint(String description) {
        this.description = description;
        this.status = "Pending";
    }
    public Complaint(String courseCode1, List<Course> courses_list,int slot) {
        for(Course c : courses_list){
            if(c.getCode().equals(courseCode1)){
                this.clash_course1 = c;
                if(slot==1){
                    this.slot=c.getSlot1();

                }
                else if(slot==2){
                    this.slot=c.getSlot2();
                }
            }}

        this.status = "Pending";
    }

    public void resolve() {
        status = "Resolved";
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Course getCourse() {
        return this.clash_course1;
    }
    public int getSlot() {
        return this.slot;
    }
    public String getDescription() {
        return description;
    }

    public String getMessage() {
        return Message;
    }

    public void setMessage(String message) {
        this.Message = message;
    }
    public void setSlot(int slot) {
        if(clash_course1.getCredit()==2) clash_course1.setSlot1(slot);
        else{
            if(clash_course1.getSlot1()==this.slot) clash_course1.setSlot1(slot);
            else clash_course1.setSlot2(slot);
        }
    }

    @Override
    public String toString() {
        return "Complaint: " + description + " | Status: " + status;
    }
}
