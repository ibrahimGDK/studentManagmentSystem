import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

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
        // Ders onaylama işlemi
        String courseName = JOptionPane.showInputDialog(frame, "Onaylamak istediğiniz dersin adı:");
        System.out.println("Öğretim üyesi " + courseName + " dersini onayladı.");
    }

    private void viewCourses() {
        // Öğretim üyesi derslerini görüntüler
        System.out.println("Öğretim üyesi derslerini görüntülüyor...");
        JOptionPane.showMessageDialog(frame, "Derslerinizi görüntülemek için burayı kullanabilirsiniz.");
    }

    private void viewProfile() {
        // Öğretim üyesinin profilini görüntüler
        System.out.println("Öğretim üyesi profili görüntüleniyor...");
        JOptionPane.showMessageDialog(frame, "Profilinizi görüntülemek için burayı kullanabilirsiniz.");
    }
}

