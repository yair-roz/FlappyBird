package org.example;
import javax.sound.sampled.*;
import javax.swing.*;
import java.io.File;
import java.io.IOException;
import java.net.URL;

public class SoundPlayer {

    private final String jumpPath = "/jump.wav";
    private final String gameOverPath = "/game over.wav";
    private final String transitionPath = "/Transition between pipes sound.wav";

    public void playJumpSound() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    URL soundUrl = getClass().getResource(jumpPath);
                    if (soundUrl == null) {
                        System.err.println("sound not found in classpath: " + jumpPath);
                        return;
                    }

                    final Clip clip = AudioSystem.getClip();
                    clip.open(AudioSystem.getAudioInputStream(soundUrl));
                    clip.addLineListener(event -> {
                        if (event.getType() == LineEvent.Type.STOP) {
                            clip.close();
                        }
                    });
                    clip.start();
                } catch (Exception e) {
                    e.printStackTrace();
                    System.err.println("Error playing sound: " + "/resources/jump.wav");
                }
            }
        }).start();
    }

    public void gameOverSound() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    URL soundUrl = getClass().getResource(gameOverPath);
                    if (soundUrl == null) {
                        System.err.println("sound not found in classpath: " + gameOverPath);
                        return;
                    }

                    final Clip clip = AudioSystem.getClip();
                    clip.open(AudioSystem.getAudioInputStream(soundUrl));
                    clip.addLineListener(event -> {
                        if (event.getType() == LineEvent.Type.STOP) {
                            clip.close();
                        }
                    });
                    clip.start();

                } catch (Exception e) {
                    e.printStackTrace();
                    System.err.println("Error playing sound: " + "/resources/jump.wav");
                }
            }
        }).start(); // מפעיל את ה-Thread החדש
    }

    public void Transition_between_pipes_sound() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    URL soundUrl = getClass().getResource(transitionPath);
                    if (soundUrl == null) {
                        System.err.println("sound not found in classpath: " + transitionPath);
                        return;
                    }

                    final Clip clip = AudioSystem.getClip();
                    clip.open(AudioSystem.getAudioInputStream(soundUrl));
                    clip.addLineListener(event -> {
                        if (event.getType() == LineEvent.Type.STOP) {
                            clip.close();
                        }
                    });
                    clip.start();

                } catch (Exception e) {
                    e.printStackTrace();
                    System.err.println("Error playing sound: " + "/resources/jump.wav");
                }
            }
        }).start();
    }































}