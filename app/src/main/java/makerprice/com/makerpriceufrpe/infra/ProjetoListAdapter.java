package makerprice.com.makerpriceufrpe.infra;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.text.Layout;
import android.util.Base64;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import java.util.ArrayList;

import makerprice.com.makerpriceufrpe.R;
import makerprice.com.makerpriceufrpe.projeto.dominio.Projeto;

public class ProjetoListAdapter extends ArrayAdapter<Projeto> {
    private Activity activity;
    private ArrayList<Projeto> listaProjetos;
    private static LayoutInflater inflater = null;
    private Converter converter = Converter.getInstancia();

    public ProjetoListAdapter (Activity activity, int textViewResourceId, ArrayList<Projeto> _listaProjetos){
        super(activity, textViewResourceId, _listaProjetos);
        try {
            this.activity = activity;
            this.listaProjetos = _listaProjetos;
            inflater = (LayoutInflater) activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        } catch (Exception e){

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
        public TextView plataforma_projeto_listagem;
        public ImageView imagemProjeto;

    }

    public View getView(int position, View convertView, ViewGroup parent) {
        View vi = convertView;
        final ViewHolder holder;
        try {
            if (convertView == null) {
                vi = inflater.inflate(R.layout.padrao_lista_projeto, null);
                holder = new ViewHolder();
                holder.nome_projeto_listagem = (TextView) vi.findViewById(R.id.nome_projeto_listagem);
                holder.plataforma_projeto_listagem = (TextView) vi.findViewById(R.id.plataforma_projeto_listagem);
                holder.imagemProjeto = (ImageView) vi.findViewById(R.id.imagemProjeto);
                vi.setTag(holder);

            } else {
                holder = (ViewHolder) vi.getTag();
            }
            String imagemPrincipal = listaProjetos.get(position).getImagens().get(0);
            Bitmap imagem = converter.StringToBitMap(imagemPrincipal);
            holder.nome_projeto_listagem.setText(listaProjetos.get(position).getNome());
            holder.plataforma_projeto_listagem.setText(listaProjetos.get(position).getPlataforma());
            holder.imagemProjeto.setImageBitmap(imagem);

        } catch (Exception e) {

        }
        return vi;
    }

}

