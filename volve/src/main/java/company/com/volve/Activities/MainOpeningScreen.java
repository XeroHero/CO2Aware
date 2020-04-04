package company.com.volve.Activities;

import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import company.com.volve.Fragments.ChooseClubNavFragment;
import company.com.volve.Fragments.ChooseSocNavigationFragment;
import company.com.volve.Fragments.HomeNavFragment;
import company.com.volve.Fragments.ImpLinksFragment;
import company.com.volve.R;

public class MainOpeningScreen extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_opening_screen);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        emailFeedbackIntent();

        //Create or update Json file

//        makeEventManager();
        createFragments();


        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
    }

    private void createFragments(){
        Fragment fragment = new HomeNavFragment();

        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.screen_area, fragment);
        fragmentTransaction.commit();
    }

    private void emailFeedbackIntent() {
        FloatingActionButton buttonEmailFeedback = findViewById(R.id.fab);
        buttonEmailFeedback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(Intent.ACTION_SEND);
                i.setType("message/rfc822");
                i.putExtra(Intent.EXTRA_EMAIL, new String[]{getString(R.string.dev_email)});
                i.putExtra(Intent.EXTRA_SUBJECT, getString(R.string.feedback_button));

                try {
                    startActivity(Intent.createChooser(i, getString(R.string.email_via)));
                } catch (android.content.ActivityNotFoundException ex) {
                    Toast.makeText(MainOpeningScreen.this, R.string.email_client_missing, Toast.LENGTH_SHORT).show();
                }

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
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();
        Fragment fragment = null;

        if (id == R.id.nav_all_events)
            fragment = new HomeNavFragment();

        else if (id == R.id.nav_choose_club)
            fragment = new ChooseClubNavFragment();

        else if (id == R.id.nav_choose_soc)
            fragment = new ChooseSocNavigationFragment();

        else if (id == R.id.nav_imp_links)
            fragment = new ImpLinksFragment();

        else if (id == R.id.login)
            goToAddEvents();

        else if (id == R.id.nav_fishhook)
            alertDownload();

        if (fragment != null) {
            FragmentManager fragmentManager = getSupportFragmentManager();
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            fragmentTransaction.replace(R.id.screen_area, fragment);
            fragmentTransaction.commit();
        }

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }


    private void alertDownload() {
        int REQUEST_CODE = 1;

        ActivityCompat.requestPermissions(this, new String[]{
                android.Manifest.permission.WRITE_EXTERNAL_STORAGE
        }, REQUEST_CODE);
        new AlertDialog.Builder(this)
                .setTitle(R.string.fishook_brochure_title)
                .setMessage(R.string.question_download)
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        DownloadManager.pdfRequestHandle();
                        Toast.makeText(getApplicationContext(), R.string.pdf_status_downloading, Toast.LENGTH_LONG).show();
                        dialog.dismiss();
                    }
                })
                .setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(getApplicationContext(), R.string.pdf_post_no, Toast.LENGTH_LONG).show();
                        dialog.dismiss();
                    }
                }).show();
    }

    void goToAddEvents() {
        Uri uriUrl = Uri.parse(getString(R.string.web_portal_url));
        Intent launchBrowser = new Intent(Intent.ACTION_VIEW, uriUrl);
        startActivity(launchBrowser);
    }
//    private void makeEventManager(){
//        EventManager.checkList();
//    }

}
