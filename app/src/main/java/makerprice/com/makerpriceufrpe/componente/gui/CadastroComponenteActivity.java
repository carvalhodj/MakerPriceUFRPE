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
    }

    public void cadastrarComponente (View v) {

        Map propriedades = new HashMap();
        Map propriedades2 = new HashMap();
        Map propriedades3 = new HashMap();
        Map propriedades4 = new HashMap();
        Map propriedades5 = new HashMap();
        Map propriedades6 = new HashMap();

        propriedades.put("tipo", ComponenteEnum.ComponenteTipo.RESISTOR.getNome());
        propriedades.put("resistencia", ComponenteEnum.Resistencia.R330.getNome());

        propriedades2.put("tipo", ComponenteEnum.ComponenteTipo.RESISTOR.getNome());
        propriedades2.put("resistencia", ComponenteEnum.Resistencia.R220.getNome());

        propriedades3.put("tipo", ComponenteEnum.ComponenteTipo.LED.getNome());
        propriedades3.put("cor", ComponenteEnum.Cor.VERDE.getNome());

        propriedades4.put("tipo", ComponenteEnum.ComponenteTipo.LED.getNome());
        propriedades4.put("cor", ComponenteEnum.Cor.VERMELHO.getNome());

        propriedades5.put("tipo", ComponenteEnum.ComponenteTipo.CAPACITOR.getNome());
        propriedades5.put("capacitancia", ComponenteEnum.Capacitancia.UF100.getNome());

        propriedades6.put("tipo", ComponenteEnum.ComponenteTipo.CAPACITOR.getNome());
        propriedades6.put("capacitancia", ComponenteEnum.Capacitancia.UF1.getNome());

        ComponenteEspc compSpec = new ComponenteEspc(propriedades);
        ComponenteEspc compSpec2 = new ComponenteEspc(propriedades2);
        ComponenteEspc compSpec3 = new ComponenteEspc(propriedades3);
        ComponenteEspc compSpec4 = new ComponenteEspc(propriedades4);
        ComponenteEspc compSpec5 = new ComponenteEspc(propriedades5);
        ComponenteEspc compSpec6 = new ComponenteEspc(propriedades6);

        Componente componente = new Componente();
        componente.setComponenteEspc(compSpec);
        componenteService.inserirComponente(componente);

        Componente componente2 = new Componente();
        componente2.setComponenteEspc(compSpec2);
        componenteService.inserirComponente(componente2);

        Componente componente3 = new Componente();
        componente3.setComponenteEspc(compSpec3);
        componenteService.inserirComponente(componente3);

        Componente componente4 = new Componente();
        componente4.setComponenteEspc(compSpec4);
        componenteService.inserirComponente(componente4);

        Componente componente5 = new Componente();
        componente5.setComponenteEspc(compSpec5);
        componenteService.inserirComponente(componente5);

        Componente componente6 = new Componente();
        componente6.setComponenteEspc(compSpec6);
        componenteService.inserirComponente(componente6);

    }

}
