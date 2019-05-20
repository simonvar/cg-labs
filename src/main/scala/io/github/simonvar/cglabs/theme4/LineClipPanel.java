package io.github.simonvar.cglabs.theme4;

import io.github.simonvar.cglabs.theme4.clipper.CohenSutherland;
import io.github.simonvar.cglabs.theme4.clipper.LineClipper;
import io.github.simonvar.cglabs.theme4.clipper.LineSegment;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.JPanel;


public class LineClipPanel extends JPanel implements MouseListener {

    private int xMin;
    private int xMax;
    private int yMin;
    private int yMax;

    private LineClipper clipper;


    /**
     * Constructor
     *
     * @param xMin Bottom side of rectangle
     * @param yMin Left side of rectangle
     * @param xMax Top side of rectangle
     * @param yMax Right side of rectangle
     */
    public LineClipPanel(int xMin, int yMin, int xMax, int yMax) {
        this.xMin = xMin;
        this.yMin = yMin;
        this.xMax = xMax;
        this.yMax = yMax;
        clipper = new CohenSutherland(xMin, yMin, xMax, yMax);

        addMouseListener(this);
    }

    @Override
    public void paintComponent(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        g2d.clearRect(0, 0, getWidth(), getHeight());

        g2d.setColor(Color.white);
        g2d.fillRect(0, 0, getWidth(), getHeight());

        g2d.setColor(Color.black);
        drawLine(g2d, xMin, 0, xMin, getHeight());
        drawLine(g2d, xMax, 0, xMax, getHeight());
        drawLine(g2d, 0, yMin, getWidth(), yMin);
        drawLine(g2d, 0, yMax, getWidth(), yMax);

        int x0, y0, x1, y1;
        LineSegment line, clippedLine;
        for (int i = 0; i < 10; i++) {
            x0 = (int) (Math.random() * getWidth());
            x1 = (int) (Math.random() * getWidth());
            y0 = (int) (Math.random() * getHeight());
            y1 = (int) (Math.random() * getHeight());
            line = new LineSegment(x0, y0, x1, y1);
            clippedLine = clipper.clip(line);

            System.out.println("Original: " + line);
            System.out.println("Clipped: " + clippedLine);

            if (clippedLine == null) {
                g2d.setColor(Color.red);
                drawLine(g2d, line.x0, line.y0, line.x1, line.y1);
            } else {
                g2d.setColor(Color.red);
                drawLine(g2d, line.x0, line.y0, clippedLine.x0, clippedLine.y0);
                drawLine(g2d, clippedLine.x1, clippedLine.y1, line.x1, line.y1);
                g2d.setColor(Color.green);
                drawLine(g2d, clippedLine.x0, clippedLine.y0, clippedLine.x1, clippedLine.y1);
            }
        }
    }

    private void drawLine(Graphics g, int x1, int y1, int x2, int y2) {
        g.drawLine(x1, getHeight() - y1, x2, getHeight() - y2);
    }


    @Override
    public void mouseClicked(MouseEvent e) {
        System.out.println("CLICK");
        repaint();
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

}