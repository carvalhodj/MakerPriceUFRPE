package makerprice.com.makerpriceufrpe.loja.gui;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import makerprice.com.makerpriceufrpe.componente.gui.CadastroComponenteActivity;
import makerprice.com.makerpriceufrpe.R;
import makerprice.com.makerpriceufrpe.infra.Sessao;
import makerprice.com.makerpriceufrpe.loja.dominio.Loja;

public class LojaMainActivity extends AppCompatActivity {
    private Sessao sessao = Sessao.getInstancia();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loja_main);

        TextView tv = (TextView) findViewById(R.id.nome_loja);
        Loja loja = sessao.getLoja();
        String nome = loja.getNome();
        tv.setText(nome);

    }

    public void telaComponente(View v) {
        Intent intent = new Intent(this, CadastroComponenteActivity.class);
        startActivity(intent);
    }

}
