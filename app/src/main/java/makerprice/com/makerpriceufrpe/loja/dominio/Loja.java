package makerprice.com.makerpriceufrpe.loja.dominio;

import makerprice.com.makerpriceufrpe.usuario.dominio.*;

public class Loja {

    private String cnpj,imagem,bancoJson,nome;
    private Usuario usuario;
    private long id;

    public String getCnpj(){
        return this.cnpj;
    }

    public void setCnpj(String cnpj){
        this.cnpj=cnpj;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public String getBancoJson() {
        return bancoJson;
    }

    public void setBancoJson(String bancoJson) {
        this.bancoJson = bancoJson;
    }

    public String getImagem() {
        return imagem;
    }

    public void setImagem(String imagem) {
        this.imagem = imagem;
    }

    public String getNome() { return nome; }

    public void setNome(String nome) { this.nome = nome; }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }
}
