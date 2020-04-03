package com.wordingly.covidcontacttracer;

import android.content.Intent;

import androidx.core.content.ContextCompat;

import com.onesignal.NotificationExtenderService;
import com.onesignal.OSNotificationReceivedResult;

public class OneSignalNotificationExtenderService extends NotificationExtenderService {
    @Override
    protected boolean onNotificationProcessing(OSNotificationReceivedResult notification) {
        Intent serviceIntent = new Intent(getApplicationContext(), LocationUpdatesService.class);
        serviceIntent.putExtra("inputExtra", "Service From One Signal");
        ContextCompat.startForegroundService(getApplicationContext(), serviceIntent);
        return true;
    }
}
