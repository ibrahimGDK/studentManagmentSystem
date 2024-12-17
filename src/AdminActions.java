public interface AdminActions { // Yönetici İşlemleri
    void addStudent(String username, String password);  // Öğrenci ekleme
    void removeStudent(String username);  // Öğrenci silme
    void viewAllStudents();  // Tüm öğrencileri görüntüleme
}
