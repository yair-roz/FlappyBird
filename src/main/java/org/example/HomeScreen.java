package org.example;
import javax.swing.*;
import java.awt.*;
import java.net.URL;

public class HomeScreen extends JPanel {

    private MainFrame mainFrame;

    public HomeScreen(MainFrame frame) {
        this.mainFrame = frame;
        setPreferredSize(new Dimension(MainFrame.APP_WIDTH, MainFrame.APP_HEIGHT));
        setLayout(new BorderLayout()); // מאפשר גמישות במיקום

        // טעינת תמונת רקע
        JLabel backgroundLabel = new JLabel();
        try {
            URL imgUrl = getClass().getResource("/homeBackground.jpg");
            if (imgUrl != null) {
                ImageIcon backgroundImgIcon = new ImageIcon(imgUrl);
                // התאמת גודל התמונה לגודל הפאנל
                Image image = backgroundImgIcon.getImage().getScaledInstance(MainFrame.APP_WIDTH, MainFrame.APP_HEIGHT, Image.SCALE_SMOOTH);
                backgroundLabel.setIcon(new ImageIcon(image));
            } else {
                System.err.println("Home screen background image not found: /images/homeBackground.jpg. Using fallback color.");
                this.setBackground(Color.LIGHT_GRAY); // צבע רקע אם התמונה לא נמצאה
            }
        } catch (Exception e) {
            System.err.println("Error loading home screen background: " + e.getMessage());
            this.setBackground(Color.LIGHT_GRAY);
        }
        backgroundLabel.setLayout(new GridBagLayout()); // כדי למרכז את הכפתורים על התמונה
        this.add(backgroundLabel, BorderLayout.CENTER);


        // פאנל לכפתורים (שקוף)
        JPanel buttonPanel = new JPanel(new GridBagLayout());
        buttonPanel.setOpaque(false); // הפאנל עצמו שקוף

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridwidth = GridBagConstraints.REMAINDER;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.anchor = GridBagConstraints.CENTER;
        gbc.insets = new Insets(10, 70, 10, 70); // ריווח (top, left, bottom, right)

        JButton playButton = new JButton("Play");
        playButton.setFont(new Font("Arial", Font.BOLD, 24));
        playButton.addActionListener(e -> mainFrame.showGamePanel());

        JButton instructionsButton = new JButton("how to play");
        instructionsButton.setFont(new Font("Arial", Font.BOLD, 24));
        instructionsButton.addActionListener(e -> mainFrame.showInstructionsPanel());
        buttonPanel.add(playButton, gbc);
        buttonPanel.add(instructionsButton, gbc);


        // הוספת פאנל הכפתורים ל-backgroundLabel (שמכיל את התמונה)
        // כך שהם יופיעו מעל התמונה ובמרכזה
        backgroundLabel.add(buttonPanel, new GridBagConstraints()); // GridBagConstraints ריקים למרכוז
    }

}