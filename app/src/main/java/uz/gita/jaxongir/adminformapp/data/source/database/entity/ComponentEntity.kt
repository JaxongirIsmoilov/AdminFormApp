package uz.gita.jaxongir.adminformapp.data.source.database.entity

import androidx.compose.ui.graphics.Color
import androidx.room.Entity
import androidx.room.PrimaryKey
import uz.gita.jaxongir.adminformapp.data.enums.ComponentEnum
import uz.gita.jaxongir.adminformapp.data.enums.TextFieldType
import uz.gita.jaxongir.adminformapp.data.model.ComponentData
import java.io.Serializable

@Entity(tableName = "components")
data class ComponentEntity(
    @PrimaryKey(autoGenerate = false)
    val id: String,
    val userId: String,
    val locId: Long,
    val idEnteredByUser: String = "",
    val content: String,
    val textFieldType: TextFieldType,
    val maxLines: Int,
    val maxLength: Int,
    val minLength: Int,
    val maxValue: Int,
    val minValue: Int,
    val isMulti: Boolean,
    val variants: List<String>,
    val selected: List<Boolean>,
    val connectedValues: List<String>,
    val connectedIds: List<String>,
    val operators: List<String>,
    val type: ComponentEnum,
    val isRequired: Boolean,
    val imgUri : String = "",
    val ratioX : Int,
    val ratioY : Int,
    val customHeight : String,
    val rowId: String = "",
    val backgroundColor: Color = Color.Transparent
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
        imgUri = imgUri,
        ratioX = ratioX,
        ratioY = ratioY,
        customHeight = customHeight,
        rowId = rowId,
        backgroundColor = backgroundColor,
    )
}