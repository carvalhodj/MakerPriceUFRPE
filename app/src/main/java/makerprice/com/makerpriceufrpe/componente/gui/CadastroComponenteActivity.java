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
import makerprice.com.makerpriceufrpe.componente.negocio.ComponenteService;
import makerprice.com.makerpriceufrpe.infra.GuiUtil;

public class CadastroComponenteActivity extends AppCompatActivity {

    private ComponenteService componenteService = new ComponenteService(this);
    private GuiUtil guiUtil = GuiUtil.getGuiUtil();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro_componente);
        guiUtil.toastLong(getApplicationContext(), ComponenteEnum.ComponenteTipo.RESISTOR.getNome());
    }

    public void cadastrarComponente (View v) {

        Map propriedades = new HashMap();
        Map propriedades2 = new HashMap();
        Map propriedades3 = new HashMap();

        propriedades.put("tipo", ComponenteEnum.ComponenteTipo.RESISTOR.getNome());
        propriedades.put("resistencia", ComponenteEnum.Resistencia.R330.getNome());

        propriedades2.put("tipo", ComponenteEnum.ComponenteTipo.LED.getNome());
        propriedades2.put("cor", ComponenteEnum.Cor.VERDE.getNome());

        propriedades3.put("tipo", ComponenteEnum.ComponenteTipo.CAPACITOR.getNome());
        propriedades3.put("capacitancia", ComponenteEnum.Capacitancia.UF100.getNome());

        ComponenteEspc compSpec = new ComponenteEspc(propriedades);
        ComponenteEspc compSpec2 = new ComponenteEspc(propriedades2);
        ComponenteEspc compSpec3 = new ComponenteEspc(propriedades3);

        Componente componente = new Componente();
        componente.setComponenteEspc(compSpec);
        componenteService.inserirComponente(componente);

        Componente componente2 = new Componente();
        componente2.setComponenteEspc(compSpec2);
        componenteService.inserirComponente(componente2);

        Componente componente3 = new Componente();
        componente3.setComponenteEspc(compSpec3);
        componenteService.inserirComponente(componente3);

    }

}
