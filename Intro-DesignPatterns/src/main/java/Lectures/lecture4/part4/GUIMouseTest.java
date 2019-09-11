package Lectures.lecture4.part4;

import javax.swing.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class GUIMouseTest {

    private JFrame jFrame;

    public GUIMouseTest() {
        jFrame = new JFrame();
        jFrame.setSize(500, 400);
        jFrame.addMouseListener(new MyMouseListener());
        jFrame.setVisible(true);
    }

    //  Mouse listener implementa MouseListener, assim podemos sobrescrever somente os métodos necessarios
    private static class MyMouseListener extends MouseAdapter { // MouseAdptar é um stub

        @Override
        public void mouseEntered(MouseEvent e) {
            System.out.println("Mouse entered the JFrame");
        }

        @Override
        public void mouseExited(MouseEvent e) {
            System.out.println("Mouse exited the JFrame");
        }
    }

    public static void main(String[] args) {
        GUIMouseTest guiMouseTest = new GUIMouseTest();
    }
}
