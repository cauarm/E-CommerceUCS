package Ecommerce.repository;

import Ecommerce.model.Administrador;
import Ecommerce.model.Cliente;
import Ecommerce.model.Usuario;

import java.io.*;
import java.util.ArrayList;

public class ArquivoUsuario {

    private String caminho = "usuarios.txt";

    public void salvarUsuarios(ArrayList<Usuario> usuarios) {
        try {
            BufferedWriter bw = new BufferedWriter(new FileWriter(caminho));

            for (Usuario u : usuarios) {
                if (u instanceof Administrador) {
                    bw.write("ADMIN;" + u.getLogin() + ";" + u.getSenha());
                } else if (u instanceof Cliente) {
                    Cliente c = (Cliente) u;
                    bw.write("CLIENTE;" + c.getLogin() + ";" + c.getSenha() + ";" + c.getNome());
                }

                bw.newLine();
            }

            bw.close();
            System.out.println("Usuários salvos com sucesso.");

        } catch (IOException e) {
            System.out.println("Erro ao salvar usuários: " + e.getMessage());
        }
    }

    public ArrayList<Usuario> carregarUsuarios() {
        ArrayList<Usuario> usuarios = new ArrayList<>();

        try {
            BufferedReader br = new BufferedReader(new FileReader(caminho));

            String linha;

            while ((linha = br.readLine()) != null) {
                String[] dados = linha.split(";");

                if (dados[0].equals("ADMIN")) {
                    usuarios.add(new Administrador(dados[1], dados[2]));
                } else if (dados[0].equals("CLIENTE")) {
                    usuarios.add(new Cliente(dados[3], dados[1], dados[2]));
                }
            }

            br.close();

        } catch (IOException e) {
            System.out.println("Erro ao carregar usuários: " + e.getMessage());
        }

        return usuarios;
    }
}