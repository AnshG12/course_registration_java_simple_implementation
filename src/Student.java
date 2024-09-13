class Student extends User {
    private int semester=1;
    private String studentName;

    public Student(String email, String password) {
        super(email, password);

    }

    @Override
    public void displayMenu() {
        System.out.println("1. View Available Courses\n2. Register for Course\n3. Drop Course\n4. Track Academic Progress\n5. Submit Complaint\n6. Change Password\n7. Logout");
    }
}
