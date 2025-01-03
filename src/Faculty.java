import java.util.ArrayList;

public class Faculty extends User implements CourseActions {

    private ArrayList<Course> courses; // Öğretim üyesinin derslerini tutan liste

    public Faculty(String username, String password) {
        super(username, password, "Faculty");
    }

    @Override
    public String getRole() {
        return "Faculty";
    }

    @Override
    public void selectCourse(String courseName) {
        // Öğretim üyeleri ders seçme işlemi yapmazlar, bu metodu boş bırakıyoruz.
    }

    @Override
    public void approveCourse(String courseName) {
        // Bu metot, öğretim üyesinin ders onaylamasını sağlayacak
        //bu metodu da facultyDashboad da oluşturduk zaten
    }

    // Öğretim üyesinin bir öğrencinin dersini onaylama işlemi
    public void approveCourse(Student student, String courseName) {
        ArrayList<Course> courses = student.getSelectedCourses();
        for (Course course : courses) {
            if (course.getCourseName().equals(courseName)) {
                course.approve(); // Onayla
                System.out.println(courseName + " dersi onaylandı.");
                return;
            }
        }
        System.out.println(courseName + " dersi bulunamadı.");
    }

    @Override
    public void login(String username, String password) {
        // Öğretim üyelerinin login işlemi
        //bu methodu login screen de doldurduk
    }

    @Override
    public void changePassword(String newPassword) {
        setPassword(newPassword);  // Şifre değişikliği
        System.out.println("Şifre başarıyla değiştirildi.");
    }

    @Override
    public void viewProfile() {
        // Öğretim üyesinin profilini görüntüleme
        System.out.println("Profil Bilgileri:");
        System.out.println("Kullanıcı Adı: " + getUsername());
        System.out.println("Rol: " + getRole());
    }

    // Öğretim üyesinin dersleri görüntüleme işlemi
    public void viewCourses() {
        System.out.println("Öğretim üyesinin derslerini görüntülüyor...");
    }

    public ArrayList<Course> getCourses() {
        return courses;
    }
}
