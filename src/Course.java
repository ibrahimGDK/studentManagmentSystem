public  class Course extends CourseManagement {
    private String courseName;
    private String courseCode;
    private boolean isApproved;


    public Course(String courseName, String courseCode) {
        this.courseName = courseName;
        this.courseCode = courseCode;
        this.isApproved = false;
    }


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
        isApproved = true; // onaylama işlemi ile dersi onayladık
    }

    // Dersin detaylarını gösteren metot
    public void displayCourseDetails() {
        System.out.println("Ders Adı: " + courseName);
        System.out.println("Ders Kodu: " + courseCode);
    }
}
