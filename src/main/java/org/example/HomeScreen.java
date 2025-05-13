package org.example;
import javax.swing.*;
import java.awt.*;
import java.net.URL;

public class HomeScreen extends JPanel {

    private ScreenManager ScreenManager;

    public HomeScreen(ScreenManager screen) {
        this.ScreenManager = screen;
        setPreferredSize(new Dimension(ScreenManager.APP_WIDTH, ScreenManager.APP_HEIGHT));
        setLayout(new BorderLayout());

        JLabel backgroundLabel = new JLabel();
        try {
            URL imgUrl = getClass().getResource("/homeBackground.jpg");
            if (imgUrl != null) {
                ImageIcon backgroundImgIcon = new ImageIcon(imgUrl);
                Image image = backgroundImgIcon.getImage().getScaledInstance(ScreenManager.APP_WIDTH, ScreenManager.APP_HEIGHT, Image.SCALE_SMOOTH);
                backgroundLabel.setIcon(new ImageIcon(image));
            } else {
                System.err.println("Home screen background image not found: /homeBackground.jpg. Using fallback color.");
                this.setBackground(Color.LIGHT_GRAY);
            }
        } catch (Exception e) {
            System.err.println("Error loading home screen background: " + e.getMessage());
            this.setBackground(Color.LIGHT_GRAY);
        }
        backgroundLabel.setLayout(new GridBagLayout());
        this.add(backgroundLabel, BorderLayout.CENTER);

        JPanel buttonPanel = new JPanel(new GridBagLayout());
        buttonPanel.setOpaque(false);

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridwidth = GridBagConstraints.REMAINDER;
        gbc.fill = GridBagConstraints.NONE;
        gbc.anchor = GridBagConstraints.CENTER;
        gbc.insets = new Insets(-10, 0, -30, 0);

        JButton playButton = createImageButton("/start bt.png", e -> ScreenManager.showGamePanel());
        JButton howToButton = createImageButton("/how to play bt.png", e -> ScreenManager.showInstructionsPanel());

        buttonPanel.add(playButton, gbc);
        buttonPanel.add(howToButton, gbc);

        backgroundLabel.add(buttonPanel, new GridBagConstraints());
    }

    private JButton createImageButton(String imagePath, java.awt.event.ActionListener action) {
        JButton button = new JButton();
        URL imageUrl = getClass().getResource(imagePath);
        if (imageUrl != null) {
            ImageIcon icon = new ImageIcon(imageUrl);
            button.setIcon(icon);
        } else {
            button.setText("Missing image");
        }
        button.setBorderPainted(false);
        button.setContentAreaFilled(false);
        button.setFocusPainted(false);
        button.setOpaque(false);
        button.addActionListener(action);
        return button;
    }
}
