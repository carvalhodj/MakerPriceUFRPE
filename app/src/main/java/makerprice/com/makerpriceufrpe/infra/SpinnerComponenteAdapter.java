package makerprice.com.makerpriceufrpe.infra;

import android.content.Context;
import android.graphics.Color;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

import makerprice.com.makerpriceufrpe.componente.dominio.Componente;

public class SpinnerComponenteAdapter extends ArrayAdapter<Componente> {

    // Your sent context
    private Context context;
    // Your custom values for the spinner (User)
    private ArrayList<Componente> listaComponentes = new ArrayList<>();

    public SpinnerComponenteAdapter(Context context, int textViewResourceId,
                       ArrayList<Componente> _listaComponentes) {
        super(context, textViewResourceId, _listaComponentes);
        this.context = context;
        this.listaComponentes = _listaComponentes;
    }

    public int getCount(){
        return listaComponentes.size();
    }

    public Componente getItem(Componente position){
        return position;
    }

    public long getItemId(int position){
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        TextView label = new TextView(context);
        label.setText((CharSequence) listaComponentes.get(position));
        return label;
    }

    @Override
    public View getDropDownView(int position, View convertView,
                                ViewGroup parent) {
        TextView label = new TextView(context);
        label.setTextColor(Color.BLACK);
        label.setText((CharSequence) listaComponentes.get(position));

        return label;
    }
}

