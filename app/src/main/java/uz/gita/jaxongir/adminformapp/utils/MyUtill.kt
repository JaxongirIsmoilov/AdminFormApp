package uz.gita.jaxongir.adminformapp.utils

import android.util.Log
import androidx.compose.ui.unit.dp

fun myLog(message :String) {
//    Log.d("TTT", message)
}

fun String.toDp(): androidx.compose.ui.unit.Dp {
    return this.toFloatOrNull()?.dp ?: 0.dp
}
