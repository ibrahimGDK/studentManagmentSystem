import java.util.HashMap;
import java.util.Map;

public class Database {
    // Örnek kullanıcı verileri: Kullanıcı adı, şifre ve rol bilgileri
    private static Map<String, String> userCredentials = new HashMap<>();
    private static Map<String, User> users = new HashMap<>();

    static {
        // İlk başta manuel olarak bazı kullanıcıları ekleyelim
        userCredentials.put("ogrenci1", "password123");
        userCredentials.put("ogretim1", "password456");
        userCredentials.put("personel1", "password789");

        // Kullanıcı bilgilerini oluşturuyoruz (Gerçek projede bu veriler bir veritabanından alınacaktır)
        users.put("ogrenci1", new Student("ogrenci1", "password123"));
        users.put("ogretim1", new Faculty("ogretim1", "password456"));
        users.put("personel1", new Staff("personel1", "password789"));
    }

    // Kullanıcı doğrulama işlemi: Kullanıcı adı ve şifreyi kontrol eder
    public static boolean isValidUser(String username, String password) {
        // Kullanıcı adı var mı ve şifre doğru mu kontrol et
        return userCredentials.containsKey(username) && userCredentials.get(username).equals(password);
    }

    // Kullanıcıyı döndürme işlemi: Kullanıcı adıyla ilişkili kullanıcıyı alır
    public static User getUser(String username) {
        return users.get(username);  // Burada kullanıcı tipine göre User (Öğrenci, Öğretim Üyesi, Personel) döndürülür.
    }
}
