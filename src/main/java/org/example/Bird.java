package org.example;
import java.awt.Image;
import java.awt.Graphics;
import java.awt.Rectangle;
import javax.swing.ImageIcon;

// מחלקת Bird – מייצגת את הציפור במשחק
// שומרת מידע על מיקום, גודל, תמונה, ומטפלת בציור ותנועה
public class Bird {
    private double x = 50;
    private double y = 300;
    private final int width = 40;
    private final int height = 38;
    private Image birdImage;

    public Bird() {
        birdImage = new ImageIcon(getClass().getResource("/BirdDown.png")).getImage();
    }


    public void draw(Graphics g) {
        g.drawImage(birdImage, (int)x, (int)y, width, height, null);
    }

    public void move(double velocity) {
        y += velocity;
    }

    public void reset() {
        x = 50;
        y = 300;
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