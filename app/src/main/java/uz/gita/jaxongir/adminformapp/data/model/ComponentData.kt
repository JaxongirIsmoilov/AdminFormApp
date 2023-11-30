package uz.gita.jaxongir.adminformapp.data.model

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.text.font.FontWeight
import com.google.gson.Gson
import uz.gita.jaxongir.adminformapp.data.enums.ComponentEnum
import uz.gita.jaxongir.adminformapp.data.enums.ImageTypeEnum
import uz.gita.jaxongir.adminformapp.data.enums.TextFieldType
import uz.gita.jaxongir.adminformapp.data.request.ComponentRequest
import uz.gita.jaxongir.adminformapp.data.source.database.entity.ComponentEntity
import java.io.Serializable

data class ComponentData(
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
    val type: ComponentEnum = ComponentEnum.SampleText,
    val isRequired: Boolean = false,
    val imgUri: String = "",
    val ratioX: Int = 0,
    val ratioY: Int = 0,
    val customHeight: String = "",
    val rowId: String = "",
    val backgroundColor: Int = Color.Transparent.toArgb(),
    val weight: String,
    val imageType: ImageTypeEnum = ImageTypeEnum.NONE,
    val inValues: List<String> = listOf()
) : Serializable {
    private val converter = Gson()

    fun toRequest(): ComponentRequest = ComponentRequest(
        id = id,
        locId = locId,
        userId = userId,
        idEnteredByUser = idEnteredByUser,
        content = content,
        textFieldType = converter.toJson(textFieldType),
        maxLines = maxLines,
        maxLength = maxLength,
        minLength = minLength,
        maxValue = maxValue,
        minValue = minValue,
        isMulti = isMulti,
        variants = converter.toJson(variants),
        selected = converter.toJson(selected),
        connectedValues = converter.toJson(connectedValues),
        connectedIds = converter.toJson(connectedIds),
        operators = converter.toJson(operators),
        type = converter.toJson(type),
        isRequired = isRequired,
        imgUri = imgUri,
        ratioX = ratioX,
        ratioY = ratioY,
        customHeight = customHeight,
        rowId = rowId,
        backgroundColor = backgroundColor,
        weight = weight,
        imageType = converter.toJson(imageType),
        inValues = converter.toJson(inValues)
    )

    fun toEntity(): ComponentEntity = ComponentEntity(
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
        weight = weight,
        imageType = imageType
    )
}