package receivers

import android.bluetooth.BluetoothAdapter
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import entities.BluetoothState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class BluetoothStateBroadcastReceiver : BroadcastReceiver() {

    private var initState = when (BluetoothAdapter.getDefaultAdapter().state) {
        BluetoothAdapter.STATE_OFF -> BluetoothState.Off
        BluetoothAdapter.STATE_ON -> BluetoothState.On
        else -> BluetoothState.Empty
    }

    private var _btStateFlow: MutableStateFlow<BluetoothState> = MutableStateFlow(initState)
    val btStateFlow = _btStateFlow.asStateFlow()

    override fun onReceive(context: Context?, intent: Intent?) {
        intent?.let {
            val action = it.action
            if (action == BluetoothAdapter.ACTION_STATE_CHANGED) {
                val adapterState = it.getIntExtra(
                    BluetoothAdapter.EXTRA_STATE,
                    BluetoothAdapter.ERROR
                )
                when (adapterState) {
                    BluetoothAdapter.STATE_OFF -> _btStateFlow.value = BluetoothState.Off
                    BluetoothAdapter.STATE_ON -> _btStateFlow.value = BluetoothState.On
                }
            }
        }
    }

}