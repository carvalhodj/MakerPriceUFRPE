package makerprice.com.makerpriceufrpe.infra;

import android.app.Activity;
import android.content.Context;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

import makerprice.com.makerpriceufrpe.R;
import makerprice.com.makerpriceufrpe.projeto.dominio.Projeto;


public class ProjetoListAdapter extends ArrayAdapter<Projeto> {
    private Activity activity;
    private ArrayList<Projeto> listaProjetos;
    private static LayoutInflater inflater = null;

    public ProjetoListAdapter (Activity activity, int textViewResourceId, ArrayList<Projeto> _listaProjetos){
        super(activity, textViewResourceId, _listaProjetos);
        try {
            this.activity = activity;
            this.listaProjetos = _listaProjetos;
            inflater = (LayoutInflater) activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        } catch (Exception e) {

        }
    }
    public int getCount() {
        return listaProjetos.size();
    }

    public Projeto getItem(Projeto position) {
        return position;
    }

    public long getItemId(int position) {
        return position;
    }

    public static class ViewHolder {
        public TextView nome_projeto_listagem;
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        View vi = convertView;
        final ViewHolder holder;
        try {
            if (convertView == null) {
                vi = inflater.inflate(R.layout.padrao_lista_projeto, null);
                holder = new ViewHolder();
                holder.nome_projeto_listagem = (TextView) vi.findViewById(R.id.nome_projeto_listagem);
                vi.setTag(holder);

            } else {
                holder = (ViewHolder) vi.getTag();
            }

            holder.nome_projeto_listagem.setText(listaProjetos.get(position).getNome());

        } catch (Exception e) {

        }
        return vi;
    }

}

