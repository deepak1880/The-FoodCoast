package com.example.thefoodcoast.receiver

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import com.example.thefoodcoast.retrofit.isInternetConnected

class ConnectivityReceiver(private val callback: (Boolean) -> Unit) : BroadcastReceiver() {

    override fun onReceive(context: Context?, intent: Intent?) {
        context?.let {
            callback(isInternetConnected(it))
        }
    }
}