    package Ecommerce.model;

    import Ecommerce.exception.EstoqueInsuficienteException;

    public class Produto {

        private String nome;
        private int id;
        private Fornecedor fornecedor;
        private String categoria;
        private double valor;
        private double peso;
        private int estoque;
        private String descricao;

        public Produto(String nome, String categoria, double valor, double peso, int id, String descricao, int estoque){
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

            if(descricao == null || descricao.trim().isEmpty()){
                System.out.println("Descrição inválida.");
                this.descricao = "Sem descrição";
            }else{
                this.descricao = descricao;
            }

            if(estoque<0){
                System.out.println("Valor de estoque inválido.");
                this.estoque = 0;
            }else{
               this.estoque = estoque;
            }
        }

        public boolean verificarEstoque(int qnt){
            return qnt > 0 && this.estoque>= qnt;
        }

        public void reduzirEstoque(int qnt) throws EstoqueInsuficienteException {
            if(verificarEstoque(qnt)){
                this.estoque-= qnt;
            }else{
                throw new EstoqueInsuficienteException("Estoque insuficiente.");
            }
        }

        public void setNome(String nome){
            if(nome==null || nome.trim().isEmpty()){
                System.out.println("Nome inválido.");
                return;
            }
            this.nome = nome;
        }

        public void setCategoria(String categoria){
            if(categoria==null || categoria.trim().isEmpty()){
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

        public int getEstoque() {
            return estoque;
        }

        public void setEstoque(int estoque) {
            if(estoque < 0){
                System.out.println("Estoque inválido.");
                return;
            }
            this.estoque = estoque;
        }

        public String getDescricao() {
            return descricao;
        }

        public void setDescricao(String descricao) {
            if(descricao == null || descricao.trim().isEmpty()){
                System.out.println("Descrição inválida.");
                return;
            }
            this.descricao = descricao;
        }

        @Override
        public String toString() {
            String nomeFornecedor = "Sem fornecedor";
            if(fornecedor != null){
                nomeFornecedor = fornecedor.getNome();
            }
            String disponibilidade = "Disponível";
            if(estoque <= 0){
                disponibilidade = "Indisponível";
            }

            return "ID: " + id +
                    " | Nome: " + nome +
                    " | Categoria: " + categoria +
                    " | Valor: R$" + valor +
                    " | Peso: " + peso + "kg" +
                    " | Estoque: " + estoque +
                    " | Status: " + disponibilidade +
                    " | Descrição: " + descricao +
                    " | Fornecedor: " + nomeFornecedor;
        }
    }
