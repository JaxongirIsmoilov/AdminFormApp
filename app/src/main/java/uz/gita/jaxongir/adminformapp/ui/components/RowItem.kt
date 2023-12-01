package uz.gita.jaxongir.adminformapp.ui.components

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import uz.gita.jaxongir.adminformapp.data.enums.ComponentEnum
import uz.gita.jaxongir.adminformapp.data.model.ComponentData
import uz.gita.jaxongir.adminformapp.presentation.splash.TextComponent
import uz.gita.jaxongir.adminformapp.ui.previewitems.DatePickerPreview
import uz.gita.jaxongir.adminformapp.ui.previewitems.InputField
import uz.gita.jaxongir.adminformapp.ui.previewitems.SampleSpinnerPreview
import uz.gita.jaxongir.adminformapp.ui.previewitems.SelectorItem
import uz.gita.jaxongir.adminformapp.utils.myLog

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun RowItem(
    components: List<ComponentData>,
    onDeleteComponent: (ComponentData) -> Unit,
) {
    Row(Modifier.fillMaxWidth()) {
        myLog("${components.size}")
        components.forEach { data ->
            when (data.type) {
                ComponentEnum.Spinner -> {
                    SampleSpinnerPreview(
                        list = data.variants,
                        preselected = data.variants[0],
                        onSelectionChanged = {},
                        content = data.content,
                        componentData = data,
                        modifier = Modifier.weight(1f),
                        deleteComp = {
                            onDeleteComponent(it)
                        }
                    )
                    Spacer(modifier = Modifier.height(10.dp))

                }

                ComponentEnum.Selector -> {
                    Column {
                        SelectorItem(
                            question = data.content,
                            list = data.variants,
                            componentData = data,
                            modifier = Modifier.weight(1f),
                            deleteComp = {
                                onDeleteComponent(it)
                            }
                        )
                        Spacer(modifier = Modifier.height(10.dp))
                    }
                }

                ComponentEnum.SampleText -> {
                    myLog("Sample Text worked")
                    TextComponent(
                        onClickDelete = {
                            onDeleteComponent(data)
                        },
                        text = data.content, modifier = Modifier.weight(1f)
                    )
                    Spacer(modifier = Modifier.height(10.dp))
                }

                ComponentEnum.Input -> {
                    myLog("Input type worked")
                    Column(modifier = Modifier.fillMaxWidth()) {
                        if (data.isRequired) {
                            Text(
                                text = "Required field!",
                                modifier = Modifier.align(
                                    Alignment.CenterHorizontally
                                )
                            )

                        }
                        InputField(
                            textFieldType = data.textFieldType,
                            maxLines = data.maxLines,
                            maxLength = data.maxLength,
                            minLength = data.minLength,
                            maxValue = data.maxValue,
                            minValue = data.minValue,
                            question = data.content,
                            data,
                            modifier = Modifier.weight(1f),
                            deleteComp = {
                                onDeleteComponent(it)
                            }
                        )
                        Spacer(modifier = Modifier.height(10.dp))
                    }
                }

                ComponentEnum.Dater -> {
                    DatePickerPreview(
                        componentData = data,
                        content = data.content,
                        deleteComp = {
                            onDeleteComponent(data)
                        }
                    ) 
                    Spacer(modifier = Modifier.height(10.dp))
                }

                else -> {
                    //
                }
            }
        }
    }


}