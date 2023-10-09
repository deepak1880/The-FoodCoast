package com.example.thefoodcoast.receiver

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.util.Log
import com.example.thefoodcoast.retrofit.isInternetConnected

class ConnectivityReceiver(private val callback: (Boolean) -> Unit) : BroadcastReceiver() {

    private  val TAG = "ConnectivityReceiver"
    override fun onReceive(context: Context?, intent: Intent?) {
        Log.e(TAG, "onReceive: ")
        context?.let {
            callback(isInternetConnected(it))
        }
    }
}