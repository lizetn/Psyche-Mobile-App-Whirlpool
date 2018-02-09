package asu.whirlpool.psychewhirlpool.InstagramClasses;

/**
 * Created by jperez60 on 1/4/2018.
 */

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.view.View;
import android.view.Window;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.squareup.picasso.Picasso;

import asu.whirlpool.psychewhirlpool.R;
//used when image is clicked, full view of image
public class Display extends AppCompatActivity {
    CardView card_view2;
    ImageView imageView;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.display);
        Intent intent = getIntent();
        int position = intent.getExtras().getInt("Pos");
        imageView = (ImageView) findViewById(R.id.imageView);
        Glide.with(getApplicationContext()).load(Adapter.path.get(position).getImage_url()).into(imageView);
        //Picasso.with(getApplicationContext()).load(Adapter.path.get(position).getImage_url()).into(imageView);
       // Glide.with(getApplicationContext()).load(Adapter.path.get(position).getImage_url()).into(imageView);

    }

    public void onBackPressed() {
        finish();
        super.onBackPressed();
    }
}