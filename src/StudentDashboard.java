import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.HashMap;

public class StudentDashboard {
    private JFrame frame;
    private Student student;

    public StudentDashboard(Student student) {
        this.student = student;

        frame = new JFrame(student.getUsername() + " - Öğrenci Paneli");
        frame.setSize(400, 300);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
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



        // Ders seçme butonuna tıklama
        selectCourseButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                selectCourse();
            }
        });

        // Notları görüntüleme butonuna tıklama
        viewGradesButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                viewGrades();
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

    private void viewSelectedCourses() {
        ArrayList<String> courses = student.getSelectedCourses();

        if (courses.isEmpty()) {
            JOptionPane.showMessageDialog(frame, "Henüz ders seçmediniz.", "Seçilen Dersler", JOptionPane.INFORMATION_MESSAGE);
        } else {
            StringBuilder courseList = new StringBuilder("Seçilen Dersler:\n");
            for (String course : courses) {
                courseList.append(course).append("\n");
            }
            JOptionPane.showMessageDialog(frame, courseList.toString(), "Seçilen Dersler", JOptionPane.INFORMATION_MESSAGE);
        }
    }

    private void selectCourse() {
        // Ders adı için kullanıcıdan giriş al
        String courseName = JOptionPane.showInputDialog(frame, "Seçmek istediğiniz dersin adını girin:");

        if (courseName != null && !courseName.trim().isEmpty()) {
            student.addCourse(courseName);
            JOptionPane.showMessageDialog(frame, courseName + " dersi seçildi!", "Ders Seçimi", JOptionPane.INFORMATION_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(frame, "Geçersiz ders adı!", "Hata", JOptionPane.ERROR_MESSAGE);
        }
    }



    private void viewGrades() {
        HashMap<String, Integer> grades = student.getGrades();

        if (grades==null) {
            JOptionPane.showMessageDialog(frame, "Henüz notunuz bulunmamaktadır.", "Notlar", JOptionPane.INFORMATION_MESSAGE);
            return;
        }

        // Notları göstermek için StringBuilder kullanıyoruz
        StringBuilder gradesInfo = new StringBuilder("Dersler ve Notlar:\n");
        for (String course : grades.keySet()) {
            gradesInfo.append(course).append(": ").append(grades.get(course)).append("\n");
        }

        // JOptionPane ile notları göster
        JOptionPane.showMessageDialog(frame, gradesInfo.toString(), "Notlar", JOptionPane.INFORMATION_MESSAGE);
    }


    private void viewProfile() {
        // Öğrencinin profilini görüntülemesi
        String profileInfo = "Kullanıcı Adı: " + student.getUsername() + "\n" +
                "Rol: " + student.getRole();

        // Profil bilgilerini JOptionPane ile göster
        JOptionPane.showMessageDialog(frame, profileInfo, "Profil Bilgileri", JOptionPane.INFORMATION_MESSAGE);
    }
}

