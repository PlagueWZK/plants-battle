package ind.plague.pvz.core;

import ind.plague.pvz.Main;
import ind.plague.pvz.event.EventBus;
import ind.plague.pvz.event.events.GameKeyEvent;
import ind.plague.pvz.scene.SceneManager;
import ind.plague.pvz.util.Painter;
import ind.plague.pvz.util.ResourceGetter;
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
    private final SceneManager sm;
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
        frame.setIconImage(ResourceGetter.ICON);
        frame.setTitle("植物明星大乱斗");

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

    public GameFrame(SceneManager sceneManager) {
        sm = sceneManager;
    }

    public static int getWidth() {
        return DEFAULT_WIDTH;
    }

    public static int getHeight() {
        return DEFAULT_HEIGHT;
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

    public void globalDraw(Graphics2D g) {
        Painter.drawText(g, "FPS: " + FPS + " UPS: " + UPS, 10, 10, Color.BLACK, 10);
        if (Main.DEBUG) {
            Painter.drawText(g, "DEBUG", GameFrame.getWidth() - 50, 15, Color.RED, 10);
        }
    }

    private static class Listener implements KeyListener, MouseListener, MouseMotionListener {

        @Override
        public void keyTyped(KeyEvent e) {

        }

        @SuppressWarnings("all")
        @Override
        public void keyPressed(KeyEvent e) {
            switch (e.getKeyCode()) {
                case KeyEvent.VK_F8 -> Main.DEBUG = !Main.DEBUG;
            }
            EventBus.instance.publish(new GameKeyEvent(e, GameKeyEvent.Action.KEY_PRESS));
        }

        @Override
        public void keyReleased(KeyEvent e) {
            EventBus.instance.publish(new GameKeyEvent(e, GameKeyEvent.Action.KEY_RELEASE));
        }

        @Override
        public void mouseClicked(MouseEvent e) {

        }

        @Override
        public void mousePressed(MouseEvent e) {
            EventBus.instance.publish(new GameKeyEvent(e, GameKeyEvent.Action.MOUSE_PRESS));
        }

        @Override
        public void mouseReleased(MouseEvent e) {
            EventBus.instance.publish(new GameKeyEvent(e, GameKeyEvent.Action.MOUSE_RELEASE));
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
            EventBus.instance.publish(new GameKeyEvent(e, GameKeyEvent.Action.MOUSE_MOVE));
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
            globalDraw(g2d);
        }
    }
}
