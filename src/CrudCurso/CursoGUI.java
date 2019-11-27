//Nome: Gabriel Pastori
//Curso: Técnico Integrado em Informática - Linguagem de Programação II
//Código responsável pela geração do código da classe GUI
package CrudCurso;

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
import tools.ManipulaImagem;
import javax.swing.ImageIcon;
import javax.swing.JDialog;
import tools.Centraliza;
import tools.DateTextField;
import tools.ManipulaArquivo;
public class CursoGUI extends JDialog {
    
    private JDialog f;
    
    private JPanel cp = new JPanel();
    private JDialog dialog = new JDialog();
    private JLabel lbIdCurso = new JLabel("IDCURSO");
    private JTextField tfIdCurso= new JTextField(20);
    private JLabel lbNome = new JLabel("Nome");
    private JTextField tfNome= new JTextField(20);
    private JLabel lbDuracao = new JLabel("Duracao");
    DateTextField tfDuracao= new DateTextField();
    private JLabel lbTurno = new JLabel("Turno");
    private JTextField tfTurno= new JTextField(20);
    private JLabel lbIdDepartamento = new JLabel("IdDepartamento");
    private JTextField tfIdDepartamento= new JTextField(20);
    private JButton btAdicionar = new JButton("Adicionar");
    private JButton btListar = new JButton("Listar");
    private JButton btBuscar = new JButton("Buscar");
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
    private JTextArea texto = new JTextArea();
    private JScrollPane scrollTexto = new JScrollPane();
    private JScrollPane scrollTabela = new JScrollPane();
    private String acao = "";
    private String chavePrimaria = "";
    private CursoControle controle = new CursoControle();
    private ManipulaImagem manipulaImagem=new ManipulaImagem();
    private Curso aluno = new Curso();
    String[] colunas = new String[]{"IdCurso","Nome","Duracao","Turno","IdDepartamento"};

    String[][] dados = new String[0][4];
    DefaultTableModel model = new DefaultTableModel(dados, colunas);
    JTable tabela = new JTable(model);
    private JPanel painel1 = new JPanel(new GridLayout(1, 1));
    private JPanel painel2 = new JPanel(new GridLayout(1, 1));
    private CardLayout cardLayout;
    private Centraliza centraliza = new Centraliza();
    public CursoGUI(JFrame pai) {
        f = new JDialog(pai, "", true);
        f.getContentPane();
        f.pack();
        
        f.setTitle("CRUD Produtos");
        f.setSize(600, 800);
        f.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        f.setLayout(new GridLayout(1,1));
        
        
        String caminhoENomeDoArquivo = "DadosCurso.csv";
        //cp.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        //cp.setSize(600, 400);
        //cp.setLocationRelativeTo(null);
        //cp.setTitle("Curso");
        //cp.getContentPane();
        cp.setLayout(new BorderLayout());
        cp.add(painelNorte, BorderLayout.NORTH);
        cp.add(painelCentro, BorderLayout.CENTER);
        cp.add(painelSul, BorderLayout.SOUTH); 
        f.add(cp);
        centraliza.centralizaFilho(pai, f);
        ImageIcon icon=manipulaImagem.criaIcon("/icones/retrieve.png", 30, 30);
        btBuscar=manipulaImagem.insereBotao(icon, "Buscar");
        
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
        painelCentro.add(lbDuracao);
        painelCentro.add(tfDuracao);
        painelCentro.add(lbTurno);
        painelCentro.add(tfTurno);
        painelCentro.add(lbIdDepartamento);
        painelCentro.add(tfIdDepartamento);
        toolBar.add(lbIdCurso);
        toolBar.add(tfIdCurso);
        toolBar.add(btAdicionar);
        toolBar.add(btBuscar);
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
        tfDuracao.setEditable(false);
        tfTurno.setEditable(false);
        tfIdDepartamento.setEditable(false);
        texto.setEditable(false);
        btCarregarDados.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ManipulaArquivo manipulaArquivo = new ManipulaArquivo();
                if (manipulaArquivo.existeOArquivo(caminhoENomeDoArquivo)) {
                    String aux[];
                    Curso t;
                    List<String> listaStringCsv = manipulaArquivo.abrirArquivo(caminhoENomeDoArquivo);
                    for (String linha : listaStringCsv) {
                        aux = linha.split(";");
                        SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
                        SimpleDateFormat sdfEua = new SimpleDateFormat("yyyy-MM-dd");
                        try{
                            t = new Curso(Integer.valueOf(aux[0]),String.valueOf(aux[1]),Date.valueOf(sdfEua.format(formato.parse(aux[2]))),Double.valueOf(aux[3]),Integer.valueOf(aux[4]));
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
                List<Curso> listaAluno = controle.listar();
                List<String> listaAlunoEmFormatoStringCSV = new ArrayList<>();
                for (Curso t : listaAluno) {
                   listaAlunoEmFormatoStringCSV.add(t.toString());
                }
                new ManipulaArquivo().salvarArquivo(caminhoENomeDoArquivo, listaAlunoEmFormatoStringCSV);
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
                    Long.valueOf(tfIdCurso.getText());
                }catch(Exception er){
                    JOptionPane.showMessageDialog(cp, "IDCURSO deve ser do tipo int");
                    ok=false;
                }
                if(tfIdCurso.getText().trim().isEmpty() || !ok){
                    if(tfIdCurso.getText().trim().isEmpty()){
                        JOptionPane.showMessageDialog(cp, "IDCURSO nâo pode ser vazio");
                    }
                    tfIdCurso.requestFocus();
                    tfIdCurso.selectAll();
                }else{
                    chavePrimaria = tfIdCurso.getText();
                    aluno = controle.buscar(tfIdCurso.getText());
                    if (aluno== null) {
                        btAdicionar.setVisible(true);
                        btAlterar.setVisible(false);
                        btExcluir.setVisible(false);
                        tfNome.setText("");
                        tfDuracao.setText("");
                        tfTurno.setText("");
                        tfIdDepartamento.setText("");
                        texto.setText("Não encontrou na lista - pode Adicionar\n\n\n");
                    }else{
                        SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
                        texto.setText("Encontrou na lista - pode Alterar ou Excluir\n\n\n");
                        btAlterar.setVisible(true);
                        btExcluir.setVisible(true);
                        tfNome.setText(String.valueOf(aluno.getNome()));
                        tfNome.setEditable(false);
                        tfDuracao.setText(formato.format(aluno.getDuracao()));
                        tfDuracao.setEditable(false);
                        tfTurno.setText(String.valueOf(aluno.getTurno()));
                        tfTurno.setEditable(false);
                        tfIdDepartamento.setText(String.valueOf(aluno.getIdDepartamento()));
                        tfIdDepartamento.setEditable(false);
                    }
                }
            }
        });
        btAdicionar.addActionListener(new ActionListener() {
           @Override
           public void actionPerformed(ActionEvent e) {
                acao = "adicionar";
                tfIdCurso.setText(chavePrimaria);
                tfNome.setEditable(true);
                tfDuracao.setEditable(true);
                tfTurno.setEditable(true);
                tfIdDepartamento.setEditable(true);
                tfIdCurso.setEditable(false);
                btSalvar.setVisible(true);
                btCancelar.setVisible(true);
                btBuscar.setVisible(false);
                btListar.setVisible(false);
                btAlterar.setVisible(false);
                btExcluir.setVisible(false);
                btAdicionar.setVisible(false);
                texto.setText("Preencha os atributos\n\n\n\n\n");
            }
        });
        btAlterar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                acao = "alterar";
                tfIdCurso.setText(chavePrimaria);
                tfIdCurso.setEditable(false);
                tfNome.requestFocus();
                btSalvar.setVisible(true);
                btCancelar.setVisible(true);
                btBuscar.setVisible(false);
                btListar.setVisible(false);
                btAlterar.setVisible(false);
                btExcluir.setVisible(false);
                texto.setText("Preencha os atributos\n\n\n\n\n");
                tfNome.setEditable(true);
                tfDuracao.setEditable(true);
                tfTurno.setEditable(true);
                tfIdDepartamento.setEditable(true);
            }
        });
        btCancelar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                btSalvar.setVisible(false);
                btCancelar.setVisible(false);
                btBuscar.setVisible(true);
                btListar.setVisible(true);
                tfIdCurso.setEditable(true);
                tfNome.setText("");
                tfNome.setEditable(false);
                tfDuracao.setText("");
                tfDuracao.setEditable(false);
                tfTurno.setText("");
                tfTurno.setEditable(false);
                tfIdDepartamento.setText("");
                tfIdDepartamento.setEditable(false);
                tfIdCurso.requestFocus();
                tfIdCurso.selectAll();
                texto.setText("Cancelou\n\n\n\n\n");
            }
        });
        btSalvar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                boolean ok=true;
                if (acao.equals("alterar")) {
                    Curso alunoAntigo = aluno;
                    SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
                    SimpleDateFormat sdfEua = new SimpleDateFormat("yyyy-MM-dd");
                    try{
                        aluno.setNome(String.valueOf(tfNome.getText()));
                        lbNome.setForeground(new Color(51,51,51));
                    }catch(Exception er){
                        lbNome.setForeground(Color.red);
                        JOptionPane.showMessageDialog(cp, "No campo Nome deve ser inserido um dado do tipo: String");
                        ok=false;
                    }
                    try{
                        aluno.setDuracao(Date.valueOf(sdfEua.format(formato.parse(tfDuracao.getText()))));
                        lbDuracao.setForeground(new Color(51,51,51));
                    }catch(Exception er){
                        lbDuracao.setForeground(Color.red);
                        JOptionPane.showMessageDialog(cp, "No campo Duracao deve ser inserido um dado do tipo: Date");
                        ok=false;
                    }
                    try{
                        aluno.setTurno(Double.valueOf(tfTurno.getText()));
                        lbTurno.setForeground(new Color(51,51,51));
                    }catch(Exception er){
                        lbTurno.setForeground(Color.red);
                        JOptionPane.showMessageDialog(cp, "No campo Turno deve ser inserido um dado do tipo: double");
                        ok=false;
                    }
                    try{
                        aluno.setIdDepartamento(Integer.valueOf(tfIdDepartamento.getText()));
                        lbIdDepartamento.setForeground(new Color(51,51,51));
                    }catch(Exception er){
                        lbIdDepartamento.setForeground(Color.red);
                        JOptionPane.showMessageDialog(cp, "No campo IdDepartamento deve ser inserido um dado do tipo: int");
                        ok=false;
                    }
                    if(ok){
                        controle.alterar(aluno, alunoAntigo);
                        texto.setText("Registro alterado\n\n\n\n\n");
                    }
                }else{
                    aluno= new Curso();
                    SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
                    SimpleDateFormat sdfEua = new SimpleDateFormat("yyyy-MM-dd");
                    try{
                        aluno.setIdCurso(Integer.valueOf(tfIdCurso.getText()));
                        lbIdCurso.setForeground(new Color(51,51,51));
                    }catch(Exception er){
                        lbIdCurso.setForeground(Color.red);
                        JOptionPane.showMessageDialog(cp, "No campo IdCurso deve ser inserido um dado do tipo: int");
                        ok=false;
                    }
                    try{
                        aluno.setNome(String.valueOf(tfNome.getText()));
                        lbNome.setForeground(new Color(51,51,51));
                    }catch(Exception er){
                        lbNome.setForeground(Color.red);
                        JOptionPane.showMessageDialog(cp, "No campo Nome deve ser inserido um dado do tipo: String");
                        ok=false;
                    }
                    try{
                        aluno.setDuracao(Date.valueOf(sdfEua.format(formato.parse(tfDuracao.getText()))));
                        lbDuracao.setForeground(new Color(51,51,51));
                    }catch(Exception er){
                        lbDuracao.setForeground(Color.red);
                        JOptionPane.showMessageDialog(cp, "No campo Duracao deve ser inserido um dado do tipo: Date");
                        ok=false;
                    }
                    try{
                        aluno.setTurno(Double.valueOf(tfTurno.getText()));
                        lbTurno.setForeground(new Color(51,51,51));
                    }catch(Exception er){
                        lbTurno.setForeground(Color.red);
                        JOptionPane.showMessageDialog(cp, "No campo Turno deve ser inserido um dado do tipo: double");
                        ok=false;
                    }
                    try{
                        aluno.setIdDepartamento(Integer.valueOf(tfIdDepartamento.getText()));
                        lbIdDepartamento.setForeground(new Color(51,51,51));
                    }catch(Exception er){
                        lbIdDepartamento.setForeground(Color.red);
                        JOptionPane.showMessageDialog(cp, "No campo IdDepartamento deve ser inserido um dado do tipo: int");
                        ok=false;
                    }
                    if(ok){
                        controle.adicionar(aluno);
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
                    tfIdCurso.setEditable(true);
                    tfIdCurso.requestFocus();
                    tfIdCurso.selectAll();
                    tfNome.setText("");
                    tfDuracao.setText("");
                    tfTurno.setText("");
                    tfIdDepartamento.setText("");
                    tfNome.setEditable(false);
                    tfDuracao.setEditable(false);
                    tfTurno.setEditable(false);
                    tfIdDepartamento.setEditable(false);
                }
            }
        });
        btListar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                List<Curso> lt = controle.listar();
                String[] colunas = {"IdCurso","Nome","Duracao","Turno","IdDepartamento"};
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
                tfIdCurso.setText(chavePrimaria);
                if (JOptionPane.YES_OPTION
                        == JOptionPane.showConfirmDialog(null,
                                "Confirma a exclusão do registro <Nome = " + aluno.getNome() + ">?", "Confirm",
                                JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE)) {
                    controle.excluir(aluno);
                    texto.setText("Excluiu o registro de " + aluno.getIdCurso() + " - " + aluno.getNome() + "\n\n\n\n\n");                }
                else texto.setText("Não excluiu o registro de " + aluno.getIdCurso() + " - " + aluno.getNome() + "\n\n\n\n\n");
                btBuscar.setVisible(true);
                btListar.setVisible(true);
                btExcluir.setVisible(false);
                btAlterar.setVisible(false);
                tfIdCurso.requestFocus();
                tfIdCurso.selectAll();
                tfIdCurso.setText("");
                tfNome.setText("");
                tfDuracao.setText("");
                tfTurno.setText("");
                tfIdDepartamento.setText("");
            }
        });
        f.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                //antes de sair, salvar a lista em disco
                btGravar.doClick();
                // Sai da classe
                f.dispose();
            }
        });
        f.setVisible(true);
        btCarregarDados.doClick();
        //cp.setVisible(true);
    }
}

