public class Staff extends User implements AdminActions{
    public Staff(String username, String password) {
        super(username, password, "Staff");
    }

    @Override
    public void login(String username, String password) {

    }

    @Override
    public String getRole() {
        return "Staff";
    }

    @Override
    public void changePassword(String newPassword) {

    }

    @Override
    public void viewProfile() {

    }

    @Override
    public void addStudent(String username, String password) {

    }

    @Override
    public void removeStudent(String username) {

    }

    @Override
    public void viewAllStudents() {

    }
}