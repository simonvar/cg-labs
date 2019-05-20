package io.github.simonvar.cglabs.theme4;

import javax.swing.*;

public class Theme4 {

    public static void main(String[] args) {
        JFrame mainFrame = new JFrame("Line Clipping");
        mainFrame.setSize(800, 600);
        mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        int x0, y0, x1, y1;
        x0 = y0 = x1 = y1 = -1;

        do {
            String response = JOptionPane.showInputDialog(mainFrame, "Please insert the coordinates of the lower left and upper right points\nof the rectangle separated by commas.\n"
                            + "Format: \"xMin,yMin,xMax,yMax\"; xMin < xMax and yMin < yMax\n"
                            + "(0 <= x <= 800 and 0 <= y <= 600)",
                    "100,100,700,500");

            if (response == null) System.exit(0);

            String[] coordinates = response.split(",");
            try {
                x0 = Integer.parseInt(coordinates[0]);
                y0 = Integer.parseInt(coordinates[1]);
                x1 = Integer.parseInt(coordinates[2]);
                y1 = Integer.parseInt(coordinates[3]);
            } catch(NumberFormatException ne) {
                JOptionPane.showMessageDialog(mainFrame, "All values must be integers");
            }

        } while (0 > x0 || x1 > 800 || 0 > y0 || y1 > 600 || x0 >= x1 || y0 >= y1);



        mainFrame.add(new LineClipPanel(x0, y0, x1, y1));
        mainFrame.setLocationRelativeTo(null);
        mainFrame.setResizable(false);
        mainFrame.setVisible(true);
    }

}
