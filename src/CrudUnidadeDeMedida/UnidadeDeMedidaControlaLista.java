package CrudUnidadeDeMedida;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;
import tools.ManipulaArquivo;

public class UnidadeDeMedidaControlaLista {

    private List<UnidadeDeMedida> lista = new ArrayList<>();

    public void inserir(UnidadeDeMedida registro) {
        lista.add(registro);
    }

    public UnidadeDeMedida retrieve(UnidadeDeMedida classe) {
        if (lista.size() > 0) {
            String chaveProcurada = classe.getSigla();
            String chaveNaLista;
            for (int i = 0; i < lista.size(); i++) {
                chaveNaLista = lista.get(i).getSigla();

                if (chaveNaLista.equals(chaveProcurada)) {
                    return lista.get(i);
                }
            }
        }
        return null;//não achou na lista
    }

    public void atualizar(UnidadeDeMedida procurado, UnidadeDeMedida alterado) {
        lista.set(lista.indexOf(procurado), alterado);
    }

    public void excluir(UnidadeDeMedida elemento) {
        lista.remove(elemento);
    }

    public void getArquivo(String arquivo) {
        lista.clear();
        List<String> listaSTR = new ManipulaArquivo().abrirArquivo(arquivo);
        String[] aux;
        UnidadeDeMedida unidadeDeMedida;
        for (String linha : listaSTR) {
            aux = linha.split(";");
            unidadeDeMedida = new UnidadeDeMedida();
            unidadeDeMedida.setSigla(aux[0]);
            unidadeDeMedida.setNome(aux[1]);
            lista.add(unidadeDeMedida);
        }

    }

    public void salvarArquivo(String arquivo) {
        ManipulaArquivo manipulaArquivo = new ManipulaArquivo();
        List<String> listaSTR = new ArrayList<>();
        for (UnidadeDeMedida linha : lista) {
            listaSTR.add(linha.toString());
        }
        manipulaArquivo.salvarArquivo(arquivo, listaSTR);

    }

    public List<UnidadeDeMedida> getLista() {
        return lista;
    }

    public List<String> getListaString() {
        List<String> listaStrings = new ArrayList();
        for (int i = 0; i < lista.size(); i++) {
            listaStrings.add(lista.get(i).toString() + System.lineSeparator());
        }
        return listaStrings;
    }
} //fim
