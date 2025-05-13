package org.example;
import java.awt.Image;
import java.awt.Graphics;
import java.awt.Rectangle;
import javax.swing.ImageIcon;

// מחלקת Bird – מייצגת את הציפור במשחק
// שומרת מידע על מיקום, גודל, תמונה, ומטפלת בציור ותנועה
public class Bird {
    private double x = 50;
    private double y = 150;
    private final int width = 40;
    private final int height = 38;
    private Image birdImageDown;
    private Image birdImageUp;

    public Bird() {
        birdImageDown = new ImageIcon(getClass().getResource("/BirdDown.png")).getImage();
        birdImageUp = new ImageIcon(getClass().getResource("/BirdUp.png")).getImage();
    }


    public void draw(Graphics g, double velocity) {
        if (velocity >= 0){
            g.drawImage(birdImageDown, (int)x, (int)y, width, height, null);
        }else {
            g.drawImage(birdImageUp, (int)x, (int)y, width, height, null);
        }
    }

    public void move(double velocity) {
        y += velocity;
    }

    public void reset() {
        x = 50;
        y = 150;
    }

    public Rectangle getBounds() {
        return new Rectangle((int)x, (int)y, width, height);
    }

    public double getY() {
        return y;
    }

    public int getHeight() {
        return height;
    }
}