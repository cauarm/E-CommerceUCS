package Ecommerce;

public class Menu {

   private BancoFake bf = new BancoFake();
    public Menu(){

        System.out.println("==== SISTEMA E-COMMERCE ====");
        mostraUsuarios();
        mostraFornecedores();
        mostraTransportadoras();
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
}
