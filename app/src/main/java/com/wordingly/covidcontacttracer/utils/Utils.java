package com.wordingly.covidcontacttracer.utils;

import android.app.ActivityManager;
import android.content.Context;
import android.location.Location;

import com.wordingly.covidcontacttracer.R;

import java.text.DateFormat;
import java.util.Date;

public class Utils {

    public static final float SIGNIFICANT_DIST = -200.0f;

    public static String getLocationText(Location location) {
        return location == null ? "Unknown location" :
                "(" + location.getLatitude() + ", " + location.getLongitude() + ")";
    }

    public static String getLocationTitle(Context context) {
        return context.getResources().getString(R.string.location_updated,
                DateFormat.getDateTimeInstance().format(new Date()));
    }

    public static boolean isServiceRunningInForeground(Context context, Class<?> serviceClass) {
        ActivityManager manager = (ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE);
        for (ActivityManager.RunningServiceInfo service : manager.getRunningServices(Integer.MAX_VALUE)) {
            if (serviceClass.getName().equals(service.service.getClassName())) {
                if (service.foreground) {
                    return true;
                }

            }
        }
        return false;
    }

    public static boolean didLocationChangeSignificantly(float significantDistance, Location currentLocation) {

        double[] lastLocation = getLatLongFromPrefs();
        if (lastLocation != null) {
            if (getLocationDistance(lastLocation[0], lastLocation[1], currentLocation) > significantDistance) {
                Prefs.updateLastLocation(currentLocation);
                return true;
            }
        }
        return false;
    }

    public static float getLocationDistance(double lat1, double long1, double lat2, double long2) {
        Location lastLocation = new Location("pointA");
        lastLocation.setLatitude(lat1);
        lastLocation.setLongitude(long1);
        Location recentLocation = new Location("pointB");
        lastLocation.setLatitude(lat2);
        lastLocation.setLongitude(long2);

        return recentLocation.distanceTo(recentLocation);

    }

    public static float getLocationDistance(double lat1, double long1, Location recentLocation) {
        Location lastLocation = new Location("pointA");
        lastLocation.setLatitude(lat1);
        lastLocation.setLongitude(long1);

        return recentLocation.distanceTo(recentLocation);

    }

    public static double[] getLatLongFromPrefs() {
        String latLong = Prefs.getLastLocation();
        if (latLong != null) {
            double[] latLongVals = new double[2];
            String[] latLongArr = latLong.split(",");
            latLongVals[0] = Double.parseDouble(latLongArr[0]);
            latLongVals[1] = Double.parseDouble(latLongArr[1]);
            return latLongVals;
        }
        return null;
    }

    public static long getTimeFromLastScan() {
        return System.currentTimeMillis() - Prefs.getLastScanTime();
    }
}
