package asu.whirlpool.psychewhirlpool.home;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.app.AppCompatDelegate;
import android.support.v7.widget.Toolbar;
import android.view.View;

import asu.whirlpool.psychewhirlpool.R;

/**
 * Displays the home page of the app which contains a navigation bar and buttons for
 * accessing the countdown clock, mission facts, NASA website, psyche news website,
 * navigation help, and a toggle for night mode coloring of the app.
 *
 * @author      Natalie Fleischaker
 * @version     4/8/2018
 *
 */
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
