package Ecommerce;

public class Usuario {

    private String login;
    private String senha;

    public Usuario(String login, String senha){
        this.login = login;
        this.senha = senha;
    }

    public void logar(){
        System.out.printf("O usuario %s logou.\n", login);
    }
}
