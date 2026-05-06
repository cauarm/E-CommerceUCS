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
        if(prod==null){
            System.out.println("Produto inválido.\n");
            return;
        }

        listaProdutos.add(prod);
        prod.setFornecedor(this);
    }

    public void excluirProduto(int id){
        Produto produto = buscaProdutoID(id);

        if(produto != null){
            listaProdutos.remove(produto);
            produto.setFornecedor(null);
            System.out.println("Produto excluído com sucesso.\n");
        }else{
            System.out.println("Produto não encontrado.\n");
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

    public Produto buscarProdutoNome(String nome){

        if( nome==null){
            return null;
        }
        for(Produto p : listaProdutos){
            if(p.getNome().equalsIgnoreCase(nome)){
                return p;
            }
        }

        return null;
    }

    public void listarProdutos(){
        for(Produto p : listaProdutos){
            System.out.printf("ID: %d | Nome: %s | Categoria: %s | Valor: %.2f | Peso: %.2f\n",
            p.getId(), p.getNome(), p.getCategoria(), p.getValor(), p.getPeso());
        }
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getNacionalidade() {
        return nacionalidade;
    }

    public ArrayList<Produto> getListaProdutos() {
        return listaProdutos;
    }

    public void setNacionalidade(String nacionalidade) {
        this.nacionalidade = nacionalidade;
    }

    @Override
    public String toString() {
        return "Fornecedor{" +
                "nome='" + nome + '\'' +
                ", categoria='" + categoria + '\'' +
                ", nacionalidade='" + nacionalidade + '\'' +
                '}';
    }
}
