package com.wordingly.covidcontacttracer;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;

import android.Manifest;
import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.ServiceConnection;
import android.content.pm.PackageManager;
import android.location.Location;
import android.net.Uri;
import android.os.Bundle;
import android.os.IBinder;
import android.provider.Settings;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.wordingly.covidcontacttracer.utils.Prefs;
import com.wordingly.covidcontacttracer.utils.Utils;

import java.util.HashMap;
import java.util.Map;

public class ProfileActivity extends AppCompatActivity {
    // Used in checking for runtime permissions.
    private static final int REQUEST_PERMISSIONS_REQUEST_CODE = 34;
    private static final String TAG = ProfileActivity.class.getSimpleName();
    private DatabaseReference mDatabase;
    // The BroadcastReceiver used to listen from broadcasts from the service.
    private MyReceiver myReceiver;

    // A reference to the service used to get location updates.
    private LocationUpdatesService mService = null;

    // Tracks the bound state of the service.
    private boolean mBound = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Check that the user hasn't revoked permissions by going to Settings.

        setContentView(R.layout.activity_home);
        myReceiver = new MyReceiver();
        mDatabase = FirebaseDatabase.getInstance().getReference();
        setupActionBar();
    }

    private void setupActionBar() {
        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle("Contact Tracer App");
        actionBar.setSubtitle("Profile");
    }


    private class MyReceiver extends BroadcastReceiver {
        @Override
        public void onReceive(Context context, Intent intent) {
            Location location = intent.getParcelableExtra(LocationUpdatesService.EXTRA_LOCATION);
            if (location != null) {
                if (Prefs.getGoogleAccountId() != null) {
                    updateLocation(Utils.getLocationText(location));
                }
                Toast.makeText(ProfileActivity.this, Utils.getLocationText(location)+"-"+location.getAccuracy(),
                        Toast.LENGTH_SHORT).show();
            }
        }
    }

    public void updateLocation(String location) {
        final FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference ref = database.getReference();
        DatabaseReference locationRef = ref.child("timestamps").child(Prefs.getGoogleAccountId()).push();
        Map<String, Object> user = new HashMap<>();
        user.put(String.valueOf(System.currentTimeMillis()), location);
        locationRef.setValue(user);
    }


    // Monitors the state of the connection to the service.
    private final ServiceConnection mServiceConnection = new ServiceConnection() {

        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            LocationUpdatesService.LocationServiceBinder binder = (LocationUpdatesService.LocationServiceBinder) service;
            mService = binder.getService();
            mService.requestLocationUpdates();
            mBound = true;
        }


        @Override
        public void onServiceDisconnected(ComponentName name) {
            mService = null;
            mBound = false;
        }
    };

    @Override
    protected void onResume() {
        super.onResume();
        // Check that the user hasn't revoked permissions by going to Settings.
        LocalBroadcastManager.getInstance(this).registerReceiver(myReceiver,
                new IntentFilter(LocationUpdatesService.ACTION_BROADCAST));

    }

    @Override
    protected void onPause() {
        LocalBroadcastManager.getInstance(this).unregisterReceiver(myReceiver);
        super.onPause();
    }

    @Override
    protected void onStart() {
        super.onStart();

        //Needs to be reviewed, throwing error

//        bindService(new Intent(this, LocationUpdatesService.class), mServiceConnection,
//                Context.BIND_AUTO_CREATE);

        startService();

    }

    public void startService() {
        Intent serviceIntent = new Intent(this, LocationUpdatesService.class);
        serviceIntent.putExtra("inputExtra", "Foreground Service Example in Android");
        ContextCompat.startForegroundService(this, serviceIntent);
    }


    @Override
    protected void onStop() {
        if (mBound) {
            // Unbind from the service. This signals to the service that this activity is no longer
            // in the foreground, and the service can respond by promoting itself to a foreground
            // service.
            unbindService(mServiceConnection);
            mBound = false;
        }

        super.onStop();
    }


}
