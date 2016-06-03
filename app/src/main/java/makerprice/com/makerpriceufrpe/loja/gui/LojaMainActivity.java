package makerprice.com.makerpriceufrpe.loja.gui;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.widget.TextView;

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
}
