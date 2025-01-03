import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Database {
    // Örnek kullanıcı verileri: Kullanıcı adı, şifre ve rol bilgileri
    private static Map<String, String> userCredentials = new HashMap<>();
    private static Map<String, User> users = new HashMap<>();
    private static List<Course> courseList = new ArrayList<>();

    static {
        // İlk başta manuel olarak bazı kullanıcıları ekleyelim
        userCredentials.put("ogrenci1", "password123");
        userCredentials.put("ogretim1", "password456");
        userCredentials.put("personel1", "password789");

        // Kullanıcı bilgilerini oluşturuyoruz
        users.put("ogrenci1", new Student("ogrenci1", "password123"));
        users.put("ogretim1", new Faculty("ogretim1", "password456"));
        users.put("personel1", new Staff("personel1", "password789"));

        // Dersler
        courseList.add(new Course("Nesneye Yönelik Programlama", "BIMU2007"));
        courseList.add(new Course("Devreler ve Sistemler", "BIMU2058"));
        courseList.add(new Course("Veri Yapıları", "BIMU2057"));
        courseList.add(new Course("Lojik Devre Tasarımı", "BIMU2005"));
    }

    public static List<Course> getCourseList() {
        return courseList;
    }

    // Login sırasında bu metodla doğrulama yapıyoruz
    public static boolean isValidUser(String username, String password) {

        return userCredentials.containsKey(username) && userCredentials.get(username).equals(password);
    }

    // Kullanıcıyı döndürme işlemi
    public static User getUser(String username) {
        return users.get(username);  // Burada kullanıcı tipine göre User (Öğrenci, Öğretim Üyesi, Personel) döndürülür.
    }

    // Öğrenci adıyla öğrenci nesnesini döndürme
    public static Student getStudentByUsername(String username) {
        User user = users.get(username);
        if (user instanceof Student) {
            return (Student) user;
        }
        return null;
    }

    // Öğrencileri alma işlemi
    public static List<Student> getAllStudents() {
        List<Student> students = new ArrayList<>();
        for (User user : users.values()) {
            if (user instanceof Student) {
                students.add((Student) user);
            }
        }
        return students;
    }

    // Öğrenci ekleme işlemi
    public static boolean addStudent(String username, String password) {
        if (userCredentials.containsKey(username)) {
            return false;  // Eğer kullanıcı adı zaten varsa, öğrenci eklenemez
        }

        // Yeni öğrenci oluşturuluyor ve kullanıcı listesine ekleniyor
        Student newStudent = new Student(username, password);
        users.put(username, newStudent);
        userCredentials.put(username, password);
        return true;
    }

    // Öğrenci silme işlemi
    public static boolean removeStudent(String username) {
        if (!userCredentials.containsKey(username)) {
            return false;  // Eğer kullanıcı adı yoksa, silme işlemi başarısız olur
        }

        // Öğrenci veritabanından siliniyor
        userCredentials.remove(username);
        users.remove(username);
        return true;
    }

}
