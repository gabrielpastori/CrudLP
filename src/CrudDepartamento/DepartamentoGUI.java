//Nome: Gabriel Pastori
//Curso: Técnico Integrado em Informática - Linguagem de Programação II
//Código responsável pela geração do código da classe GUI
package CrudDepartamento;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Container;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.JToolBar;
import javax.swing.table.DefaultTableModel;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.awt.Color;
import java.awt.Point;
import tools.ManipulaImagem;
import javax.swing.ImageIcon;
import javax.swing.JDialog;
import static javax.swing.WindowConstants.DISPOSE_ON_CLOSE;
import tools.Centraliza;
import tools.JanelaPesquisar;
import tools.ManipulaArquivo;

public class DepartamentoGUI extends JDialog {
    JDialog d;
    private JPanel cp = new JPanel();
    private JLabel lbIdDepartamento = new JLabel("IDDEPARTAMENTO");
    private JTextField tfIdDepartamento= new JTextField(20);
    private JLabel lbNome = new JLabel("Nome");
    private JTextField tfNome= new JTextField(20);
    private JLabel lbSigla = new JLabel("Sigla");
    private JTextField tfSigla= new JTextField(20);
    private JLabel lbCustoMensal = new JLabel("CustoMensal");
    private JTextField tfCustoMensal= new JTextField(20);
    private JLabel lbSenha = new JLabel("Senha");
    private JTextField tfSenha= new JTextField(20);
    private JButton btAdicionar = new JButton("Adicionar");
    private JButton btListar = new JButton("Listar");
    private JButton btBuscar = new JButton("Buscar");
    private JButton btAlterar = new JButton("Alterar");
    private JButton btExcluir = new JButton("Excluir");
    private JButton btSalvar = new JButton("Salvar");
    private JButton btCancelar = new JButton("Cancelar");
    private JButton btCarregarDados = new JButton("Carregar");
    private JButton btGravar = new JButton("Gravar");
    private JButton btLocalizar = new JButton("Localizar");
    private JToolBar toolBar = new JToolBar();
    private JPanel painelNorte = new JPanel();
    private JPanel painelCentro = new JPanel();
    private JPanel painelSul = new JPanel();
    private JTextArea texto = new JTextArea();
    private JScrollPane scrollTexto = new JScrollPane();
    private JScrollPane scrollTabela = new JScrollPane();
    private String acao = "";
    private String chavePrimaria = "";
    private DepartamentoControle controle = new DepartamentoControle();
    private ManipulaImagem manipulaImagem=new ManipulaImagem();
    private Departamento departamento = new Departamento();
    String[] colunas = new String[]{"IdDepartamento","Nome","Sigla","CustoMensal","Senha"};

    String[][] dados = new String[0][4];
    DefaultTableModel model = new DefaultTableModel(dados, colunas);
    JTable tabela = new JTable(model);
    private JPanel painel1 = new JPanel(new GridLayout(1, 1));
    private JPanel painel2 = new JPanel(new GridLayout(1, 1));
    private CardLayout cardLayout;
    private Centraliza centraliza= new Centraliza();
    public DepartamentoGUI(JFrame pai) {
        d = new JDialog(pai, "", true);
        d.getContentPane();
        d.pack();
        
        d.setTitle("CRUD Departamento");
        d.setSize(600, 800);
        d.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        d.setLayout(new GridLayout(1,1));
        centraliza.centralizaFilho(pai, d);
        String caminhoENomeDoArquivo = "DadosDepartamento.csv";
//        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
//        setSize(600, 400);
//        setLocationRelativeTo(null);
//        setTitle("Departamento");
//        cp = getContentPane();
        cp.setLayout(new BorderLayout());
        cp.add(painelNorte, BorderLayout.NORTH);
        cp.add(painelCentro, BorderLayout.CENTER);
        cp.add(painelSul, BorderLayout.SOUTH);
        d.add(cp);
        ImageIcon icon=manipulaImagem.criaIcon("/icones/retrieve.png", 30, 30);
        btBuscar=manipulaImagem.insereBotao(icon, "Buscar");
        
        icon = manipulaImagem.criaIcon("/icones/retrieve_1.png", 30, 30);
        btLocalizar=manipulaImagem.insereBotao(icon, "Localizar");
        
        icon=manipulaImagem.criaIcon("/icones/list.png", 30, 30);
        btListar=manipulaImagem.insereBotao(icon, "Listar");
           
        icon=manipulaImagem.criaIcon("/icones/delete_1.png", 30, 30);
        btExcluir=manipulaImagem.insereBotao(icon, "Excluir");
        
        icon=manipulaImagem.criaIcon("/icones/update.png", 35, 30);
        btAlterar=manipulaImagem.insereBotao(icon, "Alterar");
        
        icon=manipulaImagem.criaIcon("/icones/save-as.png", 30, 30);
        btSalvar=manipulaImagem.insereBotao(icon, "Salvar");
        
        icon=manipulaImagem.criaIcon("/icones/newCancelar.png", 30, 30);
        btCancelar=manipulaImagem.insereBotao(icon, "Cancelar");
        
        icon=manipulaImagem.criaIcon("/icones/save.png", 30, 30);
        btGravar=manipulaImagem.insereBotao(icon, "Gravar");
        
        icon=manipulaImagem.criaIcon("/icones/create_1.png", 30, 30);
        btAdicionar=manipulaImagem.insereBotao(icon, "Adicionar");
        
        
        cardLayout = new CardLayout();
        painelSul.setLayout(cardLayout);
        painel1.add(scrollTexto);
        painel2.add(scrollTabela);
        texto.setText("\n\n\n\n\n\n");
        scrollTexto.setViewportView(texto);
        painelSul.add(painel1, "Avisos");
        painelSul.add(painel2, "Listagem");
        painelNorte.setLayout(new GridLayout(1, 1));
        painelNorte.add(toolBar);
        painelCentro.setLayout(new GridLayout(4, 2));
        painelCentro.add(lbNome);
        painelCentro.add(tfNome);
        painelCentro.add(lbSigla);
        painelCentro.add(tfSigla);
        painelCentro.add(lbCustoMensal);
        painelCentro.add(tfCustoMensal);
        painelCentro.add(lbSenha);
        painelCentro.add(tfSenha);
        toolBar.add(lbIdDepartamento);
        toolBar.add(tfIdDepartamento);
        toolBar.add(btAdicionar);
        toolBar.add(btBuscar);
        toolBar.add(btLocalizar);
        toolBar.add(btListar);
        toolBar.add(btAlterar);
        toolBar.add(btExcluir);
        toolBar.add(btSalvar);
        toolBar.add(btCancelar);
        btAdicionar.setVisible(false);
        btAlterar.setVisible(false);
        btExcluir.setVisible(false);
        btSalvar.setVisible(false);
        btCancelar.setVisible(false);
        tfNome.setEditable(false);
        tfSigla.setEditable(false);
        tfCustoMensal.setEditable(false);
        tfSenha.setEditable(false);
        texto.setEditable(false);
        btCarregarDados.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ManipulaArquivo manipulaArquivo = new ManipulaArquivo();
                if (manipulaArquivo.existeOArquivo(caminhoENomeDoArquivo)) {
                    String aux[];
                    Departamento t;
                    List<String> listaStringCsv = manipulaArquivo.abrirArquivo(caminhoENomeDoArquivo);
                    for (String linha : listaStringCsv) {
                        aux = linha.split(";");
                        SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
                        SimpleDateFormat sdfEua = new SimpleDateFormat("yyyy-MM-dd");
                        try{
                            t = new Departamento(Integer.valueOf(aux[0]),String.valueOf(aux[1]),String.valueOf(aux[2]),Double.valueOf(aux[3]),String.valueOf(aux[4]));
                            controle.adicionar(t);
                        }catch (Exception err){
                            System.out.println("Deu ruim "+err);
                        }
                    }
                    cardLayout.show(painelSul,"Listagem");
                }
            }
        });
        btGravar.addActionListener(new ActionListener() {
           @Override
           public void actionPerformed(ActionEvent e) {
                List<Departamento> listaDepartamento = controle.listar();
                List<String> listaDepartamentoEmFormatoStringCSV = new ArrayList<>();
                for (Departamento t : listaDepartamento) {
                   listaDepartamentoEmFormatoStringCSV.add(t.toString());
                }
                new ManipulaArquivo().salvarArquivo(caminhoENomeDoArquivo, listaDepartamentoEmFormatoStringCSV);
                System.out.println("gravou");
            }
        });
        btBuscar.addActionListener(new ActionListener() {
           @Override
           public void actionPerformed(ActionEvent e) {
                btAdicionar.setVisible(false);
                cardLayout.show(painelSul, "Avisos");
                scrollTexto.setViewportView(texto);
                boolean ok=true;
                try{
                    Long.valueOf(tfIdDepartamento.getText());
                }catch(Exception er){
                    JOptionPane.showMessageDialog(cp, "IDDEPARTAMENTO deve ser do tipo int");
                    ok=false;
                }
                if(tfIdDepartamento.getText().trim().isEmpty() || !ok){
                    if(tfIdDepartamento.getText().trim().isEmpty()){
                        JOptionPane.showMessageDialog(cp, "IDDEPARTAMENTO nâo pode ser vazio");
                    }
                    tfIdDepartamento.requestFocus();
                    tfIdDepartamento.selectAll();
                }else{
                    chavePrimaria = tfIdDepartamento.getText();
                    departamento = controle.buscar(tfIdDepartamento.getText());
                    if (departamento== null) {
                        btAdicionar.setVisible(true);
                        btAlterar.setVisible(false);
                        btExcluir.setVisible(false);
                        tfNome.setText("");
                        tfSigla.setText("");
                        tfCustoMensal.setText("");
                        tfSenha.setText("");
                        texto.setText("Não encontrou na lista - pode Adicionar\n\n\n");
                    }else{
                        SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
                        texto.setText("Encontrou na lista - pode Alterar ou Excluir\n\n\n");
                        btAlterar.setVisible(true);
                        btExcluir.setVisible(true);
                        tfNome.setText(String.valueOf(departamento.getNome()));
                        tfNome.setEditable(false);
                        tfSigla.setText(String.valueOf(departamento.getSigla()));
                        tfSigla.setEditable(false);
                        tfCustoMensal.setText(String.valueOf(departamento.getCustoMensal()));
                        tfCustoMensal.setEditable(false);
                        tfSenha.setText(String.valueOf(departamento.getSenha()));
                        tfSenha.setEditable(false);
                    }
                }
            }
        });
        btAdicionar.addActionListener(new ActionListener() {
           @Override
           public void actionPerformed(ActionEvent e) {
                acao = "adicionar";
                tfIdDepartamento.setText(chavePrimaria);
                tfNome.setEditable(true);
                tfSigla.setEditable(true);
                tfCustoMensal.setEditable(true);
                tfSenha.setEditable(true);
                tfIdDepartamento.setEditable(false);
                btSalvar.setVisible(true);
                btCancelar.setVisible(true);
                btBuscar.setVisible(false);
                btListar.setVisible(false);
                btAlterar.setVisible(false);
                btExcluir.setVisible(false);
                btAdicionar.setVisible(false);
                btLocalizar.setVisible(false);
                texto.setText("Preencha os atributos\n\n\n\n\n");
            }
        });
        btAlterar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                acao = "alterar";
                tfIdDepartamento.setText(chavePrimaria);
                tfIdDepartamento.setEditable(false);
                tfNome.requestFocus();
                btSalvar.setVisible(true);
                btCancelar.setVisible(true);
                btBuscar.setVisible(false);
                btListar.setVisible(false);
                btAlterar.setVisible(false);
                btExcluir.setVisible(false);
                texto.setText("Preencha os atributos\n\n\n\n\n");
                tfNome.setEditable(true);
                tfSigla.setEditable(true);
                tfCustoMensal.setEditable(true);
                tfSenha.setEditable(true);
                btLocalizar.setVisible(false);
            }
        });
        btCancelar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                btSalvar.setVisible(false);
                btCancelar.setVisible(false);
                btBuscar.setVisible(true);
                btListar.setVisible(true);
                btLocalizar.setVisible(true);
                tfIdDepartamento.setEditable(true);
                tfNome.setText("");
                tfNome.setEditable(false);
                tfSigla.setText("");
                tfSigla.setEditable(false);
                tfCustoMensal.setText("");
                tfCustoMensal.setEditable(false);
                tfSenha.setText("");
                tfSenha.setEditable(false);
                tfIdDepartamento.requestFocus();
                tfIdDepartamento.selectAll();
                texto.setText("Cancelou\n\n\n\n\n");
            }
        });
        btSalvar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                boolean ok=true;
                if (acao.equals("alterar")) {
                    Departamento departamentoAntigo = departamento;
                    SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
                    SimpleDateFormat sdfEua = new SimpleDateFormat("yyyy-MM-dd");
                    try{
                        departamento.setNome(String.valueOf(tfNome.getText()));
                        lbNome.setForeground(new Color(51,51,51));
                    }catch(Exception er){
                        lbNome.setForeground(Color.red);
                        JOptionPane.showMessageDialog(cp, "No campo Nome deve ser inserido um dado do tipo: String");
                        ok=false;
                    }
                    try{
                        departamento.setSigla(String.valueOf(tfSigla.getText()));
                        lbSigla.setForeground(new Color(51,51,51));
                    }catch(Exception er){
                        lbSigla.setForeground(Color.red);
                        JOptionPane.showMessageDialog(cp, "No campo Sigla deve ser inserido um dado do tipo: String");
                        ok=false;
                    }
                    try{
                        departamento.setCustoMensal(Double.valueOf(tfCustoMensal.getText()));
                        lbCustoMensal.setForeground(new Color(51,51,51));
                    }catch(Exception er){
                        lbCustoMensal.setForeground(Color.red);
                        JOptionPane.showMessageDialog(cp, "No campo CustoMensal deve ser inserido um dado do tipo: double");
                        ok=false;
                    }
                    try{
                        departamento.setSenha(String.valueOf(tfSenha.getText()));
                        lbSenha.setForeground(new Color(51,51,51));
                    }catch(Exception er){
                        lbSenha.setForeground(Color.red);
                        JOptionPane.showMessageDialog(cp, "No campo Senha deve ser inserido um dado do tipo: String");
                        ok=false;
                    }
                    if(ok){
                        controle.alterar(departamento, departamentoAntigo);
                        texto.setText("Registro alterado\n\n\n\n\n");
                    }
                }else{
                    departamento= new Departamento();
                    SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
                    SimpleDateFormat sdfEua = new SimpleDateFormat("yyyy-MM-dd");
                    try{
                        departamento.setIdDepartamento(Integer.valueOf(tfIdDepartamento.getText()));
                        lbIdDepartamento.setForeground(new Color(51,51,51));
                    }catch(Exception er){
                        lbIdDepartamento.setForeground(Color.red);
                        JOptionPane.showMessageDialog(cp, "No campo IdDepartamento deve ser inserido um dado do tipo: int");
                        ok=false;
                    }
                    try{
                        departamento.setNome(String.valueOf(tfNome.getText()));
                        lbNome.setForeground(new Color(51,51,51));
                    }catch(Exception er){
                        lbNome.setForeground(Color.red);
                        JOptionPane.showMessageDialog(cp, "No campo Nome deve ser inserido um dado do tipo: String");
                        ok=false;
                    }
                    try{
                        departamento.setSigla(String.valueOf(tfSigla.getText()));
                        lbSigla.setForeground(new Color(51,51,51));
                    }catch(Exception er){
                        lbSigla.setForeground(Color.red);
                        JOptionPane.showMessageDialog(cp, "No campo Sigla deve ser inserido um dado do tipo: String");
                        ok=false;
                    }
                    try{
                        departamento.setCustoMensal(Double.valueOf(tfCustoMensal.getText()));
                        lbCustoMensal.setForeground(new Color(51,51,51));
                    }catch(Exception er){
                        lbCustoMensal.setForeground(Color.red);
                        JOptionPane.showMessageDialog(cp, "No campo CustoMensal deve ser inserido um dado do tipo: double");
                        ok=false;
                    }
                    try{
                        departamento.setSenha(String.valueOf(tfSenha.getText()));
                        lbSenha.setForeground(new Color(51,51,51));
                    }catch(Exception er){
                        lbSenha.setForeground(Color.red);
                        JOptionPane.showMessageDialog(cp, "No campo Senha deve ser inserido um dado do tipo: String");
                        ok=false;
                    }
                    if(ok){
                        controle.adicionar(departamento);
                        texto.setText("Foi adicionado um novo registro\n\n\n\n\n");
                        btAlterar.setVisible(true);
                        btExcluir.setVisible(true);
                    }
                }
                if(ok){
                    btSalvar.setVisible(false);
                    btCancelar.setVisible(false);
                    btBuscar.setVisible(true);
                    btListar.setVisible(true);
                    btLocalizar.setVisible(true);
                    tfIdDepartamento.setEditable(true);
                    tfIdDepartamento.requestFocus();
                    tfIdDepartamento.selectAll();
                    tfNome.setText("");
                    tfSigla.setText("");
                    tfCustoMensal.setText("");
                    tfSenha.setText("");
                    tfNome.setEditable(false);
                    tfSigla.setEditable(false);
                    tfCustoMensal.setEditable(false);
                    tfSenha.setEditable(false);
                }
            }
        });
        btListar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                List<Departamento> lt = controle.listar();
                String[] colunas = {"IdDepartamento","Nome","Sigla","CustoMensal","Senha"};
                Object[][] dados = new Object[lt.size()][colunas.length];
                String aux[];
                for (int i = 0; i < lt.size(); i++) {
                    aux = lt.get(i).toString().split(";");
                    for (int j = 0; j < colunas.length; j++) {
                        dados[i][j] = aux[j];
                    }
                }
                cardLayout.show(painelSul, "Listagem");
                scrollTabela.setPreferredSize(tabela.getPreferredSize());
                painel2.add(scrollTabela);
                scrollTabela.setViewportView(tabela);
                model.setDataVector(dados, colunas);

                btAlterar.setVisible(false);
                btExcluir.setVisible(false);
                btAdicionar.setVisible(false);
            }
        });
        btExcluir.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                tfIdDepartamento.setText(chavePrimaria);
                if (JOptionPane.YES_OPTION
                        == JOptionPane.showConfirmDialog(null,
                                "Confirma a exclusão do registro <Nome = " + departamento.getNome() + ">?", "Confirm",
                                JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE)) {
                    controle.excluir(departamento);
                    texto.setText("Excluiu o registro de " + departamento.getIdDepartamento() + " - " + departamento.getNome() + "\n\n\n\n\n");                }
                else texto.setText("Não excluiu o registro de " + departamento.getIdDepartamento() + " - " + departamento.getNome() + "\n\n\n\n\n");
                btBuscar.setVisible(true);
                btListar.setVisible(true);
                btExcluir.setVisible(false);
                btAlterar.setVisible(false);
                tfIdDepartamento.requestFocus();
                tfIdDepartamento.selectAll();
                tfIdDepartamento.setText("");
                tfNome.setText("");
                tfSigla.setText("");
                tfCustoMensal.setText("");
                tfSenha.setText("");
            }
        });
        btLocalizar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                List<String> listaAuxiliar = controle.listStrings();
                if (listaAuxiliar.size() > 0) {
                    Point lc = btLocalizar.getLocationOnScreen();
                    lc.x = lc.x + btLocalizar.getWidth();
                    String selectedItem = new JanelaPesquisar(listaAuxiliar,
                            lc.x,
                            lc.y,
                            colunas).getValorRetornado();
                    if(!selectedItem.equals("")){
                        String[] aux = selectedItem.split(";");
                        tfIdDepartamento.setText(aux[0]);
                        btBuscar.doClick();
                    } else {
                        tfIdDepartamento.requestFocus();
                        tfIdDepartamento.selectAll();
                    }
                }
            }
        });
        d.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                //antes de sair, salvar a lista em disco
                btGravar.doClick();
                // Sai da classe
                d.dispose();
            }
        });
        btCarregarDados.doClick();
        d.setVisible(true);
    }
}
