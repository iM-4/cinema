import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class Home extends JFrame {

    ImageIcon img = new ImageIcon("./Images/logo.png");
    JLabel logo = new JLabel(img);

    ImageIcon img_background = new ImageIcon("./Images/background4.png");
    JLabel background = new JLabel(img_background);

    Color
            Yellow = new Color(255, 211, 105),
            TextColor = new Color(238, 238, 238);

    JLabel Welcome = new JLabel();
    JButton start = new JButton("Start");

    public Home() {
        setTitle("Jeddah Cinema");
        setSize(350, 500);
        setLayout(null);

        // Setting the Welcome text
        String welcomeText = """
                Welcome to Jeddah Cinema App
                from our app you can choose
                 - Movie
                - Your seat
                - Food
                We hope you have fun using our app""";
        Welcome.setText(formatText(welcomeText));
        Welcome.setBounds(30, 90, 300, 300);
        Welcome.setForeground(TextColor);
        Welcome.setFont(new Font("Arial", Font.BOLD, 19));
        add(Welcome);

        // Start button
        start.setBounds(70, 350, 200, 40);
        start.setBackground(TextColor);
        add(start);

        start.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                new login();
                setVisible(false);
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                start.setBackground(Yellow);
            }

            @Override
            public void mouseExited(MouseEvent e) {
                start.setBackground(TextColor);
            }
        });

        // Logo and background
        logo.setBounds(70, 5, 200, 160);
        add(logo);
        background.setBounds(0, 0, 350, 500);
        add(background);

        // Finalize frame setup
        setLocationRelativeTo(null);
        setVisible(true);
    }

    private String formatText(String text) {
        return "<html>" + text.replace("\n", "<br>") + "</html>";
    }

}
