import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class StudentDashboard {
    private JFrame frame;
    private Student student;

    public StudentDashboard(Student student) {
        this.student = student;

        frame = new JFrame(student.getUsername() + " - Öğrenci Paneli");
        frame.setSize(400, 300);
        //frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout());

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(3, 1));

        JButton selectCourseButton = new JButton("Ders Seç");
        JButton viewGradesButton = new JButton("Notları Görüntüle");
        JButton viewProfileButton = new JButton("Profil Görüntüle");
        JButton viewSelectedCoursesButton = new JButton("Seçilen Dersleri Görüntüle");

        panel.add(selectCourseButton);
        panel.add(viewGradesButton);
        panel.add(viewProfileButton);
        panel.add(viewSelectedCoursesButton);

        viewSelectedCoursesButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                viewSelectedCourses();
            }
        });

        selectCourseButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                selectCourse();
            }
        });

        viewGradesButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                viewGrades();
            }
        });

        viewProfileButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                viewProfile();
            }
        });

        frame.add(panel, BorderLayout.CENTER);
        frame.setVisible(true);
    }

    private void viewSelectedCourses() {
        ArrayList<Course> courses = student.getSelectedCourses();

        if (courses.isEmpty()) {
            JOptionPane.showMessageDialog(frame, "Henüz ders seçmediniz.", "Seçilen Dersler", JOptionPane.INFORMATION_MESSAGE);
        } else {
            StringBuilder courseList = new StringBuilder("Seçilen Dersler:\n");
            for (Course course : courses) {
                courseList.append(course.getCourseName())
                        .append(" - ")
                        .append(course.isApproved() ? "Onaylandı" : "Onaylanmadı")
                        .append("\n");
            }
            JOptionPane.showMessageDialog(frame, courseList.toString(), "Seçilen Dersler", JOptionPane.INFORMATION_MESSAGE);
        }
    }

    private void selectCourse() {
        // Mevcut dersleri getir
        List<Course> courses = Database.getCourseList();

        if (courses.isEmpty()) {
            JOptionPane.showMessageDialog(frame, "Mevcut ders bulunamadı.", "Hata", JOptionPane.ERROR_MESSAGE);
            return;
        }

        // JComboBox oluştur ve ders isimlerini ekle
        JComboBox<String> courseDropdown = new JComboBox<>();
        for (Course course : courses) {
            courseDropdown.addItem(course.getCourseName() + " (" + course.getCourseCode() + ")");
        }

        // Kullanıcıdan ders seçmesini iste
        int option = JOptionPane.showConfirmDialog(
                frame,
                courseDropdown,
                "Seçmek istediğiniz dersi seçin:",
                JOptionPane.OK_CANCEL_OPTION,
                JOptionPane.PLAIN_MESSAGE
        );

        // Kullanıcı onaylarsa işlem yap
        if (option == JOptionPane.OK_OPTION) {
            int selectedIndex = courseDropdown.getSelectedIndex();
            Course selectedCourse = courses.get(selectedIndex);

            if (student.getSelectedCourses().contains(selectedCourse)) {
                JOptionPane.showMessageDialog(frame, "Bu dersi zaten seçtiniz.", "Hata", JOptionPane.ERROR_MESSAGE);
            } else {
                student.addCourse(selectedCourse);
                JOptionPane.showMessageDialog(frame, selectedCourse.getCourseName() + " dersi seçildi!", "Ders Seçimi", JOptionPane.INFORMATION_MESSAGE);
            }
        }
    }



    private void viewGrades() {
        HashMap<String, Integer> grades = student.getGrades();

        if (grades.isEmpty()) {
            JOptionPane.showMessageDialog(frame, "Henüz notunuz bulunmamaktadır.", "Notlar", JOptionPane.INFORMATION_MESSAGE);
            return;
        }

        StringBuilder gradesInfo = new StringBuilder("Dersler ve Notlar:\n");
        for (String course : grades.keySet()) {
            gradesInfo.append(course).append(": ").append(grades.get(course)).append("\n");
        }

        JOptionPane.showMessageDialog(frame, gradesInfo.toString(), "Notlar", JOptionPane.INFORMATION_MESSAGE);
    }

    private void viewProfile() {
        String profileInfo = "Kullanıcı Adı: " + student.getUsername() + "\n" +
                "Rol: " + student.getRole();

        JOptionPane.showMessageDialog(frame, profileInfo, "Profil Bilgileri", JOptionPane.INFORMATION_MESSAGE);
    }
}
