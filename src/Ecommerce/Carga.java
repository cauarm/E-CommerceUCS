package Ecommerce;

import java.util.ArrayList;

public class Carga {

    private ArrayList<Produto> listaProd = new ArrayList<Produto>();
    private Transportadora transportadora;
    private int id;

    public Carga(Transportadora transportadora,int id){
        if(transportadora==null){
            System.out.println("Transportadora inválida.\n");
        }else{
            this.transportadora = transportadora;
        }

        if(id<=0){
            System.out.println("ID inválido.\n");
            this.id = 1;
        }else{
            this.id = id;
        }
    }

    public void adicionarProduto(Produto prod){

        if(prod==null){
            System.out.println("Produto inválido.\n");
            return;
        }
        listaProd.add(prod);
    }

    public void removerProduto(int id){
        Produto produto = buscaProdutoID(id);

        if(produto!=null){
            listaProd.remove(produto);
            System.out.println("Produto removido da carga com sucesso.");
        }else{
            System.out.println("Produto não encontrado.");
            return;
        }
    }

    public Produto buscaProdutoID(int id){
        for(Produto p : listaProd){
            if(p.getId() == id){
                return p;
            }
        }
        return null;
    }

    public void listarProdutos(){
        for(Produto p : listaProd){
            System.out.printf("ID: %d | Nome: %s | Categoria: %s | Valor: %.2f | Peso: %.2f | Fornecedor: %s\n",
                    p.getId(), p.getNome(), p.getCategoria(), p.getValor(), p.getPeso(), p.getFornecedor().getNome());
        }
    }

    public ArrayList<Produto> getListaProd() {
        return listaProd;
    }

    public Transportadora getTransportadora() {
        return transportadora;
    }

    public void setTransportadora(Transportadora transportadora) {
        if(transportadora==null){
            System.out.println("Transportadora inválida.");
            return;
        }
        this.transportadora = transportadora;
    }

    public int getId() {
        return id;
    }

    @Override
    public String toString() {

        String nomeTransportadora = "Sem transportadora";

        if(transportadora!=null){
            nomeTransportadora = transportadora.getNome();
        }
        return "Carga{" +
                "id=" + id +
                ", transportadora=" + nomeTransportadora +
                '}';
    }
}
