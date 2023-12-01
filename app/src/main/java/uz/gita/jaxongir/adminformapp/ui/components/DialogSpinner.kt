package uz.gita.jaxongir.adminformapp.ui.components

import android.annotation.SuppressLint
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import uz.gita.jaxongir.adminformapp.data.model.ComponentData

@SuppressLint("MutableCollectionMutableState")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DialogSpinner(
    savedIdList: List<String>,
    onSaveClick: (String, String, String) -> Unit,
    onClickCancel: () -> Unit,
    multiSelectors: List<ComponentData>,
    boolean: Boolean,
    onInClick:(String, String, List<String>) -> Unit,
) {
    androidx.compose.material3.AlertDialog(
        onDismissRequest = {
            onClickCancel()
        }
    ) {
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight()
                .border(1.dp, Color(0xFFFF3951), RoundedCornerShape(12.dp))
        ) {

            var selectesValue by remember {
                mutableStateOf("More")
            }

            val list = if(boolean) listOf("More", "Less", "Equal", "Not", "In", "!In") else listOf("More", "Less", "Equal", "Not",)

            Spacer(modifier = Modifier.height(8.dp))
            Column(
                modifier = Modifier
                    .padding(top = 10.dp, start = 12.dp, end = 12.dp, bottom = 10.dp)
                    .fillMaxWidth(), horizontalAlignment = Alignment.CenterHorizontally
            ) {
                SampleSpinner(
                    list = list,
                    preselected = "More",
                    onSelectionChanged = { selection ->
                        selectesValue = selection
                    },
                    content = "Tanlang!"
                )
                Spacer(modifier = Modifier.size(10.dp))

                if (selectesValue != "In" && selectesValue != "!In") {
                    var value by remember {
                        mutableStateOf("")
                    }
                    var selectedId by remember {
                        mutableStateOf(savedIdList.firstOrNull() ?: "")
                    }
                    SampleSpinner(
                        list = savedIdList,
                        preselected = savedIdList.firstOrNull() ?: "",
                        onSelectionChanged = {
                            selectedId = it
                        },
                        content = "Id tanlang"
                    )

                    OutlinedTextField(
                        value = value, onValueChange = {
                            value = it
                        }, singleLine = true,
                        modifier = Modifier
                            .padding(top = 10.dp, start = 12.dp, end = 12.dp)
                            .fillMaxWidth()
                            .align(Alignment.CenterHorizontally),
                        colors = OutlinedTextFieldDefaults.colors(
                            focusedBorderColor = Color(0xFFFF3951),
                            unfocusedBorderColor = Color(0xFFFF7686),
                        ), label = { Text(text = "value") },
                        maxLines = 1
                    )

                    Spacer(modifier = Modifier.height(8.dp))

                    Button(
                        onClick = {
                            if (savedIdList.size == 1) {
                                selectedId = savedIdList.first()
                            }
                            onSaveClick.invoke(selectedId, selectesValue, value)
                            onClickCancel()
                        },
                        modifier = Modifier
                            .padding(top = 10.dp)
                            .height(56.dp)
                            .width(120.dp)
                            .align(Alignment.CenterHorizontally),
                        colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFFA1466))
                    ) {
                        Text(text = "Saqlash", color = Color(0xFFFFFFFF))
                    }
                }
                else{
                    if (multiSelectors.isNotEmpty()) {
                        var value by remember {
                            mutableStateOf(listOf<String>())
                        }

                        var selectedId by remember {
                            mutableStateOf("")
                        }

                        var variants by remember {
                            mutableStateOf(listOf<String>())
                        }

                        val newVariants = arrayListOf<String>()

                        newVariants.addAll(variants)

                        SampleSpinner(
                            list = multiSelectors.map { it.idEnteredByUser },
                            preselected = "",
                            onSelectionChanged = { string ->
                                selectedId = string
                                val selected =
                                    multiSelectors.filter { it.idEnteredByUser == string }.first()
                                value = selected.variants
                            },
                            content = "MultiSelector tanlang"
                        )

                        LazyColumn(){
                            item {
                                Button(
                                    onClick = {
                                        if (savedIdList.size == 1) {
                                            selectedId = savedIdList.first()
                                        }
                                        onInClick.invoke(selectedId, selectesValue, value)
                                        onClickCancel()
                                    },
                                    modifier = Modifier
                                        .padding(top = 10.dp)
                                        .height(56.dp)
                                        .width(120.dp)
                                        .align(Alignment.CenterHorizontally),
                                    colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFFA1466))
                                ) {
                                    Text(text = "Saqlash", color = Color(0xFFFFFFFF))
                                }
                            }
                        }

                        if(selectedId == ""){
                            LazyColumn(){
                                variants.forEachIndexed { index, s ->
                                    item {
                                        OutlinedTextField(
                                            value = newVariants[index],
                                            onValueChange = {
                                                newVariants[index] = it
                                                variants = newVariants
                                            },
                                            label = { Text(text = "${index + 1} - variant") },
                                            modifier = Modifier.fillMaxWidth(),
                                            colors = OutlinedTextFieldDefaults.colors(
                                                focusedBorderColor = Color(0xFFFF3951),
                                                unfocusedBorderColor = Color(0xFFFF7686)
                                            )
                                        )
                                    }
                                }


                                item {
                                    TextButton(
                                        onClick = {
                                            newVariants.add("")
                                            variants = newVariants
                                        },
                                        modifier = Modifier
                                            .wrapContentWidth()
                                            .clip(RoundedCornerShape(12.dp))
                                    ) {
                                        Text(text = "Variant qo'shish")

                                    }
                                }
                            }
                        }








                    }
                    else {
                        var variants by remember {
                            mutableStateOf(listOf<String>())
                        }
                        val newVariants = arrayListOf<String>()
                        newVariants.addAll(variants)

                        var selectedId by remember {
                            mutableStateOf("")
                        }

                        LazyColumn() {
                            variants.forEachIndexed { index, s ->
                                item {
                                    OutlinedTextField(
                                        value = newVariants[index],
                                        onValueChange = {
                                            newVariants[index] = it
                                            variants = newVariants
                                        },
                                        label = { Text(text = "${index + 1} - variant") },
                                        modifier = Modifier.fillMaxWidth(),
                                        colors = OutlinedTextFieldDefaults.colors(
                                            focusedBorderColor = Color(0xFFFF3951),
                                            unfocusedBorderColor = Color(0xFFFF7686)
                                        )
                                    )
                                }
                            }


                            item {
                                TextButton(
                                    onClick = {
                                        newVariants.add("")
                                        variants = newVariants
                                    },
                                    modifier = Modifier
                                        .wrapContentWidth()
                                        .clip(RoundedCornerShape(12.dp))
                                ) {
                                    Text(text = "Variant qo'shish")

                                }
                            }

                            item {
                                Button(
                                    onClick = {
                                        if (savedIdList.size == 1) {
                                            selectedId = savedIdList.first()
                                        }
                                        onInClick.invoke(selectedId, selectesValue, variants)
                                        onClickCancel()
                                    },
                                    modifier = Modifier
                                        .padding(top = 10.dp)
                                        .height(56.dp)
                                        .width(120.dp)
                                        .align(Alignment.CenterHorizontally),
                                    colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFFA1466))
                                ) {
                                    Text(text = "Saqlash", color = Color(0xFFFFFFFF))
                                }
                            }
                        }


                    }
                }

            }
        }
    }
}





