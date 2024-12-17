public class Student extends User {
    public Student(String username, String password) {
        super(username, password, "Student");
    }

    public String getRole() {
        return "Student";
    }


    @Override
    public void login(String username, String password) {

    }

    @Override
    public void changePassword(String newPassword) {

    }

    @Override
    public void viewProfile() {

    }
}
