package sayan.banerjee.weatherlyapp.common

import android.content.Context
import org.mockito.Mock
import org.mockito.Mockito

open class BaseUnitTest {
    @Mock
    var mContext: Context = Mockito.mock(Context::class.java)
}