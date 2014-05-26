package io;

import java.awt.Point;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.ArrayList;

public class Mouse implements MouseListener, MouseMotionListener {
//    private boolean rightPress = false;
    private Point cursor = new Point();
    private ArrayList<Point> releasePoints = new ArrayList<>();
    private ArrayList<Point> pressPoints = new ArrayList<>();

    public Mouse() {
        super();
    }

    public Point getCursor() {
        return cursor;
    }

    public Point getReleasePoint() {
        if (releasePoints.size() == 0) {
            return null;
        } else {
            return releasePoints.remove(0);
        }
    }

    public Point getPressPoint() {
        if (pressPoints.size() == 0) {
            return null;
        } else {
            return pressPoints.remove(0);
        }
    }

    public void mouseClicked(final MouseEvent e) {

    }

    public void mousePressed(final MouseEvent e) {
        pressPoints.add(new Point(e.getX() / 2, e.getY() / 2));
    }

    public void mouseReleased(final MouseEvent e) {
        releasePoints.add(new Point(e.getX() / 2, e.getY() / 2));
    }

    public void mouseEntered(final MouseEvent e) {

    }

    public void mouseExited(final MouseEvent e) {

    }

    public void mouseDragged(final MouseEvent e) {
        cursor.x = e.getX() / 2;
        cursor.y = e.getY() / 2;
    }

    public void mouseMoved(final MouseEvent e) {
        cursor.x = e.getX() / 2;
        cursor.y = e.getY() / 2;
    }
}
