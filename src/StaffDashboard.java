import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class StaffDashboard {
    private JFrame frame;
    private Staff staff;

    public StaffDashboard(Staff staff) {
        this.staff = staff;

        frame = new JFrame(staff.getUsername() + " - Personel Paneli");
        frame.setSize(500, 400);
        //frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout());
        frame.setLocationRelativeTo(null); // Ekranın ortasına yerleştirme

        // Başlık Paneli
        JPanel titlePanel = new JPanel();
        titlePanel.setBackground(new Color(70, 130, 180));
        JLabel titleLabel = new JLabel("Personel Paneli");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 24));
        titleLabel.setForeground(Color.WHITE);
        titlePanel.add(titleLabel);
        frame.add(titlePanel, BorderLayout.NORTH);

        // Ana Panel
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(5, 1, 10, 10)); // 5 satır, 1 sütun, bileşenler arasında boşluk
        panel.setBorder(BorderFactory.createEmptyBorder(20, 50, 20, 50)); // Panel kenar boşlukları

        JButton addStudentButton = new JButton("Öğrenci Ekle");
        JButton removeStudentButton = new JButton("Öğrenci Sil");
        JButton viewAllStudentsButton = new JButton("Tüm Öğrencileri Görüntüle");
        JButton viewProfileButton = new JButton("Profil Görüntüle");
        JButton enterGradeButton = new JButton("Not Girişi");

        // Buton Font ve Boyut Ayarları
        Font buttonFont = new Font("Arial", Font.BOLD, 16);
        Dimension buttonSize = new Dimension(200, 40);

        for (JButton button : new JButton[]{addStudentButton, removeStudentButton, viewAllStudentsButton, viewProfileButton, enterGradeButton}) {
            button.setFont(buttonFont);
            button.setPreferredSize(buttonSize);
            button.setFocusPainted(false);
            button.setBackground(new Color(100, 149, 237)); // CornflowerBlue renk
            button.setForeground(Color.WHITE);
        }




        panel.add(addStudentButton);
        panel.add(removeStudentButton);
        panel.add(viewAllStudentsButton);
        panel.add(viewProfileButton);
        panel.add(enterGradeButton);


        enterGradeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                enterGrade();
            }
        });

        // Öğrenci ekleme butonuna tıklama
        addStudentButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                addStudent();
            }
        });

        // Öğrenci silme butonuna tıklama
        removeStudentButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                removeStudent();
            }
        });

        // Tüm öğrencileri görüntüleme butonuna tıklama
        viewAllStudentsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                viewAllStudents();
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

    /*private void addStudent() {
        // Öğrenci ekleme işlemi
        String username = JOptionPane.showInputDialog(frame, "Eklemek istediğiniz öğrencinin kullanıcı adı:");
        System.out.println("Yeni öğrenci eklendi: " + username);
    }*/

    // Öğrenci silme işlemi
    private void removeStudent() {
        String username = JOptionPane.showInputDialog(frame, "Silmek istediğiniz öğrencinin kullanıcı adını girin:");

        if (username != null && !username.trim().isEmpty()) {
            boolean success = Database.removeStudent(username);
            if (success) {
                JOptionPane.showMessageDialog(frame, "Öğrenci başarıyla silindi.", "Öğrenci Silme", JOptionPane.INFORMATION_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(frame, "Bu kullanıcı adıyla bir öğrenci bulunamadı.", "Hata", JOptionPane.ERROR_MESSAGE);
            }
        } else {
            JOptionPane.showMessageDialog(frame, "Geçersiz kullanıcı adı!", "Hata", JOptionPane.ERROR_MESSAGE);
        }
    }

    /*private void viewAllStudents() {
        // Tüm öğrencileri görüntüleme
        System.out.println("Tüm öğrenciler görüntüleniyor...");
        JOptionPane.showMessageDialog(frame, "Tüm öğrencileri görüntülemek için burayı kullanabilirsiniz.");
    }*/

    private void viewProfile() {
        // Personelin profilini görüntülemesi
        String profileInfo = "Kullanıcı Adı: " + staff.getUsername() + "\n" +
                "Rol: " + staff.getRole();

        JOptionPane.showMessageDialog(frame, profileInfo, "Profil Bilgileri", JOptionPane.INFORMATION_MESSAGE);
    }

    private void enterGrade() {
        // Öğrenci adı ve ders adı alınacak
        String studentUsername = JOptionPane.showInputDialog(frame, "Not girmek istediğiniz öğrencinin kullanıcı adını girin:");
        String courseName = JOptionPane.showInputDialog(frame, "Not girmek istediğiniz dersin adını girin:");
        String gradeInput = JOptionPane.showInputDialog(frame, "Dersin notunu girin:");

        try {
            int grade = Integer.parseInt(gradeInput);  // Girilen notu integer'a dönüştür

            // Öğrenciyi bulalım
            Student student = Database.getStudentByUsername(studentUsername);
            if (student == null) {
                JOptionPane.showMessageDialog(frame, "Bu kullanıcı bulunamadı!", "Hata", JOptionPane.ERROR_MESSAGE);
                return;
            }

            // Öğrenci bu dersi almış mı kontrol edelim
            boolean courseExists = false;
            for (Course course : student.getSelectedCourses()) {
                if (course.getCourseName().equals(courseName)) {
                    courseExists = true;
                    break;
                }
            }

            if (!courseExists) {
                JOptionPane.showMessageDialog(frame, "Öğrenci bu dersi almadığı için not giremezsiniz!", "Hata", JOptionPane.ERROR_MESSAGE);
                return;
            }

            // Notu öğrenciye ekle
            student.addGrade(courseName, grade);
            JOptionPane.showMessageDialog(frame, "Not başarıyla girildi!", "Başarılı", JOptionPane.INFORMATION_MESSAGE);
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(frame, "Geçersiz bir not girdiniz!", "Hata", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void viewAllStudents() {
        List<Student> students = Database.getAllStudents();

        if (students.isEmpty()) {
            JOptionPane.showMessageDialog(frame, "Hiç öğrenci bulunmamaktadır.", "Öğrenciler", JOptionPane.INFORMATION_MESSAGE);
        } else {
            StringBuilder studentList = new StringBuilder("Tüm Öğrenciler:\n");
            for (Student student : students) {
                studentList.append("Kullanıcı Adı: ").append(student.getUsername())
                        .append("\nSeçilen Dersler: ");

                // Öğrencinin seçtiği dersleri listele
                if (student.getSelectedCourses().isEmpty()) {
                    studentList.append("Ders seçilmedi");
                } else {
                    for (Course course : student.getSelectedCourses()) {
                        studentList.append(course.getCourseName()).append(" ");
                    }
                }
                studentList.append("\n\n");
            }
            JOptionPane.showMessageDialog(frame, studentList.toString(), "Öğrenciler", JOptionPane.INFORMATION_MESSAGE);
        }
    }

    // Öğrenci ekleme işlemi
    private void addStudent() {
        String username = JOptionPane.showInputDialog(frame, "Yeni öğrencinin kullanıcı adını girin:");
        String password = JOptionPane.showInputDialog(frame, "Yeni öğrencinin şifresini girin:");

        if (username != null && !username.trim().isEmpty() && password != null && !password.trim().isEmpty()) {
            boolean success = Database.addStudent(username, password);
            if (success) {
                JOptionPane.showMessageDialog(frame, "Yeni öğrenci başarıyla eklendi.", "Öğrenci Ekleme", JOptionPane.INFORMATION_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(frame, "Bu kullanıcı adı zaten var. Lütfen başka bir kullanıcı adı girin.", "Hata", JOptionPane.ERROR_MESSAGE);
            }
        } else {
            JOptionPane.showMessageDialog(frame, "Geçersiz kullanıcı adı veya şifre!", "Hata", JOptionPane.ERROR_MESSAGE);
        }
    }

}

