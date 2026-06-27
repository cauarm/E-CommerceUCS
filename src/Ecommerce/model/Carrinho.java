package Ecommerce.model;

import Ecommerce.exception.EstoqueInsuficienteException;

import java.util.HashMap;

public class Carrinho {

    private HashMap<Integer, ItemCarrinho> itens;

    public Carrinho() {
        itens = new HashMap<Integer, ItemCarrinho>();
    }

    public void adicionarItem(Produto produto, int quantidade) throws EstoqueInsuficienteException {
        if (produto == null) {
            System.out.println("Produto inválido.");
            return;
        }

        if (quantidade <= 0) {
            System.out.println("Quantidade inválida.");
            return;
        }

        int idProduto = produto.getId();
        int qtdAtual = 0;

        if (itens.containsKey(idProduto)) {
            qtdAtual = itens.get(idProduto).getQuantidade();
        }

        int novaQuantidade = qtdAtual + quantidade;

        if (!produto.verificarEstoque(novaQuantidade)) {
            throw new EstoqueInsuficienteException(
                    "Estoque insuficiente. Disponível: " + produto.getEstoque()
            );
        }

        if (itens.containsKey(idProduto)) {
            itens.get(idProduto).adicionarQuantidade(quantidade);
        } else {
            itens.put(idProduto, new ItemCarrinho(produto, quantidade));
        }

        System.out.println("Produto adicionado ao carrinho.");
    }

    public void removerItem(int idProduto) {
        if (itens.remove(idProduto) != null) {
            System.out.println("Produto removido do carrinho.");
        } else {
            System.out.println("Produto não encontrado no carrinho.");
        }
    }

    public double calcularTotal() {
        double total = 0;

        for (ItemCarrinho item : itens.values()) {
            total += item.getTotal();
        }

        return total;
    }

    public Pedido finalizarPedido(int numeroPedido, Cliente cliente, double frete) throws EstoqueInsuficienteException {

        if(cliente == null){
            System.out.println("Cliente inválido.");
            return null;
        }

        if(itens.isEmpty()){
            System.out.println("Carrinho vazio.");
            return null;
        }

        Pedido pedido = new Pedido(numeroPedido, cliente, frete);

        for(ItemCarrinho item : itens.values()){
            Produto produto = item.getProduto();
            int quantidade = item.getQuantidade();

            pedido.adicionarItem(produto, quantidade);
        }

        cliente.adicionarPedido(pedido);
        itens.clear();

        System.out.println("Pedido finalizado com sucesso.");

        return pedido;
    }

    public void listarItens() {

        if(itens.isEmpty()){
            System.out.println("Carrinho vazio.");
            return;
        }

        for (ItemCarrinho item : itens.values()) {
            System.out.println(item);
        }
    }

    public HashMap<Integer, ItemCarrinho> getItens() {
        return itens;
    }
}