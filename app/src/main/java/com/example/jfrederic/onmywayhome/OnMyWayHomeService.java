package com.example.jfrederic.onmywayhome;

import android.app.Service;
import android.bluetooth.BluetoothDevice;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.IBinder;

public class OnMyWayHomeService extends Service {
    public OnMyWayHomeService() {
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        BluetoothStateChangeReceiver receiver = new BluetoothStateChangeReceiver();
        registerReceiver(receiver, new IntentFilter(BluetoothDevice.ACTION_ACL_CONNECTED));
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }
}
