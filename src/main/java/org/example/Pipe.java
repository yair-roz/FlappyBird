// מחלקת Pipe – מייצגת צינור אחד (למעלה או למטה)
// שומרת מידע על מיקום, גובה, סוג (עליון/תחתון), ודגל אם הציפור כבר עברה אותו
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
        String imagePath = top ? "/pipeUp.png" : "/pipeDown.png";
        image = new ImageIcon(getClass().getResource(imagePath)).getImage();
    }

    public void draw(Graphics g) {
        g.drawImage(image, x, y, width, height, null);
    }

    public void move() {
        x -= 4;
    }

    public Rectangle getBounds() {
        return new Rectangle(x, y, width, height);
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
