import java.util.ArrayList;
import java.util.HashMap;

public class Student extends User {
    private HashMap<String, Integer> grades;  // Ders adı - Not ilişkisini tutar
    private ArrayList<Course> selectedCourses; // Seçilen dersler listesi

    public Student(String username, String password) {
        super(username, password, "Student");
        this.grades = new HashMap<>();
        this.selectedCourses = new ArrayList<>();
    }

    public String getRole() {
        return "Student";
    }

    @Override
    public void login(String username, String password) {
        //
    }

    @Override
    public void changePassword(String newPassword) {
        setPassword(newPassword);
        System.out.println("Şifre başarıyla değiştirildi.");
    }

    @Override
    public void viewProfile() {
        System.out.println("Profil Bilgileri:");
        System.out.println("Kullanıcı Adı: " + getUsername());
        System.out.println("Rol: " + getRole());
    }

    // Ders ekleme metodu
    public void addCourse(Course course) {
        if (!selectedCourses.contains(course)) {
            selectedCourses.add(course);
            System.out.println(course.getCourseName() + " dersi seçildi.");
        } else {
            System.out.println(course.getCourseName() + " dersi zaten seçilmiş.");
        }
    }

    // Notları döndüren metot
    public HashMap<String, Integer> getGrades() {
        return grades;
    }

    // Seçilen dersleri getiriyor
    public ArrayList<Course> getSelectedCourses() {
        return selectedCourses;
    }

    // Not ekleme metodu
    public void addGrade(String courseName, int grade) {
        grades.put(courseName, grade);
        System.out.println(courseName + " dersine not eklenmiştir: " + grade);
    }
}
