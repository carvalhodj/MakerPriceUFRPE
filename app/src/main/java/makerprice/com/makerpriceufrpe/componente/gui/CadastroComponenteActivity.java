package makerprice.com.makerpriceufrpe.componente.gui;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import java.util.HashMap;
import java.util.Map;

import makerprice.com.makerpriceufrpe.componente.dao.ComponenteDAO;
import makerprice.com.makerpriceufrpe.componente.dominio.Componente;
import makerprice.com.makerpriceufrpe.componente.dominio.ComponenteEnum;
import makerprice.com.makerpriceufrpe.componente.dominio.ComponenteEspc;
import makerprice.com.makerpriceufrpe.R;

public class CadastroComponenteActivity extends AppCompatActivity {

    private ComponenteDAO componenteDAO = new ComponenteDAO(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro_componente);
    }

    public void cadastrarComponente (View v) {


        Map propriedades = new HashMap();
        Map propriedades2 = new HashMap();

        propriedades.put("tipo", ComponenteEnum.ComponenteTipo.RESISTOR);
        propriedades.put("resistencia", ComponenteEnum.Resistencia.R330);

        propriedades2.put("tipo", ComponenteEnum.ComponenteTipo.LED);
        propriedades2.put("cor", ComponenteEnum.Cor.VERDE);

        ComponenteEspc compSpec = new ComponenteEspc(propriedades);
        ComponenteEspc compSpec2 = new ComponenteEspc(propriedades2);

        Componente componente = new Componente("11277", compSpec);
        componenteDAO.inserir(componente);

        Componente componente2 = new Componente("12345", compSpec2);
        componenteDAO.inserir(componente2);

    }

}
