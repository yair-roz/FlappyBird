package org.example;

import javax.swing.*;
import java.awt.*;
import java.net.URL;

public class Instructions extends JPanel {

    private MainFrame mainFrame;

    public Instructions(MainFrame frame) {
        this.mainFrame = frame;
        setPreferredSize(new Dimension(MainFrame.APP_WIDTH, MainFrame.APP_HEIGHT));
        setLayout(new BorderLayout()); // מנהל פריסה פשוט

        // טעינת תמונת הוראות
        JLabel imageLabel = new JLabel();
        imageLabel.setHorizontalAlignment(JLabel.CENTER); // מרכוז התמונה אם היא קטנה מהפאנל
        try {
            URL imgUrl = getClass().getResource("/instructions.png");
            if (imgUrl != null) {
                ImageIcon icon = new ImageIcon(imgUrl);
                // התאמת גודל התמונה, תוך שמירה על יחס רוחב-גובה אם רוצים, או מתיחה לגודל מסוים
                // כאן נתאים לגובה הפאנל פחות מקום לכפתור, ולרוחב הפאנל
                Image image = icon.getImage().getScaledInstance(MainFrame.APP_WIDTH, MainFrame.APP_HEIGHT - 80, Image.SCALE_SMOOTH); // -80 למקום לכפתור
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


        // כפתור "Back"
        JPanel bottomPanel = new JPanel(new FlowLayout(FlowLayout.CENTER)); // פאנל לכפתור בתחתית
        JButton backButton = new JButton("Back");

        backButton.setFont(new Font("Arial", Font.BOLD, 18));
        backButton.addActionListener(e -> mainFrame.showHomeScreen());
        bottomPanel.add(backButton);

        add(bottomPanel, BorderLayout.SOUTH); // הוספת פאנל הכפתור לתחתית
    }
}