package Main;

import CrudDepartamento.DepartamentoGUI;
import CrudProfessor.ProfessorGUI;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.Action;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JToolBar;
import static javax.swing.WindowConstants.DISPOSE_ON_CLOSE;
import tools.CentroDoMonitorMaior;
import tools.ManipulaImagem;
import CrudDepartamento.Departamento;
import tools.Centraliza;
public class MenuPrincipal extends JFrame {
    
    JFrame cp = new JFrame();
    Font fonte = new Font("Monotype Corsiva", Font.BOLD, 30);
    JButton btDepartamento = new JButton();
    JButton btProfessor = new JButton();
    JLabel labelComImagemDeTamanhoDiferente = new JLabel();
    JToolBar tool = new JToolBar();
    JPanel paneCentral = new JPanel();
    
    Point p;
    ManipulaImagem manipulaImagem = new ManipulaImagem();
    public MenuPrincipal(Dimension dimensao) {
        cp.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        cp.setSize(dimensao);
        cp.setTitle("UniLambda");
        
        
        
        cp.getContentPane();
        cp.setLayout(new BorderLayout());
        
        //Centraliza centraliza = new Centraliza();
        //centraliza.centralizaComponente(cp);
        
        btProfessor = manipulaImagem.insereBotao(manipulaImagem.criaIcon("/icones/teacher.png", 30, 20), "Cadastrar Professor");
        btDepartamento = manipulaImagem.insereBotao(manipulaImagem.criaIcon("/icones/department.png", 25, 20), "Cadastrar Departamento");

        tool.add(btDepartamento);
        tool.add(btProfessor);
        tool.setBackground(Color.white);
        cp.add(tool,BorderLayout.NORTH);
        Image bgImg = new ImageIcon(this.getClass().getResource("/icones/logo.jpeg")).getImage();
        paneCentral = new ImagePanel(bgImg);
        cp.add(paneCentral,BorderLayout.CENTER);
        
        btProfessor.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent ae) {
                new ProfessorGUI(cp);
            }
        });
       
        btDepartamento.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent ae) {
                
                new DepartamentoGUI(cp);
            }
        });
        
        
        
        
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        });
        
        p = new CentroDoMonitorMaior().getCentroMonitorMaior(cp);
        cp.setLocation(p);
        
        cp.setVisible(true);
    }
}
 class ImagePanel extends JPanel {
    private static final long serialVersionUID = 1L;

    private Image img;

    public ImagePanel(String img) {
            this(new ImageIcon(img).getImage());
    }

    public ImagePanel(Image img) {
            this.img = img;
            Dimension size = new Dimension(img.getWidth(null), img.getHeight(null));
            setPreferredSize(size);
            setMinimumSize(size);
            setMaximumSize(size);
            setSize(size);
            setLayout(null);
    }

    public void paintComponent(Graphics g) {
           g.drawImage(img, 0, 0, getWidth(), getHeight(), null);
    }
}
