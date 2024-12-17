import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

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

        panel.add(selectCourseButton);
        panel.add(viewGradesButton);
        panel.add(viewProfileButton);

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

    private void selectCourse() {
        // Ders seçme işlemi burada yapılacak (Öğrencinin ders seçmesi)
        String courseName = JOptionPane.showInputDialog(frame, "Ders adı girin:");
        System.out.println("Öğrenci " + courseName + " dersini seçti.");
    }

    private void viewGrades() {
        // Öğrencinin notlarını görüntülemesi
        System.out.println("Öğrenci notlarını görüntülüyor...");
        JOptionPane.showMessageDialog(frame, "Notlarınızı görüntülemek için burayı kullanabilirsiniz.");
    }

    private void viewProfile() {
        // Öğrencinin profilini görüntülemesi
        System.out.println("Öğrenci profili görüntüleniyor...");
        JOptionPane.showMessageDialog(frame, "Profilinizi görüntülemek için burayı kullanabilirsiniz.");
    }
}

