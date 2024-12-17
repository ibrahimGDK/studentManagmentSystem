public interface CourseActions { // Ders İşlemleri
    void selectCourse(String courseName);  // Ders seçme
    void approveCourse(String courseName);  // Ders onayı (Öğretim Üyesi)
    void viewCourses();  // Dersleri görüntüleme
}
