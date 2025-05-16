package ind.plague.pvz.core;

import ind.plague.pvz.input.InputHandler;
import ind.plague.pvz.scene.Manager;
import ind.plague.pvz.util.Timer;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.concurrent.locks.LockSupport;

/**
 * @author PlagueWZK
 * description: GameFrame
 * date: 2025/5/12 16:27
 */

public class GameFrame {
    private static final int DEFAULT_WIDTH = 1280;
    private static final int DEFAULT_HEIGHT = 720;
    private static final int DEFAULT_FPS = 165;
    private static final int DEFAULT_UPS = 300;
    private static final long FRAME_TIME = 1000_000_000L / DEFAULT_FPS;
    private static final long UPDATE_TIME = 1000_000_000L / DEFAULT_UPS;
    private static final long MAX_FRAME_SKIPS = UPDATE_TIME * 3;

    private final GamePanel panel;
    private final Manager sm;
    private final InputHandler  ih;
    private final Timer PSCalculator;

    private long drawTimer;
    private int drawCount;
    private int FPS;
    private long updateTimer;
    private int updateCount;
    private int UPS;

    {
        JFrame frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);

        panel = new GamePanel();
        frame.add(panel);

        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);

        Listener ls = new Listener();
        frame.addKeyListener(ls);
        panel.addMouseListener(ls);
        panel.addMouseMotionListener(ls);

        PSCalculator = new Timer(1000, true, () -> {
            FPS = drawCount;
            UPS = updateCount;
            drawCount = 0;
            updateCount = 0;
        });
    }

    public GameFrame(Manager sceneManager, InputHandler inputHandler) {
        sm = sceneManager;
        ih = inputHandler;
    }

    public void update(long deltaTime) {
        updateTimer += deltaTime;
        updateCount++;
        PSCalculator.update(deltaTime);
        sm.update(deltaTime);

        while (updateTimer >= UPDATE_TIME) {
            if (updateTimer > MAX_FRAME_SKIPS) {
                updateTimer = MAX_FRAME_SKIPS;
            }
            updateTimer -= UPDATE_TIME;
        }

    }

    public void draw(long deltaTime) {
        drawTimer += deltaTime;
        if (drawTimer >= FRAME_TIME) {
            panel.repaint();
            drawCount++;
            drawTimer = 0;
        }
    }

    public void startGameLoop() {
        new Thread(() -> {
            long startTime = System.nanoTime();
            while (!Thread.currentThread().isInterrupted()) {
                long now = System.nanoTime();
                long deltaTime = now - startTime;
                startTime = now;
                update(deltaTime);
                LockSupport.parkNanos(UPDATE_TIME);
            }
        }).start();
        long startTime = System.nanoTime();
        while (!Thread.currentThread().isInterrupted()) {
            long now = System.nanoTime();
            long deltaTime = now - startTime;
            startTime = now;
            draw(deltaTime);
            LockSupport.parkNanos(1);
        }

    }

    private class GamePanel extends JPanel {
        public GamePanel() {
            setPreferredSize(new Dimension(DEFAULT_WIDTH, DEFAULT_HEIGHT));
            setDoubleBuffered(true);
        }

        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            Graphics2D g2d = (Graphics2D) g;
            g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            sm.draw(g2d);
            g2d.setColor(Color.BLACK);
            g2d.drawString("FPS: " + FPS, 10, 20);
            g2d.drawString("UPS: " + UPS, 10, 40);
        }
    }

    private class Listener implements KeyListener, MouseListener, MouseMotionListener {

        @Override
        public void keyTyped(KeyEvent e) {

        }

        @Override
        public void keyPressed(KeyEvent e) {
            ih.setKeyState(e.getKeyCode(), true);
        }

        @Override
        public void keyReleased(KeyEvent e) {
            ih.setKeyState(e.getKeyCode(), false);
        }

        @Override
        public void mouseClicked(MouseEvent e) {

        }

        @Override
        public void mousePressed(MouseEvent e) {
            ih.setMouseState(e.getButton(), true);
        }

        @Override
        public void mouseReleased(MouseEvent e) {
            ih.setMouseState(e.getButton(), false);
        }

        @Override
        public void mouseEntered(MouseEvent e) {

        }

        @Override
        public void mouseExited(MouseEvent e) {

        }

        @Override
        public void mouseDragged(MouseEvent e) {

        }

        @Override
        public void mouseMoved(MouseEvent e) {
            ih.setMouseX(e.getX());
            ih.setMouseY(e.getY());
        }
    }
}
