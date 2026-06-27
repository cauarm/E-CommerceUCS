package Ecommerce.repository;

import Ecommerce.model.Transportadora;

import java.io.*;
import java.util.ArrayList;

public class ArquivoTransportadora {

    private String caminho = "transportadoras.txt";

    public void salvarTransportadoras(ArrayList<Transportadora> transportadoras) {
        try {
            BufferedWriter bw = new BufferedWriter(new FileWriter(caminho));

            for (Transportadora t : transportadoras) {
                bw.write(t.getId() + ";" + t.getNome());
                bw.newLine();
            }

            bw.close();
            System.out.println("Transportadoras salvas com sucesso.");

        } catch (IOException e) {
            System.out.println("Erro ao salvar transportadoras: " + e.getMessage());
        }
    }

    public ArrayList<Transportadora> carregarTransportadoras() {
        ArrayList<Transportadora> transportadoras = new ArrayList<>();

        try {
            BufferedReader br = new BufferedReader(new FileReader(caminho));

            String linha;

            while ((linha = br.readLine()) != null) {
                String[] dados = linha.split(";");

                int id = Integer.parseInt(dados[0]);
                String nome = dados[1];

                transportadoras.add(new Transportadora(nome, id));
            }

            br.close();

        } catch (IOException e) {
            System.out.println("Erro ao carregar transportadoras: " + e.getMessage());
        }

        return transportadoras;
    }
}