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
        frame.setSize(400, 300);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout());
        frame.setLocationRelativeTo(null); // Ekranın ortasında gözükmesi için

        // Üst başlık paneli
        JPanel titlePanel = new JPanel();
        JLabel titleLabel = new JLabel("Giriş Yap");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 20));
        titlePanel.add(titleLabel);
        frame.add(titlePanel, BorderLayout.NORTH);

        // Form alanları için panel
        JPanel formPanel = new JPanel();
        formPanel.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);

        JLabel usernameLabel = new JLabel("Kullanıcı Adı:");
        usernameLabel.setFont(new Font("Arial", Font.PLAIN, 14));
        usernameField = new JTextField(15);

        JLabel passwordLabel = new JLabel("Şifre:");
        passwordLabel.setFont(new Font("Arial", Font.PLAIN, 14));
        passwordField = new JPasswordField(15);

        errorLabel = new JLabel();
        errorLabel.setForeground(Color.RED);

        // Kullanıcı adı
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.EAST;
        formPanel.add(usernameLabel, gbc);

        gbc.gridx = 1;
        gbc.anchor = GridBagConstraints.WEST;
        formPanel.add(usernameField, gbc);

        // Şifre
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.anchor = GridBagConstraints.EAST;
        formPanel.add(passwordLabel, gbc);

        gbc.gridx = 1;
        gbc.anchor = GridBagConstraints.WEST;
        formPanel.add(passwordField, gbc);

        // Hata mesajı
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        formPanel.add(errorLabel, gbc);

        frame.add(formPanel, BorderLayout.CENTER);


        JPanel buttonPanel = new JPanel();
        loginButton = new JButton("Giriş Yap");
        loginButton.setFont(new Font("Arial", Font.BOLD, 14));
        buttonPanel.add(loginButton);
        frame.add(buttonPanel, BorderLayout.SOUTH);


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

    // Giriş yapıyoruz
    private void login(String username, String password) {
        if (Database.isValidUser(username, password)) {
            User user = Database.getUser(username);
            openDashboard(user);
            //frame.dispose();
        } else {
            errorLabel.setText("Geçersiz kullanıcı adı veya şifre!");
        }
    }

    private void openDashboard(User user) {
        if (user.getRole().equals("Student")) {
            new StudentDashboard((Student) user);
        } else if (user.getRole().equals("Faculty")) {
            new FacultyDashboard((Faculty) user);
        } else if (user.getRole().equals("Staff")) {
            new StaffDashboard((Staff) user);
        }
    }

    public static void main(String[] args) {
        new LoginScreen();
    }
}
