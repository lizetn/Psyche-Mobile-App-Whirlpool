package asu.whirlpool.psychewhirlpool.home;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.app.AppCompatDelegate;
import android.support.v7.widget.Toolbar;
import android.view.View;

import asu.whirlpool.psychewhirlpool.R;

public class AppCreditsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        if (AppCompatDelegate.getDefaultNightMode() == AppCompatDelegate.MODE_NIGHT_YES) {
            setTheme(R.style.PsycheDarkTheme);
        }
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_app_credits);

    }
}
