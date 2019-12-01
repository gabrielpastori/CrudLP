//Nome: Gabriel Pastori
//Curso: Técnico Integrado em Informática - Linguagem de Programação II
//Código responsável pela geração do código da classe Entidade
package CrudDepartamento;
import java.text.SimpleDateFormat;
import java.util.Date;



public class Departamento {


    private int idDepartamento;
    private String nome;
    private String sigla;
    private double custoMensal;
    private String senha;


    public Departamento() {
    }

    public Departamento(int idDepartamento,String nome,String sigla,double custoMensal,String senha){

        this.idDepartamento=idDepartamento;
        this.nome=nome;
        this.sigla=sigla;
        this.custoMensal=custoMensal;
        this.senha=senha;
    }
    public int getIdDepartamento(){

        return idDepartamento;
    }
    public void setIdDepartamento(int idDepartamento){

        this.idDepartamento=idDepartamento;
    }
    public String getNome(){

        return nome;
    }
    public void setNome(String nome){

        this.nome=nome;
    }
    public String getSigla(){

        return sigla;
    }
    public void setSigla(String sigla){

        this.sigla=sigla;
    }
    public double getCustoMensal(){

        return custoMensal;
    }
    public void setCustoMensal(double custoMensal){

        this.custoMensal=custoMensal;
    }
    public String getSenha(){

        return senha;
    }
    public void setSenha(String senha){

        this.senha=senha;
    }
    @Override

    public String toString(){

        SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
        return String.valueOf(idDepartamento)+";" +nome+";" +sigla+";" +String.valueOf(custoMensal)+";" +senha;
    }
}
