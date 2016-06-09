package makerprice.com.makerpriceufrpe.projeto.gui;

import android.content.Intent;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import java.util.ArrayList;

import makerprice.com.makerpriceufrpe.R;

public class ImageSliderActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image_slider);
        Intent intent = getIntent();
        ArrayList<String> listaImagens = intent.getStringArrayListExtra("lista-imagens");

        ViewPager viewPager = (ViewPager) findViewById(R.id.view_pager);
        ImageAdapter adapter = new ImageAdapter(this);
        adapter.setImagesGal(listaImagens);
        viewPager.setAdapter(adapter);
    }
}
