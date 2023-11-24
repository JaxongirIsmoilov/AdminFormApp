package uz.gita.jaxongir.adminformapp.presentation.components

import android.view.View.OnLongClickListener
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import cafe.adriel.voyager.androidx.AndroidScreen
import uz.gita.jaxongir.adminformapp.data.model.UserData

class ComponentScreen(private val userData: UserData) : AndroidScreen() {
    @Composable
    override fun Content() {

    }


    @Composable
    fun SpinnerContent(
        clickListener: (Contracts.Intent) -> Unit
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
                    TextField(
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
                    TextField(
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
                        TextField(value = it, onValueChange = {

                        })
                    }
                }
            }
        }
    }

    @Composable
    fun SelectorContent(){

    }

    @Composable
    fun TextContent(){

    }
}