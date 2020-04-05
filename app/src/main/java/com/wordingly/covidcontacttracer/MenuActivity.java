package com.wordingly.covidcontacttracer;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.core.app.ActivityCompat;
import androidx.work.Constraints;
import androidx.work.NetworkType;
import androidx.work.PeriodicWorkRequest;
import androidx.work.WorkManager;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.provider.Settings;
import android.util.Log;
import android.view.View;

import com.google.android.material.snackbar.Snackbar;
import com.wordingly.covidcontacttracer.network.NetworkCalls;

import java.util.concurrent.TimeUnit;

public class MenuActivity extends AppCompatActivity implements View.OnClickListener {
    private static final int REQUEST_PERMISSIONS_REQUEST_CODE = 34;
    private static final String TAG = MenuActivity.class.getSimpleName();

    CardView cvProfile, cvTravelRecord, cvNews, cvStats;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
        setupActionBar();
        startWorker();

    }

    private void startWorker() {
        Constraints constraints = new Constraints(Constraints.NONE);

        PeriodicWorkRequest locationWork = new PeriodicWorkRequest.Builder(ServiceWorker
                .class, 15, TimeUnit.MINUTES).addTag(ServiceWorker.TAG)
                .setConstraints(constraints).build();
        WorkManager.getInstance(getApplicationContext()).enqueue(locationWork);
    }


    private void setupActionBar() {
        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle("Contact Tracer App");
        actionBar.setSubtitle("Menu");
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (!ifPermissionsGranted()) {
            requestPermissions();
        } else {
            setupViews();
        }
    }

    private void setupViews() {
        cvProfile = findViewById(R.id.cv_profile);
        cvStats = findViewById(R.id.cv_stats);
        cvProfile.setOnClickListener(this);
//        cvTravelRecord.setOnClickListener(this);
//        cvNews.setOnClickListener(this);
        cvStats.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.cv_profile:
                startActivity(new Intent(this, ProfileActivity.class));
                break;
            case R.id.cv_stats:
                Intent intent = new Intent(this, WebPageActivity.class);
                intent.putExtra("url", "https://www.arcgis.com/apps/opsdashboard/index.html#/85320e2ea5424dfaaa75ae62e5c06e61");
                startActivity(intent);
                //startActivity(new Intent(this, StatsActivity.class));
                break;
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }

    private void requestPermissions() {
        boolean shouldProvideRationale =
                ActivityCompat.shouldShowRequestPermissionRationale(this,
                        Manifest.permission.ACCESS_FINE_LOCATION);


        if (shouldProvideRationale) {
            Log.i(TAG, "Displaying permission rationale to provide additional context.");
            Snackbar.make(
                    findViewById(R.id.ll_parent),
                    R.string.permission_rationale,
                    Snackbar.LENGTH_INDEFINITE)
                    .setAction(R.string.ok, new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            // Request permission
                            ActivityCompat.requestPermissions(MenuActivity.this,
                                    new String[]{Manifest.permission.ACCESS_FINE_LOCATION},
                                    REQUEST_PERMISSIONS_REQUEST_CODE);
                        }
                    })
                    .show();
        } else {
            Log.i(TAG, "Requesting permission");
            // Request permission. It's possible this can be auto answered if device policy
            // sets the permission in a given state or the user denied the permission
            // previously and checked "Never ask again".
            askPermission();
        }
    }

    private boolean ifPermissionsGranted() {
        return  PackageManager.PERMISSION_GRANTED == ActivityCompat.checkSelfPermission(this,
                Manifest.permission.ACCESS_FINE_LOCATION);
    }

    private void askPermission() {
        ActivityCompat.requestPermissions(this,
                new String[]{Manifest.permission.ACCESS_FINE_LOCATION},
                REQUEST_PERMISSIONS_REQUEST_CODE);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions,
                                           @NonNull int[] grantResults) {
        Log.i(TAG, "onRequestPermissionResult");
        if (requestCode == REQUEST_PERMISSIONS_REQUEST_CODE) {
            if (grantResults.length <= 0) {
                // If user interaction was interrupted, the permission request is cancelled and you
                // receive empty arrays.
                Log.i(TAG, "User interaction was cancelled.");
            } else if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                // Permission was granted.
                setupViews();
            } else {
                // Permission denied.
                //setButtonsState(false);
                Snackbar.make(
                        findViewById(R.id.ll_bt_parent),
                        R.string.permission_denied_explanation,
                        Snackbar.LENGTH_INDEFINITE)
                        .setAction(R.string.settings, new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                // Build intent that displays the App settings screen.
                                Intent intent = new Intent();
                                intent.setAction(
                                        Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
                                Uri uri = Uri.fromParts("package",
                                        BuildConfig.APPLICATION_ID, null);
                                intent.setData(uri);
                                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                                startActivity(intent);
                            }
                        })
                        .show();
            }
        }
    }


}
