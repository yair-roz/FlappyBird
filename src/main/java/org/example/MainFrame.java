package org.example;
import javax.swing.*;
import java.awt.*;

public class MainFrame extends JFrame {

    public static final int APP_WIDTH = 360;
    public static final int APP_HEIGHT = 640;

    private CardLayout cardLayout;
    private JPanel mainPanelContainer;
    private HomeScreen homeScreenPanel;
    private Instructions Instructions;
    private GamePanel gamePanel;

    public MainFrame() {
        setTitle("Flappy Bird");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        setSize(APP_WIDTH, APP_HEIGHT);
        setLocationRelativeTo(null);

        try {
            ImageIcon icon = new ImageIcon(getClass().getResource("/BirdDown.png"));
            if (icon.getImage() != null && icon.getIconWidth() > 0) {
                this.setIconImage(icon.getImage());
            } else {
                System.err.println("Warning: Icon image not found or invalid: /Bird.png");
            }
        } catch (Exception e) {
            System.err.println("Error loading icon: " + e.getMessage());
        }

        cardLayout = new CardLayout();
        mainPanelContainer = new JPanel(cardLayout);

        homeScreenPanel = new HomeScreen(this);
        Instructions = new Instructions(this);

        mainPanelContainer.add(homeScreenPanel, "HOME");
        mainPanelContainer.add(Instructions, "INSTRUCTIONS");

        this.add(mainPanelContainer);

        showHomeScreen();
        this.setVisible(true);
    }

    public void showHomeScreen() {
        cardLayout.show(mainPanelContainer, "HOME");
        homeScreenPanel.requestFocusInWindow();
    }

    public void showGamePanel() {
        if (gamePanel == null) {
            gamePanel = new GamePanel();
            mainPanelContainer.add(gamePanel, "GAME");
        }
        cardLayout.show(mainPanelContainer, "GAME");
        gamePanel.requestFocusInWindow();
    }

    public void showInstructionsPanel() {
        cardLayout.show(mainPanelContainer, "INSTRUCTIONS");
        Instructions.requestFocusInWindow();
    }
}