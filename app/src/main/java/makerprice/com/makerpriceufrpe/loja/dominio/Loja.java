package makerprice.com.makerpriceufrpe.loja.dominio;

import makerprice.com.makerpriceufrpe.usuario.dominio.*;

public class Loja {

    String cnpj,imagem,bancoJson;
    Usuario pessoaJuridica;

    public String getCnpj(){
        return this.cnpj;
    }

    public void setCnpj(String cnpj){
        this.cnpj=cnpj;
    }

    public Usuario getPessoaJuridica() {
        return pessoaJuridica;
    }

    public void setPessoaJuridica(Usuario pessoaJuridica) {
        this.pessoaJuridica = pessoaJuridica;
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
