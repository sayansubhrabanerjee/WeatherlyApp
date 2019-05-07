package sayan.banerjee.weatherlyapp.common.network.util

import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.powermock.modules.junit4.PowerMockRunner
import sayan.banerjee.weatherlyapp.common.BaseUnitTest
import sayan.banerjee.weatherlyapp.common.network.listener.INetworkListener

@RunWith(PowerMockRunner::class)
class NetworkUtilTest : BaseUnitTest() {

    @Mock
    var networkListener: INetworkListener? = null

    @Test
    fun getConnectivityStatusTest() {
        NetworkUtil.getConnectivityStatus(mContext)
    }

    @Test
    fun setConnectivityListenerTest() {
        NetworkUtil.setConnectivityListener(networkListener!!)
    }
}