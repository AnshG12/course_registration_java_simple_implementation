import java.util.Scanner;

abstract class User {
    protected String email;
    protected String password;
    Scanner scanner = new Scanner(System.in);

    public User(String email, String password) {
        this.email = email;
        this.password = password;
    }

    public boolean checkPassword(String inputPassword) {
        return password.equals(inputPassword);
    }

    public void setPassword(String newPassword) {
        this.password = newPassword;
    }
    public void setEmail(String newEmail) {
        this.email = newEmail;
    }

    public String getEmail() {
        return email;
    }

    public abstract void displayMenu();
}
