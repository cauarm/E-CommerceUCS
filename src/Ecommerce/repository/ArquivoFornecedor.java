package Ecommerce.repository;

import Ecommerce.model.Fornecedor;

import java.io.*;
import java.util.ArrayList;

public class ArquivoFornecedor {

    private String caminho = "fornecedores.txt";

    public void salvarFornecedores(ArrayList<Fornecedor> fornecedores) {
        try {
            BufferedWriter bw = new BufferedWriter(new FileWriter(caminho));

            for (Fornecedor f : fornecedores) {
                bw.write(
                        f.getNome() + ";" +
                                f.getCategoria() + ";" +
                                f.getNacionalidade()
                );
                bw.newLine();
            }

            bw.close();
            System.out.println("Fornecedores salvos com sucesso.");

        } catch (IOException e) {
            System.out.println("Erro ao salvar fornecedores: " + e.getMessage());
        }
    }

    public ArrayList<Fornecedor> carregarFornecedores() {
        ArrayList<Fornecedor> fornecedores = new ArrayList<>();

        try {
            BufferedReader br = new BufferedReader(new FileReader(caminho));

            String linha;

            while ((linha = br.readLine()) != null) {
                String[] dados = linha.split(";");

                Fornecedor fornecedor = new Fornecedor(dados[0], dados[1], dados[2]);
                fornecedores.add(fornecedor);
            }

            br.close();

        } catch (IOException e) {
            System.out.println("Erro ao carregar fornecedores: " + e.getMessage());
        }

        return fornecedores;
    }
}