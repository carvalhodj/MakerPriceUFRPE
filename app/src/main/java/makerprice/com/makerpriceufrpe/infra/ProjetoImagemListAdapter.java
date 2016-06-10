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

import makerprice.com.makerpriceufrpe.R;
import makerprice.com.makerpriceufrpe.projeto.dominio.Projeto;

public class ProjetoImagemListAdapter extends ArrayAdapter<String> {
    private Activity activity;
    private ArrayList<String> listaImagensProjeto;
    private static LayoutInflater inflater = null;
    private Converter converter = Converter.getInstancia();

    public ProjetoImagemListAdapter(Activity activity, int textViewResourceId, ArrayList<String> _listaImagensProjeto){
        super(activity, textViewResourceId, _listaImagensProjeto);
        try {
            this.activity = activity;
            this.listaImagensProjeto = _listaImagensProjeto;
            inflater = (LayoutInflater) activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        } catch (Exception e){

        }
    }
    public int getCount() {
        return listaImagensProjeto.size();
    }

    public Projeto getItem(Projeto position) {
        return position;
    }

    public long getItemId(int position) {
        return position;
    }

    public static class ViewHolder {
        public ImageView imagemProjeto;

    }

    public View getView(int position, View convertView, ViewGroup parent) {
        View vi = convertView;
        final ViewHolder holder;
        try {
            if (convertView == null) {
                vi = inflater.inflate(R.layout.padrao_lista_imagens_projeto, null);
                holder = new ViewHolder();
                holder.imagemProjeto = (ImageView) vi.findViewById(R.id.imagemListaProjeto);
                vi.setTag(holder);

            } else {
                holder = (ViewHolder) vi.getTag();
            }
            String imagemPrincipal = listaImagensProjeto.get(position);
            Bitmap imagem = converter.StringToBitMap(imagemPrincipal);
            holder.imagemProjeto.setImageBitmap(imagem);

        } catch (Exception e) {

        }
        return vi;
    }

}

