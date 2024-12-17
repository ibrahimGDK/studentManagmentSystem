import java.util.ArrayList;
import java.util.HashMap;

public class Student extends User {

    private HashMap<String, Integer> grades;  // Ders adı - Not ilişkisini tutar

    private ArrayList<String> selectedCourses; // Seçilen dersler listesi

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

    }

    @Override
    public void changePassword(String newPassword) {

    }

    @Override
    public void viewProfile() {
        System.out.println("Profil Bilgileri:");
        System.out.println("Kullanıcı Adı: " + getUsername());
        System.out.println("Rol: " + getRole());
    }

    public void addCourse(String courseName) {
        if (!selectedCourses.contains(courseName)) {
            selectedCourses.add(courseName);
            System.out.println(courseName + " dersi seçildi.");
        } else {
            System.out.println(courseName + " dersi zaten seçilmiş.");
        }
    }

    // Notları döndüren metot
    public HashMap<String, Integer> getGrades() {
        return grades;
    }

    // Seçilen dersleri döndüren metot
    public ArrayList<String> getSelectedCourses() {
        return selectedCourses;
    }
}
