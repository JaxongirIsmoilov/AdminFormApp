package uz.gita.jaxongir.adminformapp.data.source.database.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import uz.gita.jaxongir.adminformapp.data.enums.ComponentEnum
import uz.gita.jaxongir.adminformapp.data.enums.TextFieldType
import uz.gita.jaxongir.adminformapp.data.model.ComponentData
import uz.gita.jaxongir.adminformapp.data.model.Conditions

@Entity(tableName = "components")
data class ComponentEntity(
    @PrimaryKey(autoGenerate = false)
    val id: String,
    val userId: String,
    val locId: Int,
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
    val conditions: List<Conditions>,
    val type: ComponentEnum,
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
        conditions = conditions,
        type = type
    )
}