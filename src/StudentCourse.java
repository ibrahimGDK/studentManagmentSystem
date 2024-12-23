class StudentCourse extends Course {
    private boolean isApproved; // Öğrencinin ders onay durumu

    // Constructor
    public StudentCourse(String courseName, String courseCode) {
        super(courseName, courseCode);
        this.isApproved = false; // Varsayılan olarak onaylanmamış
    }

    // Getter
    public boolean isApproved() {
        return isApproved;
    }

    // Onaylama işlemi
    public void approveCourse() {
        this.isApproved = true;
    }

    // Dersin detaylarını gösteren metot
    @Override
    public void displayCourseDetails() {
        // Üst sınıfın displayCourseDetails metodunu çağırıyoruz
        super.displayCourseDetails();
        // Onay durumu bilgisini ekliyoruz
        System.out.println("Onay Durumu: " + (isApproved ? "Onaylanmış" : "Onaylanmamış"));
    }
}
