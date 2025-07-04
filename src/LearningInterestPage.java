import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class LearningInterestPage extends JFrame {

    private final String thoroughSubjects;

    public LearningInterestPage(String thoroughSubjects) {
        this.thoroughSubjects = thoroughSubjects;
        setTitle("People Interested in Learning");
        setSize(1390, 1080);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        getContentPane().setBackground(Color.WHITE);

        Font arialFont = new Font("Arial", Font.PLAIN, 14);

        // Nav bar
        JPanel navBar = new JPanel();
        navBar.setLayout(new BorderLayout());
        navBar.setBackground(new Color(0xC4F5FF));

        ImageIcon originalLogoIcon = new ImageIcon("src/resources/logo.jpg");
        Image logo = originalLogoIcon.getImage().getScaledInstance(150, 50, Image.SCALE_SMOOTH);
        ImageIcon logoIcon = new ImageIcon(logo);
        JLabel logoLabel = new JLabel(logoIcon);

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

        navBar.add(logoLabel, BorderLayout.WEST);

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
        gbc.anchor = GridBagConstraints.WEST;

        JLabel titleLabel = new JLabel("People Interested in Learning From Your Subjects");
        titleLabel.setFont(arialFont);

        JPanel resultsPanel = new JPanel();
        resultsPanel.setLayout(new BoxLayout(resultsPanel, BoxLayout.Y_AXIS));

        JScrollPane scrollPane = new JScrollPane(resultsPanel);
        scrollPane.setPreferredSize(new Dimension(1210, 900));

        gbc.gridx = 0;
        gbc.gridy = 0;
        body.add(titleLabel, gbc);

        gbc.gridy = 1;
        body.add(scrollPane, gbc);

        add(body, BorderLayout.CENTER);

        // Footer
        JPanel footer = new JPanel();
        footer.setBackground(Color.WHITE);
        JLabel copyrightLabel = new JLabel("©SKIBAR");
        footer.add(copyrightLabel);
        add(footer, BorderLayout.SOUTH);

        fetchInterestedStudents(resultsPanel);
    }

    private void fetchInterestedStudents(JPanel resultsPanel) {
        String url = "jdbc:mysql://localhost:0305/skibar";
        String user = "root";
        String pass = "myPass";

        String sql = "SELECT name, email, phone_number, class, thorough_subjects FROM learners WHERE thorough_subjects LIKE ?";

        try (Connection conn = DriverManager.getConnection(url, user, pass);
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, "%" + thoroughSubjects + "%");
            ResultSet resultSet = pstmt.executeQuery();

            while (resultSet.next()) {
                String studentInfo = "Name: " + resultSet.getString("name") + "<br>"
                        + "Class: " + resultSet.getString("class") + "<br>"
                        + "Thorough Subjects: " + resultSet.getString("thorough_subjects") + "<br>";

                JLabel infoLabel = new JLabel("<html>" + studentInfo + "</html>");
                infoLabel.setPreferredSize(new Dimension(550, 60));

                JButton selectButton = new JButton("Select");

                String email = resultSet.getString("email");
                String phone = resultSet.getString("phone_number");

                selectButton.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        JOptionPane.showMessageDialog(LearningInterestPage.this,
                                "Email: " + email + "\nPhone: " + phone);
                    }
                });

                resultsPanel.add(infoLabel);
                resultsPanel.add(selectButton);
                resultsPanel.add(Box.createRigidArea(new Dimension(0, 10)));
            }

            resultsPanel.revalidate();
            resultsPanel.repaint();

        } catch (SQLException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error fetching data from database: " + ex.getMessage());
        }
    }
}
