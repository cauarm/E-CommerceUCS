package Ecommerce.repository;

import Ecommerce.model.Fornecedor;
import Ecommerce.model.Produto;

import java.io.*;
import java.util.ArrayList;

public class ArquivoProduto {

    private String caminho = "produtos.txt";

    public void salvarProdutos(ArrayList<Produto> produtos) {
        try {
            BufferedWriter bw = new BufferedWriter(new FileWriter(caminho));

            for (Produto p : produtos) {

                String nomeFornecedor = "Sem fornecedor";

                if(p.getFornecedor() != null){
                    nomeFornecedor = p.getFornecedor().getNome();
                }

                bw.write(
                        p.getId() + ";" +
                                p.getNome() + ";" +
                                p.getCategoria() + ";" +
                                p.getValor() + ";" +
                                p.getPeso() + ";" +
                                p.getDescricao() + ";" +
                                p.getEstoque() + ";" +
                                nomeFornecedor
                );

                bw.newLine();
            }

            bw.close();
            System.out.println("Produtos salvos com sucesso.");

        } catch (IOException e) {
            System.out.println("Erro ao salvar produtos: " + e.getMessage());
        }
    }

    public ArrayList<Produto> carregarProdutos(ArrayList<Fornecedor> fornecedores) {
        ArrayList<Produto> produtos = new ArrayList<Produto>();

        try {
            BufferedReader br = new BufferedReader(new FileReader(caminho));

            String linha;

            while ((linha = br.readLine()) != null) {
                String[] dados = linha.split(";");

                int id = Integer.parseInt(dados[0]);
                String nome = dados[1];
                String categoria = dados[2];
                double valor = Double.parseDouble(dados[3]);
                double peso = Double.parseDouble(dados[4]);
                String descricao = dados[5];
                int estoque = Integer.parseInt(dados[6]);
                String nomeFornecedor = dados[7];

                Produto produto = new Produto(nome, categoria, valor, peso, id, descricao, estoque);

                Fornecedor fornecedor = buscarFornecedorPorNome(fornecedores, nomeFornecedor);

                if(fornecedor != null){
                    fornecedor.incluirProduto(produto);
                }

                produtos.add(produto);
            }

            br.close();

        } catch (IOException e) {
            System.out.println("Erro ao carregar produtos: " + e.getMessage());
        }

        return produtos;
    }

    private Fornecedor buscarFornecedorPorNome(ArrayList<Fornecedor> fornecedores, String nome) {
        for(Fornecedor f : fornecedores){
            if(f.getNome().equalsIgnoreCase(nome)){
                return f;
            }
        }

        return null;
    }
}