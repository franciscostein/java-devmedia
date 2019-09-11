/*
 * Created by JFormDesigner on Sat Dec 30 13:58:17 BRST 2017
 */

package br.edu.devmedia.jdbc.gui;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

import br.edu.devmedia.jdbc.bo.LoginBO;
import br.edu.devmedia.jdbc.dao.LoginDAO;
import br.edu.devmedia.jdbc.dto.LoginDTO;
import br.edu.devmedia.jdbc.exception.NegocioException;
import br.edu.devmedia.jdbc.util.MensagensUtil;
import net.miginfocom.swing.*;

import static javax.swing.JOptionPane.showMessageDialog;

/**
 * @author Leandro
 */
public class LoginFrame extends JFrame {
    public LoginFrame() {
        initComponents();
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                LoginFrame inst = new LoginFrame();
                inst.setLocationRelativeTo(null);
                inst.setVisible(true);
            }
        });
    }

    private void btnLoginActionPerformed(ActionEvent e){

            LoginDTO loginDTO = new LoginDTO();
            loginDTO.setNome(txtUsuario.getText());
            loginDTO.setSenha(new String (txtSenha.getPassword()));


            LoginBO loginBO = new LoginBO();
            try {

                if (loginBO.logar(loginDTO)) {

                    LoginFrame.this.dispose();

                    MainFrame mainFrame = new MainFrame();
                    mainFrame.setLocationRelativeTo(null);
                    mainFrame.setVisible(true);

                } else {
                    MensagensUtil.addMsg(this, "Dados inválidos!");
                }

            } catch (NegocioException e1) {
                e1.printStackTrace();
                MensagensUtil.addMsg(this, e1.getMessage());
            }
    }

    private void btnSairActionPerformed(ActionEvent e) {
        System.exit(0);
    }

    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
        // Generated using JFormDesigner Evaluation license - Leandro Costa
        txtUsuario = new JTextField();
        btnSair = new JButton();
        label1 = new JLabel();
        label2 = new JLabel();
        btnLogin = new JButton();
        txtSenha = new JPasswordField();

        //======== this ========
        setResizable(false);
        Container contentPane = getContentPane();

        //---- btnSair ----
        btnSair.setText("Sair");
        btnSair.addActionListener(e -> btnSairActionPerformed(e));

        //---- label1 ----
        label1.setText("Usu\u00e1rio");

        //---- label2 ----
        label2.setText("Senha");

        //---- btnLogin ----
        btnLogin.setText("Acessar");
        btnLogin.addActionListener(e -> btnLoginActionPerformed(e));

        GroupLayout contentPaneLayout = new GroupLayout(contentPane);
        contentPane.setLayout(contentPaneLayout);
        contentPaneLayout.setHorizontalGroup(
            contentPaneLayout.createParallelGroup()
                .addGroup(contentPaneLayout.createSequentialGroup()
                    .addGap(60, 60, 60)
                    .addGroup(contentPaneLayout.createParallelGroup(GroupLayout.Alignment.LEADING, false)
                        .addComponent(label1)
                        .addComponent(txtUsuario, GroupLayout.DEFAULT_SIZE, 258, Short.MAX_VALUE)
                        .addComponent(label2)
                        .addGroup(GroupLayout.Alignment.TRAILING, contentPaneLayout.createSequentialGroup()
                            .addComponent(btnLogin, GroupLayout.PREFERRED_SIZE, 80, GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                            .addComponent(btnSair, GroupLayout.PREFERRED_SIZE, 80, GroupLayout.PREFERRED_SIZE))
                        .addComponent(txtSenha, GroupLayout.DEFAULT_SIZE, 258, Short.MAX_VALUE))
                    .addContainerGap(65, Short.MAX_VALUE))
        );
        contentPaneLayout.setVerticalGroup(
            contentPaneLayout.createParallelGroup()
                .addGroup(contentPaneLayout.createSequentialGroup()
                    .addGap(35, 35, 35)
                    .addComponent(label1)
                    .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                    .addComponent(txtUsuario, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                    .addGap(9, 9, 9)
                    .addComponent(label2)
                    .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                    .addComponent(txtSenha, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                    .addGap(37, 37, 37)
                    .addGroup(contentPaneLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                        .addComponent(btnSair)
                        .addComponent(btnLogin))
                    .addContainerGap(37, Short.MAX_VALUE))
        );
        setSize(385, 265);
        setLocationRelativeTo(null);
        // JFormDesigner - End of component initialization  //GEN-END:initComponents
    }

    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
    // Generated using JFormDesigner Evaluation license - Leandro Costa
    private JTextField txtUsuario;
    private JButton btnSair;
    private JLabel label1;
    private JLabel label2;
    private JButton btnLogin;
    private JPasswordField txtSenha;
    // JFormDesigner - End of variables declaration  //GEN-END:variables
}
