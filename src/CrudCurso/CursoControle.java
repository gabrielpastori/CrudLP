//Nome: Gabriel Pastori
//Curso: Técnico Integrado em Informática - Linguagem de Programação II
//Código responsável pela geração do código da classe Controle
package CrudCurso;

import java.util.ArrayList;
import java.util.List;



public class CursoControle {


    private List<Curso> lista = new ArrayList<>();
    public CursoControle() {

    }
    public void limparLista(){
        lista.clear();
    }
    public void adicionar(Curso aluno){
        lista.add(aluno);
    }
    public List<Curso> listar(){
        return lista;
    }
    public Curso buscar(String idCurso){
        for (int i = 0; i < lista.size(); i++) {
            if (String.valueOf(lista.get(i).getIdCurso()).equals(idCurso)) {
                return lista.get(i);
            }        }
        return null;
    }
    public void alterar(Curso aluno, Curso alunoAntigo){
        lista.set(lista.indexOf(alunoAntigo),aluno);

    }
    public void excluir(Curso aluno) {
        lista.remove(aluno);
    }
}
