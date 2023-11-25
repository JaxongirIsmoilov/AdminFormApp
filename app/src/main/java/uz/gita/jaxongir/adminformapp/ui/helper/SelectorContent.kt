package uz.gita.jaxongir.adminformapp.ui.helper

import android.widget.Toast
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import uz.gita.jaxongir.adminformapp.presentation.componentsscreen.Contracts
import uz.gita.jaxongir.adminformapp.ui.components.SampleSpinner

@Composable
fun SelectorContent(
    onSaveClickListener: (Contracts.Intent) -> Unit,
    id: String,
    userId: String
) {
    val context = LocalContext.current
    var questionText: String by remember { mutableStateOf("") }
    var componentId: String by remember { mutableStateOf("") }
    var isMulti: Boolean by remember { mutableStateOf(false) }
    var firstOption: String by remember { mutableStateOf("") }
    var secondOption: String by remember { mutableStateOf("") }
    var thirdOption: String by remember { mutableStateOf("") }
    var fourthOption: String by remember { mutableStateOf("") }
    var count: Int by remember { mutableStateOf(1) }

    Box(modifier = Modifier.fillMaxSize()) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentSize()
                .align(Alignment.Center), horizontalAlignment = Alignment.CenterHorizontally
        ) {
            OutlinedTextField(value = questionText, onValueChange = { questionText = it },
                label = { Text(text = "Selctor uchun savol kiriting") })

            Spacer(modifier = Modifier.size(10.dp))

            OutlinedTextField(value = componentId, onValueChange = { componentId = it },
                label = { Text(text = "Selector uchun id") })

            Spacer(modifier = Modifier.size(10.dp))

            SampleSpinner(
                list = listOf("Ha", "Yo'q"),
                preselected = "Yo'q",
                onSelectionChanged = { selection ->
                    isMulti = selection == "Ha"
                },
                content = "Ko'p variant tanlaydigan qilmoqchimisiz?"
            )

            Spacer(modifier = Modifier.size(10.dp))

            Button(onClick = {
                count++
                if (count > 4) {
                    Toast.makeText(
                        context,
                        "Maximum 4 ta variant qo'sha olasiz!",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }) {
                Text(
                    text = "Variant qo'shish",
                    modifier = Modifier.padding(horizontal = 10.dp)
                )
            }

            LazyColumn() {
                item {
                    if (count > 0) {
                        Spacer(modifier = Modifier.size(10.dp))
                        OutlinedTextField(
                            value = firstOption,
                            onValueChange = { firstOption = it },
                            label = { Text(text = "Birinchi variant") })
                        Spacer(modifier = Modifier.size(10.dp))
                    }
                }
                item {
                    if (count > 1) {
                        OutlinedTextField(
                            value = secondOption,
                            onValueChange = { secondOption = it },
                            label = { Text(text = "Ikkinchi variant") })
                        Spacer(modifier = Modifier.size(10.dp))
                    }

                }
                item {
                    if (count > 2) {
                        OutlinedTextField(
                            value = thirdOption,
                            onValueChange = { thirdOption = it },
                            label = { Text(text = "Uchinchi variant") })
                        Spacer(modifier = Modifier.size(10.dp))
                    }
                }
                item {
                    if (count > 3) {
                        OutlinedTextField(
                            value = fourthOption,
                            onValueChange = { fourthOption = it },
                            label = { Text(text = "To'rtinchi variant") })
                        Spacer(modifier = Modifier.size(10.dp))
                    }
                }

            }


        }

        Button(
            onClick = {

            }, modifier = Modifier
                .align(Alignment.BottomCenter)
                .padding(bottom = 15.dp)
        ) {
            Text(text = "Selectorni qo'shish")
        }


    }
}