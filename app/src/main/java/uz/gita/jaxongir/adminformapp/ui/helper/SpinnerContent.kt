package uz.gita.jaxongir.adminformapp.ui.helper

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import uz.gita.jaxongir.adminformapp.presentation.componentsscreen.Contracts

@Composable
fun SpinnerContent(
    clickListener: (Contracts.Intent) -> Unit,
    id: String,
) {
    var variants by remember {
        mutableStateOf(listOf<String>())
    }

    var newVariants = ArrayList(variants)

    var content by remember {
        mutableStateOf("")
    }

    var userId by remember {
        mutableStateOf("")
    }
    Box(modifier = Modifier.fillMaxSize()) {
        LazyColumn(
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            item {
                OutlinedTextField(
                    value = content,
                    onValueChange = {
                        content = it
                    },
                    label = {
                        Text(text = "Spinner nima haqida")
                    },
                    placeholder = {
                        Text(text = "Spinner nima haqida")
                    },
                    modifier = Modifier.padding(vertical = 6.dp)
                )
            }

            item {
                OutlinedTextField(
                    value = content,
                    onValueChange = {
                        content = it
                    },
                    label = {
                        Text(text = "Spinner nima haqida")
                    },
                    placeholder = {
                        Text(text = "Spinner nima haqida")
                    },
                    modifier = Modifier.padding(vertical = 6.dp)
                )
            }

            variants.forEach {
                item {
                    OutlinedTextField(value = it, onValueChange = {

                    })
                }
            }
        }
    }
}