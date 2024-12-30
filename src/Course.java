public  class Course extends CourseManagement {
    private String courseName;
    private String courseCode; // Ders Kodu
    private boolean isApproved;

    // Constructor
    public Course(String courseName, String courseCode) {
        this.courseName = courseName;
        this.courseCode = courseCode;
        this.isApproved = false;
    }

    // Getter'lar
    public String getCourseName() {
        return courseName;
    }

    public String getCourseCode() {
        return courseCode;
    }

    // Dersin onaylanmış olup olmadığını döndüren metot
    public boolean isApproved() {
        return isApproved; // Varsayılan olarak onaylanmamış
    }

    // Dersin onaylanması
    public void approve() {
        isApproved = true; // onaylama işlemi burada yapılabilir
    }

    // Dersin detaylarını gösteren metot
    public void displayCourseDetails() {
        System.out.println("Ders Adı: " + courseName);
        System.out.println("Ders Kodu: " + courseCode);
    }
}
