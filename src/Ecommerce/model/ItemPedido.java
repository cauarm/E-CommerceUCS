package Ecommerce.model;

public class ItemPedido {

    private Produto produto;
    private int quantidade;
    private double valorUnitario;

    public ItemPedido(Produto produto, int quantidade) {
        this.produto = produto;
        this.quantidade = quantidade;
        this.valorUnitario = produto.getValor();
    }
    public double getTotalItem() {
        return valorUnitario * quantidade;
    }
    public Produto getProduto() {
        return produto;
    }
    public int getQuantidade() {
        return quantidade;
    }

    public double getValorUnitario() {
        return valorUnitario;
    }

    @Override
    public String toString() {
        return "Produto: " + produto.getNome() +
                " | Quantidade: " + quantidade +
                " | Valor unitário: R$" + valorUnitario +
                " | Total: R$" + getTotalItem();
    }
}