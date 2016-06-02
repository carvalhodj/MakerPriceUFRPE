package makerprice.com.makerpriceufrpe.loja.dominio;

import makerprice.com.makerpriceufrpe.usuario.dominio.*;

public class Loja {

    private String cnpj,imagem,bancoJson;
    private Usuario usuario;
    private int x;

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
}
