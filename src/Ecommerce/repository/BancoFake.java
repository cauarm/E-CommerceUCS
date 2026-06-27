package Ecommerce.repository;
import Ecommerce.exception.EstoqueInsuficienteException;
import Ecommerce.model.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;

public class BancoFake {

    private ArrayList<Fornecedor> fornecedores = new ArrayList<Fornecedor>();
    private ArrayList<Transportadora> transportadoras = new ArrayList<Transportadora>();
    private ArrayList<Carga> cargas = new ArrayList<Carga>();
    private ArrayList<Usuario> usuarios = new ArrayList<Usuario>();
    private ArrayList<Pedido> pedidos = new ArrayList<Pedido>();

    private HashMap<Integer, Produto> produtosPorId = new HashMap<Integer, Produto>();
    private HashMap<String, Usuario> usuariosPorLogin = new HashMap<String, Usuario>();
    private HashMap<Integer, Pedido> pedidosPorNumero = new HashMap<Integer, Pedido>();

    public BancoFake() {
        if(!carregarArquivos()){
            carregarDados();
            salvarArquivos();
        }
    }

    private void carregarDados() {
        Administrador admin = new Administrador("admin", "clebermachado");
        Cliente cliente1 = new Cliente("Cauã", "caua", "43673246");
        Cliente cliente2 = new Cliente("Pedro", "pedro", "int1c35");

        adicionarUsuario(admin);
        adicionarUsuario(cliente1);
        adicionarUsuario(cliente2);

        Fornecedor fornecedor1 = new Fornecedor("TechMax", "Eletrônicos", "Brasil");
        Fornecedor fornecedor2 = new Fornecedor("CasaBella", "Casa e Decoração", "Brasil");
        Fornecedor fornecedor3 = new Fornecedor("SportLife", "Esportes", "Brasil");

        fornecedores.add(fornecedor1);
        fornecedores.add(fornecedor2);
        fornecedores.add(fornecedor3);

        Produto p1 = new Produto("Mouse Gamer", "Eletrônicos", 120.00, 0.30, 1, "Mouse gamer com iluminação RGB", 20);
        Produto p2 = new Produto("Teclado Mecânico", "Eletrônicos", 250.00, 0.80, 2, "Teclado mecânico ABNT2", 15);
        Produto p3 = new Produto("Monitor LED", "Eletrônicos", 899.90, 3.50, 3, "Monitor LED Full HD 24 polegadas", 10);
        Produto p4 = new Produto("Headset", "Eletrônicos", 180.00, 0.40, 4, "Headset gamer com microfone", 18);
        Produto p5 = new Produto("Webcam", "Eletrônicos", 150.00, 0.20, 5, "Webcam HD para chamadas", 12);
        Produto p6 = new Produto("Notebook", "Eletrônicos", 3500.00, 2.20, 6, "Notebook para estudos e trabalho", 6);
        Produto p7 = new Produto("Caixa de Som", "Eletrônicos", 99.90, 0.60, 7, "Caixa de som bluetooth", 25);

        fornecedor1.incluirProduto(p1);
        fornecedor1.incluirProduto(p2);
        fornecedor1.incluirProduto(p3);
        fornecedor1.incluirProduto(p4);
        fornecedor1.incluirProduto(p5);
        fornecedor1.incluirProduto(p6);
        fornecedor1.incluirProduto(p7);

        Produto p8 = new Produto("Sofá", "Móveis", 1200.00, 40.00, 8, "Sofá de três lugares", 4);
        Produto p9 = new Produto("Mesa de Jantar", "Móveis", 850.00, 30.00, 9, "Mesa de jantar em madeira", 5);
        Produto p10 = new Produto("Cadeira", "Móveis", 150.00, 5.00, 10, "Cadeira acolchoada", 20);
        Produto p11 = new Produto("Luminária", "Decoração", 90.00, 1.20, 11, "Luminária de mesa", 16);
        Produto p12 = new Produto("Tapete", "Decoração", 200.00, 3.00, 12, "Tapete para sala", 8);
        Produto p13 = new Produto("Estante", "Móveis", 600.00, 25.00, 13, "Estante para livros", 7);
        Produto p14 = new Produto("Cortina", "Decoração", 130.00, 1.50, 14, "Cortina blackout", 14);

        fornecedor2.incluirProduto(p8);
        fornecedor2.incluirProduto(p9);
        fornecedor2.incluirProduto(p10);
        fornecedor2.incluirProduto(p11);
        fornecedor2.incluirProduto(p12);
        fornecedor2.incluirProduto(p13);
        fornecedor2.incluirProduto(p14);

        Produto p15 = new Produto("Bola de Futebol", "Esportes", 80.00, 0.45, 15, "Bola oficial de futebol", 30);
        Produto p16 = new Produto("Tênis de Corrida", "Esportes", 300.00, 0.80, 16, "Tênis leve para corrida", 11);
        Produto p17 = new Produto("Camiseta Esportiva", "Esportes", 70.00, 0.20, 17, "Camiseta dry fit", 40);
        Produto p18 = new Produto("Halteres", "Esportes", 120.00, 10.00, 18, "Par de halteres de 5kg", 9);
        Produto p19 = new Produto("Mochila Esportiva", "Esportes", 160.00, 0.90, 19, "Mochila para academia", 13);
        Produto p20 = new Produto("Luva de Academia", "Esportes", 45.00, 0.10, 20, "Luva para musculação", 22);
        Produto p21 = new Produto("Garrafa Térmica", "Esportes", 55.00, 0.30, 21, "Garrafa térmica 750ml", 18);

        fornecedor3.incluirProduto(p15);
        fornecedor3.incluirProduto(p16);
        fornecedor3.incluirProduto(p17);
        fornecedor3.incluirProduto(p18);
        fornecedor3.incluirProduto(p19);
        fornecedor3.incluirProduto(p20);
        fornecedor3.incluirProduto(p21);

        adicionarProdutoMapa(p1);
        adicionarProdutoMapa(p2);
        adicionarProdutoMapa(p3);
        adicionarProdutoMapa(p4);
        adicionarProdutoMapa(p5);
        adicionarProdutoMapa(p6);
        adicionarProdutoMapa(p7);
        adicionarProdutoMapa(p8);
        adicionarProdutoMapa(p9);
        adicionarProdutoMapa(p10);
        adicionarProdutoMapa(p11);
        adicionarProdutoMapa(p12);
        adicionarProdutoMapa(p13);
        adicionarProdutoMapa(p14);
        adicionarProdutoMapa(p15);
        adicionarProdutoMapa(p16);
        adicionarProdutoMapa(p17);
        adicionarProdutoMapa(p18);
        adicionarProdutoMapa(p19);
        adicionarProdutoMapa(p20);
        adicionarProdutoMapa(p21);

        Transportadora t1 = new Transportadora("Rápido Sul", 1);
        Transportadora t2 = new Transportadora("Entrega Brasil", 2);
        Transportadora t3 = new Transportadora("Log Express", 3);

        transportadoras.add(t1);
        transportadoras.add(t2);
        transportadoras.add(t3);

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

        try {
            Pedido pedido1 = new Pedido(1, cliente1, 25.0);
            pedido1.adicionarItem(p1, 2);
            pedido1.adicionarItem(p2, 1);
            adicionarPedido(pedido1);
            cliente1.adicionarPedido(pedido1);

            Pedido pedido2 = new Pedido(2, cliente1, 40.0);
            pedido2.adicionarItem(p15, 1);
            pedido2.adicionarItem(p21, 2);
            adicionarPedido(pedido2);
            cliente1.adicionarPedido(pedido2);

            Pedido pedido3 = new Pedido(3, cliente2, 60.0);
            pedido3.adicionarItem(p8, 1);
            pedido3.adicionarItem(p11, 2);
            adicionarPedido(pedido3);
            cliente2.adicionarPedido(pedido3);

            Pedido pedido4 = new Pedido(4, cliente2, 30.0);
            pedido4.adicionarItem(p16, 1);
            pedido4.adicionarItem(p20, 3);
            adicionarPedido(pedido4);
            cliente2.adicionarPedido(pedido4);

        } catch (EstoqueInsuficienteException e) {
            System.out.println(e.getMessage());
        }
    }

    private boolean carregarArquivos(){
        ArquivoUsuario arqUsuario = new ArquivoUsuario();
        ArquivoProduto arqProduto = new ArquivoProduto();
        ArquivoFornecedor arqFornecedor = new ArquivoFornecedor();
        ArquivoTransportadora arqTransportadora = new ArquivoTransportadora();
        ArquivoPedido arqPedido = new ArquivoPedido();
        ArquivoCarga arqCarga = new ArquivoCarga();

        usuarios = arqUsuario.carregarUsuarios();
        fornecedores = arqFornecedor.carregarFornecedores();
        transportadoras = arqTransportadora.carregarTransportadoras();

        ArrayList<Produto> produtos = arqProduto.carregarProdutos(fornecedores);

        if(usuarios.isEmpty() || produtos.isEmpty()){
            return false;
        }

        for(Usuario u : usuarios){
            usuariosPorLogin.put(u.getLogin(), u);
        }

        for(Produto p : produtos){
            produtosPorId.put(p.getId(), p);
        }

        cargas = arqCarga.carregarCargas(transportadoras, produtosPorId);

        pedidos = arqPedido.carregarPedidos(usuariosPorLogin, produtosPorId);

        for(Pedido p : pedidos){
            pedidosPorNumero.put(p.getNumero(), p);
        }

        return true;
    }

    public void salvarArquivos(){
        ArquivoUsuario arqUsuario = new ArquivoUsuario();
        ArquivoProduto arqProduto = new ArquivoProduto();
        ArquivoFornecedor arqFornecedor = new ArquivoFornecedor();
        ArquivoTransportadora arqTransportadora = new ArquivoTransportadora();
        ArquivoPedido arqPedido = new ArquivoPedido();
        ArquivoCarga arqCarga = new ArquivoCarga();

        ArrayList<Produto> todosProdutos = new ArrayList<Produto>(produtosPorId.values());

        arqUsuario.salvarUsuarios(usuarios);
        arqFornecedor.salvarFornecedores(fornecedores);
        arqProduto.salvarProdutos(todosProdutos);
        arqTransportadora.salvarTransportadoras(transportadoras);
        arqPedido.salvarPedidos(pedidos);
        arqCarga.salvarCargas(cargas);
    }

    public void adicionarUsuario(Usuario usuario) {
        if (usuario == null) {
            return;
        }

        usuarios.add(usuario);
        usuariosPorLogin.put(usuario.getLogin(), usuario);
    }

    public void adicionarProdutoMapa(Produto produto) {
        if (produto == null) {
            return;
        }

        produtosPorId.put(produto.getId(), produto);
    }

    public void adicionarPedido(Pedido pedido) {
        if (pedido == null) {
            return;
        }

        pedidos.add(pedido);
        pedidosPorNumero.put(pedido.getNumero(), pedido);
    }

    public ArrayList<Produto> buscarProdutosPorPalavra(String palavra){
        ArrayList<Produto> encontrados = new ArrayList<Produto>();

        if(palavra == null || palavra.trim().isEmpty()){
            return encontrados;
        }

        for(Produto p : produtosPorId.values()){
            if(p.getNome().toLowerCase().contains(palavra.toLowerCase()) ||
                    p.getDescricao().toLowerCase().contains(palavra.toLowerCase())){
                encontrados.add(p);
            }
        }

        return encontrados;
    }

    public ArrayList<Pedido> buscarPedidosPorData(LocalDate data){
        ArrayList<Pedido> encontrados = new ArrayList<Pedido>();

        for(Pedido p : pedidos){
            if(p.getDataPedido().equals(data)){
                encontrados.add(p);
            }
        }

        return encontrados;
    }

    public ArrayList<Pedido> buscarPedidosPorIntervalo(LocalDate inicio, LocalDate fim){
        ArrayList<Pedido> encontrados = new ArrayList<Pedido>();

        for(Pedido p : pedidos){
            LocalDate data = p.getDataPedido();

            if((data.isEqual(inicio) || data.isAfter(inicio)) &&
                    (data.isEqual(fim) || data.isBefore(fim))){
                encontrados.add(p);
            }
        }

        return encontrados;
    }

    public Produto buscarProdutoPorId(int id) {
        return produtosPorId.get(id);
    }

    public Usuario buscarUsuarioPorLogin(String login) {
        return usuariosPorLogin.get(login);
    }

    public Pedido buscarPedidoPorNumero(int numero) {
        return pedidosPorNumero.get(numero);
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

    public ArrayList<Pedido> getPedidos() {
        return pedidos;
    }

    public HashMap<Integer, Produto> getProdutosPorId() {
        return produtosPorId;
    }

    public HashMap<String, Usuario> getUsuariosPorLogin() {
        return usuariosPorLogin;
    }

    public HashMap<Integer, Pedido> getPedidosPorNumero() {
        return pedidosPorNumero;
    }
}