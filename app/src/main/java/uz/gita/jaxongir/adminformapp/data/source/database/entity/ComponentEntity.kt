package uz.gita.jaxongir.adminformapp.data.source.database.entity

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.room.Entity
import androidx.room.PrimaryKey
import uz.gita.jaxongir.adminformapp.data.enums.ComponentEnum
import uz.gita.jaxongir.adminformapp.data.enums.ImageTypeEnum
import uz.gita.jaxongir.adminformapp.data.enums.TextFieldType
import uz.gita.jaxongir.adminformapp.data.model.ComponentData

@Entity(tableName = "components")
data class ComponentEntity(
    @PrimaryKey(autoGenerate = false)
    val id: String = "",
    val userId: String = "",
    val locId: Long = 0L,
    val idEnteredByUser: String = "",
    val content: String = "",
    val textFieldType: TextFieldType,
    val maxLines: Int,
    val maxLength: Int,
    val minLength: Int,
    val maxValue: Int,
    val minValue: Int,
    val isMulti: Boolean,
    val variants: List<String> = listOf(),
    val selected: List<Boolean> = listOf(),
    val connectedValues: List<String> = listOf(),
    val connectedIds: List<String> = listOf(),
    val operators: List<String> = listOf(),
    val type: ComponentEnum,
    val isRequired: Boolean = false,
    val imgUri : String = "",
    val ratioX : Int,
    val ratioY : Int,
    val customHeight : String,
    val rowId: String = "",
    val backgroundColor: Int = Color.Transparent.toArgb(),
    val weight: String,
    val imageType: ImageTypeEnum,
    val inValues: List<String> = listOf()
) {


    fun toData(): ComponentData = ComponentData(
        id = id,
        userId = userId,
        locId = locId,
        idEnteredByUser = idEnteredByUser,
        content = content,
        textFieldType = textFieldType,
        maxLines = maxLines,
        maxLength = maxLength,
        minLength = minLength,
        maxValue = maxValue,
        minValue = minValue,
        isMulti = isMulti,
        variants = variants,
        selected = selected,
        connectedValues = connectedValues,
        connectedIds = connectedIds,
        operators = operators,
        type = type,
        isRequired = isRequired,
        imgUri,
        ratioX,
        ratioY,
        customHeight,
        rowId,
        backgroundColor,
        weight,
        imageType,
        inValues,
    )


}