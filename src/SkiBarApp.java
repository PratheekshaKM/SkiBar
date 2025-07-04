import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
import javax.swing.*;

public class SkiBarApp{

    JFrame frame = new JFrame();

    private final CardLayout cardLayout = new CardLayout();
    private final JPanel formPanel = new JPanel(cardLayout);

    public SkiBarApp() {
        frame.setTitle("SkiBar");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(1210, 990);
        frame.setLocationRelativeTo(null);

        // Main 
        JPanel container = new JPanel(new BorderLayout());
        frame.setContentPane(container);

        // Navbar
        JPanel navbar = createNavbar();
        container.add(navbar, BorderLayout.NORTH);

        // Main content with scroll pane
        JPanel mainContent = createMainContent();
        JScrollPane scrollPane = new JScrollPane(mainContent);
        container.add(scrollPane, BorderLayout.CENTER);

        // Footer
        JPanel footer = createFooter();
        container.add(footer, BorderLayout.SOUTH);
    }

    private JPanel createNavbar() {
        
        JPanel navbar = new JPanel(new BorderLayout());
        navbar.setBackground(new Color(0xC4F5FF));

        ImageIcon logoIcon = new ImageIcon(new ImageIcon("src/resources/logo.jpg").getImage().getScaledInstance(150, 50, Image.SCALE_SMOOTH));
        JLabel logo = new JLabel(logoIcon);
        navbar.add(logo, BorderLayout.WEST);

        // Menu
        JPanel menu = new JPanel();
        menu.setBackground(new Color(0xC4F5FF));
        JLabel aboutUs = new JLabel("About Us   ");
        aboutUs.setFont(new Font("Arial", Font.PLAIN, 18));
        aboutUs.setForeground(Color.BLACK);
        JLabel contactUs = new JLabel("Contact Us      ");
        contactUs.setFont(new Font("Arial", Font.PLAIN, 18));
        contactUs.setForeground(Color.BLACK);
        menu.add(aboutUs);
        menu.add(Box.createRigidArea(new Dimension(20, 0)));
        menu.add(contactUs);
        navbar.add(menu, BorderLayout.EAST);

        return navbar;
    }

    private JPanel createMainContent() {
        JPanel mainContent = new JPanel(new BorderLayout());

        // Welcome Message
        JPanel welcomePanel = new JPanel(new BorderLayout());
        JLabel welcomeMessage = new JLabel("<html><center><h2><br>Welcome to</h2><h1>SKIBAR</h1><h5><i>BARTER FOR BRILLIANCE</i></h5></center></html>", JLabel.CENTER);
        welcomePanel.add(welcomeMessage, BorderLayout.NORTH);
        mainContent.add(welcomePanel, BorderLayout.NORTH);

        // Login/Signup and QR
        JPanel formAndQRPanel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.BOTH;
        gbc.insets = new Insets(10, 10, 10, 10);

        // Login/Signup form
        formPanel.setPreferredSize(new Dimension(400, 300));
        formPanel.add(createLoginForm(), "login");
        formPanel.add(createSignupForm(), "signup");
        cardLayout.show(formPanel, "login");

        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.weightx = 0.5;
        formAndQRPanel.add(formPanel, gbc);

        gbc.gridx = 1;
        gbc.gridy = 0;
        gbc.weightx = 0.5;
        ImageIcon logoIcon2 = new ImageIcon(new ImageIcon("src/resources/qrcode.png").getImage().getScaledInstance(350, 350, Image.SCALE_SMOOTH));
        JLabel logo = new JLabel(logoIcon2);
        formAndQRPanel.add(logo, gbc);
        gbc.gridx = 1;
        gbc.gridy = 1;
        JLabel altText = new JLabel("<html>Scan the above QR or <a href=https://gamma.app/docs/Unlock-Your-Potential-with-SkiBar-o0zm8biwh9vl73x> click here</a> to learn about our project mission, and everything about our complete <u><b><strong>IDEA</strong></b></u></html>");
        altText.setHorizontalAlignment(JLabel.CENTER);
        formAndQRPanel.add(altText, gbc);

        mainContent.add(formAndQRPanel, BorderLayout.CENTER);

        // Additional content
        JPanel additionalContentPanel = new JPanel(new GridLayout(3, 1));
        additionalContentPanel.add(createAdditionalContent());
        additionalContentPanel.add(createHowItWorksContent());
        additionalContentPanel.add(createKeyFeaturesContent());
        mainContent.add(additionalContentPanel, BorderLayout.SOUTH);

        return mainContent;
    }

    private JPanel createLoginForm() {
        JPanel loginForm = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(5, 5, 5, 5);

        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        loginForm.add(new JLabel("Login", JLabel.CENTER), gbc);

        gbc.gridy++;
        gbc.gridwidth = 1;
        loginForm.add(new JLabel("Username:"), gbc);

        gbc.gridx = 1;
        JTextField usernameField = new JTextField(15);
        loginForm.add(usernameField, gbc);

        gbc.gridx = 0;
        gbc.gridy++;
        loginForm.add(new JLabel("Password:"), gbc);

        gbc.gridx = 1;
        JPasswordField passwordField = new JPasswordField(15);
        loginForm.add(passwordField, gbc);

        gbc.gridx = 0;
        gbc.gridy++;
        gbc.gridwidth = 2;
        JButton loginButton = new JButton("Login");
        loginForm.add(loginButton, gbc);

        loginButton.addActionListener(new ActionListener() {
            private Component JFrame;
            private Component SkiBarApp;

            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                String username = usernameField.getText();
                String password = new String(passwordField.getPassword());
                if(username.isEmpty() || password.isEmpty()){
                    JOptionPane.showMessageDialog(this.JFrame, "All fields must be filled", "Input error!", JOptionPane.ERROR_MESSAGE);
                } else {
                    try {
                        checkLoginDetails(username, password);
                    } catch (SQLException e) {
                    }
                }
            }

            private void checkLoginDetails(String username, String password) throws SQLException{

                try {
                    Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:0305/skibar", "root", "pass");

                    String sql = "SELECT * FROM users WHERE username = ? AND password = ?";
                    PreparedStatement statement = connection.prepareStatement(sql);
                    statement.setString(1, username);
                    statement.setString(2, password);

                    ResultSet resultSet = statement.executeQuery();

                    if (resultSet.next()) {
                        JOptionPane.showMessageDialog(this.SkiBarApp, "Login successful!");
                        frame.dispose();
                        userinfo userinfoPage = new userinfo();
                        userinfoPage.setVisible(true);
                    } else {
                        JOptionPane.showMessageDialog(this.SkiBarApp, "Invalid username or password.");
                    }

                    connection.close();
                } catch (Exception ex) {
                    ex.printStackTrace();
                    JOptionPane.showMessageDialog(this.SkiBarApp, "Database error: " + ex.getMessage());
                }

            }
        });

        gbc.gridy++;
        JLabel signupLink = new JLabel("Don't have an account? Sign Up", JLabel.CENTER);
        signupLink.setForeground(Color.BLUE.darker());
        signupLink.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        signupLink.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                cardLayout.show(formPanel, "signup");
            }
        });
        loginForm.add(signupLink, gbc);

        return loginForm;
    }

    private JPanel createSignupForm() {
        JPanel signupForm = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(5, 5, 5, 5);

        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        signupForm.add(new JLabel("Sign Up", JLabel.CENTER), gbc);

        gbc.gridy++;
        gbc.gridwidth = 1;
        signupForm.add(new JLabel("Name:"), gbc);

        gbc.gridx = 1;
        JTextField nameField = new JTextField(15);
        signupForm.add(nameField, gbc);

        gbc.gridx = 0;
        gbc.gridy++;
        signupForm.add(new JLabel("Email:"), gbc);

        gbc.gridx = 1;
        JTextField emailField = new JTextField(15);
        signupForm.add(emailField, gbc);

        gbc.gridx = 0;
        gbc.gridy++;
        signupForm.add(new JLabel("Phone number:"), gbc);

        gbc.gridx = 1;
        JTextField phnoField = new JTextField(15);
        signupForm.add(phnoField, gbc);

        gbc.gridx = 0;
        gbc.gridy++;
        signupForm.add(new JLabel("Password:"), gbc);

        gbc.gridx = 1;
        JPasswordField passwordField = new JPasswordField(15);
        signupForm.add(passwordField, gbc);

        gbc.gridx = 0;
        gbc.gridy++;
        signupForm.add(new JLabel("Re-enter Password:"), gbc);

        gbc.gridx = 1;
        JPasswordField confirmPasswordField = new JPasswordField(15);
        signupForm.add(confirmPasswordField, gbc);

        gbc.gridx = 0;
        gbc.gridy++;
        gbc.gridwidth = 2;
        JButton signupButton = new JButton("Sign Up");
        signupForm.add(signupButton, gbc);

        gbc.gridy++;
        JLabel loginLink = new JLabel("Already have an account? Login", JLabel.CENTER);
        loginLink.setForeground(Color.BLUE.darker());
        loginLink.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        loginLink.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                cardLayout.show(formPanel, "login");
            }
        });
        signupForm.add(loginLink, gbc);

        signupButton.addActionListener(new ActionListener() {
            private Component SkiBarApp;

            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                String name = nameField.getText();
                String email = emailField.getText();
                long phno = Long.parseLong(phnoField.getText());
                String password = new String(passwordField.getPassword());
                String confirmPassword = new String(confirmPasswordField.getPassword());

                if (name.isEmpty() || email.isEmpty() || password.isEmpty() || confirmPassword.isEmpty()) {
                    JOptionPane.showMessageDialog(frame, "All fields must be filled", "Input error!", JOptionPane.ERROR_MESSAGE);
                } else if (!password.equals(confirmPassword)) {
                    JOptionPane.showMessageDialog(frame, "Passwords do not match", "Input error!", JOptionPane.ERROR_MESSAGE);
                } else {
                    registerNewUser(name, email, phno, password);
                }
            }

            private void registerNewUser(String name, String email, long phno, String password) {
                try {
                    Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:0305/skibar", "root", "pass");

                    String userSql = "INSERT INTO users (username, password) VALUES (?, ?)";
                    PreparedStatement userStatement = connection.prepareStatement(userSql);
                    userStatement.setString(1, email);
                    userStatement.setString(2, password);
                    userStatement.executeUpdate();

                    String detailsSql = "INSERT INTO user_details (name, email, password, phno) VALUES (?, ?, ?, ?)";
                    PreparedStatement detailsStatement = connection.prepareStatement(detailsSql);
                    detailsStatement.setString(1, name);
                    detailsStatement.setString(2, email);
                    detailsStatement.setString(3, password);
                    detailsStatement.setLong(4, phno);
                    detailsStatement.executeUpdate();

                    userStatement.close();
                    detailsStatement.close();
                    connection.close();

                    JOptionPane.showMessageDialog(this.SkiBarApp, "Registration successful!");
                    cardLayout.show(formPanel, "login");
                } catch (SQLException ex) {
                    ex.printStackTrace();
                    JOptionPane.showMessageDialog(this.SkiBarApp, "Database error: " + ex.getMessage());
                }
            }
        });
        return signupForm;
    }

    private JPanel createAdditionalContent() {
        JPanel additionalContent = new JPanel(new BorderLayout());
        JLabel title = new JLabel("<html><br>Why SkiBar?</html>", JLabel.CENTER);
        title.setFont(new Font("Serif", Font.BOLD, 25));
        additionalContent.add(title, BorderLayout.NORTH);
        JLabel content = new JLabel("To explore the world where knowledge is not for sale!", JLabel.CENTER);
        additionalContent.add(content, BorderLayout.CENTER);
        return additionalContent;
    }

    private JPanel createHowItWorksContent() {
        JPanel howItWorksContent = new JPanel(new BorderLayout());
        JLabel title = new JLabel("How SkillBarter Works", JLabel.CENTER);
        title.setFont(new Font("Serif", Font.BOLD, 18));
        howItWorksContent.add(title, BorderLayout.NORTH);
        String steps = "<html><ol><li>Sign up for an account to join the SkillBarter community.</li>"
                + "<li>Browse through available skills and find someone offering what you're looking for.</li>"
                + "<li>Propose a skill exchange and negotiate the terms with the other party.</li>"
                + "<li>Once the exchange is agreed upon, meet up or schedule a virtual session to share your skills.</li></ol></html>";
        JLabel content = new JLabel(steps, JLabel.CENTER);
        howItWorksContent.add(content, BorderLayout.CENTER);
        return howItWorksContent;
    }

    private JPanel createKeyFeaturesContent() {
        JPanel keyFeaturesContent = new JPanel(new BorderLayout());
        JLabel title = new JLabel("Key Features", JLabel.CENTER);
        title.setFont(new Font("Serif", Font.BOLD, 18));
        keyFeaturesContent.add(title, BorderLayout.NORTH);
        String features = "<html><ul><li>Search for specific skills or browse categories.</li>"
                + "<li>Message other users to discuss potential skill exchanges.</li>"
                + "<li>Rate and review users based on your experience.</li>"
                + "<li>Join local meetups and workshops organized by SkillBarter community members.</li></ul></html>";
        JLabel content = new JLabel(features, JLabel.CENTER);
        keyFeaturesContent.add(content, BorderLayout.CENTER);
        return keyFeaturesContent;
    }

    private JPanel createFooter() {
        JPanel footer = new JPanel(new BorderLayout());
        footer.setBackground(Color.DARK_GRAY);

        JPanel contactInfo = new JPanel(new GridLayout(2, 1));
        contactInfo.setBackground(Color.DARK_GRAY);
        contactInfo.setForeground(Color.WHITE);
        JLabel contactTitle = new JLabel("Contact Us", JLabel.CENTER);
        contactTitle.setForeground(Color.WHITE);
        JLabel contactDetails = new JLabel("<html>Email: example@example.com<br>Phone: +1234567890</html>", JLabel.CENTER);
        contactDetails.setForeground(Color.WHITE);
        contactInfo.add(contactTitle);
        contactInfo.add(contactDetails);
        footer.add(contactInfo, BorderLayout.WEST);

        JLabel copyright = new JLabel("<html>&copy; 2024 SkiBar. All rights reserved.</html>", JLabel.CENTER);
        copyright.setForeground(Color.WHITE);
        footer.add(copyright, BorderLayout.SOUTH);

        return footer;
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new SkiBarApp().frame.setVisible(true);
            }
        });
    }
}
