package org.example;
import javax.swing.*;
import java.awt.*;
import java.net.URL;

public class Instructions extends JPanel {

    private MainFrame mainFrame;

    public Instructions(MainFrame frame) {
        this.mainFrame = frame;
        setPreferredSize(new Dimension(MainFrame.APP_WIDTH, MainFrame.APP_HEIGHT));
        setLayout(new BorderLayout());

        JLabel imageLabel = new JLabel();
        imageLabel.setHorizontalAlignment(JLabel.CENTER);

        try {
            URL imgUrl = getClass().getResource("/instructions.png");
            if (imgUrl != null) {
                ImageIcon icon = new ImageIcon(imgUrl);
                Image image = icon.getImage().getScaledInstance(MainFrame.APP_WIDTH, MainFrame.APP_HEIGHT - 80, Image.SCALE_SMOOTH);
                imageLabel.setIcon(new ImageIcon(image));
            } else {
                System.err.println("Instructions image not found: /instructions.png. Displaying text.");
                imageLabel.setText("<html><div style='text-align: center;'>Instructions Image Not Found.<br>Press Space to jump. Avoid pipes.</div></html>");
                imageLabel.setFont(new Font("Arial", Font.PLAIN, 18));
            }
        } catch (Exception e) {
            System.err.println("Error loading instructions image: " + e.getMessage());
            imageLabel.setText("Error loading image.");
        }

        add(imageLabel, BorderLayout.CENTER);

        JPanel bottomPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        JButton backButton = new JButton("Back");

        backButton.setFont(new Font("Arial", Font.BOLD, 18));
        backButton.addActionListener(e -> mainFrame.showHomeScreen());
        bottomPanel.add(backButton);

        add(bottomPanel, BorderLayout.SOUTH);
    }
}