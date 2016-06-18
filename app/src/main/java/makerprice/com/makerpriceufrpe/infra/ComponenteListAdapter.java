package makerprice.com.makerpriceufrpe.infra;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Map;

import makerprice.com.makerpriceufrpe.R;
import makerprice.com.makerpriceufrpe.componente.dominio.Componente;
import makerprice.com.makerpriceufrpe.projeto.dominio.Projeto;

public class ComponenteListAdapter extends ArrayAdapter<Componente> {
    private Activity activity;
    private ArrayList<Componente> listaComponente;
    private static LayoutInflater inflater = null;
    private Converter converter = Converter.getInstancia();

    public ComponenteListAdapter(Activity activity, int textViewResourceId, ArrayList<Componente> _listaComponentes){
        super(activity, textViewResourceId, _listaComponentes);
        try {
            this.activity = activity;
            this.listaComponente = _listaComponentes;
            inflater = (LayoutInflater) activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        } catch (Exception e){

        }
    }
    public int getCount() {
        return listaComponente.size();
    }

    public Projeto getItem(Projeto position) {
        return position;
    }

    public long getItemId(int position) {
        return position;
    }

    public static class ViewHolder {
        public TextView nome_componente_listagem;

    }

    public View getView(int position, View convertView, ViewGroup parent) {
        View vi = convertView;
        final ViewHolder holder;
        try {
            if (convertView == null) {
                vi = inflater.inflate(R.layout.padrao_lista_componente, null);
                holder = new ViewHolder();
                holder.nome_componente_listagem = (TextView) vi.findViewById(R.id.nome_componente_listagem);
                vi.setTag(holder);

            } else {
                holder = (ViewHolder) vi.getTag();
            }

            Map prop = listaComponente.get(position).getComponenteEspc().getPropriedades();
            Iterator<Map.Entry<String,String>> iterator = prop.entrySet().iterator();
            String dado = "";
            while (iterator.hasNext()) {
                Map.Entry<String,String> entry = (Map.Entry<String,String>) iterator.next();
                dado += entry.getValue() + " ";
            }
            holder.nome_componente_listagem.setText(dado);
            
        } catch (Exception e) {

        }
        return vi;
    }

}

