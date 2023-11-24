package uz.gita.jaxongir.adminformapp.data.request

import uz.gita.jaxongir.adminformapp.data.enums.TextFieldType
import uz.gita.jaxongir.adminformapp.data.model.Conditions

data class ComponentRequest (
    val id: String,
    val locId: Int,
    val idEnteredByUser: String = "",
    val content: String,
    val textFieldType: String,
    val maxLines: Int,
    val maxLength: Int,
    val minLength: Int,
    val maxValue: Int,
    val minValue: Int,
    val isMulti: Int,
    val variants: String,
    val selected: String,
    val conditions: String,
)