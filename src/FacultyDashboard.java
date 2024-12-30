import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class FacultyDashboard {
    private JFrame frame;
    private Faculty faculty;

    public FacultyDashboard(Faculty faculty) {
        this.faculty = faculty;

        // Çerçeveyi oluştur ve ayarla
        frame = new JFrame(faculty.getUsername() + " - Öğretim Üyesi Paneli");
        frame.setSize(600, 400);  // Ekran boyutunu büyüttük
        frame.setLocationRelativeTo(null);  // Ortaya yerleştir
        //frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout());

        // Başlık Paneli
        JPanel titlePanel = new JPanel();
        titlePanel.setBackground(new Color(70, 130, 180));  // SteelBlue
        JLabel titleLabel = new JLabel("Öğretim Üyesi Paneli");
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

        // Ders Onayı Butonu
        JButton approveCourseButton = createStyledButton("Ders Onayı");
        mainPanel.add(approveCourseButton, gbc);

        // Dersleri Görüntüle Butonu
        gbc.gridy++;
        JButton viewCoursesButton = createStyledButton("Dersleri Görüntüle");
        mainPanel.add(viewCoursesButton, gbc);

        // Profil Görüntüle Butonu
        gbc.gridy++;
        JButton viewProfileButton = createStyledButton("Profil Görüntüle");
        mainPanel.add(viewProfileButton, gbc);

        // Not Ekle Butonu
        gbc.gridy++;
        JButton addGradeButton = createStyledButton("Not Ekle");
        mainPanel.add(addGradeButton, gbc);

        // Buton Olayları
        approveCourseButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                approveCourse();
            }
        });

        viewCoursesButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                viewCourses();
            }
        });

        viewProfileButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                viewProfile();
            }
        });

        addGradeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                addGrade();
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
        List<Course> courses = Database.getCourseList();

        if (courses.isEmpty()) {
            JOptionPane.showMessageDialog(frame, "Mevcut ders bulunamadı.", "Bilgilendirme", JOptionPane.INFORMATION_MESSAGE);
            return;
        }

        // Ders bilgilerini JTextArea'da görüntüle
        JTextArea courseInfoArea = new JTextArea();
        courseInfoArea.setEditable(false); // Kullanıcı düzenleyemesin
        StringBuilder courseInfo = new StringBuilder();

        for (Course course : courses) {
            courseInfo.append("Ders Adı: ").append(course.getCourseName())
                    .append("\nDers Kodu: ").append(course.getCourseCode())
                    .append("\n\n");
        }

        courseInfoArea.setText(courseInfo.toString());

        // Ders bilgilerini bir JScrollPane içinde göster
        JScrollPane scrollPane = new JScrollPane(courseInfoArea);
        scrollPane.setPreferredSize(new Dimension(350, 200));

        JOptionPane.showMessageDialog(
                frame,
                scrollPane,
                "Mevcut Dersler",
                JOptionPane.INFORMATION_MESSAGE
        );
    }

    private void viewProfile() {
        // Öğretim üyesinin profilini görüntüler
        String profileInfo = "Kullanıcı Adı: " + faculty.getUsername() + "\n" +
                "Rol: " + faculty.getRole();

        // Profil bilgilerini JOptionPane ile göster
        JOptionPane.showMessageDialog(frame, profileInfo, "Profil Bilgileri", JOptionPane.INFORMATION_MESSAGE);
    }

    private void addGrade() {
        // Öğrenci adı iste
        String studentUsername = JOptionPane.showInputDialog(frame, "Not eklemek istediğiniz öğrencinin kullanıcı adını girin:");
        if (studentUsername == null || studentUsername.trim().isEmpty()) {
            JOptionPane.showMessageDialog(frame, "Geçersiz kullanıcı adı!", "Hata", JOptionPane.ERROR_MESSAGE);
            return;
        }

        // Öğrenci var mı kontrol et
        Student student = Database.getStudentByUsername(studentUsername);
        if (student == null) {
            JOptionPane.showMessageDialog(frame, "Bu kullanıcı adıyla bir öğrenci bulunamadı.", "Hata", JOptionPane.ERROR_MESSAGE);
            return;
        }

        // Ders adı iste
        String courseName = JOptionPane.showInputDialog(frame, "Not eklemek istediğiniz dersin adını girin:");
        if (courseName == null || courseName.trim().isEmpty()) {
            JOptionPane.showMessageDialog(frame, "Geçersiz ders adı!", "Hata", JOptionPane.ERROR_MESSAGE);
            return;
        }

        // Dersin öğrenci tarafından seçilip seçilmediğini kontrol et
        boolean courseExists = student.getSelectedCourses().stream()
                .anyMatch(course -> course.getCourseName().equalsIgnoreCase(courseName));
        if (!courseExists) {
            JOptionPane.showMessageDialog(frame, "Bu öğrenci bu dersi seçmemiş.", "Hata", JOptionPane.ERROR_MESSAGE);
            return;
        }

        // Not iste
        String gradeStr = JOptionPane.showInputDialog(frame, "Notu girin:");
        try {
            int grade = Integer.parseInt(gradeStr);

            // Notu ekle
            student.addGrade(courseName, grade);
            JOptionPane.showMessageDialog(frame, "Not başarıyla eklendi.", "Başarılı", JOptionPane.INFORMATION_MESSAGE);
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(frame, "Geçersiz not formatı!", "Hata", JOptionPane.ERROR_MESSAGE);
        }
    }
}
