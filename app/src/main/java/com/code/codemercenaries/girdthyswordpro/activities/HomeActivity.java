package com.code.codemercenaries.girdthyswordpro.activities;

import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.code.codemercenaries.girdthyswordpro.R;
import com.code.codemercenaries.girdthyswordpro.adapters.ViewPagerAdapter;
import com.code.codemercenaries.girdthyswordpro.fragments.DailiesFragment;
import com.code.codemercenaries.girdthyswordpro.fragments.ForgeFragment;
import com.code.codemercenaries.girdthyswordpro.fragments.OverviewFragment;
import com.code.codemercenaries.girdthyswordpro.utilities.FontHelper;
import com.google.firebase.auth.FirebaseAuth;

import io.github.inflationx.viewpump.ViewPumpContextWrapper;

public class HomeActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener,
        DailiesFragment.OnFragmentInteractionListener,
        OverviewFragment.OnFragmentInteractionListener,
        ForgeFragment.OnFragmentInteractionListener {

    FirebaseAuth mAuth;

    FontHelper fontHelper;
    ViewPagerAdapter viewPagerAdapter;
    BottomNavigationView bottomNavigationView;

    DailiesFragment dailiesFragment;
    OverviewFragment overviewFragment;
    ForgeFragment forgeFragment;

    MenuItem prevMenuItem;
    TextView displayName;
    ImageView displayImage;
    FloatingActionButton fab;
    FloatingActionButton addSectionFab;
    FloatingActionButton removeSectionFab;
    TextView addSectionText;
    TextView removeSectionText;

    boolean fabStatus;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        fontHelper = new FontHelper();
        fontHelper.initialize(this);

        setContentView(R.layout.activity_home);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
        }

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        final ViewPager viewPager = findViewById(R.id.view_pager);
        setupViewPager(viewPager);

        fab = findViewById(R.id.fab);
        addSectionFab = findViewById(R.id.addSectionFab);
        removeSectionFab = findViewById(R.id.removeSectionFab);
        addSectionText = findViewById(R.id.addSectionText);
        removeSectionText = findViewById(R.id.removeSectionText);

        setupFabs();

        bottomNavigationView = findViewById(R.id.navigation);

        bottomNavigationView.setOnNavigationItemSelectedListener
                (new BottomNavigationView.OnNavigationItemSelectedListener() {
                    @Override
                    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                        Fragment selectedFragment = null;

                        switch (item.getItemId()) {
                            case R.id.action_dailies:
                                viewPager.setCurrentItem(0);

                                break;
                            case R.id.action_overview:
                                viewPager.setCurrentItem(1);

                                break;
                            case R.id.action_forge:
                                viewPager.setCurrentItem(2);
                                break;

                        }

                        return false;
                    }
                });

        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                if (prevMenuItem != null)
                    prevMenuItem.setChecked(false);
                else
                    bottomNavigationView.getMenu().getItem(0).setChecked(false);

                bottomNavigationView.getMenu().getItem(position).setChecked(true);
                prevMenuItem = bottomNavigationView.getMenu().getItem(position);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        mAuth = FirebaseAuth.getInstance();

        displayName = navigationView.getHeaderView(0).findViewById(R.id.display_name);
        displayImage = navigationView.getHeaderView(0).findViewById(R.id.display_image);

        if(mAuth != null && mAuth.getCurrentUser() != null){
            displayName.setText(mAuth.getCurrentUser().getDisplayName());
            Glide.with(this).load(mAuth.getCurrentUser().getPhotoUrl()).into(displayImage);
        }

        ImageView settingsIcon = navigationView.getHeaderView(0).findViewById(R.id.settings_icon);
        settingsIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(HomeActivity.this,SettingsActivity.class));
            }
        });

    }

    private void setupFabs() {
        fabStatus = false;
        fabHide();

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!fabStatus) {
                    fabShow();
                    fabStatus = true;
                } else {
                    fabHide();
                    fabStatus = false;
                }
            }


        });

        addSectionFab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(HomeActivity.this,AddSectionActivity.class);
                startActivity(intent);
            }
        });

        removeSectionFab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(HomeActivity.this,RemoveSectionActivity.class);
                startActivity(intent);
            }
        });
    }

    private void fabShow() {
        addSectionFab.show();
        removeSectionFab.show();
        addSectionText.setVisibility(View.VISIBLE);
        removeSectionText.setVisibility(View.VISIBLE);
    }

    private void fabHide() {
        addSectionFab.hide();
        removeSectionFab.hide();
        addSectionText.setVisibility(View.INVISIBLE);
        removeSectionText.setVisibility(View.INVISIBLE);
    }

    private void setupViewPager(ViewPager viewPager) {
        Log.d("HomeActivity:", "Home setupViewPager");
        viewPagerAdapter = new ViewPagerAdapter(getSupportFragmentManager());
        dailiesFragment = new DailiesFragment();
        overviewFragment = new OverviewFragment();
        forgeFragment = new ForgeFragment();
        String fragmentTitle[] = {getString(R.string.title_fragment_dailies), getString(R.string.title_fragment_overview), getString(R.string.title_fragment_forge)};
        viewPagerAdapter.addFragment(dailiesFragment,fragmentTitle[0]);
        viewPagerAdapter.addFragment(overviewFragment,fragmentTitle[1]);
        viewPagerAdapter.addFragment(forgeFragment,fragmentTitle[2]);
        viewPager.setAdapter(viewPagerAdapter);
        viewPager.setCurrentItem(0);
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
            startActivity(new Intent(HomeActivity.this, HelpActivity.class));
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
                break;
            case R.id.nav_stats:
                startActivity(new Intent(HomeActivity.this,StatsActivity.class));
                break;
            case R.id.nav_read:
                startActivity(new Intent(HomeActivity.this,ReadActivity.class));
                break;
            case R.id.nav_leaderboard:
                startActivity(new Intent(HomeActivity.this,LeaderboardActivity.class));
                break;
            case R.id.nav_tavern:
                startActivity(new Intent(HomeActivity.this,TavernActivity.class));
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
                startActivity(new Intent(HomeActivity.this,BlogActivity.class));
                break;
            case R.id.nav_help:
                startActivity(new Intent(HomeActivity.this, HelpActivity.class));
                break;
            case R.id.nav_about:
                startActivity(new Intent(HomeActivity.this,AboutActivity.class));
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

    @Override
    public void onFragmentInteraction(Uri uri) {

    }
}
