package sayan.banerjee.weatherlyapp.common.network.receiver

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.util.Log
import sayan.banerjee.weatherlyapp.common.network.util.NetworkUtil

class NetworkReceiver: BroadcastReceiver() {
    override fun onReceive(context: Context?, intent: Intent?) {
        Log.i(TAG, "NetworkReceiver onReceive")
        NetworkUtil.getConnectivityStatus(context!!)
    }

    companion object {
        val TAG: String = NetworkReceiver::class.java.simpleName
    }
}