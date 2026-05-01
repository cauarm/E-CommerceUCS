package Ecommerce;

import java.util.ArrayList;

public class Transportadora {

    private String nome;
    private int id;
    private ArrayList<Carga> listaCargas = new ArrayList<Carga>();

    public Transportadora(String nome, int id){
        if(nome == null || nome.trim().isEmpty()){
            System.out.println("Nome inválido.\n");
        }else{
            this.nome = nome;
        }

        if(id<=0){
            System.out.println("ID inválido.\n");
            this.id = 1;
        }else{
            this.id = id;
        }
    }

    public void adicionarCarga(Carga carga){
        if(carga==null){
            System.out.println("Carga inválida.\n");
            return;
        }

        listaCargas.add(carga);
        carga.setTransportadora(this);
    }

        public void excluirCarga(int id){

        Carga carga = buscaCargaId(id);

        if(carga!=null){
            listaCargas.remove(carga);
            carga.setTransportadora(null);
            System.out.printf("Carga removida com sucesso.\n");
        }else{
            System.out.printf("Carga não encontrada.\n");
        }
    }

    public Carga buscaCargaId(int id){

        for(Carga c : listaCargas){
            if(c.getId() == id){
                return c;
            }
        }

        return null;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getId() {
        return id;
    }

}
