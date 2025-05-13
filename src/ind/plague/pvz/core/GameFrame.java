package ind.plague.pvz.core;

import ind.plague.pvz.scene.SceneManager;
import ind.plague.pvz.util.Timer;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

/**
 * @author PlagueWZK
 * description: GameFrame
 * date: 2025/5/12 16:27
 */

public class GameFrame {
    private static final int DEFAULT_WIDTH = 1280;
    private static final int DEFAULT_HEIGHT = 720;
    private static final int DEFAULT_FPS = 60;
    private static final int DEFAULT_UPS = 20;
    private static final long FRAME_TIME = 1000_000_000L / DEFAULT_FPS;
    private static final long UPDATE_TIME = 1000_000_000L / DEFAULT_UPS;
    private static final long MAX_FRAME_SKIPS = UPDATE_TIME * 3;

    private final GamePanel panel;
    private final SceneManager sm;
    private final Timer FPSCalculator;

    private long drawTimer;
    private long updateTimer;
    private int drawCount;
    private int FPS;

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

        FPSCalculator = new Timer(1000, true, () -> {
            FPS = drawCount;
            drawCount = 0;
        });
    }

    public GameFrame(SceneManager sceneManager) {
        sm = sceneManager;
    }

    public void update(long deltaTime) {
        updateTimer += deltaTime;
//        while (updateTimer >= UPDATE_TIME) {
//            if (updateTimer > MAX_FRAME_SKIPS) {
//                updateTimer = MAX_FRAME_SKIPS;
//            }
//
//            updateTimer -= UPDATE_TIME;
//        }
        FPSCalculator.update(deltaTime);
        sm.update(deltaTime);
    }

    public void draw(long deltaTime) {
        drawTimer += deltaTime;
        if (drawTimer >= FRAME_TIME) {
            panel.repaint();
            drawCount++;
            drawTimer = 0;
        }
    }

    private class GamePanel extends JPanel {
        public GamePanel() {
            setPreferredSize(new Dimension(DEFAULT_WIDTH, DEFAULT_HEIGHT));
            setDoubleBuffered(true);
        }

        @Override
        public void paint(Graphics g) {
            super.paint(g);
            Graphics2D g2d = (Graphics2D) g;
            g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            sm.draw(g2d);
        }
    }

    private class Listener implements KeyListener, MouseListener, MouseMotionListener {

        @Override
        public void keyTyped(KeyEvent e) {

        }

        @Override
        public void keyPressed(KeyEvent e) {
            sm.setKeyState(e.getKeyCode(), true);
        }

        @Override
        public void keyReleased(KeyEvent e) {
            sm.setKeyState(e.getKeyCode(), false);
        }

        @Override
        public void mouseClicked(MouseEvent e) {

        }

        @Override
        public void mousePressed(MouseEvent e) {

        }

        @Override
        public void mouseReleased(MouseEvent e) {

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

        }
    }
}
