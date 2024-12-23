import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class FacultyDashboard {
    private JFrame frame;
    private Faculty faculty;

    public FacultyDashboard(Faculty faculty) {
        this.faculty = faculty;

        frame = new JFrame(faculty.getUsername() + " - Öğretim Üyesi Paneli");
        frame.setSize(400, 300);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout());

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(3, 1));

        JButton approveCourseButton = new JButton("Ders Onayı");
        JButton viewCoursesButton = new JButton("Dersleri Görüntüle");
        JButton viewProfileButton = new JButton("Profil Görüntüle");

        panel.add(approveCourseButton);
        panel.add(viewCoursesButton);
        panel.add(viewProfileButton);

        // Ders onaylama butonuna tıklama
        approveCourseButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                approveCourse();
            }
        });

        // Dersleri görüntüleme butonuna tıklama
        viewCoursesButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                viewCourses();
            }
        });

        // Profil görüntüleme butonuna tıklama
        viewProfileButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                viewProfile();
            }
        });

        frame.add(panel, BorderLayout.CENTER);
        frame.setVisible(true);
    }

    private void approveCourse() {
        // Öğretim üyesinin öğrenci seçmesi
        String studentUsername = JOptionPane.showInputDialog(frame, "Ders onaylamak istediğiniz öğrencinin kullanıcı adını girin:");

        if (studentUsername != null && !studentUsername.trim().isEmpty()) {
            Student student = Database.getStudentByUsername(studentUsername); // Öğrenciyi veritabanından al
            if (student != null) {
                // Öğrencinin derslerini görüntüle
                ArrayList<Course> selectedCourses = student.getSelectedCourses();
                StringBuilder courseList = new StringBuilder("Onaylamak istediğiniz dersler:\n");

                for (Course course : selectedCourses) {
                    courseList.append(course.getCourseName()).append(" - ")
                            .append(course.isApproved() ? "Onaylı" : "Onaysız").append("\n");
                }

                int result = JOptionPane.showConfirmDialog(frame, courseList.toString() + "\nDersleri onaylamak istiyor musunuz?",
                        "Ders Onayı", JOptionPane.YES_NO_OPTION);

                if (result == JOptionPane.YES_OPTION) {
                    for (Course course : selectedCourses) {
                        if (!course.isApproved()) {
                            course.approve(); // Ders onaylanıyor
                            JOptionPane.showMessageDialog(frame, course.getCourseName() + " dersi onaylandı!");
                        }
                    }
                }
            } else {
                JOptionPane.showMessageDialog(frame, "Böyle bir öğrenci bulunamadı!", "Hata", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    private void viewCourses() {
        // Öğretim üyesi derslerini görüntüler
        ArrayList<Course> facultyCourses = faculty.getCourses();

        if (facultyCourses.isEmpty()) {
            JOptionPane.showMessageDialog(frame, "Öğretim üyesinin dersleri bulunmamaktadır.", "Dersler", JOptionPane.INFORMATION_MESSAGE);
        } else {
            StringBuilder coursesInfo = new StringBuilder("Öğretim Üyesi Dersleri:\n");
            for (Course course : facultyCourses) {
                coursesInfo.append(course.getCourseName()).append("\n");
            }
            JOptionPane.showMessageDialog(frame, coursesInfo.toString(), "Dersler", JOptionPane.INFORMATION_MESSAGE);
        }
    }

    private void viewProfile() {
        // Öğretim üyesinin profilini görüntüler
        String profileInfo = "Kullanıcı Adı: " + faculty.getUsername() + "\n" +
                "Rol: " + faculty.getRole();

        // Profil bilgilerini JOptionPane ile göster
        JOptionPane.showMessageDialog(frame, profileInfo, "Profil Bilgileri", JOptionPane.INFORMATION_MESSAGE);
    }
}
