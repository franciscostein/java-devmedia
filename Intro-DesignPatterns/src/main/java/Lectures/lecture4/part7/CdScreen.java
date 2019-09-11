package Lectures.lecture4.part7;

import javax.swing.*;
import java.awt.*;

public class CdScreen {

    private JFrame screen;
    private JTable table;
    private JScrollPane scrollPane;
    private JLabel screenTitle;

    public CdScreen() {
        screen = new JFrame("CD Collection");
        screen.setSize(600, 400);
        screen.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        screenTitle = new JLabel("These are my favorite CDs of all time");
        table = new JTable(3, 4);
        scrollPane = new JScrollPane(table);
    }

    public void configureScreen() {
        Container container = screen.getContentPane();
        container.add(screenTitle, BorderLayout.NORTH);
        container.add(scrollPane, BorderLayout.CENTER);
    }

    public void render() {
        screen.setVisible(true);
    }

    public static void main(String[] args) {
        CdScreen cd = new CdScreen();
        cd.configureScreen();
        cd.render();
    }
}
