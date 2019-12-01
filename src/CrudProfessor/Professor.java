//Nome: Gabriel Pastori
//Curso: Técnico Integrado em Informática - Linguagem de Programação II
//Código responsável pela geração do código da classe Entidade
package CrudProfessor;
import java.text.SimpleDateFormat;
import java.util.Date;



public class Professor {


    private int idProfessor;
    private String nome;
    private Date data_nascimento;
    private Date data_entrada;
    private double salario;
    private boolean ativo;
    private int idDepartamento;


    public Professor() {
    }

    public Professor(int idProfessor,String nome,Date data_nascimento,Date data_entrada,double salario,boolean ativo,int idDepartamento){

        this.idProfessor=idProfessor;
        this.nome=nome;
        this.data_nascimento=data_nascimento;
        this.data_entrada=data_entrada;
        this.salario=salario;
        this.ativo=ativo;
        this.idDepartamento=idDepartamento;
    }
    public int getIdProfessor(){

        return idProfessor;
    }
    public void setIdProfessor(int idProfessor){

        this.idProfessor=idProfessor;
    }
    public String getNome(){

        return nome;
    }
    public void setNome(String nome){

        this.nome=nome;
    }
    public Date getData_nascimento(){

        return data_nascimento;
    }
    public void setData_nascimento(Date data_nascimento){

        this.data_nascimento=data_nascimento;
    }
    public Date getData_entrada(){

        return data_entrada;
    }
    public void setData_entrada(Date data_entrada){

        this.data_entrada=data_entrada;
    }
    public double getSalario(){

        return salario;
    }
    public void setSalario(double salario){

        this.salario=salario;
    }
    public boolean getAtivo(){

        return ativo;
    }
    public void setAtivo(boolean ativo){

        this.ativo=ativo;
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
        return String.valueOf(idProfessor)+";" +nome+";" +String.valueOf(formato.format(data_nascimento))+";" +String.valueOf(formato.format(data_entrada))+";" +String.valueOf(salario)+";" +String.valueOf(ativo)+";" +String.valueOf(idDepartamento);
    }
}
