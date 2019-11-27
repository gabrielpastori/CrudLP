package CrudUnidadeDeMedida;

public class UnidadeDeMedida implements java.io.Serializable {

    private String sigla;
    private String nome;

    public void setSigla(String sigla) {
        this.sigla = sigla;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getSigla() {
        return this.sigla;
    }

    public String getNome() {
        return this.nome;
    }

    @Override
    public String toString() {
        return this.sigla + ";" + this.nome;
    }
}
