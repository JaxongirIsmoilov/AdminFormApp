package uz.gita.jaxongir.adminformapp.data.model

import com.google.gson.Gson
import uz.gita.jaxongir.adminformapp.data.enums.ComponentEnum
import uz.gita.jaxongir.adminformapp.data.enums.TextFieldType
import uz.gita.jaxongir.adminformapp.data.request.ComponentRequest
import uz.gita.jaxongir.adminformapp.data.source.database.entity.ComponentEntity
import java.io.Serializable

data class ComponentData(
    val id: String = "",                      //firebase id
    val userId: String = "",                     //qaysi user qoshgani
    val locId: Long = 0L,                        //Screendagi indexi
    val idEnteredByUser: String = "",       //operator qoshishlik uchun id
    val content: String = "",                    //ekranda ko'rinadigan matn
    val textFieldType: TextFieldType,       //input bosa uning tipi
    val maxLines: Int,                      //tipi text bogan input bosa maximal qatorla soni
    val maxLength: Int,                     //tipi text bogan input bosa maximal uzunligi
    val minLength: Int,                     //tipi text bogan input bosa minimal uzunligi
    val maxValue: Int,                      //tipi number bogan inputga qiymatni maximal chegarasi
    val minValue: Int,                      //tipi number bogan inputga qiymatni minimal chegarasi,
    val isMulti: Boolean,                   //selelctor kop qiymat qabul qilish qimasligi
    val variants: List<String> = listOf(),             //spinner bilan selectordagi variantla
    val selected: List<Boolean> = listOf(),            //selectorda tanlangala listi
    val connectedValues: List<String> = listOf(),      //visibitily boyicha berilgan qiymatlar
    val connectedIds: List<String> = listOf(),         //boglangan id lar
    val operators: List<String> = listOf(),            //berilgan operatorlar
    val type: ComponentEnum,
    val isRequired: Boolean = false
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
        isRequired = isRequired
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
        isRequired = isRequired
    )
}