package com.blackbirds.shakil.checkinternetconnwithbr;

import androidx.appcompat.app.AppCompatActivity;

import android.content.BroadcastReceiver;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.os.Build;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    BroadcastReceiver broadcastReceiver;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        broadcastReceiver = new NetworkChangeReceiver();
        registerNetworkBroadcastReceiver();
    }

    private void registerNetworkBroadcastReceiver() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N){
            registerReceiver(broadcastReceiver, new IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION));
        }
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
            registerReceiver(broadcastReceiver, new IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION));
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unregisteredNetwork();
    }

    private void unregisteredNetwork() {
        try {
            unregisterReceiver(broadcastReceiver);
        }
        catch (IllegalArgumentException e){
            e.printStackTrace();
        }
    }
}