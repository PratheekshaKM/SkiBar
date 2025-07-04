import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class KnowledgeSharingDialog extends JDialog {

    public KnowledgeSharingDialog(JFrame parentFrame, String thoroughSubjects) {
        super(parentFrame, "Share Your Knowledge", true);
        setSize(400, 200);
        setLocationRelativeTo(parentFrame);

        Font arialFont = new Font("Arial", Font.PLAIN, 14);

        JPanel dialogPanel = new JPanel();
        dialogPanel.setLayout(new BorderLayout());
        dialogPanel.setBackground(Color.WHITE);

        JLabel messageLabel = new JLabel("Are you interested in sharing your knowledge?", SwingConstants.CENTER);
        messageLabel.setFont(arialFont);
        dialogPanel.add(messageLabel, BorderLayout.CENTER);

        JButton yesButton = new JButton("Yes");
        JButton noButton = new JButton("No");

        yesButton.setFont(arialFont);
        noButton.setFont(arialFont);
        yesButton.setBackground(new Color(0xC4F5FF));
        noButton.setBackground(new Color(0xC4F5FF));

        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout());
        buttonPanel.setBackground(Color.WHITE);
        buttonPanel.add(yesButton);
        buttonPanel.add(noButton);

        dialogPanel.add(buttonPanel, BorderLayout.SOUTH);

        add(dialogPanel);

        yesButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Open the LearningInterestPage with the thorough subjects
                new LearningInterestPage(thoroughSubjects).setVisible(true);
                dispose(); // Close the dialog
            }
        });

        noButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new FileUploadPage(parentFrame, thoroughSubjects).setVisible(true);
                dispose();
            }
        });
    }
}
