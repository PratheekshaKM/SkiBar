import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class userinfo extends JFrame {

    private final JTextField thoroughSubjectsField;

    public userinfo() {
        setTitle("SkiBar");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        getContentPane().setBackground(new Color(0xC4F5FF));

        Font arialFont = new Font("Arial", Font.PLAIN, 14);

        // Nav bar
        JPanel navBar = new JPanel();
        navBar.setLayout(new BorderLayout());
        navBar.setBackground(new Color(0xC4F5FF));

        // Logo image
        ImageIcon originalLogoIcon = new ImageIcon("src/resources/logo.jpg");
        Image logo = originalLogoIcon.getImage().getScaledInstance(150, 50, Image.SCALE_SMOOTH);
        ImageIcon logoIcon = new ImageIcon(logo);
        JLabel logoLable = new JLabel(logoIcon);

        navBar.add(logoLable, BorderLayout.WEST);

        add(navBar, BorderLayout.NORTH);

        // Body
        JPanel body = new JPanel();
        body.setLayout(new GridBagLayout());
        body.setOpaque(true);
        body.setBackground(Color.WHITE);

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.anchor = GridBagConstraints.CENTER;

        JLabel classLabel = new JLabel("Enter your class:");
        JTextField classField = new JTextField(15);
        JLabel thoroughSubjectsLabel = new JLabel("Subjects you want to learn:");
        thoroughSubjectsField = new JTextField(15);
        JLabel learnSubjectsLabel = new JLabel("Subjects you are thorough in:");
        JTextField learnSubjectsField = new JTextField(15);
        JButton submitButton = new JButton("Submit");

        classLabel.setFont(arialFont);
        thoroughSubjectsLabel.setFont(arialFont);
        learnSubjectsLabel.setFont(arialFont);
        classField.setFont(arialFont);
        thoroughSubjectsField.setFont(arialFont);
        learnSubjectsField.setFont(arialFont);
        submitButton.setFont(arialFont);

        gbc.gridx = 0;
        gbc.gridy = 0;
        body.add(classLabel, gbc);

        gbc.gridy = 1;
        body.add(classField, gbc);

        gbc.gridy = 4;
        body.add(thoroughSubjectsLabel, gbc);

        gbc.gridy = 5;
        body.add(thoroughSubjectsField, gbc);

        gbc.gridy = 2;
        body.add(learnSubjectsLabel, gbc);

        gbc.gridy = 3;
        body.add(learnSubjectsField, gbc);

        gbc.gridy = 6;
        body.add(submitButton, gbc);

        add(body, BorderLayout.CENTER);

        // Footer
        JPanel footer = new JPanel();
        footer.setBackground(Color.WHITE);
        JLabel copyrightLabel = new JLabel("©SKIBAR");
        footer.add(copyrightLabel);
        add(footer, BorderLayout.SOUTH);

        // Submit Button Action
        submitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String thoroughSubjects = thoroughSubjectsField.getText();
                new KnowledgeSharingDialog(userinfo.this, thoroughSubjects).setVisible(true);
                dispose();
            }
        });
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            userinfo gui = new userinfo();
            gui.setVisible(true);
        });
    }
}
