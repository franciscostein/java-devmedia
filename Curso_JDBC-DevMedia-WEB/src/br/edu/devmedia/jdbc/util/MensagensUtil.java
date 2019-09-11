package br.edu.devmedia.jdbc.util;

import javax.swing.*;
import java.awt.*;

public class MensagensUtil {

    public static void addMsg (Component component, String msg) {

        JOptionPane.showMessageDialog(component, msg);
    }
}
