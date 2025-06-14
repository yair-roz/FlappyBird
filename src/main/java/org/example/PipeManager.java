package org.example;
import java.awt.*;
import java.util.Random;
import java.util.concurrent.CopyOnWriteArrayList;

public class PipeManager {
    private CopyOnWriteArrayList<Pipe> pipes;
    private Random random = new Random();

    public PipeManager() {
        pipes = new CopyOnWriteArrayList<>();
    }

    public void addPipePair() {
        int space = (int) (200);
        int topHeight = random.nextInt(300) + 100;
        pipes.add(new Pipe(400, topHeight - 512, true));
        pipes.add(new Pipe(400, topHeight + space, false));
    }

    public void draw(Graphics g) {
        for (int i = 0; i < pipes.size(); i++) {
            Pipe pipe = pipes.get(i);
            pipe.draw(g);
        }
    }

    public double update(Bird bird) {
        double scoreIncrease = 0;
        for (int i = 0; i < pipes.size(); i++) {
            Pipe pipe = pipes.get(i);
            pipe.move();
            if (pipe.getX() + 64 < 0) {
                pipes.remove(pipe);
            }
            if (!pipe.isPassed() && pipe.getX() + 64 < 50) {
                pipe.setPassed(true);
                scoreIncrease += 0.5;
            }
        }
        return scoreIncrease;
    }


    public boolean checkCollision(Bird bird) {
        Rectangle birdRect = bird.getBounds();
        for (int i = 0; i < pipes.size(); i++) {
            Pipe pipe = pipes.get(i);
            if (birdRect.intersects(pipe.getBounds())) {
                return true;
            }
        }
        return false;
    }

    public void reset() {
        pipes.clear();
    }

}
