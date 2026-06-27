package Ecommerce.repository;

import Ecommerce.model.Carga;
import Ecommerce.model.Produto;
import Ecommerce.model.Transportadora;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;

public class ArquivoCarga {

    private String caminho = "cargas.txt";

    public void salvarCargas(ArrayList<Carga> cargas) {
        try {
            BufferedWriter bw = new BufferedWriter(new FileWriter(caminho));

            for (Carga c : cargas) {
                bw.write(
                        c.getId() + ";" +
                                c.getTransportadora().getId() + ";" +
                                montarIdsProdutos(c)
                );
                bw.newLine();
            }

            bw.close();
            System.out.println("Cargas salvas com sucesso.");

        } catch (IOException e) {
            System.out.println("Erro ao salvar cargas: " + e.getMessage());
        }
    }

    public ArrayList<Carga> carregarCargas(ArrayList<Transportadora> transportadoras,
                                           HashMap<Integer, Produto> produtosPorId) {

        ArrayList<Carga> cargas = new ArrayList<Carga>();

        try {
            BufferedReader br = new BufferedReader(new FileReader(caminho));

            String linha;

            while ((linha = br.readLine()) != null) {
                String[] dados = linha.split(";");

                int idCarga = Integer.parseInt(dados[0]);
                int idTransportadora = Integer.parseInt(dados[1]);

                Transportadora transportadora = buscarTransportadoraPorId(transportadoras, idTransportadora);

                if (transportadora != null) {
                    Carga carga = new Carga(transportadora, idCarga);

                    if (dados.length > 2 && !dados[2].trim().isEmpty()) {
                        String[] idsProdutos = dados[2].split(",");

                        for (String idTexto : idsProdutos) {
                            int idProduto = Integer.parseInt(idTexto);
                            Produto produto = produtosPorId.get(idProduto);

                            if (produto != null) {
                                carga.adicionarProduto(produto);
                            }
                        }
                    }

                    cargas.add(carga);
                    transportadora.adicionarCarga(carga);
                }
            }

            br.close();

        } catch (IOException e) {
            System.out.println("Erro ao carregar cargas: " + e.getMessage());
        }

        return cargas;
    }

    private String montarIdsProdutos(Carga carga) {
        String ids = "";

        for (Produto p : carga.getListaProd()) {
            if (!ids.isEmpty()) {
                ids += ",";
            }

            ids += p.getId();
        }

        return ids;
    }

    private Transportadora buscarTransportadoraPorId(ArrayList<Transportadora> transportadoras, int id) {
        for (Transportadora t : transportadoras) {
            if (t.getId() == id) {
                return t;
            }
        }

        return null;
    }
}