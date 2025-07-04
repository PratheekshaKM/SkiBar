import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class StudyMaterialsPage extends JFrame {

    public StudyMaterialsPage() {
        setTitle("Study Materials");
        setSize(600, 400);
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

        // Set font and margins for navigation bar buttons
        aboutUsBtn.setFont(arialFont);
        contactUsBtn.setFont(arialFont);
        aboutUsBtn.setBackground(new Color(0xC4F5FF));
        contactUsBtn.setBackground(new Color(0xC4F5FF));
        aboutUsBtn.setBorderPainted(false);
        contactUsBtn.setBorderPainted(false);
        aboutUsBtn.setMargin(new Insets(5, 10, 5, 10));
        contactUsBtn.setMargin(new Insets(5, 10, 5, 10));

        // Add logo to the left of the navigation bar
        navBar.add(logo, BorderLayout.WEST);

        // Create a panel for the buttons and align them to the right
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout(FlowLayout.RIGHT, 0, 0));
        buttonPanel.setBackground(new Color(0xC4F5FF));
        buttonPanel.add(aboutUsBtn);
        buttonPanel.add(contactUsBtn);

        // Add the button panel to the right of the navigation bar
        navBar.add(buttonPanel, BorderLayout.EAST);

        add(navBar, BorderLayout.NORTH);

        // Body
        JPanel body = new JPanel();
        body.setLayout(new BorderLayout());
        body.setOpaque(true);
        body.setBackground(Color.WHITE);

        // Center panel to hold the content
        JPanel centerPanel = new JPanel();
        centerPanel.setLayout(new BoxLayout(centerPanel, BoxLayout.Y_AXIS));
        centerPanel.setBackground(Color.WHITE);
        centerPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        // Add study materials
        JLabel materialsLabel = new JLabel("Study Materials:");
        materialsLabel.setFont(arialFont);
        centerPanel.add(materialsLabel);

        JTextArea materialsArea = new JTextArea(10, 40);
        materialsArea.setText("1. Study Material 1\n2. Study Material 2\n3. Study Material 3\n4. Study Material 4\n5. Study Material 5");
        materialsArea.setFont(arialFont);
        materialsArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(materialsArea);
        centerPanel.add(scrollPane);

        // Create a button to go back
        JButton backButton = new JButton("Back to Study Partners");
        backButton.setFont(arialFont);
        backButton.setBackground(new Color(0xC4F5FF));
        backButton.setBorderPainted(false);
        backButton.setMargin(new Insets(5, 10, 5, 10));

        centerPanel.add(backButton);

        // Add center panel to body
        body.add(centerPanel, BorderLayout.CENTER);

        add(body, BorderLayout.CENTER);

        // Add action listener for the back button
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                new StudyPartnersPage(new String[]{ "John Doe", "Jane Smith", "Alice Johnson" }).setVisible(true);
            }
        });
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new StudyMaterialsPage().setVisible(true);
        });
    }
}
