/*
 * Created by JFormDesigner on Tue Jan 02 22:24:57 BRST 2018
 */

package br.edu.devmedia.jdbc.gui;

import br.edu.devmedia.jdbc.bo.PessoaBO;
import br.edu.devmedia.jdbc.bo.UfBO;
import br.edu.devmedia.jdbc.dao.PessoaDAO;
import br.edu.devmedia.jdbc.dto.EnderecoDTO;
import br.edu.devmedia.jdbc.dto.PessoaDTO;
import br.edu.devmedia.jdbc.dto.UfDTO;
import br.edu.devmedia.jdbc.exception.NegocioException;
import br.edu.devmedia.jdbc.util.MensagensUtil;
import javafx.scene.input.KeyEvent;

import java.awt.*;
import java.awt.event.*;
import java.lang.Integer;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import javax.swing.*;
import javax.swing.GroupLayout;
import javax.swing.border.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

/**
 * @author Leandro Costa
 */
public class MainFrame extends JFrame {
    public MainFrame() {
        initComponents();
    }

    //Formatter de datas
    private DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
    private List<Integer> idPessoas = new ArrayList<Integer>();
    private PessoaDTO pessoaDTO;

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                MainFrame inst = new MainFrame();
                inst.setLocationRelativeTo(null);
                inst.setVisible(true);
            }
        });
    }

    private void btnLimparActionPerformed(ActionEvent e) {

        txtNome.setText("");
        txtCPF.setText("");
        txtNascimento.setText("");
        rbtMasculino.setSelected(true);

        txtLogradouro.setText("");
        txtBairro.setText("");
        txtCep.setText("");
        txtCidade.setText("");
        txtNumero.setText("");
        comboUF.setSelectedIndex(-1);
}

    private void btnCadastrarActionPerformed(ActionEvent e) {

        PessoaDTO pessoaDTO = new PessoaDTO();
        EnderecoDTO enderecoDTO = new EnderecoDTO();
        PessoaBO pessoaBO = new PessoaBO();

        try {

            String nome = txtNome.getText();
            String cpf = txtCPF.getText();
            //String endereco = txtEndereco.getText();
            String nasc = txtNascimento.getText();

            pessoaBO.validaNome(nome);
            pessoaBO.validaCpf(cpf);
            //pessoaBO.validaEndereco(endereco);
            pessoaBO.validaDtNascimento(nasc);

            pessoaDTO.setNome(nome);
            pessoaDTO.setCpf(Long.parseLong(cpf));
            //pessoaDTO.setEndereco(endereco);
            pessoaDTO.setDtNascimento(dateFormat.parse(nasc));

            char sexo = rbtMasculino.isSelected() ? 'M' : 'F';
            pessoaDTO.setSexo(sexo);

            enderecoDTO.setLogradouro(txtLogradouro.getText());
            enderecoDTO.setNumero(txtNumero.getText().equals("") ? null : Long.parseLong(txtNumero.getText()));
            enderecoDTO.setBairro(txtBairro.getText());
            enderecoDTO.setCep(txtCep.getText().equals("") ? null : Integer.parseInt(txtCep.getText()));
            enderecoDTO.setCidade(txtCidade.getText());

            pessoaBO.validaEndereco(enderecoDTO);

            UfDTO ufDTO = new UfDTO();

            ufDTO.setIdUf(comboUF.getSelectedIndex() + 1);
            enderecoDTO.setUfDTO(ufDTO);

            pessoaDTO.setEnderecoDTO(enderecoDTO);

            pessoaBO.cadatrar(pessoaDTO);

            MensagensUtil.addMsg(MainFrame.this, "Cadastro efetuado com sucesso");

            //Recarrega o form pra recarregar os dados
            MainFrame.this.dispose();
            main(null);

        } catch (Exception e1) {
            e1.printStackTrace();
            MensagensUtil.addMsg(MainFrame.this, e1.getMessage());
        }
    }

    private JTable getTableListagem() {

        PessoaBO pessoaBO = new PessoaBO();

        try {

            String[][] lista = pessoaBO.listagem(idPessoas);

            if (lista.length == 0) {
                this.btnDeleteAll.setEnabled(false);
            }
            //if (tableListagem == null) {
            TableModel tableListagemModel =
                    new DefaultTableModel(
                            lista,
                            new String[] { "Id", "Nome", "CPF", "Sexo", "Dt. Nasc.", "Logradouro", "CEP", "UF", "", "" });
            tableListagem = new JTable();
            tableListagem.setModel(tableListagemModel);
            //}

            Action actionDelecao = new AbstractAction() {
                @Override
                public void actionPerformed(ActionEvent actionEvent) {

                    int resp = JOptionPane.showConfirmDialog(MainFrame.this, "Deseja realmente remover esse registro?");

                    if (resp == 0) {

                        JTable table = (JTable) actionEvent.getSource();

                        int linha = Integer.parseInt(actionEvent.getActionCommand());

                        ((DefaultTableModel) table.getModel()).removeRow(linha);

                        try {

                            pessoaDTO = pessoaBO.buscaPorId(idPessoas.get(linha));

                            pessoaBO.removePessoa(idPessoas.get(linha), getPessoaDTO().getEnderecoDTO().getIdEndereco());
                            idPessoas.remove(linha);

                            MensagensUtil.addMsg(MainFrame.this, "Registro removido com sucesso!");

                        } catch (NegocioException e) {
                            e.printStackTrace();
                            MensagensUtil.addMsg(MainFrame.this, e.getMessage());
                        }
                    }
                }
            };

            Action actionEdicao = new AbstractAction() {
                @Override
                public void actionPerformed(ActionEvent actionEvent) {

                    int linha = Integer.parseInt(actionEvent.getActionCommand());

                    try {

                        pessoaDTO = pessoaBO.buscaPorId(idPessoas.get(linha));

                        populaInternalUpdate(pessoaDTO);

                    } catch (NegocioException e) {
                        e.printStackTrace();
                        MensagensUtil.addMsg(MainFrame.this, e.getMessage());
                    }
                }
            };

            ButtonColumn buttonColumnDelecao = new ButtonColumn(tableListagem, actionDelecao, 8);
            ButtonColumn buttonColumnEdicao = new ButtonColumn(tableListagem, actionEdicao, 9);
            //Atalhos
            buttonColumnDelecao.setMnemonic(java.awt.event.KeyEvent.VK_D);
            buttonColumnEdicao.setMnemonic(java.awt.event.KeyEvent.VK_E);

        } catch (NegocioException e){
            e.printStackTrace();
            MensagensUtil.addMsg(MainFrame.this, e.getMessage());
        }

        return tableListagem;
    }

    private void populaInternalUpdate(PessoaDTO pessoaDTO) {

        getComboUpdateUF();

        this.lblIdValor.setText(pessoaDTO.getIdPessoa().toString());
        this.txtNomeUpdate.setText(pessoaDTO.getNome());
        this.txtCPFUpdate.setText(pessoaDTO.getCpf().toString());
        this.txtNascimentoUpdate.setText(dateFormat.format(pessoaDTO.getDtNascimento()));
        this.rbtMasculinoUpdate.setSelected(pessoaDTO.getSexo() == 'M');
        this.rbtFemininoUpdate.setSelected(pessoaDTO.getSexo() == 'F');

        EnderecoDTO endereco = pessoaDTO.getEnderecoDTO();

        this.txtLogradouroUpdate.setText(endereco.getLogradouro());
        this.txtBairroUpdate.setText(endereco.getBairro());
        this.txtCidadeUpdate.setText(endereco.getCidade());
        this.txtNumeroUpdate.setText(endereco.getNumero().toString());
        this.txtCepUpdate.setText(endereco.getCep().toString());
        this.comboUFUpdate.setSelectedIndex(endereco.getUfDTO().getIdUf() - 1);

        this.internalUpdate.setVisible(true);
    }

    private JScrollPane getScrollListagem() {
        //if(scrollListagem == null) {
            //scrollListagem = new JScrollPane();
            scrollListagem.setViewportView(getTableListagem());
        //}
        return scrollListagem;
    }

    private void thisWindowOpened(WindowEvent e) {

        getScrollListagem();

        internalUpdate.setVisible(false);

        getComboUF();
        getComboConsultaUF();
    }

    @SuppressWarnings({ "rawtypes", "unchecked" })
    private JComboBox getComboUF() {

        try {

            UfBO ufBO = new UfBO();

            ComboBoxModel comboUfModel =
                    new DefaultComboBoxModel(converteEstados(ufBO.listaUFs()));
            //comboUF = new JComboBox();
            comboUF.setModel(comboUfModel);

        } catch (Exception e) {
            e.printStackTrace();
            MensagensUtil.addMsg(MainFrame.this, e.getMessage());
        }

        return comboUF;
    }

    @SuppressWarnings({ "rawtypes", "unchecked" })
    private JComboBox getComboUpdateUF() {

        try {

            UfBO ufBO = new UfBO();

            ComboBoxModel comboUfModel =
                    new DefaultComboBoxModel(converteEstados(ufBO.listaUFs()));
            comboUFUpdate.setModel(comboUfModel);

        } catch (Exception e) {
            e.printStackTrace();
            MensagensUtil.addMsg(MainFrame.this, e.getMessage());
        }

        return comboUFUpdate;
    }

    @SuppressWarnings({ "rawtypes", "unchecked" })
    private JComboBox getComboConsultaUF() {

        try {

            UfBO ufBO = new UfBO();

            ComboBoxModel comboUfModel =
                    new DefaultComboBoxModel(converteEstados(ufBO.listaUFs()));
            comboUfConsulta.setModel(comboUfModel);

        } catch (Exception e) {
            e.printStackTrace();
            MensagensUtil.addMsg(MainFrame.this, e.getMessage());
        }

        return comboUfConsulta;
    }

    private void btnConsultaActionPerformed(ActionEvent e) {

        getscrollListConsulta();
    }

    private JScrollPane getscrollListConsulta() {

        scrollListConsulta.setViewportView(getTableListConsulta());

        return scrollListConsulta;
    }

    private JTable getTableListConsulta() {

        String nome = txtNomeConsulta.getText();
        Long cpf = txtCPFConsulta.getText().equals("") ? null : Long.parseLong(txtCPFConsulta.getText());
        char sexo = rbtMasculinoConsulta.isSelected() ? 'M' : 'F';
        String orderBy = rbtCpfOrder.isSelected() ? "nome" : "cpf";

        if (tableListConsulta == null) {

            tableListConsulta = new JTable();

        } else {

            PessoaBO pessoaBO = new PessoaBO();

            try {

                String[][] lista = pessoaBO.listaConsulta(nome, cpf, sexo, orderBy);

                TableModel tableListConsultaModel =
                        new DefaultTableModel(
                                lista,
                                new String[]{"Nome", "CPF", "Endereço", "Sexo", "Dt. Nasc."});
                tableListConsulta.setModel(tableListConsultaModel);

            } catch (NegocioException e) {
                e.printStackTrace();
                MensagensUtil.addMsg(MainFrame.this, e.getMessage());
            }
        }

        return tableListConsulta;
    }

    private void btnDeleteAllActionPerformed(ActionEvent actionEvent) {

        PessoaBO pessoaBO = new PessoaBO();

        try {
            int resp = JOptionPane.showConfirmDialog(MainFrame.this, "Deseja realmente remover todos os registros?");

            if (resp == 0) {

                pessoaBO.removeTudo();
                MensagensUtil.addMsg(MainFrame.this, "Removido todos os registros");

                getScrollListagem();
            }

        } catch (NegocioException e) {
            e.printStackTrace();
        }
    }

    private void btnUpdateActionPerformed(ActionEvent actionEvent) {

        PessoaDTO pessoaDTO = new PessoaDTO();
        EnderecoDTO enderecoDTO = new EnderecoDTO();
        PessoaBO pessoaBO = new PessoaBO();

        try {

            String nome = txtNomeUpdate.getText();
            String cpf = txtCPFUpdate.getText();
            String nasc = txtNascimentoUpdate.getText();

            pessoaBO.validaNome(nome);
            pessoaBO.validaCpf(cpf);
            pessoaBO.validaDtNascimento(nasc);

            pessoaDTO.setNome(nome);
            pessoaDTO.setCpf(Long.parseLong(cpf));
            pessoaDTO.setDtNascimento(dateFormat.parse(nasc));

            char sexo = rbtMasculinoUpdate.isSelected() ? 'M' : 'F';
            pessoaDTO.setSexo(sexo);

            enderecoDTO.setIdEndereco(getPessoaDTO().getEnderecoDTO().getIdEndereco());
            enderecoDTO.setLogradouro(txtLogradouroUpdate.getText());
            enderecoDTO.setNumero(txtNumeroUpdate.getText().equals("") ? null : Long.parseLong(txtNumeroUpdate.getText()));
            enderecoDTO.setBairro(txtBairroUpdate.getText());
            enderecoDTO.setCep(txtCepUpdate.getText().equals("") ? null : Integer.parseInt(txtCepUpdate.getText()));
            enderecoDTO.setCidade(txtCidadeUpdate.getText());

            pessoaBO.validaEndereco(enderecoDTO);

            UfDTO ufDTO = new UfDTO();

            ufDTO.setIdUf(comboUFUpdate.getSelectedIndex() + 1);
            enderecoDTO.setUfDTO(ufDTO);

            pessoaDTO.setEnderecoDTO(enderecoDTO);
            pessoaDTO.setIdPessoa(getPessoaDTO().getIdPessoa());
            pessoaDTO.setIdPessoa(getPessoaDTO().getIdPessoa());

            PessoaDAO pessoaDAO = new PessoaDAO();
            pessoaDAO.atualizar(pessoaDTO);

            MensagensUtil.addMsg(MainFrame.this, "Dados atualizados com sucesso!");

            getScrollListagem();

        } catch (Exception e) {
            e.printStackTrace();
            MensagensUtil.addMsg(MainFrame.this, e.getMessage());
        }
    }

    public PessoaDTO getPessoaDTO() {
        return pessoaDTO;
    }

    private String[] converteEstados(List<UfDTO> lista) {

        String[] resultado = new String[lista.size()];

        for (int i = 0; i < lista.size(); i++) {

            UfDTO ufDTO = lista.get(i);

            resultado[i] = ufDTO.getDescricao();
        }

        return resultado;
    }

    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
        // Generated using JFormDesigner Evaluation license - Leandro Costa
        mainTabbedPane = new JTabbedPane();
        panelCadastro = new JPanel();
        btnLimpar = new JButton();
        btnCadastrar = new JButton();
        panelPrincipalCadastro = new JPanel();
        lblNome = new JLabel();
        txtNome = new JTextField();
        lblCPF = new JLabel();
        txtCPF = new JTextField();
        lblNascimento = new JLabel();
        txtNascimento = new JTextField();
        panelEnderecoCadastro = new JPanel();
        txtLogradouro = new JTextField();
        lblLogradouro = new JLabel();
        txtBairro = new JTextField();
        lblBairro = new JLabel();
        txtCidade = new JTextField();
        lblCidade = new JLabel();
        txtNumero = new JTextField();
        lblNumero = new JLabel();
        txtCep = new JTextField();
        lblCep = new JLabel();
        comboUF = new JComboBox();
        lblEstado = new JLabel();
        panelSexo = new JPanel();
        rbtFeminino = new JRadioButton();
        rbtMasculino = new JRadioButton();
        txtSexo = new JLabel();
        panelListagem = new JPanel();
        scrollListagem = new JScrollPane();
        tableListagem = new JTable();
        btnDeleteAll = new JButton();
        internalUpdate = new JInternalFrame();
        panelUpdate = new JPanel();
        lblNomeUpdate = new JLabel();
        txtNomeUpdate = new JTextField();
        lblCPFUpdate = new JLabel();
        txtCPFUpdate = new JTextField();
        txtSexoUpdate = new JLabel();
        rbtMasculinoUpdate = new JRadioButton();
        rbtFemininoUpdate = new JRadioButton();
        lblNascimentoUpdate = new JLabel();
        txtNascimentoUpdate = new JTextField();
        labelIdUpdate = new JLabel();
        lblIdValor = new JLabel();
        panelEnderecoUpdate = new JPanel();
        txtLogradouroUpdate = new JTextField();
        lblLogradouroUpdate = new JLabel();
        txtNumeroUpdate = new JTextField();
        lblNumeroUpdate = new JLabel();
        txtCepUpdate = new JTextField();
        lblCepUpdate = new JLabel();
        txtBairroUpdate = new JTextField();
        lblBairroUpdate = new JLabel();
        txtCidadeUpdate = new JTextField();
        lblCidadeUpdate = new JLabel();
        comboUFUpdate = new JComboBox();
        lblEstadoUpdate = new JLabel();
        btnUpdate = new JButton();
        panelConsulta = new JPanel();
        panelTabelaList = new JPanel();
        scrollListConsulta = new JScrollPane();
        tableListConsulta = new JTable();
        panel1 = new JPanel();
        txtNomeConsulta = new JTextField();
        lblNomeConsulta = new JLabel();
        txtSexoConsulta = new JLabel();
        rbtMasculinoConsulta = new JRadioButton();
        rbtFemininoConsulta = new JRadioButton();
        comboUfConsulta = new JComboBox();
        lblEstadoConsulta = new JLabel();
        txtCPFConsulta = new JTextField();
        lblCPFConsulta = new JLabel();
        txtCepConsulta = new JTextField();
        lblCepConsulta = new JLabel();
        btnConsulta = new JButton();
        rbtCpfOrder = new JRadioButton();
        rbtNomeOrder = new JRadioButton();
        lblOrder = new JLabel();

        //======== this ========
        setTitle("Principal");
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowOpened(WindowEvent e) {
                thisWindowOpened(e);
            }
        });
        Container contentPane = getContentPane();

        //======== mainTabbedPane ========
        {

            //======== panelCadastro ========
            {

                // JFormDesigner evaluation mark
                panelCadastro.setBorder(new javax.swing.border.CompoundBorder(
                    new javax.swing.border.TitledBorder(new javax.swing.border.EmptyBorder(0, 0, 0, 0),
                        "JFormDesigner Evaluation", javax.swing.border.TitledBorder.CENTER,
                        javax.swing.border.TitledBorder.BOTTOM, new java.awt.Font("Dialog", java.awt.Font.BOLD, 12),
                        java.awt.Color.red), panelCadastro.getBorder())); panelCadastro.addPropertyChangeListener(new java.beans.PropertyChangeListener(){public void propertyChange(java.beans.PropertyChangeEvent e){if("border".equals(e.getPropertyName()))throw new RuntimeException();}});


                //---- btnLimpar ----
                btnLimpar.setText("Limpar");
                btnLimpar.addActionListener(e -> btnLimparActionPerformed(e));

                //---- btnCadastrar ----
                btnCadastrar.setText("Cadastrar");
                btnCadastrar.addActionListener(e -> btnCadastrarActionPerformed(e));

                //======== panelPrincipalCadastro ========
                {
                    panelPrincipalCadastro.setBorder(new TitledBorder("Pessoa"));

                    //---- lblNome ----
                    lblNome.setText("Nome");

                    //---- lblCPF ----
                    lblCPF.setText("CPF");

                    //---- lblNascimento ----
                    lblNascimento.setText("Data de Nascimento");

                    GroupLayout panelPrincipalCadastroLayout = new GroupLayout(panelPrincipalCadastro);
                    panelPrincipalCadastro.setLayout(panelPrincipalCadastroLayout);
                    panelPrincipalCadastroLayout.setHorizontalGroup(
                        panelPrincipalCadastroLayout.createParallelGroup()
                            .addGroup(panelPrincipalCadastroLayout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(panelPrincipalCadastroLayout.createParallelGroup()
                                    .addComponent(lblNome)
                                    .addComponent(txtNome, GroupLayout.PREFERRED_SIZE, 350, GroupLayout.PREFERRED_SIZE)
                                    .addComponent(lblCPF)
                                    .addComponent(txtCPF, GroupLayout.Alignment.TRAILING, GroupLayout.PREFERRED_SIZE, 350, GroupLayout.PREFERRED_SIZE))
                                .addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addGroup(GroupLayout.Alignment.TRAILING, panelPrincipalCadastroLayout.createSequentialGroup()
                                .addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGroup(panelPrincipalCadastroLayout.createParallelGroup()
                                    .addComponent(lblNascimento)
                                    .addComponent(txtNascimento, GroupLayout.PREFERRED_SIZE, 350, GroupLayout.PREFERRED_SIZE))
                                .addContainerGap())
                    );
                    panelPrincipalCadastroLayout.setVerticalGroup(
                        panelPrincipalCadastroLayout.createParallelGroup()
                            .addGroup(panelPrincipalCadastroLayout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(lblNome)
                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtNome, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(lblCPF)
                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtCPF, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(lblNascimento)
                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtNascimento, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                .addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    );
                }

                //======== panelEnderecoCadastro ========
                {
                    panelEnderecoCadastro.setBorder(new TitledBorder("Endere\u00e7o"));

                    //---- lblLogradouro ----
                    lblLogradouro.setText("Logradouro");

                    //---- lblBairro ----
                    lblBairro.setText("Bairro");

                    //---- lblCidade ----
                    lblCidade.setText("Cidade");

                    //---- lblNumero ----
                    lblNumero.setText("N\u00famero");

                    //---- lblCep ----
                    lblCep.setText("CEP");

                    //---- lblEstado ----
                    lblEstado.setText("UF");

                    GroupLayout panelEnderecoCadastroLayout = new GroupLayout(panelEnderecoCadastro);
                    panelEnderecoCadastro.setLayout(panelEnderecoCadastroLayout);
                    panelEnderecoCadastroLayout.setHorizontalGroup(
                        panelEnderecoCadastroLayout.createParallelGroup()
                            .addGroup(panelEnderecoCadastroLayout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(panelEnderecoCadastroLayout.createParallelGroup()
                                    .addGroup(panelEnderecoCadastroLayout.createSequentialGroup()
                                        .addGroup(panelEnderecoCadastroLayout.createParallelGroup(GroupLayout.Alignment.LEADING, false)
                                            .addComponent(lblLogradouro)
                                            .addComponent(txtLogradouro)
                                            .addComponent(lblBairro)
                                            .addComponent(lblCidade)
                                            .addComponent(txtCidade)
                                            .addComponent(lblNumero)
                                            .addComponent(txtNumero)
                                            .addGroup(GroupLayout.Alignment.TRAILING, panelEnderecoCadastroLayout.createSequentialGroup()
                                                .addComponent(lblCep)
                                                .addGap(189, 189, 189)
                                                .addComponent(lblEstado, GroupLayout.PREFERRED_SIZE, 25, GroupLayout.PREFERRED_SIZE)
                                                .addGap(115, 115, 115))
                                            .addComponent(txtBairro))
                                        .addContainerGap(106, Short.MAX_VALUE))
                                    .addGroup(panelEnderecoCadastroLayout.createSequentialGroup()
                                        .addComponent(txtCep, GroupLayout.PREFERRED_SIZE, 174, GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, 36, Short.MAX_VALUE)
                                        .addComponent(comboUF, GroupLayout.PREFERRED_SIZE, 140, GroupLayout.PREFERRED_SIZE)
                                        .addContainerGap(106, Short.MAX_VALUE))))
                    );
                    panelEnderecoCadastroLayout.setVerticalGroup(
                        panelEnderecoCadastroLayout.createParallelGroup()
                            .addGroup(panelEnderecoCadastroLayout.createSequentialGroup()
                                .addComponent(lblLogradouro)
                                .addGap(6, 6, 6)
                                .addComponent(txtLogradouro, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(lblBairro)
                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtBairro, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(lblCidade)
                                .addGap(6, 6, 6)
                                .addComponent(txtCidade, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(lblNumero)
                                .addGap(6, 6, 6)
                                .addComponent(txtNumero, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(panelEnderecoCadastroLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                    .addComponent(lblCep)
                                    .addComponent(lblEstado))
                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(panelEnderecoCadastroLayout.createParallelGroup()
                                    .addComponent(txtCep, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                    .addComponent(comboUF, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                                .addGap(0, 15, Short.MAX_VALUE))
                    );
                }

                //======== panelSexo ========
                {
                    panelSexo.setBorder(new TitledBorder("Sexo"));

                    //---- rbtFeminino ----
                    rbtFeminino.setText("Feminino");

                    //---- rbtMasculino ----
                    rbtMasculino.setText("Masculino");
                    rbtMasculino.setSelected(true);

                    //---- txtSexo ----
                    txtSexo.setText("Sexo:");

                    GroupLayout panelSexoLayout = new GroupLayout(panelSexo);
                    panelSexo.setLayout(panelSexoLayout);
                    panelSexoLayout.setHorizontalGroup(
                        panelSexoLayout.createParallelGroup()
                            .addGroup(panelSexoLayout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(panelSexoLayout.createParallelGroup()
                                    .addComponent(txtSexo)
                                    .addGroup(panelSexoLayout.createSequentialGroup()
                                        .addComponent(rbtMasculino)
                                        .addGap(18, 18, 18)
                                        .addComponent(rbtFeminino)))
                                .addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    );
                    panelSexoLayout.setVerticalGroup(
                        panelSexoLayout.createParallelGroup()
                            .addGroup(GroupLayout.Alignment.TRAILING, panelSexoLayout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(txtSexo)
                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(panelSexoLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                    .addComponent(rbtMasculino)
                                    .addComponent(rbtFeminino))
                                .addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    );
                }

                GroupLayout panelCadastroLayout = new GroupLayout(panelCadastro);
                panelCadastro.setLayout(panelCadastroLayout);
                panelCadastroLayout.setHorizontalGroup(
                    panelCadastroLayout.createParallelGroup()
                        .addGroup(panelCadastroLayout.createSequentialGroup()
                            .addGroup(panelCadastroLayout.createParallelGroup(GroupLayout.Alignment.LEADING, false)
                                .addComponent(panelPrincipalCadastro, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(panelSexo, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(panelEnderecoCadastro, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addContainerGap())
                        .addGroup(panelCadastroLayout.createSequentialGroup()
                            .addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(btnCadastrar)
                            .addGap(18, 18, 18)
                            .addComponent(btnLimpar)
                            .addGap(20, 20, 20))
                );
                panelCadastroLayout.setVerticalGroup(
                    panelCadastroLayout.createParallelGroup()
                        .addGroup(panelCadastroLayout.createSequentialGroup()
                            .addGroup(panelCadastroLayout.createParallelGroup(GroupLayout.Alignment.LEADING, false)
                                .addGroup(panelCadastroLayout.createSequentialGroup()
                                    .addContainerGap()
                                    .addComponent(panelEnderecoCadastro, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                                .addGroup(panelCadastroLayout.createSequentialGroup()
                                    .addComponent(panelPrincipalCadastro, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(panelSexo, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                            .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                            .addGroup(panelCadastroLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                .addComponent(btnLimpar)
                                .addComponent(btnCadastrar))
                            .addContainerGap(225, Short.MAX_VALUE))
                );
            }
            mainTabbedPane.addTab("Cadastro", panelCadastro);

            //======== panelListagem ========
            {

                //======== scrollListagem ========
                {
                    scrollListagem.setViewportView(tableListagem);
                }

                //---- btnDeleteAll ----
                btnDeleteAll.setText("Deletar Tudo");
                btnDeleteAll.addActionListener(e -> btnDeleteAllActionPerformed(e));

                //======== internalUpdate ========
                {
                    internalUpdate.setVisible(true);
                    internalUpdate.setTitle("Edi\u00e7\u00e3o de Pessoa");
                    internalUpdate.setPreferredSize(new Dimension(781, 345));
                    Container internalUpdateContentPane = internalUpdate.getContentPane();

                    //======== panelUpdate ========
                    {

                        //---- lblNomeUpdate ----
                        lblNomeUpdate.setText("Nome");

                        //---- lblCPFUpdate ----
                        lblCPFUpdate.setText("CPF");

                        //---- txtSexoUpdate ----
                        txtSexoUpdate.setText("Sexo:");

                        //---- rbtMasculinoUpdate ----
                        rbtMasculinoUpdate.setText("Masculino");
                        rbtMasculinoUpdate.setSelected(true);

                        //---- rbtFemininoUpdate ----
                        rbtFemininoUpdate.setText("Feminino");

                        //---- lblNascimentoUpdate ----
                        lblNascimentoUpdate.setText("Data de Nascimento");

                        //---- labelIdUpdate ----
                        labelIdUpdate.setText("ID:");

                        //---- lblIdValor ----
                        lblIdValor.setText("text");

                        GroupLayout panelUpdateLayout = new GroupLayout(panelUpdate);
                        panelUpdate.setLayout(panelUpdateLayout);
                        panelUpdateLayout.setHorizontalGroup(
                            panelUpdateLayout.createParallelGroup()
                                .addGroup(panelUpdateLayout.createSequentialGroup()
                                    .addContainerGap()
                                    .addGroup(panelUpdateLayout.createParallelGroup()
                                        .addComponent(txtNomeUpdate)
                                        .addComponent(txtCPFUpdate)
                                        .addGroup(panelUpdateLayout.createSequentialGroup()
                                            .addGroup(panelUpdateLayout.createParallelGroup()
                                                .addComponent(lblCPFUpdate)
                                                .addComponent(lblNomeUpdate))
                                            .addGap(0, 305, Short.MAX_VALUE)))
                                    .addGap(55, 55, 55)
                                    .addComponent(labelIdUpdate)
                                    .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(lblIdValor)
                                    .addGap(94, 94, 94)
                                    .addGroup(panelUpdateLayout.createParallelGroup()
                                        .addGroup(panelUpdateLayout.createParallelGroup()
                                            .addComponent(txtNascimentoUpdate, GroupLayout.Alignment.TRAILING, GroupLayout.PREFERRED_SIZE, 300, GroupLayout.PREFERRED_SIZE)
                                            .addGroup(GroupLayout.Alignment.TRAILING, panelUpdateLayout.createSequentialGroup()
                                                .addComponent(txtSexoUpdate)
                                                .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                                                .addComponent(rbtMasculinoUpdate)
                                                .addGap(18, 18, 18)
                                                .addComponent(rbtFemininoUpdate)))
                                        .addComponent(lblNascimentoUpdate))
                                    .addContainerGap())
                        );
                        panelUpdateLayout.setVerticalGroup(
                            panelUpdateLayout.createParallelGroup()
                                .addGroup(panelUpdateLayout.createSequentialGroup()
                                    .addContainerGap()
                                    .addGroup(panelUpdateLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                        .addComponent(lblNomeUpdate)
                                        .addComponent(lblNascimentoUpdate)
                                        .addComponent(labelIdUpdate)
                                        .addComponent(lblIdValor))
                                    .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                    .addGroup(panelUpdateLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                        .addComponent(txtNomeUpdate, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                        .addComponent(txtNascimentoUpdate, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                                    .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                    .addGroup(panelUpdateLayout.createParallelGroup(GroupLayout.Alignment.TRAILING)
                                        .addGroup(panelUpdateLayout.createSequentialGroup()
                                            .addComponent(lblCPFUpdate)
                                            .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                            .addComponent(txtCPFUpdate, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                                        .addGroup(panelUpdateLayout.createParallelGroup()
                                            .addGroup(panelUpdateLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                                .addComponent(rbtMasculinoUpdate)
                                                .addComponent(txtSexoUpdate))
                                            .addComponent(rbtFemininoUpdate)))
                                    .addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        );
                    }

                    //======== panelEnderecoUpdate ========
                    {
                        panelEnderecoUpdate.setBorder(new TitledBorder("Endereco"));

                        //---- lblLogradouroUpdate ----
                        lblLogradouroUpdate.setText("Logradouro");

                        //---- lblNumeroUpdate ----
                        lblNumeroUpdate.setText("N\u00famero");

                        //---- lblCepUpdate ----
                        lblCepUpdate.setText("CEP");

                        //---- lblBairroUpdate ----
                        lblBairroUpdate.setText("Bairro");

                        //---- lblCidadeUpdate ----
                        lblCidadeUpdate.setText("Cidade");

                        //---- lblEstadoUpdate ----
                        lblEstadoUpdate.setText("UF");

                        //---- btnUpdate ----
                        btnUpdate.setText("Atualizar");
                        btnUpdate.addActionListener(e -> btnUpdateActionPerformed(e));

                        GroupLayout panelEnderecoUpdateLayout = new GroupLayout(panelEnderecoUpdate);
                        panelEnderecoUpdate.setLayout(panelEnderecoUpdateLayout);
                        panelEnderecoUpdateLayout.setHorizontalGroup(
                            panelEnderecoUpdateLayout.createParallelGroup()
                                .addGroup(panelEnderecoUpdateLayout.createSequentialGroup()
                                    .addContainerGap()
                                    .addGroup(panelEnderecoUpdateLayout.createParallelGroup()
                                        .addGroup(panelEnderecoUpdateLayout.createSequentialGroup()
                                            .addGroup(panelEnderecoUpdateLayout.createParallelGroup()
                                                .addGroup(panelEnderecoUpdateLayout.createSequentialGroup()
                                                    .addGroup(panelEnderecoUpdateLayout.createParallelGroup()
                                                        .addComponent(lblBairroUpdate)
                                                        .addComponent(txtBairroUpdate, GroupLayout.PREFERRED_SIZE, 350, GroupLayout.PREFERRED_SIZE)
                                                        .addComponent(lblLogradouroUpdate)
                                                        .addComponent(txtLogradouroUpdate, GroupLayout.PREFERRED_SIZE, 350, GroupLayout.PREFERRED_SIZE))
                                                    .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                    .addGroup(panelEnderecoUpdateLayout.createParallelGroup()
                                                        .addGroup(panelEnderecoUpdateLayout.createSequentialGroup()
                                                            .addGroup(panelEnderecoUpdateLayout.createParallelGroup()
                                                                .addComponent(lblNumeroUpdate)
                                                                .addComponent(txtNumeroUpdate, GroupLayout.PREFERRED_SIZE, 65, GroupLayout.PREFERRED_SIZE))
                                                            .addGap(111, 111, 111)
                                                            .addGroup(panelEnderecoUpdateLayout.createParallelGroup()
                                                                .addComponent(lblCepUpdate)
                                                                .addComponent(txtCepUpdate, GroupLayout.PREFERRED_SIZE, 174, GroupLayout.PREFERRED_SIZE)))
                                                        .addComponent(lblCidadeUpdate)
                                                        .addComponent(txtCidadeUpdate, GroupLayout.PREFERRED_SIZE, 350, GroupLayout.PREFERRED_SIZE)))
                                                .addGroup(panelEnderecoUpdateLayout.createSequentialGroup()
                                                    .addComponent(lblEstadoUpdate, GroupLayout.PREFERRED_SIZE, 25, GroupLayout.PREFERRED_SIZE)
                                                    .addGap(0, 0, Short.MAX_VALUE)))
                                            .addContainerGap())
                                        .addGroup(panelEnderecoUpdateLayout.createSequentialGroup()
                                            .addComponent(comboUFUpdate, GroupLayout.PREFERRED_SIZE, 140, GroupLayout.PREFERRED_SIZE)
                                            .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                            .addComponent(btnUpdate)
                                            .addGap(14, 14, 14))))
                        );
                        panelEnderecoUpdateLayout.setVerticalGroup(
                            panelEnderecoUpdateLayout.createParallelGroup()
                                .addGroup(panelEnderecoUpdateLayout.createSequentialGroup()
                                    .addContainerGap()
                                    .addGroup(panelEnderecoUpdateLayout.createParallelGroup()
                                        .addGroup(panelEnderecoUpdateLayout.createSequentialGroup()
                                            .addComponent(lblLogradouroUpdate)
                                            .addGap(6, 6, 6)
                                            .addComponent(txtLogradouroUpdate, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                                        .addGroup(panelEnderecoUpdateLayout.createSequentialGroup()
                                            .addComponent(lblCepUpdate)
                                            .addGap(6, 6, 6)
                                            .addComponent(txtCepUpdate, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                                        .addGroup(GroupLayout.Alignment.TRAILING, panelEnderecoUpdateLayout.createSequentialGroup()
                                            .addComponent(lblNumeroUpdate)
                                            .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                            .addComponent(txtNumeroUpdate, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))
                                    .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                    .addGroup(panelEnderecoUpdateLayout.createParallelGroup()
                                        .addGroup(panelEnderecoUpdateLayout.createSequentialGroup()
                                            .addComponent(lblBairroUpdate)
                                            .addGap(6, 6, 6)
                                            .addComponent(txtBairroUpdate, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                                        .addGroup(panelEnderecoUpdateLayout.createSequentialGroup()
                                            .addComponent(lblCidadeUpdate)
                                            .addGap(6, 6, 6)
                                            .addComponent(txtCidadeUpdate, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))
                                    .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                    .addGroup(panelEnderecoUpdateLayout.createParallelGroup(GroupLayout.Alignment.TRAILING)
                                        .addGroup(panelEnderecoUpdateLayout.createSequentialGroup()
                                            .addComponent(lblEstadoUpdate)
                                            .addGap(6, 6, 6)
                                            .addComponent(comboUFUpdate, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                                        .addComponent(btnUpdate))
                                    .addContainerGap(20, Short.MAX_VALUE))
                        );
                    }

                    GroupLayout internalUpdateContentPaneLayout = new GroupLayout(internalUpdateContentPane);
                    internalUpdateContentPane.setLayout(internalUpdateContentPaneLayout);
                    internalUpdateContentPaneLayout.setHorizontalGroup(
                        internalUpdateContentPaneLayout.createParallelGroup()
                            .addGroup(internalUpdateContentPaneLayout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(internalUpdateContentPaneLayout.createParallelGroup()
                                    .addComponent(panelUpdate, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(panelEnderecoUpdate, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addContainerGap())
                    );
                    internalUpdateContentPaneLayout.setVerticalGroup(
                        internalUpdateContentPaneLayout.createParallelGroup()
                            .addGroup(GroupLayout.Alignment.TRAILING, internalUpdateContentPaneLayout.createSequentialGroup()
                                .addContainerGap(17, Short.MAX_VALUE)
                                .addComponent(panelUpdate, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(panelEnderecoUpdate, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                .addContainerGap())
                    );
                }

                GroupLayout panelListagemLayout = new GroupLayout(panelListagem);
                panelListagem.setLayout(panelListagemLayout);
                panelListagemLayout.setHorizontalGroup(
                    panelListagemLayout.createParallelGroup()
                        .addGroup(panelListagemLayout.createSequentialGroup()
                            .addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(panelListagemLayout.createParallelGroup()
                                .addComponent(btnDeleteAll, GroupLayout.Alignment.TRAILING)
                                .addComponent(scrollListagem, GroupLayout.Alignment.TRAILING, GroupLayout.PREFERRED_SIZE, 844, GroupLayout.PREFERRED_SIZE)
                                .addComponent(internalUpdate, GroupLayout.Alignment.TRAILING, GroupLayout.PREFERRED_SIZE, 850, GroupLayout.PREFERRED_SIZE))
                            .addContainerGap())
                );
                panelListagemLayout.setVerticalGroup(
                    panelListagemLayout.createParallelGroup()
                        .addGroup(panelListagemLayout.createSequentialGroup()
                            .addComponent(scrollListagem, GroupLayout.PREFERRED_SIZE, 173, GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(btnDeleteAll)
                            .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(internalUpdate, GroupLayout.DEFAULT_SIZE, 356, Short.MAX_VALUE))
                );
            }
            mainTabbedPane.addTab("Listagem", panelListagem);

            //======== panelConsulta ========
            {

                //======== panelTabelaList ========
                {
                    panelTabelaList.setBorder(new TitledBorder("Listagem"));

                    //======== scrollListConsulta ========
                    {
                        scrollListConsulta.setViewportView(tableListConsulta);
                    }

                    GroupLayout panelTabelaListLayout = new GroupLayout(panelTabelaList);
                    panelTabelaList.setLayout(panelTabelaListLayout);
                    panelTabelaListLayout.setHorizontalGroup(
                        panelTabelaListLayout.createParallelGroup()
                            .addComponent(scrollListConsulta, GroupLayout.DEFAULT_SIZE, 834, Short.MAX_VALUE)
                    );
                    panelTabelaListLayout.setVerticalGroup(
                        panelTabelaListLayout.createParallelGroup()
                            .addComponent(scrollListConsulta, GroupLayout.DEFAULT_SIZE, 349, Short.MAX_VALUE)
                    );
                }

                //======== panel1 ========
                {
                    panel1.setBorder(new TitledBorder("Filtro"));

                    //---- lblNomeConsulta ----
                    lblNomeConsulta.setText("Nome");

                    //---- txtSexoConsulta ----
                    txtSexoConsulta.setText("Sexo:");

                    //---- rbtMasculinoConsulta ----
                    rbtMasculinoConsulta.setText("Masculino");
                    rbtMasculinoConsulta.setSelected(true);

                    //---- rbtFemininoConsulta ----
                    rbtFemininoConsulta.setText("Feminino");

                    //---- lblEstadoConsulta ----
                    lblEstadoConsulta.setText("UF");

                    //---- lblCPFConsulta ----
                    lblCPFConsulta.setText("CPF");

                    //---- lblCepConsulta ----
                    lblCepConsulta.setText("CEP");

                    //---- btnConsulta ----
                    btnConsulta.setText("Consultar");
                    btnConsulta.addActionListener(e -> btnConsultaActionPerformed(e));

                    //---- rbtCpfOrder ----
                    rbtCpfOrder.setText("CPF");

                    //---- rbtNomeOrder ----
                    rbtNomeOrder.setText("Nome");
                    rbtNomeOrder.setSelected(true);

                    //---- lblOrder ----
                    lblOrder.setText("Ordernar por:");

                    GroupLayout panel1Layout = new GroupLayout(panel1);
                    panel1.setLayout(panel1Layout);
                    panel1Layout.setHorizontalGroup(
                        panel1Layout.createParallelGroup()
                            .addGroup(panel1Layout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(panel1Layout.createParallelGroup()
                                    .addGroup(panel1Layout.createSequentialGroup()
                                        .addGroup(panel1Layout.createParallelGroup()
                                            .addComponent(lblNomeConsulta)
                                            .addComponent(txtNomeConsulta, GroupLayout.PREFERRED_SIZE, 300, GroupLayout.PREFERRED_SIZE))
                                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addGroup(panel1Layout.createParallelGroup()
                                            .addComponent(txtCPFConsulta, GroupLayout.PREFERRED_SIZE, 298, GroupLayout.PREFERRED_SIZE)
                                            .addComponent(lblCPFConsulta)))
                                    .addGroup(panel1Layout.createSequentialGroup()
                                        .addGroup(panel1Layout.createParallelGroup()
                                            .addComponent(txtSexoConsulta)
                                            .addGroup(panel1Layout.createSequentialGroup()
                                                .addComponent(rbtMasculinoConsulta)
                                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(rbtFemininoConsulta)))
                                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, 222, Short.MAX_VALUE)
                                        .addGroup(panel1Layout.createParallelGroup()
                                            .addComponent(comboUfConsulta, GroupLayout.PREFERRED_SIZE, 140, GroupLayout.PREFERRED_SIZE)
                                            .addComponent(lblEstadoConsulta, GroupLayout.PREFERRED_SIZE, 25, GroupLayout.PREFERRED_SIZE))
                                        .addGap(123, 123, 123)
                                        .addGroup(panel1Layout.createParallelGroup()
                                            .addComponent(txtCepConsulta, GroupLayout.PREFERRED_SIZE, 174, GroupLayout.PREFERRED_SIZE)
                                            .addComponent(lblCepConsulta)))
                                    .addGroup(panel1Layout.createSequentialGroup()
                                        .addComponent(lblOrder)
                                        .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(rbtNomeOrder, GroupLayout.PREFERRED_SIZE, 87, GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(rbtCpfOrder, GroupLayout.PREFERRED_SIZE, 82, GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, 491, Short.MAX_VALUE)
                                        .addComponent(btnConsulta)))
                                .addContainerGap())
                    );
                    panel1Layout.setVerticalGroup(
                        panel1Layout.createParallelGroup()
                            .addGroup(panel1Layout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(panel1Layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                    .addComponent(lblNomeConsulta)
                                    .addComponent(lblCPFConsulta))
                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(panel1Layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                    .addComponent(txtNomeConsulta, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txtCPFConsulta, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(panel1Layout.createParallelGroup(GroupLayout.Alignment.TRAILING, false)
                                    .addGroup(panel1Layout.createSequentialGroup()
                                        .addComponent(txtSexoConsulta)
                                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                        .addGroup(panel1Layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                            .addComponent(rbtMasculinoConsulta)
                                            .addComponent(rbtFemininoConsulta)))
                                    .addGroup(panel1Layout.createParallelGroup()
                                        .addGroup(panel1Layout.createSequentialGroup()
                                            .addComponent(lblEstadoConsulta)
                                            .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                            .addComponent(comboUfConsulta, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                                        .addGroup(panel1Layout.createSequentialGroup()
                                            .addComponent(lblCepConsulta)
                                            .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                            .addComponent(txtCepConsulta, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))))
                                .addGap(18, 18, 18)
                                .addGroup(panel1Layout.createParallelGroup()
                                    .addGroup(panel1Layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                        .addComponent(lblOrder)
                                        .addComponent(rbtCpfOrder)
                                        .addComponent(rbtNomeOrder))
                                    .addComponent(btnConsulta))
                                .addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    );
                }

                GroupLayout panelConsultaLayout = new GroupLayout(panelConsulta);
                panelConsulta.setLayout(panelConsultaLayout);
                panelConsultaLayout.setHorizontalGroup(
                    panelConsultaLayout.createParallelGroup()
                        .addGroup(panelConsultaLayout.createSequentialGroup()
                            .addContainerGap()
                            .addComponent(panelTabelaList, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addContainerGap())
                        .addComponent(panel1, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                );
                panelConsultaLayout.setVerticalGroup(
                    panelConsultaLayout.createParallelGroup()
                        .addGroup(panelConsultaLayout.createSequentialGroup()
                            .addComponent(panel1, GroupLayout.PREFERRED_SIZE, 180, GroupLayout.PREFERRED_SIZE)
                            .addGap(5, 5, 5)
                            .addComponent(panelTabelaList, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addContainerGap())
                );
            }
            mainTabbedPane.addTab("Consulta", panelConsulta);
        }

        GroupLayout contentPaneLayout = new GroupLayout(contentPane);
        contentPane.setLayout(contentPaneLayout);
        contentPaneLayout.setHorizontalGroup(
            contentPaneLayout.createParallelGroup()
                .addComponent(mainTabbedPane, GroupLayout.Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 858, Short.MAX_VALUE)
        );
        contentPaneLayout.setVerticalGroup(
            contentPaneLayout.createParallelGroup()
                .addGroup(contentPaneLayout.createSequentialGroup()
                    .addComponent(mainTabbedPane)
                    .addContainerGap())
        );
        pack();
        setLocationRelativeTo(getOwner());

        //---- grpSexo ----
        ButtonGroup grpSexo = new ButtonGroup();
        grpSexo.add(rbtFeminino);
        grpSexo.add(rbtMasculino);

        //---- grpSexoUpdate ----
        ButtonGroup grpSexoUpdate = new ButtonGroup();
        grpSexoUpdate.add(rbtMasculinoUpdate);
        grpSexoUpdate.add(rbtFemininoUpdate);

        //---- grpSexoConsulta ----
        ButtonGroup grpSexoConsulta = new ButtonGroup();
        grpSexoConsulta.add(rbtMasculinoConsulta);
        grpSexoConsulta.add(rbtFemininoConsulta);

        //---- grpOrderBy ----
        ButtonGroup grpOrderBy = new ButtonGroup();
        grpOrderBy.add(rbtCpfOrder);
        grpOrderBy.add(rbtNomeOrder);
        // JFormDesigner - End of component initialization  //GEN-END:initComponents
    }

    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
    // Generated using JFormDesigner Evaluation license - Leandro Costa
    private JTabbedPane mainTabbedPane;
    private JPanel panelCadastro;
    private JButton btnLimpar;
    private JButton btnCadastrar;
    private JPanel panelPrincipalCadastro;
    private JLabel lblNome;
    private JTextField txtNome;
    private JLabel lblCPF;
    private JTextField txtCPF;
    private JLabel lblNascimento;
    private JTextField txtNascimento;
    private JPanel panelEnderecoCadastro;
    private JTextField txtLogradouro;
    private JLabel lblLogradouro;
    private JTextField txtBairro;
    private JLabel lblBairro;
    private JTextField txtCidade;
    private JLabel lblCidade;
    private JTextField txtNumero;
    private JLabel lblNumero;
    private JTextField txtCep;
    private JLabel lblCep;
    private JComboBox comboUF;
    private JLabel lblEstado;
    private JPanel panelSexo;
    private JRadioButton rbtFeminino;
    private JRadioButton rbtMasculino;
    private JLabel txtSexo;
    private JPanel panelListagem;
    private JScrollPane scrollListagem;
    private JTable tableListagem;
    private JButton btnDeleteAll;
    private JInternalFrame internalUpdate;
    private JPanel panelUpdate;
    private JLabel lblNomeUpdate;
    private JTextField txtNomeUpdate;
    private JLabel lblCPFUpdate;
    private JTextField txtCPFUpdate;
    private JLabel txtSexoUpdate;
    private JRadioButton rbtMasculinoUpdate;
    private JRadioButton rbtFemininoUpdate;
    private JLabel lblNascimentoUpdate;
    private JTextField txtNascimentoUpdate;
    private JLabel labelIdUpdate;
    private JLabel lblIdValor;
    private JPanel panelEnderecoUpdate;
    private JTextField txtLogradouroUpdate;
    private JLabel lblLogradouroUpdate;
    private JTextField txtNumeroUpdate;
    private JLabel lblNumeroUpdate;
    private JTextField txtCepUpdate;
    private JLabel lblCepUpdate;
    private JTextField txtBairroUpdate;
    private JLabel lblBairroUpdate;
    private JTextField txtCidadeUpdate;
    private JLabel lblCidadeUpdate;
    private JComboBox comboUFUpdate;
    private JLabel lblEstadoUpdate;
    private JButton btnUpdate;
    private JPanel panelConsulta;
    private JPanel panelTabelaList;
    private JScrollPane scrollListConsulta;
    private JTable tableListConsulta;
    private JPanel panel1;
    private JTextField txtNomeConsulta;
    private JLabel lblNomeConsulta;
    private JLabel txtSexoConsulta;
    private JRadioButton rbtMasculinoConsulta;
    private JRadioButton rbtFemininoConsulta;
    private JComboBox comboUfConsulta;
    private JLabel lblEstadoConsulta;
    private JTextField txtCPFConsulta;
    private JLabel lblCPFConsulta;
    private JTextField txtCepConsulta;
    private JLabel lblCepConsulta;
    private JButton btnConsulta;
    private JRadioButton rbtCpfOrder;
    private JRadioButton rbtNomeOrder;
    private JLabel lblOrder;
    // JFormDesigner - End of variables declaration  //GEN-END:variables
}
