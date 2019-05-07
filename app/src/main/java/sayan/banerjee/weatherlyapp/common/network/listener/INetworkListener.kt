package sayan.banerjee.weatherlyapp.common.network.listener

interface INetworkListener {
    fun onConnectivityChange(connectionStatus: String)
}