//Nome: Gabriel Pastori
//Curso: Técnico Integrado em Informática - Linguagem de Programação II
//Código responsável pela geração do código da classe Controle
package CrudProfessor;

import java.util.ArrayList;
import java.util.List;



public class ProfessorControle {


    private List<Professor> lista = new ArrayList<>();
    public ProfessorControle() {

    }
    public void limparLista(){
        lista.clear();
    }
    public void adicionar(Professor professor){
        lista.add(professor);
    }
    public List<Professor> listar(){
        return lista;
    }
    public Professor buscar(String idProfessor){
        for (int i = 0; i < lista.size(); i++) {
            if (String.valueOf(lista.get(i).getIdProfessor()).equals(idProfessor)) {
                return lista.get(i);
            }        }
        return null;
    }
    public void alterar(Professor professor, Professor professorAntigo){
        lista.set(lista.indexOf(professorAntigo),professor);

    }
    public List<String> listStrings(){
        List<String> ls = new ArrayList<>();
        for (Professor l : lista) {
            ls.add(l.toString());
        }
        return ls;
    }
    public void excluir(Professor professor) {
        lista.remove(professor);
    }
}
