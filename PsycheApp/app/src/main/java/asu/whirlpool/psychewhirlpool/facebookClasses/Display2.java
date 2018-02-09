package asu.whirlpool.psychewhirlpool.facebookClasses;

/**
 * Created by jperez60 on 1/19/2018.
 */
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.squareup.picasso.Picasso;

import java.util.List;

import asu.whirlpool.psychewhirlpool.InstagramClasses.Adapter;
import asu.whirlpool.psychewhirlpool.R;
//used when image is clicked, full view of image
public class Display2 extends AppCompatActivity {
    ImageView imageView;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_facebookdisplay);
        Intent intent = getIntent();
        int position = intent.getExtras().getInt("Pos");
        imageView = (ImageView) findViewById(R.id.imageView);
        Glide.with(getApplicationContext()).load(ListAdapter.fbList.get(position).getPicture()).into(imageView);
        //Picasso.with(getApplicationContext()).load(ListAdapter.fbList.get(position).getPicture()).into(imageView);
    }

    public void onBackPressed() {
        finish();
        super.onBackPressed();
    }
}