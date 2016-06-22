package makerprice.com.makerpriceufrpe.infra;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Map;

import makerprice.com.makerpriceufrpe.R;
import makerprice.com.makerpriceufrpe.componente.dominio.ComponenteLoja;

public class ComponenteTelaListAdapter extends ArrayAdapter<ComponenteLoja> {
    private Activity activity;
    private ArrayList<ComponenteLoja> listaComponenteLoja;
    private static LayoutInflater inflater = null;
    private Converter converter = Converter.getInstancia();

    public ComponenteTelaListAdapter(Activity activity, int textViewResourceId, ArrayList<ComponenteLoja> _listaComponenteLoja){
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
        public TextView loja_componente_tela;
        public TextView preco_componente_tela;

    }

    public View getView(int position, View convertView, ViewGroup parent) {
        View vi = convertView;
        final ViewHolder holder;
        try {
            if (convertView == null) {
                vi = inflater.inflate(R.layout.padrao_lista_tela_componente, null);
                holder = new ViewHolder();
                holder.loja_componente_tela = (TextView) vi.findViewById(R.id.loja_componente_tela);
                holder.preco_componente_tela = (TextView) vi.findViewById(R.id.preco_componente_tela);
                vi.setTag(holder);

            } else {
                holder = (ViewHolder) vi.getTag();
            }

            ComponenteLoja componenteLoja = listaComponenteLoja.get(position);
            holder.loja_componente_tela.setText(componenteLoja.getLoja().getNome());
            holder.preco_componente_tela.setText("R$ " + String.valueOf(componenteLoja.getPreco()));

        } catch (Exception e) {

        }
        return vi;
    }
}
