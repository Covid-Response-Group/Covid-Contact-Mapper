package com.wordingly.covidcontacttracer;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.bluetooth.BluetoothA2dp;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothManager;
import android.bluetooth.BluetoothProfile;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.SystemClock;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.google.android.material.snackbar.Snackbar;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import no.nordicsemi.android.support.v18.scanner.BluetoothLeScannerCompat;
import no.nordicsemi.android.support.v18.scanner.ScanCallback;
import no.nordicsemi.android.support.v18.scanner.ScanFilter;
import no.nordicsemi.android.support.v18.scanner.ScanResult;
import no.nordicsemi.android.support.v18.scanner.ScanSettings;

public class BluetoothActivity extends AppCompatActivity {

    private static final String TAG = "BT Device Found";
    BluetoothAdapter mBluetoothAdapter = null;

    private static final Set<String> TARGET_DEVICE_NAME = new HashSet<String>();

    static {
        //todo: add target device name after getting device name with startDiscovery
        TARGET_DEVICE_NAME.add("BT-S15");
    }

    private List<BluetoothDevice> mTargetDevices = new ArrayList<>();



    private BluetoothAdapter BA;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bluetooth);
        mBluetoothAdapter = BluetoothAdapter.getDefaultAdapter();
        if (mBluetoothAdapter == null) {
            Log.d(TAG, "BT not supported..");
            return;
        }

        if(!mBluetoothAdapter.isEnabled()){
            Log.d(TAG, "BT enable.");
            mBluetoothAdapter.enable();
        }
        else{
            Log.d(TAG, "BT already enabled.");
        }



    }

    @Override
    protected void onResume() {
        super.onResume();
        startDiscovery();
    }

    @Override
    protected void onStart() {
        super.onStart();
        IntentFilter filter = new IntentFilter();
        filter.addAction(BluetoothDevice.ACTION_FOUND);
        registerReceiver(mReceiver, filter);
    }

    @Override
    protected void onStop() {
        super.onStop();
        unregisterReceiver(mReceiver);
    }





    private void startDiscovery() {
        Log.d(TAG, "startDiscovery");
        if (mBluetoothAdapter != null) {
            mTargetDevices.clear();

            Log.d(TAG, "request BT startDiscovery");
            mBluetoothAdapter.startDiscovery();
        } else {
            Log.d(TAG, "mBluetoothAdapter!=null");
        }
    }

    private void stopDiscovery() {
        Log.d(TAG, "stopDiscovery");
        if (mBluetoothAdapter != null) {
            Log.d(TAG, "cancel BT Discovery");
            mBluetoothAdapter.cancelDiscovery();
        } else {
            Log.d(TAG, "mBluetoothAdapter!=null");
        }
    }

    private void createBondDiscoveredDevices() {
        Log.d(TAG, "createBondDiscoveredDevices");
        if (mTargetDevices.size() <= 0) {
            Log.d(TAG, "no new discovered device");
            return;
        }
        for (BluetoothDevice device : mTargetDevices) {
            Log.d(TAG, "discovered device:" + device.getName());

            if (device.getBondState() == BluetoothDevice.BOND_BONDED) {
                Log.d(TAG, "already bonded.");
            } else {
                Log.d(TAG, "try to createBond..");
            }
        }
    }

    BroadcastReceiver mReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            String action = intent.getAction();

            switch (action) {
                case BluetoothDevice.ACTION_FOUND:
                    BluetoothDevice device = intent.getParcelableExtra(BluetoothDevice.EXTRA_DEVICE);
                    int  rssi = intent.getShortExtra(BluetoothDevice.EXTRA_RSSI,Short.MIN_VALUE);
                    Log.d(TAG, "ACTION_FOUND: " + device.getName()+"__"+rssi);
                    if (device.getName() != null && TARGET_DEVICE_NAME.contains(device.getName())) {
                        Log.d(TAG, "target device found..");
                        mTargetDevices.add(device);
                    }
                    break;
                case BluetoothDevice.ACTION_BOND_STATE_CHANGED:
                    int state = intent.getIntExtra(BluetoothDevice.EXTRA_BOND_STATE, -1);
                    int previousState = intent.getIntExtra(BluetoothDevice.EXTRA_PREVIOUS_BOND_STATE, -1);

                    Log.d(TAG, "ACTION_BOND_STATE_CHANGED: state:" + state + ", previous:" + previousState);

                    break;
            }
        }
    };
}