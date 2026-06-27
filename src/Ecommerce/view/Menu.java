package Ecommerce.view;
import Ecommerce.repository.*;
import Ecommerce.model.*;
import Ecommerce.exception.EstoqueInsuficienteException;
import Ecommerce.model.*;
import Ecommerce.repository.BancoFake;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Scanner;

public class Menu {

    private BancoFake bf;
    private Scanner sc;
    private Carrinho carrinho;
    private int proximoNumeroPedido;

    public Menu() {
        bf = new BancoFake();
        sc = new Scanner(System.in);
        carrinho = new Carrinho();
        proximoNumeroPedido = 5; // BancoFake já criou 4 pedidos
        login();
    }

    public void login() {
        System.out.println("==== LOGIN ====");
        System.out.print("Login: ");
        String login = sc.nextLine();

        System.out.print("Senha: ");
        String senha = sc.nextLine();

        Usuario usuario = bf.buscarUsuarioPorLogin(login);

        if (usuario != null && usuario.autenticar(login, senha)) {
            System.out.println("Login realizado com sucesso.\n");

            if (usuario instanceof Administrador) {
                menuAdministrador();
            } else if (usuario instanceof Cliente) {
                menuCliente((Cliente) usuario);
            }
        } else {
            System.out.println("Login ou senha inválidos.");
        }
    }

    public void menuAdministrador() {
        int opcao;

        do {
            System.out.println("==== MENU ADMINISTRADOR ====");
            System.out.println("1 - Listar fornecedores e produtos");
            System.out.println("2 - Listar transportadoras e cargas");
            System.out.println("3 - Listar todos os pedidos");
            System.out.println("4 - Buscar pedido por número");
            System.out.println("5 - Enviar pedido");
            System.out.println("6 - Cancelar pedido");
            System.out.println("7 - Buscar pedidos por data");
            System.out.println("8 - Buscar pedidos por intervalo de datas");
            System.out.println("9 - Salvar dados em arquivos");
            System.out.println("10 - Carregar dados dos arquivos");
            System.out.println("0 - Sair");
            System.out.print("Escolha: ");

            opcao = sc.nextInt();
            sc.nextLine();

            switch (opcao) {
                case 1:
                    mostraFornecedores();
                    break;
                case 2:
                    mostraTransportadoras();
                    break;
                case 3:
                    listarTodosPedidos();
                    break;
                case 4:
                    buscarPedidoAdmin();
                    break;
                case 5:
                    enviarPedido();
                    break;
                case 6:
                    cancelarPedido();
                    break;
                case 7:
                    buscarPedidosPorData();
                    break;
                case 8:
                    buscarPedidosPorIntervalo();
                    break;
                case 9:
                    salvarDadosArquivos();
                    break;

                case 10:
                    carregarDadosArquivos();
                    break;
                case 0:
                    System.out.println("Saindo...");
                    break;
                default:
                    System.out.println("Opção inválida.\n");
            }

        } while (opcao != 0);
    }

    public void menuCliente(Cliente cliente) {
        int opcao;

        do {
            System.out.println("==== MENU CLIENTE ====");
            System.out.println("1 - Consultar produtos");
            System.out.println("2 - Adicionar produto ao carrinho");
            System.out.println("3 - Ver carrinho");
            System.out.println("4 - Finalizar pedido");
            System.out.println("5 - Ver meus pedidos");
            System.out.println("6 - Pesquisar produto por palavra");
            System.out.println("0 - Sair");
            System.out.print("Escolha: ");

            opcao = sc.nextInt();
            sc.nextLine();

            switch (opcao) {
                case 1:
                    consultarProdutos();
                    break;
                case 2:
                    adicionarProdutoCarrinho();
                    break;
                case 3:
                    carrinho.listarItens();
                    System.out.printf("Total: R$ %.2f\n\n", carrinho.calcularTotal());
                    break;
                case 4:
                    finalizarPedido(cliente);
                    break;
                case 5:
                    listarPedidosCliente(cliente);
                    break;
                case 6:
                    pesquisarProdutoPorPalavra();
                    break;
                case 0:
                    System.out.println("Saindo...");
                    break;
                default:
                    System.out.println("Opção inválida.\n");
            }

        } while (opcao != 0);
    }

    public void consultarProdutos() {
        System.out.println("==== PRODUTOS ====");

        for (Produto p : bf.getProdutosPorId().values()) {
            System.out.println(p);
        }

        System.out.println();
    }

    public void adicionarProdutoCarrinho() {
        String continuar;

        do {
            consultarProdutos();

            System.out.print("Digite o ID do produto: ");
            int id = sc.nextInt();

            System.out.print("Digite a quantidade: ");
            int quantidade = sc.nextInt();
            sc.nextLine();

            Produto produto = bf.buscarProdutoPorId(id);

            try {
                carrinho.adicionarItem(produto, quantidade);
            } catch (EstoqueInsuficienteException e) {
                System.out.println(e.getMessage());
            }

            System.out.print("Deseja adicionar outro produto? (s/n): ");
            continuar = sc.nextLine();

            System.out.println();

        } while (continuar.equalsIgnoreCase("s"));
    }

    public void finalizarPedido(Cliente cliente) {
        try {
            Pedido pedido = carrinho.finalizarPedido(proximoNumeroPedido, cliente, 25.0);

            if (pedido != null) {
                bf.adicionarPedido(pedido);
                proximoNumeroPedido++;
                pedido.listarDetalhes();
            }

        } catch (EstoqueInsuficienteException e) {
            System.out.println(e.getMessage());
        }

        System.out.println();
    }

    public void listarPedidosCliente(Cliente cliente) {
        System.out.println("==== MEUS PEDIDOS ====");

        for (Pedido p : cliente.getListaPedidos()) {
            p.listarDetalhes();
            System.out.println();
        }
    }

    public void listarTodosPedidos() {
        System.out.println("==== TODOS OS PEDIDOS ====");

        for (Pedido p : bf.getPedidos()) {
            p.listarDetalhes();
            System.out.println();
        }
    }

    public void buscarPedidoAdmin() {
        System.out.print("Digite o número do pedido: ");
        int numero = sc.nextInt();
        sc.nextLine();

        Pedido pedido = bf.buscarPedidoPorNumero(numero);

        if (pedido != null) {
            pedido.listarDetalhes();
        } else {
            System.out.println("Pedido não encontrado.");
        }

        System.out.println();
    }

    public void enviarPedido() {
        System.out.print("Digite o número do pedido: ");
        int numero = sc.nextInt();
        sc.nextLine();

        Pedido pedido = bf.buscarPedidoPorNumero(numero);

        if (pedido != null) {
            pedido.enviarPedido();
            System.out.println("Pedido enviado.");
        } else {
            System.out.println("Pedido não encontrado.");
        }

        System.out.println();
    }

    public void cancelarPedido() {
        System.out.print("Digite o número do pedido: ");
        int numero = sc.nextInt();
        sc.nextLine();

        Pedido pedido = bf.buscarPedidoPorNumero(numero);

        if (pedido != null) {
            pedido.cancelarPedido();
            System.out.println("Pedido cancelado.");
        } else {
            System.out.println("Pedido não encontrado.");
        }

        System.out.println();
    }

    public void mostraFornecedores() {
        System.out.println("==== FORNECEDORES ====");
        System.out.println();

        for (Fornecedor f : bf.getFornecedores()) {
            System.out.println(f);
            System.out.println("Produtos:");

            for (Produto p : f.getListaProdutos()) {
                System.out.println(p);
            }

            System.out.println("========================");
            System.out.println();
        }
    }

    public void buscarPedidosPorData(){
        System.out.print("Digite a data do pedido (AAAA-MM-DD): ");
        String textoData = sc.nextLine();

        LocalDate data = LocalDate.parse(textoData);

        ArrayList<Pedido> encontrados = bf.buscarPedidosPorData(data);

        if(encontrados.isEmpty()){
            System.out.println("Nenhum pedido encontrado.");
        }else{
            for(Pedido p : encontrados){
                p.listarDetalhes();
                System.out.println();
            }
        }
    }



    public void buscarPedidosPorIntervalo(){
        System.out.print("Data inicial (AAAA-MM-DD): ");
        String dataInicialTexto = sc.nextLine();

        System.out.print("Data final (AAAA-MM-DD): ");
        String dataFinalTexto = sc.nextLine();

        LocalDate inicio = LocalDate.parse(dataInicialTexto);
        LocalDate fim = LocalDate.parse(dataFinalTexto);

        ArrayList<Pedido> encontrados = bf.buscarPedidosPorIntervalo(inicio, fim);

        if(encontrados.isEmpty()){
            System.out.println("Nenhum pedido encontrado.");
        }else{
            for(Pedido p : encontrados){
                p.listarDetalhes();
                System.out.println();
            }
        }
    }

    public void salvarDadosArquivos(){
        ArquivoProduto arqProduto = new ArquivoProduto();
        ArquivoUsuario arqUsuario = new ArquivoUsuario();
        ArquivoFornecedor arqFornecedor = new ArquivoFornecedor();
        ArquivoTransportadora arqTransportadora = new ArquivoTransportadora();
        ArquivoPedido arqPedido = new ArquivoPedido();

        ArrayList<Produto> todosProdutos = new ArrayList<Produto>();

        for(Fornecedor f : bf.getFornecedores()){
            todosProdutos.addAll(f.getListaProdutos());
        }

        arqProduto.salvarProdutos(todosProdutos);
        arqUsuario.salvarUsuarios(bf.getUsuarios());
        arqFornecedor.salvarFornecedores(bf.getFornecedores());
        arqTransportadora.salvarTransportadoras(bf.getTransportadoras());
        arqPedido.salvarPedidos(bf.getPedidos());
    }

    public void carregarDadosArquivos(){
        ArquivoProduto arqProduto = new ArquivoProduto();
        ArquivoUsuario arqUsuario = new ArquivoUsuario();
        ArquivoFornecedor arqFornecedor = new ArquivoFornecedor();
        ArquivoTransportadora arqTransportadora = new ArquivoTransportadora();
        ArquivoPedido arqPedido = new ArquivoPedido();
        ArrayList<Fornecedor> fornecedoresCarregados = arqFornecedor.carregarFornecedores();

        System.out.println("==== PRODUTOS CARREGADOS ====");
        for(Produto p : arqProduto.carregarProdutos(fornecedoresCarregados)){
            System.out.println(p);
        }

        System.out.println("==== USUÁRIOS CARREGADOS ====");
        for(Usuario u : arqUsuario.carregarUsuarios()){
            System.out.println(u);
        }

        System.out.println("==== FORNECEDORES CARREGADOS ====");
        for(Fornecedor f : arqFornecedor.carregarFornecedores()){
            System.out.println(f);
        }

        System.out.println("==== TRANSPORTADORAS CARREGADAS ====");
        for(Transportadora t : arqTransportadora.carregarTransportadoras()){
            System.out.println(t);
        }

        System.out.println("==== PEDIDOS CARREGADOS ====");
        for(Pedido p : arqPedido.carregarPedidos(bf.getUsuariosPorLogin(), bf.getProdutosPorId())){
            p.listarDetalhes();
            System.out.println();
        }
    }

    public void pesquisarProdutoPorPalavra(){
        System.out.print("Digite uma palavra para pesquisar: ");
        String palavra = sc.nextLine();

        ArrayList<Produto> encontrados = bf.buscarProdutosPorPalavra(palavra);

        if(encontrados.isEmpty()){
            System.out.println("Nenhum produto encontrado.");
        }else{
            for(Produto p : encontrados){
                System.out.println(p);
            }
        }

        System.out.println();
    }

    public void mostraTransportadoras() {
        System.out.println("==== TRANSPORTADORAS ====");
        System.out.println();

        for (Transportadora t : bf.getTransportadoras()) {
            System.out.println(t);
            System.out.println("Cargas:");

            for (Carga c : t.getListaCargas()) {
                System.out.println(c);
                System.out.println("Produtos da carga:");

                for (Produto p : c.getListaProd()) {
                    System.out.println(p);
                }

                System.out.println();
            }

            System.out.println("========================");
            System.out.println();
        }
    }
}