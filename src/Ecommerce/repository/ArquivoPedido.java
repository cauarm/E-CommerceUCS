package Ecommerce.repository;

import Ecommerce.model.*;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;

public class ArquivoPedido {

    private String caminhoPedidos = "pedidos.txt";
    private String caminhoItens = "itens_pedidos.txt";

    public void salvarPedidos(ArrayList<Pedido> pedidos) {
        try {
            BufferedWriter bwPedidos = new BufferedWriter(new FileWriter(caminhoPedidos));
            BufferedWriter bwItens = new BufferedWriter(new FileWriter(caminhoItens));

            for (Pedido p : pedidos) {
                bwPedidos.write(
                        p.getNumero() + ";" +
                                p.getCliente().getLogin() + ";" +
                                p.getDataPedido() + ";" +
                                p.getFrete() + ";" +
                                p.getStatus()
                );
                bwPedidos.newLine();

                for (ItemPedido item : p.getItens()) {
                    bwItens.write(
                            p.getNumero() + ";" +
                                    item.getProduto().getId() + ";" +
                                    item.getQuantidade() + ";" +
                                    item.getValorUnitario()
                    );
                    bwItens.newLine();
                }
            }

            bwPedidos.close();
            bwItens.close();

            System.out.println("Pedidos salvos com sucesso.");

        } catch (IOException e) {
            System.out.println("Erro ao salvar pedidos: " + e.getMessage());
        }
    }

    public ArrayList<Pedido> carregarPedidos(HashMap<String, Usuario> usuariosPorLogin,
                                             HashMap<Integer, Produto> produtosPorId) {

        ArrayList<Pedido> pedidos = new ArrayList<Pedido>();
        HashMap<Integer, Pedido> pedidosPorNumero = new HashMap<Integer, Pedido>();

        try {
            BufferedReader br = new BufferedReader(new FileReader(caminhoPedidos));

            String linha;

            while ((linha = br.readLine()) != null) {
                String[] dados = linha.split(";");

                int numero = Integer.parseInt(dados[0]);
                String loginCliente = dados[1];
                double frete = Double.parseDouble(dados[3]);

                Usuario usuario = usuariosPorLogin.get(loginCliente);

                if (usuario instanceof Cliente) {
                    Cliente cliente = (Cliente) usuario;

                    Pedido pedido = new Pedido(numero, cliente, frete);

                    pedidos.add(pedido);
                    pedidosPorNumero.put(numero, pedido);
                    cliente.adicionarPedido(pedido);
                }
            }

            br.close();

        } catch (IOException e) {
            System.out.println("Erro ao carregar pedidos: " + e.getMessage());
        }

        try {
            BufferedReader brItens = new BufferedReader(new FileReader(caminhoItens));

            String linha;

            while ((linha = brItens.readLine()) != null) {
                String[] dados = linha.split(";");

                int numeroPedido = Integer.parseInt(dados[0]);
                int idProduto = Integer.parseInt(dados[1]);
                int quantidade = Integer.parseInt(dados[2]);

                Pedido pedido = pedidosPorNumero.get(numeroPedido);
                Produto produto = produtosPorId.get(idProduto);

                if (pedido != null && produto != null) {
                    pedido.adicionarItemArquivo(produto, quantidade);
                }
            }

            brItens.close();

        } catch (IOException e) {
            System.out.println("Erro ao carregar itens dos pedidos: " + e.getMessage());
        }

        return pedidos;
    }
}