class StudentCourse extends Course {
    private boolean isApproved; // Öğrencinin ders onay durumu


    public StudentCourse(String courseName, String courseCode) {
        super(courseName, courseCode);
        this.isApproved = false; // Varsayılan olarak onaylanmamış
    }


    public boolean isApproved() {
        return isApproved;
    }

    // Onaylama işlemi
    public void approveCourse() {
        this.isApproved = true;
    }


    @Override
    public void displayCourseDetails() {

        super.displayCourseDetails();

        System.out.println("Onay Durumu: " + (isApproved ? "Onaylanmış" : "Onaylanmamış"));
    }
}
