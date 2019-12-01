//Nome: Gabriel Pastori
//Curso: Técnico Integrado em Informática - Linguagem de Programação II
//Código responsável pela geração do código da classe Controle
package CrudDepartamento;

import java.util.ArrayList;
import java.util.List;



public class DepartamentoControle {


    private List<Departamento> lista = new ArrayList<>();
    public DepartamentoControle() {

    }
    public void limparLista(){
        lista.clear();
    }
    public void adicionar(Departamento departamento){
        lista.add(departamento);
    }
    public List<Departamento> listar(){
        return lista;
    }
    public Departamento buscar(String idDepartamento){
        for (int i = 0; i < lista.size(); i++) {
            if (String.valueOf(lista.get(i).getIdDepartamento()).equals(idDepartamento)) {
                return lista.get(i);
            }        }
        return null;
    }
    public void alterar(Departamento departamento, Departamento departamentoAntigo){
        lista.set(lista.indexOf(departamentoAntigo),departamento);

    }
    public List<String> listStrings(){
        List<String> ls = new ArrayList<>();
        for (Departamento l : lista) {
            ls.add(l.toString());
        }
        return ls;
    }
    public void excluir(Departamento departamento) {
        lista.remove(departamento);
    }
}
