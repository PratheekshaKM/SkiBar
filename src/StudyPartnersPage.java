import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class StudyPartnersPage extends JFrame {

    public StudyPartnersPage(String[] studyPartners) {
        setTitle("Study Partners");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        // Set background color for the entire page
        getContentPane().setBackground(Color.WHITE);

        // Set font to Arial
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

        // Add study partners list
        JLabel partnersLabel = new JLabel("Select a study partner:");
        partnersLabel.setFont(arialFont);
        partnersLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        centerPanel.add(partnersLabel);

        JButton selectButton = null;
        String[] email = {"doejohn@example.com", "jane@example.com", "alice@example.com"};
        long[] phone = {123456789, 987654321, 741852963};
        for (int i=0; i<3; i++) {
            JPanel partnerPanel = new JPanel();
            partnerPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
            partnerPanel.setBackground(Color.WHITE);
            JLabel partnerLabel = new JLabel(studyPartners[i]);
            partnerLabel.setFont(arialFont);
            selectButton = new JButton("Select");
            selectButton.setFont(arialFont);
            selectButton.setBackground(new Color(0xC4F5FF));
            selectButton.setBorderPainted(false);
            selectButton.setMargin(new Insets(5, 10, 5, 10));
            partnerPanel.add(partnerLabel);
            partnerPanel.add(selectButton);
            centerPanel.add(partnerPanel);

            int finalI = i;
            selectButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    JOptionPane.showMessageDialog(StudyPartnersPage.this,
                            "Email: " + email[finalI] + "\nPhone: " + phone[finalI]);
                }
            });
        }


        body.add(centerPanel, BorderLayout.CENTER);

        // Footer
        JPanel footer = new JPanel();
        footer.setBackground(new Color(0xC4F5FF));
        JLabel copyrightLabel = new JLabel("© 2024 School Management System");
        copyrightLabel.setFont(arialFont);
        footer.add(copyrightLabel);
        add(footer, BorderLayout.SOUTH);

        // Buttons for navigation
        JPanel navButtonsPanel = new JPanel();
        navButtonsPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
        navButtonsPanel.setBackground(Color.WHITE);
        JButton studyMaterialsButton = new JButton("Study Materials");
        JButton sorryButton = new JButton("Sorry Page");

        studyMaterialsButton.setFont(arialFont);
        studyMaterialsButton.setBackground(new Color(0xC4F5FF));
        studyMaterialsButton.setBorderPainted(false);
        studyMaterialsButton.setMargin(new Insets(5, 10, 5, 10));

        sorryButton.setFont(arialFont);
        sorryButton.setBackground(new Color(0xC4F5FF));
        sorryButton.setBorderPainted(false);
        sorryButton.setMargin(new Insets(5, 10, 5, 10));

        navButtonsPanel.add(studyMaterialsButton);
        navButtonsPanel.add(sorryButton);

        body.add(navButtonsPanel, BorderLayout.SOUTH);

        add(body, BorderLayout.CENTER);

        // Add action listeners for navigation buttons
        studyMaterialsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                new StudyMaterialsPage().setVisible(true);
            }
        });

        sorryButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                new SorryPage().setVisible(true);
            }
        });
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new StudyPartnersPage(new String[]{ "John Doe", "Jane Smith", "Alice Johnson" }).setVisible(true);
        });
    }
}
