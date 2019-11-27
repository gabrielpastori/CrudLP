//Nome: Gabriel Pastori
//Curso: Técnico Integrado em Informática - Linguagem de Programação II
//Código responsável pela geração do código da classe Entidade
package CrudCurso;
import java.text.SimpleDateFormat;
import java.util.Date;



public class Curso {


    private int idCurso;
    private String nome;
    private Date duracao;
    private double turno;
    private int idDepartamento;


    public Curso() {
    }

    public Curso(int idCurso,String nome,Date duracao,double turno,int idDepartamento){

        this.idCurso=idCurso;
        this.nome=nome;
        this.duracao=duracao;
        this.turno=turno;
        this.idDepartamento=idDepartamento;
    }
    public int getIdCurso(){

        return idCurso;
    }
    public void setIdCurso(int idCurso){

        this.idCurso=idCurso;
    }
    public String getNome(){

        return nome;
    }
    public void setNome(String nome){

        this.nome=nome;
    }
    public Date getDuracao(){

        return duracao;
    }
    public void setDuracao(Date duracao){

        this.duracao=duracao;
    }
    public double getTurno(){

        return turno;
    }
    public void setTurno(double turno){

        this.turno=turno;
    }
    public int getIdDepartamento(){

        return idDepartamento;
    }
    public void setIdDepartamento(int idDepartamento){

        this.idDepartamento=idDepartamento;
    }
    @Override

    public String toString(){

        SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
        return String.valueOf(idCurso)+";" +nome+";" +String.valueOf(formato.format(duracao))+";" +String.valueOf(turno)+";" +String.valueOf(idDepartamento);
    }
}
