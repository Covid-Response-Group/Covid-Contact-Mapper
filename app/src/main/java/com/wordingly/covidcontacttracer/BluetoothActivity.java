package com.wordingly.covidcontacttracer;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class BluetoothActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bluetooth);

        Button startServiceButton = (Button)findViewById(R.id.start_foreground_service_button);
        startServiceButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(BluetoothActivity.this, BluetoothForegroundService.class);
                intent.setAction(BluetoothForegroundService.ACTION_START_FOREGROUND_SERVICE);
                startService(intent);
            }
        });

        Button stopServiceButton = (Button)findViewById(R.id.stop_foreground_service_button);
        stopServiceButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(BluetoothActivity.this, BluetoothForegroundService.class);
                intent.setAction(BluetoothForegroundService.ACTION_STOP_FOREGROUND_SERVICE);
                startService(intent);
            }
        });
    }

}