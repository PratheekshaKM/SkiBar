import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class FileUploadPage extends JFrame {

    public FileUploadPage(JFrame parentFrame, String thoroughSubjects) {
        setTitle("Upload PDF Files");
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

        navBar.add(logo, BorderLayout.WEST);

        // Create a panel for the buttons and align them to the right
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout(FlowLayout.RIGHT, 0, 0));
        buttonPanel.setBackground(new Color(0xC4F5FF));
        buttonPanel.add(aboutUsBtn);
        buttonPanel.add(contactUsBtn);

        navBar.add(buttonPanel, BorderLayout.EAST);

        add(navBar, BorderLayout.NORTH);

        // Body
        JPanel body = new JPanel();
        body.setLayout(new GridBagLayout());
        body.setOpaque(true);
        body.setBackground(Color.WHITE);

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.anchor = GridBagConstraints.CENTER;

        JLabel titleLabel = new JLabel("Upload PDF Files Related to Your Thorough Subjects");
        titleLabel.setFont(arialFont);

        // Create file chooser and upload button
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
        JButton uploadButton = new JButton("Upload PDF");
        uploadButton.setFont(arialFont);
        uploadButton.setBackground(new Color(0xC4F5FF));

        // Add components to the body panel
        gbc.gridx = 0;
        gbc.gridy = 0;
        body.add(titleLabel, gbc);

        gbc.gridy = 1;
        body.add(fileChooser, gbc);

        gbc.gridy = 2;
        body.add(uploadButton, gbc);

        add(body, BorderLayout.CENTER);

        // Footer
        JPanel footer = new JPanel();
        footer.setBackground(Color.WHITE);
        JLabel copyrightLabel = new JLabel("©SKIBAR");
        footer.add(copyrightLabel);
        add(footer, BorderLayout.SOUTH);

        // Upload Button Action
        uploadButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int returnValue = fileChooser.showOpenDialog(null);
                if (returnValue == JFileChooser.APPROVE_OPTION) {
                    java.io.File selectedFile = fileChooser.getSelectedFile();
                    JOptionPane.showMessageDialog(null, "Uploaded: " + selectedFile.getName());

                    new ThankYouDialog(FileUploadPage.this).setVisible(true);
                }
            }
        });
    }
}
