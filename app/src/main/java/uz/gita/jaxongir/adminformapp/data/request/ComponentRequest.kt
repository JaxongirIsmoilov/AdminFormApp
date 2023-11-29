package uz.gita.jaxongir.adminformapp.data.request

import androidx.compose.ui.graphics.Color
import java.io.Serializable


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
    val imgUri : String = "",
    val ratioX : Int,
    val ratioY : Int,
    val customHeight : String,
    val rowId: String = "",
    val backgroundColor: Color = Color.Transparent
)