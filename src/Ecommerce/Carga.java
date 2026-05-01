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

    public void listarProd(){
        for(Produto p : listaProd){
            System.out.printf("ID: %d | Nome: %s | Categoria: %s | Valor: %.2f | Peso: %.2f\n",
                    p.getId(), p.getNome(), p.getCategoria(), p.getValor(), p.getPeso());
        }
    }

    public Transportadora getTransportadora() {
        return transportadora;
    }

    public void setTransportadora(Transportadora transportadora) {
        this.transportadora = transportadora;
    }

    public int getId() {
        return id;
    }
}
