public class feedback<T> {
    private final String studentEmail;
    private final String courseCode;
    private final T feedback;

    public feedback(String studentEmail, String courseCode, T feedback) {
        this.studentEmail = studentEmail;
        this.courseCode = courseCode;
        this.feedback = feedback;
    }

    // Getters
    public String getStudentEmail() {
        return studentEmail;
    }

    public String getCourseCode() {
        return courseCode;
    }

    public T getFeedback() {
        return feedback;
    }

    @Override
    public String toString() {
        return "Feedback from Student: " + studentEmail + "\nCourse Code: " + courseCode + "\nFeedback: " + feedback;
    }
}
