package asu.whirlpool.psychewhirlpool.InstagramClasses;

/**
 * Created by jperez60 on 1/4/2018.
 */

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.view.View;
import android.view.Window;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

import asu.whirlpool.psychewhirlpool.R;

public class Display extends AppCompatActivity {
    CardView card_view2;
    ImageView imageView;
    protected void onCreate(Bundle savedInstanceState) {
        getWindow().requestFeature(Window.FEATURE_CONTENT_TRANSITIONS);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.display);
        getSupportActionBar().hide();
        card_view2 = (CardView) findViewById(R.id.card_view2);
        card_view2.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                finishAfterTransition();
            }
        });
        imageView = (ImageView) findViewById(R.id.imageView);
        Glide.with(getApplicationContext()).load(Adapter.path.get(Adapter.pos).getImage_url()).into(imageView);

    }

    public void onBackPressed() {
        finishAfterTransition();
        super.onBackPressed();
    }
}