package uz.gita.jaxongir.adminformapp.data.request

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb


data class ComponentRequest(
    val id: String,
    val userId: String,
    val locId: Long,
    val idEnteredByUser: String = "",
    val content: String,
    val textFieldType: String,
    val maxLines: Int,
    val maxLength: Int,
    val minLength: Int,
    val maxValue: Int,
    val minValue: Int,
    val isMulti: Boolean,
    val variants: String,
    val selected: String,
    val connectedValues: String,
    val connectedIds: String,
    val operators: String,
    val type: String,
    val isRequired: Boolean,
    val imgUri: String = "",
    val ratioX: Int,
    val ratioY: Int,
    val customHeight: String,
    val rowId: String = "",
    val backgroundColor: Int = Color.Transparent.toArgb(),
    val weight: String,
    val imageType: String
)
