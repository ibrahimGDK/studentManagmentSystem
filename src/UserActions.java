public interface UserActions { // Kullanıcı İşlemleri
    void login(String username, String password);  // Kullanıcı giriş işlemi
    void changePassword(String newPassword);  // Şifre değiştirme
    void viewProfile();  // Profil görüntüleme
}
