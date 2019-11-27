package CrudUnidadeDeMedida;

import tools.MinhaJOptionPane;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.GridLayout;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JToolBar;
import javax.swing.WindowConstants;
import tools.CentroDoMonitorMaior;

public class UnidadeDeMedidaGUI extends JDialog {

    ImageIcon iconeCreate;
    ImageIcon iconeRetrieve;
    ImageIcon iconeUpdate;
    ImageIcon iconeDelete;
    ImageIcon iconeSave;
    ImageIcon iconeCancel;
    ImageIcon iconeListar;

    JButton btnCreate;
    JButton btnRetrieve;
    JButton btnUpdate;
    JButton btnDelete;
    JButton btnSave;
    JButton btnCancel;
    JButton btnList;
    JLabel labelSigla = new JLabel("Sigla");
    JTextField textFieldSigla = new JTextField();
    JLabel labelNome = new JLabel("Nome");
    JTextField textFieldNome = new JTextField();
    JPanel aviso = new JPanel();
    JLabel labelAviso = new JLabel("");
    String acao = "";//variavel para facilitar insert e update 

    UnidadeDeMedidaControlaLista cl = new UnidadeDeMedidaControlaLista();
    UnidadeDeMedida unidadedemedida = new UnidadeDeMedida();

    private void atvBotoes(boolean c, boolean r, boolean u, boolean d) {
        btnCreate.setEnabled(c);
        btnRetrieve.setEnabled(r);
        btnUpdate.setEnabled(u);
        btnDelete.setEnabled(d);
        btnList.setEnabled(r);
    }

    public void mostrarBotoes(boolean visivel) {
        btnCreate.setVisible(visivel);
        btnRetrieve.setVisible(visivel);
        btnUpdate.setVisible(visivel);
        btnDelete.setVisible(visivel);
        btnList.setVisible(visivel);
        btnSave.setVisible(!visivel);
        btnCancel.setVisible(!visivel);
    }

    private void habilitarAtributos(boolean sigla, boolean nome) {

        if (sigla) {
            textFieldSigla.requestFocus();
            textFieldSigla.selectAll();
        }
        textFieldSigla.setEnabled(sigla);

        textFieldSigla.setEditable(sigla);
        textFieldNome.setEditable(nome);
    }

    public void zerarAtributos() {
        textFieldNome.setText("");
    }

    public UnidadeDeMedidaGUI() {
        //carregar imagens para os botões
        try {
            iconeCreate = new ImageIcon(getClass().getResource("/icones/create.png"));
            iconeRetrieve = new ImageIcon(getClass().getResource("/icones/retrieve.png"));
            iconeUpdate = new ImageIcon(getClass().getResource("/icones/update.png"));
            iconeDelete = new ImageIcon(getClass().getResource("/icones/delete.png"));
            iconeSave = new ImageIcon(getClass().getResource("/icones/save.png"));
            iconeCancel = new ImageIcon(getClass().getResource("/icones/cancel.png"));
            iconeListar = new ImageIcon(getClass().getResource("/icones/list.png"));

            btnCreate = new JButton(iconeCreate);
            btnRetrieve = new JButton(iconeRetrieve);
            btnUpdate = new JButton(iconeUpdate);
            btnDelete = new JButton(iconeDelete);
            btnSave = new JButton(iconeSave);
            btnCancel = new JButton(iconeCancel);
            btnList = new JButton(iconeListar);
        } catch (Exception e) {
            System.out.println("Não achou alguma imagem para os botões, confira o caminho e se existe a package icones");
        }
        setTitle("Cadastro de UnidadeDeMedida");
        try {
            File arq = new File("UnidadeDeMedida.dat"); //tenta abrir o arquivo
            if (arq.exists()) { //se o arquivo já existe, abre e lê os dados
                cl.getArquivo("UnidadeDeMedida.dat");
            }
        } catch (Exception e) {
            System.out.println("arquivo não encontrado");
        }
        setSize(500, 200);//tamanho da janela

        setLayout(new BorderLayout());//informa qual gerenciador de layout será usado
        setBackground(Color.CYAN);//cor do fundo da janela
        Container cp = getContentPane();//container principal, para adicionar nele os outros componentes

        atvBotoes(false, true, false, false);
        habilitarAtributos(true, false);
        btnCreate.setToolTipText("Inserir novo registro");
        btnRetrieve.setToolTipText("Pesquisar por chave");
        btnUpdate.setToolTipText("Alterar");
        btnDelete.setToolTipText("Excluir");
        btnList.setToolTipText("Listar todos");
        btnSave.setToolTipText("Salvar");
        btnCancel.setToolTipText("Cancelar");
        JToolBar Toolbar1 = new JToolBar();
        Toolbar1.add(btnRetrieve);
        Toolbar1.add(btnCreate);
        Toolbar1.add(btnUpdate);
        Toolbar1.add(btnDelete);
        Toolbar1.add(btnSave);
        Toolbar1.add(btnCancel);
        Toolbar1.add(btnList);
        btnSave.setVisible(false);
        btnCancel.setVisible(false);  //atributos
        JPanel centro = new JPanel();
        centro.setLayout(new GridLayout(2, 2));
        centro.add(labelSigla);
        centro.add(textFieldSigla);
        centro.add(labelNome);
        centro.add(textFieldNome);
        aviso.add(labelAviso);
        aviso.setBackground(Color.yellow);
        cp.add(Toolbar1, BorderLayout.NORTH);
        cp.add(centro, BorderLayout.CENTER);
        cp.add(aviso, BorderLayout.SOUTH);
        textFieldSigla.requestFocus();
        textFieldSigla.selectAll();
        textFieldSigla.setBackground(Color.GREEN);
        labelAviso.setText("Digite uma placa e clic [Pesquisar]");

        // Listeners
        btnRetrieve.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                textFieldSigla.setText(textFieldSigla.getText().trim());//caso tenham sido digitados espaços
                if (textFieldSigla.getText().equals("")) {
                    MinhaJOptionPane myJOptionPane
                            = new MinhaJOptionPane(
                                    new Point(getBounds().x + (int) (getBounds().getWidth() / 2),
                                            getBounds().y + (int) (getBounds().getHeight() / 2)),
                                    "Deve ser informado um valor para esse campo");
                    if (myJOptionPane.getValorRetornado()) {
                        textFieldSigla.requestFocus();
                        textFieldSigla.selectAll();
                    }
                } else {
                    unidadedemedida = new UnidadeDeMedida();
                    unidadedemedida.setSigla(textFieldSigla.getText());
                    unidadedemedida = cl.retrieve(unidadedemedida);
                    if (unidadedemedida != null) { //se encontrou na lista

                        textFieldNome.setText(unidadedemedida.getNome());
                        atvBotoes(false, true, true, true);
                        habilitarAtributos(true, false);
                        labelAviso.setText("Encontrou - clic [Pesquisar], [Alterar] ou [Excluir]");
                        acao = "encontrou";
                    } else {
                        atvBotoes(true, true, false, false);
                        zerarAtributos();
                        labelAviso.setText("Não cadastrado - clic [Inserir] ou digite outra placa [Pesquisar]");
                    }
                }
            }
        });
        btnCreate.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                zerarAtributos();
                habilitarAtributos(false, true);
                textFieldNome.requestFocus();
                mostrarBotoes(false);
                labelAviso.setText("Preencha os campos e clic [Salvar] ou clic [Cancelar]");
                acao = "insert";
            }
        });
        btnUpdate.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                acao = "update";
                mostrarBotoes(false);
                habilitarAtributos(false, true);
            }
        });
        btnDelete.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                if (JOptionPane.YES_OPTION == JOptionPane.showConfirmDialog(null,
                        "Confirma a exclusão do registro <ID = " + unidadedemedida.getSigla() + ">?", "Confirm",
                        JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE)) {
                    labelAviso.setText("Registro excluído...");
                    cl.excluir(unidadedemedida);
                    zerarAtributos();
                    textFieldSigla.requestFocus();
                    textFieldSigla.selectAll();
                }
            }
        });

        btnSave.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                if (acao.equals("insert")) {
                    unidadedemedida = new UnidadeDeMedida();
                    unidadedemedida.setSigla(textFieldSigla.getText());
                    unidadedemedida.setNome(textFieldNome.getText());
                    cl.inserir(unidadedemedida);
                    habilitarAtributos(true, false);
                    mostrarBotoes(true);
                    atvBotoes(false, true, false, false);
                    labelAviso.setText("Registro inserido...");
                } else {  //acao = update
                    UnidadeDeMedida original = unidadedemedida;
                    unidadedemedida.setSigla(textFieldSigla.getText());
                    unidadedemedida.setNome(textFieldNome.getText());
                    cl.atualizar(original, unidadedemedida);
                    mostrarBotoes(true);
                    habilitarAtributos(true, false);
                    atvBotoes(false, true, false, false);
                    labelAviso.setText("Registro atualizado...");
                }
            }
        });
        btnCancel.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                zerarAtributos();
                atvBotoes(false, true, false, false);
                habilitarAtributos(true, false);
                mostrarBotoes(true);
            }
        });
        btnList.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {

                acao = "list";
                UnidadeDeMedidaGUIListagem listagem = new UnidadeDeMedidaGUIListagem(cl.getLista(), getBounds().x, getBounds().y);
            }
        });

        setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE); //antes de sair do sistema, grava os dados da lista em disco
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                //antes de sair, salvar a lista em disco
                cl.salvarArquivo("UnidadeDeMedida.dat");
                // Sai  
                dispose();
            }
        });

        textFieldSigla.addFocusListener(new FocusListener() { //ao receber o foco, fica verde
            @Override
            public void focusGained(FocusEvent fe) {
                textFieldSigla.setBackground(Color.GREEN);
            }

            @Override
            public void focusLost(FocusEvent fe) { //ao perder o foco, fica branco
                textFieldSigla.setBackground(Color.white);
            }
        });

        textFieldNome.addFocusListener(new FocusListener() { //ao receber o foco, fica verde
            @Override
            public void focusGained(FocusEvent fe) {
                textFieldNome.setBackground(Color.GREEN);
            }

            @Override
            public void focusLost(FocusEvent fe) { //ao perder o foco, fica branco
                textFieldNome.setBackground(Color.white);
            }
        });
        CentroDoMonitorMaior centroDoMonitorMaior = new CentroDoMonitorMaior();
        setLocation(centroDoMonitorMaior.getCentroMonitorMaior(this));
        setModal(true);
        setVisible(true);//faz a janela ficar visível
        textFieldSigla.requestFocus();

    } //fim do construtor 

}//fim
