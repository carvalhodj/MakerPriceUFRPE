package makerprice.com.makerpriceufrpe.infra;

import android.content.Context;
import android.graphics.Color;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

import makerprice.com.makerpriceufrpe.componente.dominio.Componente;

/**
 * Created by d3jota on 17/06/16.
 */
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


    // And the "magic" goes here
    // This is for the "passive" state of the spinner
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // I created a dynamic TextView here, but you can reference your own  custom layout for each spinner item
        TextView label = new TextView(context);
        // Then you can get the current item using the values array (Users array) and the current position
        // You can NOW reference each method you has created in your bean object (User class)
        label.setText((CharSequence) listaComponentes.get(position));

        // And finally return your dynamic (or custom) view for each spinner item
        return label;
    }

    // And here is when the "chooser" is popped up
    // Normally is the same view, but you can customize it if you want
    @Override
    public View getDropDownView(int position, View convertView,
                                ViewGroup parent) {
        TextView label = new TextView(context);
        label.setTextColor(Color.BLACK);
        label.setText((CharSequence) listaComponentes.get(position));

        return label;
    }
}

