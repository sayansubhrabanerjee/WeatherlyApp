package sayan.banerjee.weatherlyapp.weather.view

import android.content.IntentFilter
import android.content.pm.ActivityInfo
import android.net.ConnectivityManager
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import sayan.banerjee.weatherlyapp.R
import sayan.banerjee.weatherlyapp.common.network.listener.INetworkListener
import sayan.banerjee.weatherlyapp.common.network.receiver.NetworkReceiver
import sayan.banerjee.weatherlyapp.common.network.util.NetworkConstants
import sayan.banerjee.weatherlyapp.common.network.util.NetworkUtil
import sayan.banerjee.weatherlyapp.common.network.util.displayToast

open class BaseActivity : AppCompatActivity(), INetworkListener {
    private var mNetworkReceiver: NetworkReceiver? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        NetworkUtil.setConnectivityListener(this)
        registerNetworkReceiver()
        setOrientation()
    }

    private fun setOrientation() {
        if (resources.getBoolean(R.bool.portrait_only)) {
            requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT
        }
    }

    private fun registerNetworkReceiver() {
        mNetworkReceiver = NetworkReceiver()
        val intentFilter = IntentFilter()
        intentFilter.addAction(ConnectivityManager.CONNECTIVITY_ACTION)
        this.registerReceiver(mNetworkReceiver, intentFilter)
    }

    override fun onConnectivityChange(connectionStatus: String) {
        when (connectionStatus) {
            NetworkConstants.OPTIMAL_NETWORK -> {
                mIsNetworkAvailable = true
                mIsConnectivityPoor = false
                this.displayToast(getString(R.string.optimal_network))
            }
            NetworkConstants.POOR_NETWORK -> {
                mIsNetworkAvailable = true
                mIsConnectivityPoor = true
                this.displayToast(getString(R.string.poor_network))
            }
            NetworkConstants.TYPE_WIFI -> {
                mIsNetworkAvailable = true
                mIsConnectivityPoor = false
                this.displayToast(getString(R.string.wifi_network))
            }
            NetworkConstants.TYPE_NOT_CONNECTED -> {
                mIsNetworkAvailable = false
                mIsConnectivityPoor = false
                this.displayToast(getString(R.string.no_network))
            }
        }

        if (mIsNetworkAvailable) initOnlineFlow() else initOfflineFlow()
    }

    open fun initOnlineFlow() {
        //Needs to be implemented in respective activity/fragments
    }

    open fun initOfflineFlow() {
        //Needs to be implemented in respective activity/fragments
    }

    companion object {
        var mIsNetworkAvailable: Boolean = false
        var mIsConnectivityPoor: Boolean = false
    }

}