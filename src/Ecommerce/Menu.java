package Ecommerce;

public class Menu {

   private BancoFake bf = new BancoFake();
    public Menu(){

        System.out.println("==== SISTEMA E-COMMERCE ====");
        mostraUsuarios();
        mostraFornecedores();
        mostraTransportadoras();
        demonstrarCrud();
    }

    public void mostraFornecedores(){

        System.out.println("==== FORNECEDOR ====");
        System.out.println();

        for(Fornecedor f : bf.getFornecedores()){
            System.out.println(f);

            System.out.println("========================");

            for(Produto p : f.getListaProdutos()){
                System.out.println(p);
            }
            System.out.println("========================");
            System.out.println();
        }
    }

    public void mostraTransportadoras(){

        System.out.println("==== TRANSPORTADORAS ====");
        System.out.println();

        for(Transportadora t : bf.getTransportadoras()){
            System.out.println(t);
            System.out.println("==== CARGAS DA TRANSPORTADORA ====");

            for(Carga c : t.getListaCargas()){
                System.out.println(c);

                System.out.println("Produtos da carga:");
                for(Produto p : c.getListaProd()){
                    System.out.println(p);
                }

                System.out.println();
            }

            System.out.println("========================");
            System.out.println();
        }
    }

    public void mostraUsuarios(){

        System.out.println("==== USUÁRIOS ====");
        System.out.println();

        for(Usuario u : bf.getUsuarios()){
            System.out.println(u);
        }

        System.out.println("========================");
        System.out.println();
    }

    public void demonstrarCrud(){

        System.out.println("==== DEMONSTRAÇÃO CRUD ====");
        System.out.println();

        // Pegando um fornecedor já existente
        Fornecedor fornecedor = bf.getFornecedores().get(0);

        System.out.println("Fornecedor escolhido:");
        System.out.println(fornecedor);
        System.out.println();

        // CREATE
        Produto produtoNovo = new Produto("Mousepad Gamer", "Eletrônicos", 59.90, 0.25, 100);
        fornecedor.incluirProduto(produtoNovo);

        System.out.println("Produto incluído:");
        System.out.println(produtoNovo);
        System.out.println();

        // READ
        Produto produtoBuscado = fornecedor.buscaProdutoID(100);

        if(produtoBuscado != null){
            System.out.println("Produto consultado por ID:");
            System.out.println(produtoBuscado);
        }else{
            System.out.println("Produto não encontrado.");
        }

        System.out.println();

        // UPDATE
        if(produtoBuscado != null){
            produtoBuscado.setNome("Mousepad Gamer Grande");
            produtoBuscado.setValor(79.90);

            System.out.println("Produto alterado:");
            System.out.println(produtoBuscado);
        }

        System.out.println();

        // DELETE
        fornecedor.excluirProduto(100);

        System.out.println();
        System.out.println("Lista de produtos após exclusão:");
        fornecedor.listarProdutos();

        System.out.println("===========================");
        System.out.println();
    }
}
