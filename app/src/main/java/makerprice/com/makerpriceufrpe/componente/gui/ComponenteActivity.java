package makerprice.com.makerpriceufrpe.componente.gui;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

import makerprice.com.makerpriceufrpe.R;
import makerprice.com.makerpriceufrpe.componente.dominio.Componente;
import makerprice.com.makerpriceufrpe.componente.dominio.ComponenteLoja;
import makerprice.com.makerpriceufrpe.componente.negocio.Comparador;
import makerprice.com.makerpriceufrpe.componente.negocio.ComponenteService;
import makerprice.com.makerpriceufrpe.infra.ComponenteTelaListAdapter;

public class ComponenteActivity extends AppCompatActivity {
    private ComponenteService componenteService = new ComponenteService(this);
    private Comparador comparador = new Comparador(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_componente);

        Intent intent = getIntent();
        String idcompString = intent.getStringExtra("selected-item");
        long idComponente = Long.valueOf(idcompString);

        TextView nomeComponente = (TextView) findViewById(R.id.nome_componente_tela);
        ListView listaComponenteLoja = (ListView) findViewById(R.id.lista_componenteloja);

        Componente componente = componenteService.getComponente(idComponente);

        nomeComponente.setText(componente.toString());

        ArrayList<ComponenteLoja> listaCompLoja = (ArrayList<ComponenteLoja>) comparador.getPrecosComponente(componente);
        ComponenteTelaListAdapter componenteTelaAdapter = new ComponenteTelaListAdapter(this, 0, listaCompLoja);
        listaComponenteLoja.setAdapter(componenteTelaAdapter);

    }


}
