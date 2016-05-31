package makerprice.com.makerpriceufrpe.infra;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

import makerprice.com.makerpriceufrpe.R;
import makerprice.com.makerpriceufrpe.projeto.dominio.Projeto;


public class ProjetoListAdapter extends ArrayAdapter<String> {

    public ProjetoListAdapter(Context context, ArrayList<String> arrayList){
        super(context, 0 , arrayList);
    }
    public View getView(int posicao, View view, ViewGroup viewGroup){
        String item = getItem(posicao);
        if (view==null){
            view = LayoutInflater.from(getContext()).inflate(R.layout.padrao_lista_projeto, viewGroup, false);
        }

        TextView nome = (TextView) view.findViewById(R.id.nome_projeto_listagem);
        nome.setText(item);
        return view;
    }

}

