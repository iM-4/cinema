import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.io.RandomAccessFile;


class login extends JFrame {
    // Images
    ImageIcon background_img = new ImageIcon("./Images/background4.png"),
            showPassword_img = new ImageIcon("./Images/showPassword.png"),
            hidePassword_img = new ImageIcon("./Images/HidePassword.png");

    JLabel background = new JLabel(background_img);

    Image
            editImage1 = showPassword_img.getImage().getScaledInstance(16, 16, Image.SCALE_SMOOTH),
            editImage2 = hidePassword_img.getImage().getScaledInstance(16, 16, Image.SCALE_SMOOTH);

    ImageIcon resizedIcon1 = new ImageIcon(editImage1), resizedIcon2 = new ImageIcon(editImage2);

    // Labels
    JLabel
            Login = new JLabel("Login"),
            UserName = new JLabel("User Name"),
            Password = new JLabel("Password"),
            Account = new JLabel("Create Account Here"),
            Error = new JLabel("User Name or password is incorrect");

    // Fields
    JTextField
            UserField = new JTextField(25);
    JPasswordField
            Pwd_Filed = new JPasswordField(10);

    // Buttons
    JButton login = new JButton("Login"),
            Guest = new JButton("Guest"),
            showPasswordButton = new JButton(resizedIcon1);

    // Colors
    Color
            Yellow = new Color(255, 211, 105),
            TextColor = new Color(238, 238, 238),
            errorColor = new Color(255, 0, 58);

    private static String
            user,
            email;

    public login() {
        setTitle("Login Form");
        setSize(350, 500);
        setLayout(null);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);

        // Setup components
        setLabel(Login, 125, 70, 35, TextColor);
        setLabel(UserName, 70, 120, 15, TextColor);
        setLabel(Password, 70, 190, 15, TextColor);
        setLabel(Account, 70, 245, 10, TextColor);
        setLabel(Error, 70, 260, 10, errorColor);
        Error.setVisible(false);


        setFiled(UserField, 70, 150, 200, 30);
        setFiled(Pwd_Filed, 70, 220, 200, 30);

        setupButton(login, 320);
        setupButton(Guest, 370);
        // Create action for Account Button
        Account.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                new Register();
                setVisible(false);
            }

            public void mouseEntered(MouseEvent e) { Account.setForeground(Yellow); }
            public void mouseExited(MouseEvent e) { Account.setForeground(TextColor); }
        });
        // Guest Button
        Guest.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                user = "Stranger";
                new Movie();
                setVisible(false);
            }

            public void mouseEntered(MouseEvent e) { Guest.setBackground(Yellow); }
            public void mouseExited(MouseEvent e) { Guest.setBackground(TextColor); }
        });

        // Login Button
        login.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                performLogin();
            }

            public void mouseEntered(MouseEvent e) { login.setBackground(Yellow); }
            public void mouseExited(MouseEvent e) { login.setBackground(TextColor); }
        });

        // Show/Hide Password Button
        setupPasswordToggleButton();

        // Background
        background.setBounds(0, 0, 350, 500);
        add(background);
        setVisible(true);
    }

    private void setupPasswordToggleButton() {
        showPasswordButton.setBounds(270, 220, 25, 25);
        showPasswordButton.setBorderPainted(false);
        showPasswordButton.setContentAreaFilled(false);
        showPasswordButton.setFocusPainted(false);
        showPasswordButton.setToolTipText("Show Password");
        add(showPasswordButton);

        showPasswordButton.addActionListener(_ -> {
            if (Pwd_Filed.getEchoChar() != (char) 0) {
                Pwd_Filed.setEchoChar((char) 0); // Show password
                showPasswordButton.setIcon(resizedIcon2);
                showPasswordButton.setToolTipText("Hide Password");
            } else {
                Pwd_Filed.setEchoChar('•'); // Hide password
                showPasswordButton.setIcon(resizedIcon1);
                showPasswordButton.setToolTipText("Show Password");
            }
        });
    }
    private void performLogin() {
        try {
            if (validateInput()) {
                Error.setVisible(false);
                JOptionPane.showMessageDialog(null, "Welcome " + UserField.getText());
                user = UserField.getText();
                new Movie();
                setVisible(false);
            } else  if(UserField.getText().trim().isEmpty() && !(Pwd_Filed.getPassword().length == 0)) {
                JOptionPane.showMessageDialog(null, "Username can't be empty.", "Error", JOptionPane.ERROR_MESSAGE);
                UserName.setForeground(errorColor);
                Password.setForeground(TextColor);
            }else if(Pwd_Filed.getPassword().length == 0 && !(UserField.getText().trim().isEmpty())) {
                JOptionPane.showMessageDialog(null, "Password can't be empty.", "Error", JOptionPane.ERROR_MESSAGE);
                Password.setForeground(errorColor);
                UserName.setForeground(TextColor);
            } else if (UserField.getText().trim().isEmpty() && Pwd_Filed.getPassword().length == 0) {
                JOptionPane.showMessageDialog(null, "Username and Password can't be empty.", "Error", JOptionPane.ERROR_MESSAGE);
                UserName.setForeground(errorColor);
                Password.setForeground(errorColor);
            } else {
                Error.setVisible(true);
                UserName.setForeground(errorColor);
                Password.setForeground(errorColor);
            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    public void setFiled(JTextField field, int x, int y, int width, int height) {
        field.setOpaque(false);
        field.setBorder(null);
        field.setForeground(TextColor);
        field.setBorder(BorderFactory.createMatteBorder(0, 0, 2, 0, TextColor));
        field.setBounds(x, y, width, height);
        add(field);
    }

    public void setLabel(JLabel label, int x, int y, int size, Color color) {
        label.setFont(new Font("Arial", Font.BOLD, size));
        label.setForeground(color);
        label.setBounds(x, y, 200, 35);
        add(label);
    }

    private void setupButton(JButton button, int y) {
        button.setBackground(TextColor);
        button.setFont(new Font("Sans", Font.BOLD, 20));
        button.setBounds(70, y, 200, 30);
        button.setFocusPainted(false);
        add(button);
    }

    private boolean validateInput() {
        try (RandomAccessFile raf = new RandomAccessFile("details.txt", "r")) {
            String line;
            while ((line = raf.readLine()) != null) {
                String[] parts = line.split(" ");

                if (parts.length == 5) {
                    String fileUsername = parts[2].trim();
                    email = parts[3].trim();
                    String filePassword = parts[4].trim();
                    if (fileUsername.equals(UserField.getText()) && filePassword.equals(new String(Pwd_Filed.getPassword()))) {
                        return true;
                    }
                }
            }
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Error reading user data: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
        return false;
    }

    public static String getUserName() {
        return user;
    }
    public static String getEmail(){
        return email;
    }
}
