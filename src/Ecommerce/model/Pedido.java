package Ecommerce.model;

import Ecommerce.exception.EstoqueInsuficienteException;

import java.time.LocalDate;
import java.util.ArrayList;

public class Pedido {

    private int numero;
    private Cliente cliente;
    private LocalDate dataPedido;
    private LocalDate dataEnvio;
    private LocalDate dataCancelamento;
    private ArrayList<ItemPedido> itens;
    private double frete;
    private String status;

    public Pedido(int numero, Cliente cliente, double frete) {
        this.numero = numero;
        this.cliente = cliente;
        this.frete = frete;
        this.dataPedido = LocalDate.now();
        this.status = "PENDENTE";
        this.itens = new ArrayList<ItemPedido>();
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

        produto.reduzirEstoque(quantidade);
        itens.add(new ItemPedido(produto, quantidade));
    }

    public double calcularTotalProdutos() {
        double total = 0;

        for (ItemPedido item : itens) {
            total += item.getTotalItem();
        }

        return total;
    }

    public double calcularTotalPedido() {
        return calcularTotalProdutos() + frete;
    }

    public void enviarPedido() {
        if (status.equals("CANCELADO")) {
            System.out.println("Pedido cancelado não pode ser enviado.");
            return;
        }

        status = "ENVIADO";
        dataEnvio = LocalDate.now();
    }

    public void cancelarPedido() {
        if (status.equals("ENVIADO")) {
            System.out.println("Pedido enviado não pode ser cancelado.");
            return;
        }

        status = "CANCELADO";
        dataCancelamento = LocalDate.now();
    }

    public void listarDetalhes() {
        System.out.println("Pedido nº " + numero);

        if (cliente != null) {
            System.out.println("Cliente: " + cliente.getNome());
        } else {
            System.out.println("Cliente: Sem cliente");
        }

        System.out.println("Data: " + dataPedido);
        System.out.println("Status: " + status);
        System.out.printf("Frete: R$ %.2f\n", frete);
        System.out.println("Itens:");

        for (ItemPedido item : itens) {
            System.out.println(item);
        }

        System.out.printf("Total do pedido: R$ %.2f\n", calcularTotalPedido());
    }

    public int getNumero() {
        return numero;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public LocalDate getDataPedido() {
        return dataPedido;
    }

    public LocalDate getDataEnvio() {
        return dataEnvio;
    }

    public LocalDate getDataCancelamento() {
        return dataCancelamento;
    }

    public ArrayList<ItemPedido> getItens() {
        return itens;
    }

    public double getFrete() {
        return frete;
    }

    public String getStatus() {
        return status;
    }

    public void setFrete(double frete) {
        if (frete < 0) {
            System.out.println("Frete inválido.");
            return;
        }
        this.frete = frete;
    }

    @Override
    public String toString() {
        return "Pedido nº " + numero +
                " | Data: " + dataPedido +
                " | Status: " + status +
                " | Total: R$" + calcularTotalPedido();
    }
}