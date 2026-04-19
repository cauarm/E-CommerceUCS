package Ecommerce;

import java.util.ArrayList;

public class Fornecedor {

    private String nome;
    private String categoria;
    private String nacionalidade;
    private ArrayList<Produto> listaProdutos = new ArrayList<Produto>();

    public Fornecedor(String nome, String categoria, String nacionalidade) {

        if (nome == null || nome.trim().isEmpty()) {
            System.out.println("Nome inválido.");
            this.nome = "Sem nome";
        } else {
            this.nome = nome;
        }

        if (categoria == null || categoria.trim().isEmpty()) {
            System.out.println("Categoria inválida.");
            this.categoria = "Sem categoria";
        }else{
            this.categoria = categoria;
        }

        if(nacionalidade == null || nacionalidade.trim().isEmpty()){
            System.out.println("Nacionalidade inválida.");
            this.nacionalidade = "Sem nacionalidade.";
        }else{
            this.nacionalidade = nacionalidade;
        }
    }

    public void incluirProduto(Produto prod){
        listaProdutos.add(prod);
    }

    public void excluirProduto(int id){
        for(Produto p : listaProdutos){
            if(p.getId() == id){
                listaProdutos.remove(p);
                break;
            }
        }
    }

    public Produto buscaProdutoID(int id){
        for(Produto p : listaProdutos){
            if(p.getId() == id){
                return p;
            }
        }

        return null;
    }

    public void alterarProduto(int id){

    }

}
