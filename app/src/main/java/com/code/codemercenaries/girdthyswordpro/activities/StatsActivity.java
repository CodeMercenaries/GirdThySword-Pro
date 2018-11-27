package com.code.codemercenaries.girdthyswordpro.activities;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;

import com.code.codemercenaries.girdthyswordpro.R;
import com.code.codemercenaries.girdthyswordpro.utilities.FontHelper;

import java.io.IOException;
import java.io.InputStream;

import io.github.inflationx.viewpump.ViewPumpContextWrapper;

public class StatsActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    FontHelper fontHelper;
    ImageView badge5;
    ImageView badge20;
    ImageView badge40;
    ImageView badge70;
    ImageView badge100;
    ImageView badge150;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        fontHelper = new FontHelper();
        fontHelper.initialize(this);

        setContentView(R.layout.activity_stats);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

    }

    @Override
    protected void onResume() {
        super.onResume();

        badge5 = findViewById(R.id.badge5);
        badge20 = findViewById(R.id.badge20);
        badge40 = findViewById(R.id.badge40);
        badge70 = findViewById(R.id.badge70);
        badge100 = findViewById(R.id.badge100);
        badge150 = findViewById(R.id.badge150);

        InputStream inputStream = null;
        Drawable drawable = null;
        try {
            inputStream = getAssets().open("images/badges/5.png");
            drawable = Drawable.createFromStream(inputStream,null);
            badge5.setImageDrawable(drawable);
            inputStream.close();

            inputStream = getAssets().open("images/badges/20.png");
            drawable = Drawable.createFromStream(inputStream,null);
            badge20.setImageDrawable(drawable);
            inputStream.close();

            inputStream = getAssets().open("images/badges/40_hide.png");
            drawable = Drawable.createFromStream(inputStream,null);
            badge40.setImageDrawable(drawable);
            inputStream.close();

            inputStream = getAssets().open("images/badges/70_hide.png");
            drawable = Drawable.createFromStream(inputStream,null);
            badge70.setImageDrawable(drawable);
            inputStream.close();

            inputStream = getAssets().open("images/badges/100_hide.png");
            drawable = Drawable.createFromStream(inputStream,null);
            badge100.setImageDrawable(drawable);
            inputStream.close();

            inputStream = getAssets().open("images/badges/150_hide.png");
            drawable = Drawable.createFromStream(inputStream,null);
            badge150.setImageDrawable(drawable);
            inputStream.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(ViewPumpContextWrapper.wrap(newBase));
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.stats, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        switch(id) {
            case R.id.nav_home:
                startActivity(new Intent(StatsActivity.this,HomeActivity.class));
                break;
            case R.id.nav_stats:
                break;
            case R.id.nav_read:
                startActivity(new Intent(StatsActivity.this,ReadActivity.class));
                break;
            case R.id.nav_leaderboard:
                break;
            case R.id.nav_tavern:
                break;
            case R.id.nav_share:
                break;
            case R.id.nav_blog:
                startActivity(new Intent(StatsActivity.this,BlogActivity.class));
                break;
        }

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}