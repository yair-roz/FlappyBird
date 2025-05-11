// מחלקת Background – יוצרת את חלון המשחק
// מגדירה את הגודל, מוסיפה את GamePanel שמכיל את הלוגיקה הגרפית והמשחקית
package org.example;
import javax.swing.ImageIcon;

import javax.swing.*;

public class BackgroundGame extends JFrame {
    ImageIcon image;

    public BackgroundGame(){
        this.setSize(360, 640);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setResizable(false);

        GamePanel gamePanel = new GamePanel();
        this.add(gamePanel);
        this.pack();
        gamePanel.requestFocus();

        image = new ImageIcon(getClass().getResource("/Bird.png"));
        this.setIconImage(image.getImage());

        this.setVisible(true);
    }

}
