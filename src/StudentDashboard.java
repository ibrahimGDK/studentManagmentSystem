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

        // Çerçeveyi oluştur ve ayarla
        frame = new JFrame(student.getUsername() + " - Öğrenci Paneli");
        frame.setSize(600, 400);  // Ekran boyutunu büyüttük
        frame.setLocationRelativeTo(null);  // Ortaya yerleştir
        //frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout());

        // Başlık Paneli
        JPanel titlePanel = new JPanel();
        titlePanel.setBackground(new Color(70, 130, 180));  // SteelBlue
        JLabel titleLabel = new JLabel("Öğrenci Paneli");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 24));
        titleLabel.setForeground(Color.WHITE);
        titlePanel.add(titleLabel);
        frame.add(titlePanel, BorderLayout.NORTH);

        // Ana Panel
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new GridBagLayout());
        mainPanel.setBorder(BorderFactory.createEmptyBorder(20, 50, 20, 50));  // Kenar boşlukları
        mainPanel.setBackground(new Color(240, 248, 255));  // AliceBlue arka plan rengi

        // GridBagConstraints ile butonları hizalayacağız
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);  // Bileşenler arası boşluk
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridx = 0;
        gbc.gridy = 0;

        // Ders Seç Butonu
        JButton selectCourseButton = createStyledButton("Ders Seç");
        mainPanel.add(selectCourseButton, gbc);

        // Notları Görüntüle Butonu
        gbc.gridy++;
        JButton viewGradesButton = createStyledButton("Notları Görüntüle");
        mainPanel.add(viewGradesButton, gbc);

        // Profil Görüntüle Butonu
        gbc.gridy++;
        JButton viewProfileButton = createStyledButton("Profil Görüntüle");
        mainPanel.add(viewProfileButton, gbc);

        // Seçilen Dersleri Görüntüle Butonu
        gbc.gridy++;
        JButton viewSelectedCoursesButton = createStyledButton("Seçilen Dersleri Görüntüle");
        mainPanel.add(viewSelectedCoursesButton, gbc);

        // Buton Olayları
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

        viewSelectedCoursesButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                viewSelectedCourses();
            }
        });

        frame.add(mainPanel, BorderLayout.CENTER);
        frame.setVisible(true);
    }

    // Butonları stilize eden yardımcı metod
    private JButton createStyledButton(String text) {
        JButton button = new JButton(text);
        button.setFont(new Font("Arial", Font.BOLD, 16));
        button.setBackground(new Color(100, 149, 237));  // CornflowerBlue
        button.setForeground(Color.WHITE);
        button.setFocusPainted(false);
        return button;
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
