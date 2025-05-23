package org.example;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;
import javax.swing.ImageIcon;
import javax.swing.*;
import java.awt.*;

public class Pipe {
    private int x;
    private int y;
    private int width = 64;
    private int height = 512;
    private boolean top;
    private boolean passed = false;
    private Image image;

    public Pipe(int x, int y, boolean top) {
        this.x = x;
        this.y = y;
        this.top = top;
        String imagePath;
        if (top) {
            imagePath = "/pipeUp.png";
        } else {
            imagePath = "/pipeDown.png";
        }        image = new ImageIcon(getClass().getResource(imagePath)).getImage();
    }

    public void draw(Graphics g) {
        g.drawImage(image, x, y, width, height, null);
    }

    public void move() {
        x -= 4;
    }

    public Rectangle getBounds() {
        return new Rectangle(x, y, width, height - 3);
    }

    public int getX() {
        return x;
    }

    public boolean isPassed() {
        return passed;
    }

    public void setPassed(boolean passed) {
        this.passed = passed;
    }
}
