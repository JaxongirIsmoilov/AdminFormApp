package uz.gita.jaxongir.adminformapp.ui.helper

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import uz.gita.jaxongir.adminformapp.data.enums.TextFieldType
import uz.gita.jaxongir.adminformapp.data.model.Conditions
import uz.gita.jaxongir.adminformapp.ui.components.SampleSpinner

@Composable
fun InputContent(
    onSaveClickListener: (
            type: TextFieldType,
            maxLines: Int,
            maxLength: Int,
            minLength: Int,
            maxValue: Int,
            minValue: Int,
            required: Boolean,
            content: String,
            conditions: List<Conditions>
            ) -> Unit,
    id: String,
) {
        var type by remember {
                mutableStateOf(TextFieldType.Text)
        }
        LazyColumn(horizontalAlignment = Alignment.CenterHorizontally){
//                SampleSpinner(
//                        list = listOf(
//                                TextFieldType.Text.content,
//                                TextFieldType.Email.content,
//                                TextFieldType.Number.content,
//                                TextFieldType.Phone.content
//                        ),
//                        preselected = TextFieldType.Text.content,
//                        onSelectionChanged = {
//                                             when()
//                        },
//                        content =
//                )

        }

}