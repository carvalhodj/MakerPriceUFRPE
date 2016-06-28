package makerprice.com.makerpriceufrpe.infra;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Map;

import makerprice.com.makerpriceufrpe.R;
import makerprice.com.makerpriceufrpe.componente.dominio.ComponenteLoja;

public class ComponenteLojaListAdapter extends ArrayAdapter<ComponenteLoja> {
    private Activity activity;
    private ArrayList<ComponenteLoja> listaComponenteLoja;
    private static LayoutInflater inflater = null;
    private Converter converter = Converter.getInstancia();

    public ComponenteLojaListAdapter(Activity activity, int textViewResourceId, ArrayList<ComponenteLoja> _listaComponenteLoja){
        super(activity, textViewResourceId, _listaComponenteLoja);
        try {
            this.activity = activity;
            this.listaComponenteLoja = _listaComponenteLoja;
            inflater = (LayoutInflater) activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        } catch (Exception e){

        }
    }
    public int getCount() {
        return listaComponenteLoja.size();
    }

    public ComponenteLoja getItem(ComponenteLoja position) {
        return position;
    }

    public long getItemId(int position) {
        return position;
    }

    public static class ViewHolder {
        public TextView nome_componente_listagem;
        public TextView nome_loja_listagem;
        public TextView preco_listagem;

    }

    public View getView(int position, View convertView, ViewGroup parent) {
        View vi = convertView;
        DecimalFormat df = new DecimalFormat("0.00");//
        DecimalFormatSymbols dfSymbols = new DecimalFormatSymbols();//
        dfSymbols.setDecimalSeparator(',');//
        df.setDecimalFormatSymbols(dfSymbols);//
        final ViewHolder holder;
        try {
            if (convertView == null) {
                vi = inflater.inflate(R.layout.padrao_componente_loja_listagem, null);
                holder = new ViewHolder();
                holder.nome_componente_listagem = (TextView) vi.findViewById(R.id.nome_componente_loja_adapter);
                holder.nome_loja_listagem = (TextView) vi.findViewById(R.id.loja_componente_loja_adapter);
                holder.preco_listagem = (TextView) vi.findViewById(R.id.preco_componente_loja_adapter);
                vi.setTag(holder);

            } else {
                holder = (ViewHolder) vi.getTag();
            }

            ComponenteLoja componenteLoja = listaComponenteLoja.get(position);
            Map prop = componenteLoja.getComponente().getComponenteEspc().getPropriedades();
            Iterator<Map.Entry<String,String>> iterator = prop.entrySet().iterator();
            String nome = "";
            while (iterator.hasNext()) {
                Map.Entry<String,String> entry = (Map.Entry<String,String>) iterator.next();
                nome += entry.getValue() + " ";
            }
            holder.nome_componente_listagem.setText(nome);
            holder.nome_loja_listagem.setText(componenteLoja.getLoja().getNome());
            holder.preco_listagem.setText("R$ " + String.valueOf(df.format(componenteLoja.getPreco())));//

        } catch (Exception e) {

        }
        return vi;
    }
}
