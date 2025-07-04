import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ThankYouDialog extends JFrame {

    public ThankYouDialog(FileUploadPage fileUploadPage) {
        setTitle("Thank You");
        setSize(400, 200);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        getContentPane().setBackground(Color.WHITE);
        Font arialFont = new Font("Arial", Font.PLAIN, 14);

        // Navigation bar
        JPanel navBar = new JPanel();
        navBar.setLayout(new BorderLayout());
        navBar.setBackground(new Color(0xC4F5FF));

        // Load and resize logo image
        ImageIcon originalLogoIcon = new ImageIcon("src/resources/logo.jpg");
        Image logoImage = originalLogoIcon.getImage().getScaledInstance(150, 50, Image.SCALE_SMOOTH);
        ImageIcon logoIcon = new ImageIcon(logoImage);
        JLabel logo = new JLabel(logoIcon);

        // Buttons
        JButton aboutUsBtn = new JButton("About Us");
        JButton contactUsBtn = new JButton("Contact Us");

        aboutUsBtn.setFont(arialFont);
        contactUsBtn.setFont(arialFont);
        aboutUsBtn.setBackground(new Color(0xC4F5FF));
        contactUsBtn.setBackground(new Color(0xC4F5FF));
        aboutUsBtn.setBorderPainted(false);
        contactUsBtn.setBorderPainted(false);
        aboutUsBtn.setMargin(new Insets(5, 10, 5, 10));
        contactUsBtn.setMargin(new Insets(5, 10, 5, 10));

        // Logo to the left of the navigation bar
        navBar.add(logo, BorderLayout.WEST);

        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout(FlowLayout.RIGHT, 0, 0));
        buttonPanel.setBackground(new Color(0xC4F5FF));
        buttonPanel.add(aboutUsBtn);
        buttonPanel.add(contactUsBtn);
        navBar.add(buttonPanel, BorderLayout.EAST);
        add(navBar, BorderLayout.NORTH);

        // Body
        JPanel body = new JPanel();
        body.setLayout(new BorderLayout());
        body.setOpaque(true);
        body.setBackground(Color.WHITE);

        // Message label
        JLabel messageLabel = new JLabel("Thank you for sharing your knowledge!", SwingConstants.CENTER);
        messageLabel.setFont(arialFont);
        body.add(messageLabel, BorderLayout.CENTER);

        // Continue button
        JButton continueButton = new JButton("To continue your studying click here");
        continueButton.setFont(arialFont);
        continueButton.setBackground(new Color(0xC4F5FF));
        continueButton.setBorderPainted(false);
        continueButton.setMargin(new Insets(5, 10, 5, 10));

        body.add(continueButton, BorderLayout.SOUTH);

        add(body, BorderLayout.CENTER);

        // Action listener to the continue button
        continueButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                String[] dummyPartners = {"John Doe", "Jane Smith", "Alice Johnson"};
                new StudyPartnersPage(dummyPartners).setVisible(true);
            }
        });
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            FileUploadPage FileUploadPage = null;
            new ThankYouDialog(FileUploadPage).setVisible(true);
        });
    }
}
