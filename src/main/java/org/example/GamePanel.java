package org.example;
import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class GamePanel extends JPanel implements KeyListener {
    public static final int WIDTH = 360;
    public static final int HEIGHT = 640;

    private Image backgroundImage;
    private Bird bird;
    private PipeManager pipeManager;
    private boolean running = true;
    private double velocity = 0;
    private double gravity = 0.5;
    private double jumpSpeed = -8;
    private double score = 0;
    private double gameSpeed = 1;
    private boolean cheat = false;
    private SoundPlayer SoundPlayer = new SoundPlayer();

    public GamePanel() {
        this.setPreferredSize(new Dimension(WIDTH, HEIGHT));
        this.setFocusable(true);
        this.addKeyListener(this);

        backgroundImage = new ImageIcon(getClass().getResource("/Background.jpg")).getImage();
        bird = new Bird();
        pipeManager = new PipeManager();
        startPipeSpawner();
        startGameLoop();
    }

    private void startPipeSpawner() {
        new Thread(() -> {
            while (true) {
                pipeManager.addPipePair();
                try {
                    Thread.sleep((long) (1250 * gameSpeed));
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        }).start();
    }

    private void startGameLoop() {
        new Thread(() -> {
            while (true) {
                move();
                addSpeed();
                repaint();
                if (!running) break;
                try {
                    Thread.sleep((long) (1000 / 60 * gameSpeed));
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(backgroundImage, 0, 0, WIDTH, HEIGHT, null);
        bird.draw(g, this.velocity);
        pipeManager.draw(g);
        drawScore(g);
    }

    private void drawScore(Graphics g) {
        g.setColor(Color.WHITE);
        g.setFont(new Font("Arial", Font.PLAIN, 32));
        g.drawString((running ? "" : "Game Over: ") + (int) score, 10, 35);
    }

    private void move() {
        velocity += gravity;
        bird.move(velocity);
        if (bird.getY() <= 0 || bird.getY() + bird.getHeight() > HEIGHT) {
            SoundPlayer.gameOverSound();
            running = false;
        }

        double add = pipeManager.update(bird);
        score += add;

        if (add > 0) {
            SoundPlayer.Transition_between_pipes_sound();
        }
        if (pipeManager.checkCollision(bird) && !cheat) {
            SoundPlayer.gameOverSound();
            running = false;
        }
    }

    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_SPACE) {
            if (!running) {
                restartGame();
            } else {
                velocity = jumpSpeed;
                SoundPlayer.playJumpSound();

            }
        }
        if (e.getKeyCode() == KeyEvent.VK_S){
            this.cheat = !cheat;
        }
    }

    private void restartGame() {
        bird.reset();
        this.gameSpeed = 1;
        velocity = 0;
        score = 0;
        pipeManager.reset();
        running = true;
        startGameLoop();
    }

    private void addSpeed(){
        if (this.score == 15){
            this.gameSpeed = 0.85;
        } else if (this.score == 30) {
            this.gameSpeed = 0.75;
        }
    }

    @Override public void keyTyped(KeyEvent e) {}
    @Override public void keyReleased(KeyEvent e) {}
}
