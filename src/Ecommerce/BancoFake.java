package Ecommerce;

import java.util.ArrayList;

public class BancoFake {

    private ArrayList<Fornecedor> fornecedores = new ArrayList<Fornecedor>();
    private ArrayList<Transportadora> transportadoras = new ArrayList<Transportadora>();
    private ArrayList<Carga> cargas = new ArrayList<Carga>();
    private ArrayList<Usuario> usuarios = new ArrayList<Usuario>();

    public BancoFake() {
        carregarDados();
    }

    private void carregarDados() {
        // Usuários
        usuarios.add(new Administrador("pedro", "123"));
        usuarios.add(new Administrador("caua", "123"));

        // Fornecedores
        Fornecedor fornecedor1 = new Fornecedor("TechMax", "Eletrônicos", "Brasil");
        Fornecedor fornecedor2 = new Fornecedor("CasaBella", "Casa e Decoração", "Brasil");
        Fornecedor fornecedor3 = new Fornecedor("SportLife", "Esportes", "Brasil");

        fornecedores.add(fornecedor1);
        fornecedores.add(fornecedor2);
        fornecedores.add(fornecedor3);

        // Produtos fornecedor 1
        Produto p1 = new Produto("Mouse Gamer", "Eletrônicos", 120.00, 0.30, 1);
        Produto p2 = new Produto("Teclado Mecânico", "Eletrônicos", 250.00, 0.80, 2);
        Produto p3 = new Produto("Monitor LED", "Eletrônicos", 899.90, 3.50, 3);
        Produto p4 = new Produto("Headset", "Eletrônicos", 180.00, 0.40, 4);
        Produto p5 = new Produto("Webcam", "Eletrônicos", 150.00, 0.20, 5);
        Produto p6 = new Produto("Notebook", "Eletrônicos", 3500.00, 2.20, 6);
        Produto p7 = new Produto("Caixa de Som", "Eletrônicos", 99.90, 0.60, 7);

        fornecedor1.incluirProduto(p1);
        fornecedor1.incluirProduto(p2);
        fornecedor1.incluirProduto(p3);
        fornecedor1.incluirProduto(p4);
        fornecedor1.incluirProduto(p5);
        fornecedor1.incluirProduto(p6);
        fornecedor1.incluirProduto(p7);

        // Produtos fornecedor 2
        Produto p8 = new Produto("Sofá", "Móveis", 1200.00, 40.00, 8);
        Produto p9 = new Produto("Mesa de Jantar", "Móveis", 850.00, 30.00, 9);
        Produto p10 = new Produto("Cadeira", "Móveis", 150.00, 5.00, 10);
        Produto p11 = new Produto("Luminária", "Decoração", 90.00, 1.20, 11);
        Produto p12 = new Produto("Tapete", "Decoração", 200.00, 3.00, 12);
        Produto p13 = new Produto("Estante", "Móveis", 600.00, 25.00, 13);
        Produto p14 = new Produto("Cortina", "Decoração", 130.00, 1.50, 14);

        fornecedor2.incluirProduto(p8);
        fornecedor2.incluirProduto(p9);
        fornecedor2.incluirProduto(p10);
        fornecedor2.incluirProduto(p11);
        fornecedor2.incluirProduto(p12);
        fornecedor2.incluirProduto(p13);
        fornecedor2.incluirProduto(p14);

        // Produtos fornecedor 3
        Produto p15 = new Produto("Bola de Futebol", "Esportes", 80.00, 0.45, 15);
        Produto p16 = new Produto("Tênis de Corrida", "Esportes", 300.00, 0.80, 16);
        Produto p17 = new Produto("Camiseta Esportiva", "Esportes", 70.00, 0.20, 17);
        Produto p18 = new Produto("Halteres", "Esportes", 120.00, 10.00, 18);
        Produto p19 = new Produto("Mochila Esportiva", "Esportes", 160.00, 0.90, 19);
        Produto p20 = new Produto("Luva de Academia", "Esportes", 45.00, 0.10, 20);
        Produto p21 = new Produto("Garrafa Térmica", "Esportes", 55.00, 0.30, 21);

        fornecedor3.incluirProduto(p15);
        fornecedor3.incluirProduto(p16);
        fornecedor3.incluirProduto(p17);
        fornecedor3.incluirProduto(p18);
        fornecedor3.incluirProduto(p19);
        fornecedor3.incluirProduto(p20);
        fornecedor3.incluirProduto(p21);

        // Transportadoras
        Transportadora t1 = new Transportadora("Rápido Sul", 1);
        Transportadora t2 = new Transportadora("Entrega Brasil", 2);
        Transportadora t3 = new Transportadora("Log Express", 3);

        transportadoras.add(t1);
        transportadoras.add(t2);
        transportadoras.add(t3);

        // Cargas
        Carga carga1 = new Carga(t1, 1);
        carga1.adicionarProduto(p1);
        carga1.adicionarProduto(p2);
        carga1.adicionarProduto(p3);

        Carga carga2 = new Carga(t2, 2);
        carga2.adicionarProduto(p8);
        carga2.adicionarProduto(p9);
        carga2.adicionarProduto(p10);

        Carga carga3 = new Carga(t3, 3);
        carga3.adicionarProduto(p15);
        carga3.adicionarProduto(p16);
        carga3.adicionarProduto(p18);

        Carga carga4 = new Carga(t1, 4);
        carga4.adicionarProduto(p5);
        carga4.adicionarProduto(p12);
        carga4.adicionarProduto(p21);

        cargas.add(carga1);
        cargas.add(carga2);
        cargas.add(carga3);
        cargas.add(carga4);

        t1.adicionarCarga(carga1);
        t2.adicionarCarga(carga2);
        t3.adicionarCarga(carga3);
        t1.adicionarCarga(carga4);
    }

    public ArrayList<Fornecedor> getFornecedores() {
        return fornecedores;
    }

    public ArrayList<Transportadora> getTransportadoras() {
        return transportadoras;
    }

    public ArrayList<Carga> getCargas() {
        return cargas;
    }

    public ArrayList<Usuario> getUsuarios() {
        return usuarios;
    }
}