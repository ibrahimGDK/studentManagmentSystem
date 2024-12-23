import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LoginScreen {
    private JFrame frame;
    private JTextField usernameField;
    private JPasswordField passwordField;
    private JButton loginButton;
    private JLabel errorLabel;

    public LoginScreen() {
        frame = new JFrame("Öğrenci İşleri Otomasyonu - Giriş");
        //frame.setSize(400, 300);
        frame.setBounds(200,200,400,300);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout());

        // Panel for form fields
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(4, 2));

        JLabel usernameLabel = new JLabel("Kullanıcı Adı: ");
        usernameField = new JTextField();
        JLabel passwordLabel = new JLabel("Şifre: ");
        passwordField = new JPasswordField();

        panel.add(usernameLabel);
        panel.add(usernameField);
        panel.add(passwordLabel);
        panel.add(passwordField);

        // Error label for incorrect credentials
        errorLabel = new JLabel();
        errorLabel.setForeground(Color.RED);
        panel.add(errorLabel);

        // Login button
        loginButton = new JButton("Giriş Yap");
        panel.add(loginButton);

        // Add the panel to the frame
        frame.add(panel, BorderLayout.CENTER);

        // Action listener for the login button
        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String username = usernameField.getText();
                String password = new String(passwordField.getPassword());
                login(username, password);
            }
        });

        frame.setVisible(true);
    }

    // Login logic
    private void login(String username, String password) {
        // Burada, kullanıcı doğrulama işlemi yapılacak (Database sınıfı ile)
        if (Database.isValidUser(username, password)) {
            // Kullanıcı geçerli ise, ilgili kullanıcı türüne yönlendirilecek
            User user = Database.getUser(username);
            openDashboard(user);
        } else {
            // Geçersiz giriş durumunda hata mesajı
            errorLabel.setText("Geçersiz kullanıcı adı veya şifre!");
        }
    }

    // Kullanıcı türüne göre yönlendirme
    // (Aşağıdaki kodda DOWNCAST vardır)
    private void openDashboard(User user) {
        // Kullanıcı türüne göre ilgili ekran açılacak
        if (user.getRole().equals("Student")) {
            // Öğrenci ekranı
            new StudentDashboard((Student) user);
        } else if (user.getRole().equals("Faculty")) {
            // Öğretim Üyesi ekranı
            new FacultyDashboard((Faculty) user);
        } else if (user.getRole().equals("Staff")) {
            // Personel ekranı
            new StaffDashboard((Staff) user);
        }
        //frame.dispose();  // Login ekranını kapat
    }

    public static void main(String[] args) {
        new LoginScreen();

    }
}

