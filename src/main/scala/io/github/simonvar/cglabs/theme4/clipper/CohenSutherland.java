package io.github.simonvar.cglabs.theme4.clipper;

public class CohenSutherland implements LineClipper {

    private static final int INSIDE = 0;
    private static final int LEFT = 1;
    private static final int RIGHT = 2;
    private static final int BOTTOM = 4;
    private static final int TOP = 8;

    private int xMin;
    private int xMax;
    private int yMin;
    private int yMax;

    public CohenSutherland(int xMin, int yMin, int xMax, int yMax) {
        this.xMin = xMin;
        this.yMin = yMin;
        this.xMax = xMax;
        this.yMax = yMax;
    }

    /**
     * Computes OutCode for given point (x,y)
     *
     * @param x Horizontal coordinate
     * @param y Vertical coordinate
     * @return Computed OutCode
     */
    private int computeOutCode(double x, double y) {
        int code = INSIDE;

        if (x < xMin) {
            code |= LEFT;
        } else if (x > xMax) {
            code |= RIGHT;
        }
        if (y < yMin) {
            code |= BOTTOM;
        } else if (y > yMax) {
            code |= TOP;
        }

        return code;
    }

    public LineSegment clip(LineSegment line) {
        System.out.println("\nExecuting Cohen-Sutherland...");

        int x0 = line.x0, x1 = line.x1, y0 = line.y0, y1 = line.y1;
        int outCode0 = computeOutCode(x0, y0);
        int outCode1 = computeOutCode(x1, y1);
        System.out.println("OutCode0: " + outCode0 + ", OutCode1: " + outCode1);

        boolean accept = false;

        while (true) {
            if ((outCode0 | outCode1) == 0) { // Bitwise OR is 0. Trivially accept
                accept = true;
                break;
            } else if ((outCode0 & outCode1) != 0) { // Bitwise AND is not 0. Trivially reject
                break;
            } else {
                int x, y;

                // Pick at least one point outside rectangle
                int outCodeOut = (outCode0 != 0) ? outCode0 : outCode1;

                // Now find the intersection point;
                // use formulas y = y0 + slope * (x - x0), x = x0 + (1 / slope) * (y - y0)
                if ((outCodeOut & TOP) != 0) {
                    x = x0 + (x1 - x0) * (yMax - y0) / (y1 - y0);
                    y = yMax;
                } else if ((outCodeOut & BOTTOM) != 0) {
                    x = x0 + (x1 - x0) * (yMin - y0) / (y1 - y0);
                    y = yMin;
                } else if ((outCodeOut & RIGHT) != 0) {
                    y = y0 + (y1 - y0) * (xMax - x0) / (x1 - x0);
                    x = xMax;
                } else {
                    y = y0 + (y1 - y0) * (xMin - x0) / (x1 - x0);
                    x = xMin;
                }

                // Now we move outside point to intersection point to clip
                if (outCodeOut == outCode0) {
                    x0 = x;
                    y0 = y;
                    outCode0 = computeOutCode(x0, y0);
                } else {
                    x1 = x;
                    y1 = y;
                    outCode1 = computeOutCode(x1, y1);
                }
            }
        }

        if (accept) {
            return new LineSegment(x0, y0, x1, y1);
        }

        return null;
    }

}