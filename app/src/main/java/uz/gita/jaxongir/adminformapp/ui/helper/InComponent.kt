package uz.gita.jaxongir.adminformapp.ui.helper

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue

@Composable
fun InComponent(
    multiSelectors: List<String>,
) {
    var type by remember {
        mutableStateOf("")
    }

    var variants by remember {
        mutableStateOf(listOf<String>())
    }

    val newVariants = arrayListOf<String>()
    newVariants.addAll(variants)

    var reference1 by remember {
        mutableStateOf("")
    }
}