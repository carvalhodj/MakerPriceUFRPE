package makerprice.com.makerpriceufrpe.Componente.gui;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import java.util.HashMap;
import java.util.Map;

import makerprice.com.makerpriceufrpe.Componente.dao.ComponenteDAO;
import makerprice.com.makerpriceufrpe.Componente.dominio.Componente;
import makerprice.com.makerpriceufrpe.Componente.dominio.ComponenteEnum;
import makerprice.com.makerpriceufrpe.Componente.dominio.ComponenteEspc;
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

        propriedades.put("tipo", ComponenteEnum.ComponenteTipo.RESISTOR);
        //propriedades.put("cor", ComponenteEnum.Cor.VERMELHO);
        propriedades.put("resistencia", ComponenteEnum.Resistencia.R330);

        ComponenteEspc compSpec = new ComponenteEspc(propriedades);

        Componente componente = new Componente("11277", compSpec);
            componenteDAO.inserir(componente);

    }

}
