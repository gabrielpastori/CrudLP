//Nome: Gabriel Pastori
//Curso: Técnico Integrado em Informática - Linguagem de Programação II
//Código responsável pela geração do código da classe GUI
package CrudProfessor;

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
import java.awt.Image;
import java.awt.Point;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.awt.image.RenderedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import javax.activation.MimetypesFileTypeMap;
import javax.imageio.ImageIO;
import tools.ManipulaImagem;
import javax.swing.ImageIcon;
import javax.swing.JDialog;
import javax.swing.JFileChooser;
import tools.Centraliza;
import tools.CopiaImagem;
import tools.DateTextField;
import tools.JanelaPesquisar;
import tools.ManipulaArquivo;

public class ProfessorGUI extends JDialog {
    private JDialog d;
    
    private JPanel cp = new JPanel();
    private JLabel lbIdProfessor = new JLabel("IDPROFESSOR");
    private JTextField tfIdProfessor= new JTextField(20);
    private JLabel lbNome = new JLabel("Nome");
    private JTextField tfNome= new JTextField(20);
    private JLabel lbData_nascimento = new JLabel("Data_nascimento");
    DateTextField tfData_nascimento= new DateTextField();
    private JLabel lbData_entrada = new JLabel("Data_entrada");
    DateTextField tfData_entrada= new DateTextField();
    private JLabel lbSalario = new JLabel("Salario");
    private JTextField tfSalario= new JTextField(20);
    private JLabel lbAtivo = new JLabel("Ativo");
    private JLabel lbFotoProfessor = new JLabel();
    private JCheckBox cbAtivo= new JCheckBox("Ativo",false);    private JTextField tfAtivo= new JTextField(20);
    private JLabel lbIdDepartamento = new JLabel("IdDepartamento");
    private JTextField tfIdDepartamento= new JTextField(20);
    private JButton btAdicionar = new JButton("Adicionar");
    private JButton btListar = new JButton("Listar");
    private JButton btBuscar = new JButton("Buscar");
    private JButton btLocalizar = new JButton("Localizar");
    private JButton btAlterar = new JButton("Alterar");
    private JButton btExcluir = new JButton("Excluir");
    private JButton btSalvar = new JButton("Salvar");
    private JButton btCancelar = new JButton("Cancelar");
    private JButton btCarregarDados = new JButton("Carregar");
    private JButton btGravar = new JButton("Gravar");
    private JToolBar toolBar = new JToolBar();
    private JPanel painelNorte = new JPanel();
    private JPanel painelCentro = new JPanel();
    private JPanel painelSul = new JPanel();
    private JPanel painelLeste = new JPanel();
    private JTextArea texto = new JTextArea();
    private JScrollPane scrollTexto = new JScrollPane();
    private JScrollPane scrollTabela = new JScrollPane();
    private String acao = "";
    private String chavePrimaria = "";
    private ProfessorControle controle = new ProfessorControle();
    private ManipulaImagem manipulaImagem=new ManipulaImagem();
    private Professor professor = new Professor();
    String[] colunas = new String[]{"IdProfessor","Nome","Data_nascimento","Data_entrada","Salario","Ativo","IdDepartamento"};

    String[][] dados = new String[0][4];
    DefaultTableModel model = new DefaultTableModel(dados, colunas);
    JTable tabela = new JTable(model);
    private JPanel painel1 = new JPanel(new GridLayout(1, 1));
    private JPanel painel2 = new JPanel(new GridLayout(1, 1));
    private CardLayout cardLayout;
    private Centraliza centraliza= new Centraliza();
    private CopiaImagem copia = new CopiaImagem();
    private ManipulaArquivo manipulaArquivo = new ManipulaArquivo();
    public ProfessorGUI(JFrame pai) {
        d = new JDialog(pai, "", true);
        d.getContentPane();
        d.pack();
        
        d.setTitle("CRUD Professor");
        d.setSize(600, 800);
        d.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        d.setLayout(new GridLayout(1,1));
        centraliza.centralizaFilho(pai, d);
        String caminhoENomeDoArquivo = "DadosProfessor.csv";
        //setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        //setSize(600, 400);
        //setLocationRelativeTo(null);
        //setTitle("Professor");
        //cp = getContentPane();
        cp.setLayout(new BorderLayout());
        cp.add(painelNorte, BorderLayout.NORTH);
        cp.add(painelCentro, BorderLayout.CENTER);
        cp.add(painelSul, BorderLayout.SOUTH);
        cp.add(painelLeste,BorderLayout.EAST);
        painelLeste.setLayout(new GridLayout(1,1));
        painelLeste.add(lbFotoProfessor);
        lbFotoProfessor.setVisible(true);
        ImageIcon icon=manipulaImagem.criaIcon("/fotos/professorPadrao.png", 200, 200);
        lbFotoProfessor.setIcon(icon);
        d.add(cp);
        icon=manipulaImagem.criaIcon("/icones/retrieve.png", 30, 30);
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
        painelCentro.setLayout(new GridLayout(6, 2));
        painelCentro.add(lbNome);
        painelCentro.add(tfNome);
        painelCentro.add(lbData_nascimento);
        painelCentro.add(tfData_nascimento);
        painelCentro.add(lbData_entrada);
        painelCentro.add(tfData_entrada);
        painelCentro.add(lbSalario);
        painelCentro.add(tfSalario);
        painelCentro.add(cbAtivo);
        painelCentro.add(new JLabel(""));
        painelCentro.add(lbIdDepartamento);
        painelCentro.add(tfIdDepartamento);
        toolBar.add(lbIdProfessor);
        toolBar.add(tfIdProfessor);
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
        tfData_nascimento.setEditable(false);
        tfData_entrada.setEditable(false);
        tfSalario.setEditable(false);
        cbAtivo.setEnabled(false);
        tfIdDepartamento.setEditable(false);
        texto.setEditable(false);
        lbFotoProfessor.setEnabled(false);
        painelLeste.setEnabled(false);
        btCarregarDados.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ManipulaArquivo manipulaArquivo = new ManipulaArquivo();
                if (manipulaArquivo.existeOArquivo(caminhoENomeDoArquivo)) {
                    String aux[];
                    Professor t;
                    List<String> listaStringCsv = manipulaArquivo.abrirArquivo(caminhoENomeDoArquivo);
                    for (String linha : listaStringCsv) {
                        aux = linha.split(";");
                        SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
                        SimpleDateFormat sdfEua = new SimpleDateFormat("yyyy-MM-dd");
                        try{
                            t = new Professor(Integer.valueOf(aux[0]),String.valueOf(aux[1]),Date.valueOf(sdfEua.format(formato.parse(aux[2]))),Date.valueOf(sdfEua.format(formato.parse(aux[3]))),Double.valueOf(aux[4]),Boolean.valueOf(aux[5].equals("true")?true:false),Integer.valueOf(aux[6]));
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
                List<Professor> listaProfessor = controle.listar();
                List<String> listaProfessorEmFormatoStringCSV = new ArrayList<>();
                for (Professor t : listaProfessor) {
                   listaProfessorEmFormatoStringCSV.add(t.toString());
                }
                new ManipulaArquivo().salvarArquivo(caminhoENomeDoArquivo, listaProfessorEmFormatoStringCSV);
                System.out.println("gravou");
            }
        });
        btBuscar.addActionListener(new ActionListener() {
           @Override
           public void actionPerformed(ActionEvent e) {
                btAdicionar.setVisible(false);
                cardLayout.show(painelSul, "Avisos");
                scrollTexto.setViewportView(texto);
                lbNome.setForeground(new Color(51,51,51));
                lbData_nascimento.setForeground(new Color(51,51,51));
                lbData_entrada.setForeground(new Color(51,51,51));
                lbSalario.setForeground(new Color(51,51,51));
                lbIdDepartamento.setForeground(new Color(51,51,51));
                boolean ok=true;
                try{
                    Long.valueOf(tfIdProfessor.getText());
                }catch(Exception er){
                    JOptionPane.showMessageDialog(cp, "IDPROFESSOR deve ser do tipo int");
                    lbIdProfessor.setForeground(Color.red);
                    ok=false;
                }
                if(tfIdProfessor.getText().trim().isEmpty() || !ok){
                    if(tfIdProfessor.getText().trim().isEmpty()){
                        JOptionPane.showMessageDialog(cp, "IDPROFESSOR nâo pode ser vazio");
                        lbIdProfessor.setForeground(Color.red);
                    }
                    tfIdProfessor.requestFocus();
                    tfIdProfessor.selectAll();
                }else{
                    lbIdProfessor.setForeground(Color.black);
                    chavePrimaria = tfIdProfessor.getText();
                    professor = controle.buscar(tfIdProfessor.getText());
                    if (professor== null) {
                        btAdicionar.setVisible(true);
                        btAlterar.setVisible(false);
                        btExcluir.setVisible(false);
                        tfNome.setText("");
                        tfData_nascimento.setText("");
                        tfData_entrada.setText("");
                        tfSalario.setText("");
                        cbAtivo.setSelected(false);
                        tfIdDepartamento.setText("");
                        
                        String origem = new File("").getAbsolutePath();
                        origem+="/src/fotos/professorPadrao.png";
                        File img = new File(origem);
                        ImageIcon icone = new javax.swing.ImageIcon(img.getAbsolutePath());
                        icone = new ImageIcon(img.getAbsolutePath());

                        Image imagemAux;
                        imagemAux = icone.getImage();
                        icone.setImage(imagemAux.getScaledInstance(200, 200, Image.SCALE_FAST));
                        lbFotoProfessor.setIcon(icone);
                        lbFotoProfessor.setEnabled(false);
                        painelLeste.setEnabled(false);
                        texto.setText("Não encontrou na lista - pode Adicionar\n\n\n");
                    }else{
                        SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
                        texto.setText("Encontrou na lista - pode Alterar ou Excluir\n\n\n");
                        btAlterar.setVisible(true);
                        btExcluir.setVisible(true);
                        tfNome.setText(String.valueOf(professor.getNome()));
                        tfNome.setEditable(false);
                        tfData_nascimento.setText(formato.format(professor.getData_nascimento()));
                        tfData_nascimento.setEditable(false);
                        tfData_entrada.setText(formato.format(professor.getData_entrada()));
                        tfData_entrada.setEditable(false);
                        tfSalario.setText(String.valueOf(professor.getSalario()));
                        tfSalario.setEditable(false);
                        cbAtivo.setSelected(professor.getAtivo());
                        cbAtivo.setEnabled(false);
                        lbFotoProfessor.setEnabled(true);
                        painelLeste.setEnabled(false);
                        String origem = new File("").getAbsolutePath();
                        origem+="/src/fotos/"+tfIdProfessor.getText()+".png";
                        File img = new File(origem);
                        ImageIcon icone;
                        if(img.exists()){
                            icone = new ImageIcon(img.getAbsolutePath());
                        }else{
                            origem = new File("").getAbsolutePath();
                            origem+="/src/fotos/professorPadrao.png";
                            img = new File(origem);
                            icone = new javax.swing.ImageIcon(img.getAbsolutePath());
                            icone = new ImageIcon(img.getAbsolutePath());
                        }
                        Image imagemAux;
                        imagemAux = icone.getImage();
                        icone.setImage(imagemAux.getScaledInstance(200, 200, Image.SCALE_FAST));
                        lbFotoProfessor.setIcon(icone);
                        
                        
                       
                        tfIdDepartamento.setText(String.valueOf(professor.getIdDepartamento()));
                        tfIdDepartamento.setEditable(false);
                    }
                }
            }
        });
        btAdicionar.addActionListener(new ActionListener() {
           @Override
           public void actionPerformed(ActionEvent e) {
                acao = "adicionar";
                tfIdProfessor.setText(chavePrimaria);
                tfNome.setEditable(true);
                tfData_nascimento.setEditable(true);
                tfData_entrada.setEditable(true);
                tfSalario.setEditable(true);
                cbAtivo.setEnabled(true);
                tfIdDepartamento.setEditable(true);
                tfIdProfessor.setEditable(false);
                btSalvar.setVisible(true);
                btCancelar.setVisible(true);
                btBuscar.setVisible(false);
                btListar.setVisible(false);
                btAlterar.setVisible(false);
                btExcluir.setVisible(false);
                btAdicionar.setVisible(false);
                btLocalizar.setVisible(false);
                lbFotoProfessor.setEnabled(true);
                painelLeste.setEnabled(true);
                ImageIcon foto = manipulaImagem.criaIcon("/fotos/professorPadrao.png", 200, 200);
                lbFotoProfessor.setIcon(foto);
                texto.setText("Preencha os atributos\n\n\n\n\n");
            }
        });
        btAlterar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                acao = "alterar";
                tfIdProfessor.setText(chavePrimaria);
                tfIdProfessor.setEditable(false);
                tfNome.requestFocus();
                btSalvar.setVisible(true);
                btCancelar.setVisible(true);
                btBuscar.setVisible(false);
                btListar.setVisible(false);
                btAlterar.setVisible(false);
                btExcluir.setVisible(false);
                btLocalizar.setVisible(false);
                lbFotoProfessor.setEnabled(true);
                painelLeste.setEnabled(true);
                texto.setText("Preencha os atributos\n\n\n\n\n");
                tfNome.setEditable(true);
                String origem = new File("").getAbsolutePath();
                origem+="/src/fotos/"+tfIdProfessor.getText()+".png";
                File img = new File(origem);
                ImageIcon icone;
                if(img.exists()){
                    icone = new ImageIcon(img.getAbsolutePath());
                }else{
                    origem = new File("").getAbsolutePath();
                    origem+="/src/fotos/professorPadrao.png";
                    img = new File(origem);
                    icone = new javax.swing.ImageIcon(img.getAbsolutePath());
                    icone = new ImageIcon(img.getAbsolutePath());
                }
                Image imagemAux;
                imagemAux = icone.getImage();
                icone.setImage(imagemAux.getScaledInstance(200, 200, Image.SCALE_FAST));
                lbFotoProfessor.setIcon(icone);
                tfData_nascimento.setEditable(true);
                tfData_entrada.setEditable(true);
                tfSalario.setEditable(true);
                cbAtivo.setEnabled(true);
                tfIdDepartamento.setEditable(true);
            }
        });
        btCancelar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                lbNome.setForeground(new Color(51,51,51));
                lbData_nascimento.setForeground(new Color(51,51,51));
                lbData_entrada.setForeground(new Color(51,51,51));
                lbSalario.setForeground(new Color(51,51,51));
                lbIdDepartamento.setForeground(new Color(51,51,51));
                btSalvar.setVisible(false);
                btCancelar.setVisible(false);
                btBuscar.setVisible(true);
                btListar.setVisible(true);
                btLocalizar.setVisible(true);
                tfIdProfessor.setEditable(true);
                tfNome.setText("");
                tfNome.setEditable(false);
                tfData_nascimento.setText("");
                tfData_nascimento.setEditable(false);
                tfData_entrada.setText("");
                tfData_entrada.setEditable(false);
                tfSalario.setText("");
                tfSalario.setEditable(false);
                cbAtivo.setSelected(false);
                cbAtivo.setEnabled(false);
                tfIdDepartamento.setText("");
                tfIdDepartamento.setEditable(false);
                lbFotoProfessor.setEnabled(false);
                painelLeste.setEnabled(false);
                tfIdProfessor.requestFocus();
                tfIdProfessor.selectAll();
                ImageIcon foto = manipulaImagem.criaIcon("/fotos/professorPadrao.png", 200, 200);
                lbFotoProfessor.setIcon(foto);
                texto.setText("Cancelou\n\n\n\n\n");
                
            }
        });
        btSalvar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                boolean ok=true;
                if (acao.equals("alterar")) {
                    Professor professorAntigo = professor;
                    SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
                    SimpleDateFormat sdfEua = new SimpleDateFormat("yyyy-MM-dd");
                    try{
                        professor.setNome(String.valueOf(tfNome.getText()));
                        lbNome.setForeground(new Color(51,51,51));
                    }catch(Exception er){
                        lbNome.setForeground(Color.red);
                        JOptionPane.showMessageDialog(cp, "No campo Nome deve ser inserido um dado do tipo: String");
                        ok=false;
                    }
                    try{
                        professor.setData_nascimento(Date.valueOf(sdfEua.format(formato.parse(tfData_nascimento.getText()))));
                        lbData_nascimento.setForeground(new Color(51,51,51));
                    }catch(Exception er){
                        lbData_nascimento.setForeground(Color.red);
                        JOptionPane.showMessageDialog(cp, "No campo Data_nascimento deve ser inserido um dado do tipo: Date");
                        ok=false;
                    }
                    try{
                        professor.setData_entrada(Date.valueOf(sdfEua.format(formato.parse(tfData_entrada.getText()))));
                        lbData_entrada.setForeground(new Color(51,51,51));
                    }catch(Exception er){
                        lbData_entrada.setForeground(Color.red);
                        JOptionPane.showMessageDialog(cp, "No campo Data_entrada deve ser inserido um dado do tipo: Date");
                        ok=false;
                    }
                    try{
                        professor.setSalario(Double.valueOf(tfSalario.getText()));
                        lbSalario.setForeground(new Color(51,51,51));
                    }catch(Exception er){
                        lbSalario.setForeground(Color.red);
                        JOptionPane.showMessageDialog(cp, "No campo Salario deve ser inserido um dado do tipo: double");
                        ok=false;
                    }
                    try{
                        professor.setAtivo(cbAtivo.isSelected());
                        lbAtivo.setForeground(new Color(51,51,51));
                    }catch(Exception er){
                        lbAtivo.setForeground(Color.red);
                        JOptionPane.showMessageDialog(cp, "No campo Ativo deve ser inserido um dado do tipo: boolean");
                        ok=false;
                    }
                    try{
                        professor.setIdDepartamento(Integer.valueOf(tfIdDepartamento.getText()));
                        lbIdDepartamento.setForeground(new Color(51,51,51));
                    }catch(Exception er){
                        lbIdDepartamento.setForeground(Color.red);
                        JOptionPane.showMessageDialog(cp, "No campo IdDepartamento deve ser inserido um dado do tipo: int");
                        ok=false;
                    }
                    if(ok){
                        controle.alterar(professor, professorAntigo);
                        texto.setText("Registro alterado\n\n\n\n\n");
                    }
                }else{
                    professor= new Professor();
                    SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
                    SimpleDateFormat sdfEua = new SimpleDateFormat("yyyy-MM-dd");
                    try{
                        professor.setIdProfessor(Integer.valueOf(tfIdProfessor.getText()));
                        lbIdProfessor.setForeground(new Color(51,51,51));
                    }catch(Exception er){
                        lbIdProfessor.setForeground(Color.red);
                        JOptionPane.showMessageDialog(cp, "No campo IdProfessor deve ser inserido um dado do tipo: int");
                        ok=false;
                    }
                    try{
                        professor.setNome(String.valueOf(tfNome.getText()));
                        lbNome.setForeground(new Color(51,51,51));
                    }catch(Exception er){
                        lbNome.setForeground(Color.red);
                        JOptionPane.showMessageDialog(cp, "No campo Nome deve ser inserido um dado do tipo: String");
                        ok=false;
                    }
                    try{
                        professor.setData_nascimento(Date.valueOf(sdfEua.format(formato.parse(tfData_nascimento.getText()))));
                        lbData_nascimento.setForeground(new Color(51,51,51));
                    }catch(Exception er){
                        lbData_nascimento.setForeground(Color.red);
                        JOptionPane.showMessageDialog(cp, "No campo Data_nascimento deve ser inserido um dado do tipo: Date");
                        ok=false;
                    }
                    try{
                        professor.setData_entrada(Date.valueOf(sdfEua.format(formato.parse(tfData_entrada.getText()))));
                        lbData_entrada.setForeground(new Color(51,51,51));
                    }catch(Exception er){
                        lbData_entrada.setForeground(Color.red);
                        JOptionPane.showMessageDialog(cp, "No campo Data_entrada deve ser inserido um dado do tipo: Date");
                        ok=false;
                    }
                    try{
                        professor.setSalario(Double.valueOf(tfSalario.getText()));
                        lbSalario.setForeground(new Color(51,51,51));
                    }catch(Exception er){
                        lbSalario.setForeground(Color.red);
                        JOptionPane.showMessageDialog(cp, "No campo Salario deve ser inserido um dado do tipo: double");
                        ok=false;
                    }
                    try{
                        professor.setAtivo(cbAtivo.isSelected());
                        lbAtivo.setForeground(new Color(51,51,51));
                    }catch(Exception er){
                        lbAtivo.setForeground(Color.red);
                        JOptionPane.showMessageDialog(cp, "No campo Ativo deve ser inserido um dado do tipo: boolean");
                        ok=false;
                    }
                    try{
                        professor.setIdDepartamento(Integer.valueOf(tfIdDepartamento.getText()));
                        lbIdDepartamento.setForeground(new Color(51,51,51));
                    }catch(Exception er){
                        lbIdDepartamento.setForeground(Color.red);
                        JOptionPane.showMessageDialog(cp, "No campo IdDepartamento deve ser inserido um dado do tipo: int");
                        ok=false;
                    }
                    if(ok){
                        controle.adicionar(professor);
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
                    tfIdProfessor.setEditable(true);
                    lbFotoProfessor.setEnabled(false);
                    painelLeste.setEnabled(false);
                    tfIdProfessor.requestFocus();
                    tfIdProfessor.selectAll();
                    tfNome.setText("");
                    tfData_nascimento.setText("");
                    tfData_entrada.setText("");
                    tfSalario.setText("");
                    cbAtivo.setSelected(false);
                    tfIdDepartamento.setText("");
                    tfNome.setEditable(false);
                    tfData_nascimento.setEditable(false);
                    tfData_entrada.setEditable(false);
                    tfSalario.setEditable(false);
                    cbAtivo.setEnabled(false);
                    tfIdDepartamento.setEditable(false);
                    ImageIcon foto = manipulaImagem.criaIcon("/fotos/professorPadrao.png", 200, 200);
                    lbFotoProfessor.setIcon(foto);
                }
            }
        });
        btListar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                List<Professor> lt = controle.listar();
                String[] colunas = {"IdProfessor","Nome","Data_nascimento","Data_entrada","Salario","Ativo","IdDepartamento"};
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
                tfIdProfessor.setText(chavePrimaria);
                if (JOptionPane.YES_OPTION
                        == JOptionPane.showConfirmDialog(null,
                                "Confirma a exclusão do registro <Nome = " + professor.getNome() + ">?", "Confirm",
                                JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE)) {
                    controle.excluir(professor);
                    String origem = new File("").getAbsolutePath();
                    origem += "/src/fotos/" + tfIdProfessor.getText() + ".png";
                    File img = new File(origem);
                    if(img.exists()){
                        img.delete();
                    }
                    texto.setText("Excluiu o registro de " + professor.getIdProfessor() + " - " + professor.getNome() + "\n\n\n\n\n");                }
                else texto.setText("Não excluiu o registro de " + professor.getIdProfessor() + " - " + professor.getNome() + "\n\n\n\n\n");
                btBuscar.setVisible(true);
                btListar.setVisible(true);
                btExcluir.setVisible(false);
                btAlterar.setVisible(false);
                ImageIcon foto = manipulaImagem.criaIcon("/fotos/professorPadrao.png", 200, 200);
                lbFotoProfessor.setIcon(foto);
                tfIdProfessor.requestFocus();
                tfIdProfessor.selectAll();
                tfIdProfessor.setText("");
                tfNome.setText("");
                tfData_nascimento.setText("");
                tfData_entrada.setText("");
                tfSalario.setText("");
                tfIdProfessor.setEditable(true);
                lbFotoProfessor.setEnabled(false);
                cbAtivo.setSelected(false);
                cbAtivo.setEnabled(true);
                tfIdDepartamento.setText("");
            }
        });
        tfIdDepartamento.addKeyListener(new KeyAdapter() {
            public void keyTyped(KeyEvent e) {
               e.consume();
               
            }
        });

        tfIdDepartamento.addMouseListener(new MouseAdapter() {
            String[] nomeColuna = {"IdDepartamento","Nome","Sigla","CustoMensal","Senha"};
            @Override
            public void mouseClicked(MouseEvent e){
                ManipulaArquivo manipulaArquivo = new ManipulaArquivo();
                List<String> listaAuxiliar = manipulaArquivo.abrirArquivo("DadosDepartamento.csv");
                if (listaAuxiliar.size() > 0) {
                    Point lc = tfIdDepartamento.getLocationOnScreen();
                    lc.x = lc.x + tfIdDepartamento.getWidth();
                    String selectedItem = new JanelaPesquisar(listaAuxiliar, lc.x,
                            lc.y, nomeColuna).getValorRetornado();
                    if (!selectedItem.equals("")) {
                        String[] aux = selectedItem.split(";");
                        tfIdDepartamento.setText(aux[0]);
                    } else {
                        tfIdDepartamento.requestFocus();
                        tfIdDepartamento.selectAll();
                    }
                }
            }
        });
        btLocalizar.addActionListener(new ActionListener() {
            String[] nomeColuna = {"IdProfessor","Nome","Data_nascimento","Data_entrada","Salario","Ativo","IdDepartamento"};
            @Override
            public void actionPerformed(ActionEvent ae) {
                List<String> listaAuxiliar = controle.listStrings();
                if (listaAuxiliar.size() > 0) {
                    Point lc = btLocalizar.getLocationOnScreen();
                    lc.x = lc.x + btLocalizar.getWidth();
                    String selectedItem = new JanelaPesquisar(listaAuxiliar,
                            lc.x,
                            lc.y,
                            nomeColuna).getValorRetornado();
                    if(!selectedItem.equals("")){
                        String[] aux = selectedItem.split(";");
                        tfIdProfessor.setText(aux[0]);
                        btBuscar.doClick();
                    } else {
                        tfIdProfessor.requestFocus();
                        tfIdProfessor.selectAll();
                    }
                }
            }
        });
        lbFotoProfessor.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (acao.equals("alterar")||acao.equals("adicionar")) {
                    JFileChooser fc = new JFileChooser();
                    fc.setFileSelectionMode(JFileChooser.FILES_ONLY);
                    if (fc.showOpenDialog(cp) == JFileChooser.APPROVE_OPTION) {
                        File img = fc.getSelectedFile();
                        String origem = fc.getSelectedFile().getAbsolutePath();
                        String aux1[]=origem.split("/");
                        String aux2=aux1[aux1.length-1];
                        aux1 = aux2.split("\\.");
                        String mimetype= new MimetypesFileTypeMap().getContentType(img);
                        String type = mimetype.split("/")[0];
                        if(!type.equals("image")){
                            JOptionPane.showMessageDialog(cp, "Deve ser inserido uma imagem.");
                        }else{
                            try {
                                ImageIcon icone = new javax.swing.ImageIcon(img.getAbsolutePath());
                                Image imagemAux;
                                imagemAux = icone.getImage();
                                icone.setImage(imagemAux.getScaledInstance(200, 200, Image.SCALE_FAST));
                                lbFotoProfessor.setIcon(icone);

                                String destino = new File("").getAbsolutePath();
                                destino+="/src/fotos/"+ String.valueOf(tfIdProfessor.getText()) + ".png";
                                File inputFile = new File(img.getAbsolutePath());
                                File outputFile = new File(destino);
                                try (InputStream is = new FileInputStream(inputFile)) {
                                    BufferedImage image = ImageIO.read(is);
                                    try (OutputStream os = new FileOutputStream(outputFile)) {
                                        ImageIO.write(image, "png", os);
                                    } catch (Exception exp) {
                                        exp.printStackTrace();
                                    }
                                } catch (Exception exp) {
                                    exp.printStackTrace();
                                }
                                //copia.copiar(origem, destino);
                            } catch (Exception ex){
                                System.out.println("Erro: " + ex.getMessage());
                            }
                        }
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
