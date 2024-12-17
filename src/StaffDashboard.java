import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class StaffDashboard {
    private JFrame frame;
    private Staff staff;

    public StaffDashboard(Staff staff) {
        this.staff = staff;

        frame = new JFrame(staff.getUsername() + " - Personel Paneli");
        frame.setSize(400, 300);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout());

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(4, 1));

        JButton addStudentButton = new JButton("Öğrenci Ekle");
        JButton removeStudentButton = new JButton("Öğrenci Sil");
        JButton viewAllStudentsButton = new JButton("Tüm Öğrencileri Görüntüle");
        JButton viewProfileButton = new JButton("Profil Görüntüle");

        panel.add(addStudentButton);
        panel.add(removeStudentButton);
        panel.add(viewAllStudentsButton);
        panel.add(viewProfileButton);

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

    private void addStudent() {
        // Öğrenci ekleme işlemi
        String username = JOptionPane.showInputDialog(frame, "Eklemek istediğiniz öğrencinin kullanıcı adı:");
        System.out.println("Yeni öğrenci eklendi: " + username);
    }

    private void removeStudent() {
        // Öğrenci silme işlemi
        String username = JOptionPane.showInputDialog(frame, "Silmek istediğiniz öğrencinin kullanıcı adı:");
        System.out.println("Öğrenci silindi: " + username);
    }

    private void viewAllStudents() {
        // Tüm öğrencileri görüntüleme
        System.out.println("Tüm öğrenciler görüntüleniyor...");
        JOptionPane.showMessageDialog(frame, "Tüm öğrencileri görüntülemek için burayı kullanabilirsiniz.");
    }

    private void viewProfile() {
        // Personelin profilini görüntülemesi
        System.out.println("Personel profili görüntüleniyor...");
        JOptionPane.showMessageDialog(frame, "Profilinizi görüntülemek için burayı kullanabilirsiniz.");
    }
}

