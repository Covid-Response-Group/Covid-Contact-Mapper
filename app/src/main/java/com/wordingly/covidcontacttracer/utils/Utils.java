package com.wordingly.covidcontacttracer.utils;

import android.content.Context;
import android.location.Location;

import com.wordingly.covidcontacttracer.R;

import java.text.DateFormat;
import java.util.Date;

public class Utils {

    public static String getLocationText(Location location) {
        return location == null ? "Unknown location" :
                "(" + location.getLatitude() + ", " + location.getLongitude() + ")";
    }

    public static String getLocationTitle(Context context) {
        return context.getResources().getString(R.string.location_updated,
                DateFormat.getDateTimeInstance().format(new Date()));
    }
}
