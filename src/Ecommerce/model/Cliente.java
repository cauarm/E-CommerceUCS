package Ecommerce.model;

import java.util.ArrayList;
import java.util.List;

public class Cliente extends Usuario{

    private String nome;
    private List<Pedido> listaPedidos;

    public Cliente(String nome, String login, String senha){
        super(login, senha);
        if(nome == null || nome.trim().isEmpty()){
            System.out.println("Nome inválido.");
            this.nome = "Sem nome";
        }else{
            this.nome = nome;
        }
        this.listaPedidos = new ArrayList<Pedido>();
    }

    public void adicionarPedido(Pedido pedido) {
        if(pedido == null){
            System.out.println("Pedido inválido.");
            return;
        }
        listaPedidos.add(pedido);
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        if(nome == null || nome.trim().isEmpty()){
            System.out.println("Nome inválido.");
        }else{
            this.nome = nome;
        }
    }

    public List<Pedido> getListaPedidos() {
        return listaPedidos;
    }

}
