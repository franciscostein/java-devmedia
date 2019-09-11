package Lectures.lecture4.part8;

import javax.swing.*;
import java.awt.*;

//      Object Adapter
public class CdScreen {

    private JFrame screen;
    private JTable table;
    private JScrollPane scrollPane;
    private JLabel screenTitle;
    private CdAdapter cdAdapter;

    public CdScreen() {
        screen = new JFrame("CD Collection");
        screen.setSize(600, 400);
        screen.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        screenTitle = new JLabel("These are my favorite CDs of all time");

        // Configuring the adapter
        CD monolith = new CD("Cattle Decapitation","Monolith of Inhumanaty", "Metal Blade", 2012);
        CD theirMight = new CD("Dyscarnate", "With all their might", "Unique Leader", 2017);
        CD thirteen = new CD("Black Sabbath", "13", "Universal International", 2013);
        CD depopulation = new CD("Implore", "Depopulation", "Pelagic Records", 2015);

        cdAdapter = new CdAdapter();
        cdAdapter.addCD(monolith);
        cdAdapter.addCD(theirMight);
        cdAdapter.addCD(thirteen);
        cdAdapter.addCD(depopulation);

        table = new JTable(cdAdapter);
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
