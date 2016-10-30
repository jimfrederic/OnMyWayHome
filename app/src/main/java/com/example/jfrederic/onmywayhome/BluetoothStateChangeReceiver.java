package com.example.jfrederic.onmywayhome;

import android.bluetooth.BluetoothDevice;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.telephony.SmsManager;

import java.util.Calendar;

public class BluetoothStateChangeReceiver extends BroadcastReceiver {

    private int previousDayOfYear = -1;
    private int previousYear = -1;

    public BluetoothStateChangeReceiver() {
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        Calendar cal = Calendar.getInstance();
        int dayOfYear = cal.get(Calendar.DAY_OF_YEAR);
        int year = cal.get(Calendar.YEAR);
        if (dayOfYear != previousDayOfYear && year != previousYear) {
            int dayOfWeek = cal.get(Calendar.DAY_OF_WEEK);
            if (dayOfWeek > 1 && dayOfWeek < 7) {
                // weekday
                if (cal.get(Calendar.HOUR_OF_DAY) > 16) {
                    // after 4pm
                    BluetoothDevice device = intent.getParcelableExtra(BluetoothDevice.EXTRA_DEVICE);
                    if ("00:1E:B2:9D:C1:A1".equals(device.getAddress())) {
                        SmsManager.getDefault().sendTextMessage("+15125735194", null, "On my way home. Need anything?", null, null);
                        previousDayOfYear = dayOfYear;
                        previousYear = year;
                    }
                }
            }
        }
    }
}
