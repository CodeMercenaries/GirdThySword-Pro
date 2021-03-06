package com.code.codemercenaries.girdthyswordpro.activities;

import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.code.codemercenaries.girdthyswordpro.R;
import com.code.codemercenaries.girdthyswordpro.utilities.FontHelper;
import com.google.firebase.auth.FirebaseAuth;

import java.io.IOException;
import java.io.InputStream;

import io.github.inflationx.viewpump.ViewPumpContextWrapper;

public class AboutActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    FontHelper fontHelper;
    ImageView girdthysword;
    ImageButton facebook;
    ImageButton instagram;
    ImageButton twitter;
    ImageButton wordpress;
    ImageButton linkedin;

    FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        fontHelper = new FontHelper();
        fontHelper.initialize(this);

        setContentView(R.layout.activity_about);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        girdthysword = findViewById(R.id.girdthysword);
        facebook = findViewById(R.id.facebook);
        instagram = findViewById(R.id.instagram);
        twitter = findViewById(R.id.twitter);
        wordpress = findViewById(R.id.wordpress);
        linkedin = findViewById(R.id.linkedin);

        InputStream inputStream;
        Drawable drawable;
        try {

            inputStream = getAssets().open(getString(R.string.logo_path));
            drawable = Drawable.createFromStream(inputStream, null);
            girdthysword.setImageDrawable(drawable);

            inputStream = getAssets().open(getString(R.string.facebook_image));
            drawable = Drawable.createFromStream(inputStream,null);
            facebook.setImageDrawable(drawable);

            inputStream = getAssets().open(getString(R.string.twitter_image));
            drawable = Drawable.createFromStream(inputStream,null);
            twitter.setImageDrawable(drawable);

            inputStream = getAssets().open(getString(R.string.instagram_image));
            drawable = Drawable.createFromStream(inputStream,null);
            instagram.setImageDrawable(drawable);

            inputStream = getAssets().open(getString(R.string.wordpress_image));
            drawable = Drawable.createFromStream(inputStream,null);
            wordpress.setImageDrawable(drawable);

            inputStream = getAssets().open(getString(R.string.linkedin_image));
            drawable = Drawable.createFromStream(inputStream,null);
            linkedin.setImageDrawable(drawable);

        } catch (IOException e) {
            e.printStackTrace();
        }

        facebook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setAction(Intent.ACTION_VIEW);
                intent.addCategory(Intent.CATEGORY_BROWSABLE);
                intent.setData(Uri.parse("https://www.facebook.com/joelkingsley.r"));
                startActivity(intent);
            }
        });

        twitter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setAction(Intent.ACTION_VIEW);
                intent.addCategory(Intent.CATEGORY_BROWSABLE);
                intent.setData(Uri.parse("https://twitter.com/JoelKingsley"));
                startActivity(intent);
            }
        });

        instagram.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setAction(Intent.ACTION_VIEW);
                intent.addCategory(Intent.CATEGORY_BROWSABLE);
                intent.setData(Uri.parse("https://www.instagram.com/joelkingsleyr/"));
                startActivity(intent);
            }
        });

        wordpress.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setAction(Intent.ACTION_VIEW);
                intent.addCategory(Intent.CATEGORY_BROWSABLE);
                intent.setData(Uri.parse("https://joelkingsley.wordpress.com/"));
                startActivity(intent);
            }
        });

        linkedin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setAction(Intent.ACTION_VIEW);
                intent.addCategory(Intent.CATEGORY_BROWSABLE);
                intent.setData(Uri.parse("https://www.linkedin.com/in/joelkingsleyr/"));
                startActivity(intent);
            }
        });

        mAuth = FirebaseAuth.getInstance();

        TextView displayName = navigationView.getHeaderView(0).findViewById(R.id.display_name);
        ImageView displayImage = navigationView.getHeaderView(0).findViewById(R.id.display_image);

        if (mAuth != null && mAuth.getCurrentUser() != null) {
            displayName.setText(mAuth.getCurrentUser().getDisplayName());
            Glide.with(this).load(mAuth.getCurrentUser().getPhotoUrl()).into(displayImage);
        }

        ImageView settingsIcon = navigationView.getHeaderView(0).findViewById(R.id.settings_icon);
        settingsIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(AboutActivity.this,SettingsActivity.class));
            }
        });
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
        getMenuInflater().inflate(R.menu.actions, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_help) {
            startActivity(new Intent(AboutActivity.this, HelpActivity.class));
            return true;
        } else if (id == R.id.action_rate_app) {
            Uri uri = Uri.parse("market://details?id=" + getPackageName());
            Intent goToMarket = new Intent(Intent.ACTION_VIEW, uri);
            // To count with Play market backstack, After pressing back button,
            // to taken back to our application, we need to add following flags to intent.
            goToMarket.addFlags(Intent.FLAG_ACTIVITY_NO_HISTORY |
                    Intent.FLAG_ACTIVITY_NEW_DOCUMENT |
                    Intent.FLAG_ACTIVITY_MULTIPLE_TASK);
            try {
                startActivity(goToMarket);
            } catch (ActivityNotFoundException e) {
                startActivity(new Intent(Intent.ACTION_VIEW,
                        Uri.parse("http://play.google.com/store/apps/details?id=" + getPackageName())));
            }
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
                startActivity(new Intent(AboutActivity.this,HomeActivity.class));
                break;
            case R.id.nav_stats:
                startActivity(new Intent(AboutActivity.this,StatsActivity.class));
                break;
            case R.id.nav_read:
                startActivity(new Intent(AboutActivity.this,ReadActivity.class));
                break;
            case R.id.nav_leaderboard:
                startActivity(new Intent(AboutActivity.this,LeaderboardActivity.class));
                break;
            case R.id.nav_tavern:
                startActivity(new Intent(AboutActivity.this,TavernActivity.class));
                break;
            case R.id.nav_share:
                Intent shareIntent = new Intent();
                shareIntent.setAction(Intent.ACTION_SEND);
                shareIntent.putExtra(Intent.EXTRA_TEXT,
                        getString(R.string.share_message));
                shareIntent.setType("text/plain");
                startActivity(shareIntent);
                break;
            case R.id.nav_blog:
                startActivity(new Intent(AboutActivity.this,BlogActivity.class));
                break;
            case R.id.nav_help:
                startActivity(new Intent(AboutActivity.this, HelpActivity.class));
                break;
            case R.id.nav_about:
                break;
        }

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(ViewPumpContextWrapper.wrap(newBase));
    }
}
