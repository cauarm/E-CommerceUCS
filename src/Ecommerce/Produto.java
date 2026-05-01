    package Ecommerce;
    public class Produto {

        private String nome;
        private int id;
        private Fornecedor fornecedor;
        private String categoria;
        private double valor;
        private double peso;

        public Produto(String nome, String categoria, double valor, double peso, int id){
            if(nome == null || nome.trim().isEmpty()){
                System.out.println("Nome inválido.");
                this.nome = "Sem nome";
            }else{
                this.nome = nome;
            }

            if(categoria == null || categoria.trim().isEmpty()){
                System.out.println("Categoria inválida.");
                this.categoria = "Sem categoria";
            }else{
                this.categoria = categoria;
            }

            if(valor<0 ){
                System.out.println("Valor inválido.");
                this.valor = 0;
            }else{
                this.valor = valor;
            }

            if(peso<0){
                System.out.println("Peso inválido.");
                this.peso = 0;
            }else{
                this.peso = peso;
            }

            if(id<=0){
                System.out.println("ID inválido.");
                this.id = 1;
            }else{
                this.id = id;
            }
        }

        public void setNome(String nome){
            if(nome.trim().isEmpty() || nome == null){
                System.out.println("Nome inválido.");
                return;
            }
            this.nome = nome;
        }

        public void setCategoria(String categoria){
            if(categoria.trim().isEmpty() || categoria == null){
                System.out.println("Categoria inválida.");
                return;
            }
            this.categoria = categoria;
        }

        public void setValor(double valor){
            if(valor<0){
                System.out.println("Valor negativo não permitido.\n");
                return;
            }else{
                this.valor = valor;
            }
        }

        public void setPeso(double peso){
            if(peso<0){
                System.out.println("Peso negativo não permitido.\n");
                return;
            }else{
                this.peso = peso;
            }
        }

        public String getNome() {
            return nome;
        }

        public String getCategoria() {
            return categoria;
        }

        public int getId() {
            return id;
        }

        public double getValor() {
            return valor;
        }

        public double getPeso() {
            return peso;
        }

        public Fornecedor getFornecedor() {
            return fornecedor;
        }

        public void setFornecedor(Fornecedor fornecedor) {
            this.fornecedor = fornecedor;
        }
    }
