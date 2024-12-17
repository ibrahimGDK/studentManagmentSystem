public class Faculty extends User implements CourseActions{
    public Faculty(String username, String password) {
        super(username, password, "Faculty");
    }

    @Override
    public String getRole() {
        return "Faculty";
    }

    @Override
    public void selectCourse(String courseName) {

    }

    @Override
    public void approveCourse(String courseName) {

    }

    @Override
    public void viewCourses() {

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
