package uz.gita.jaxongir.adminformapp.data.model

import com.google.gson.Gson
import uz.gita.jaxongir.adminformapp.data.enums.TextFieldType
import uz.gita.jaxongir.adminformapp.data.request.ComponentRequest

data class ComponentData(
    val id: String,                         //firebase id
    val locId: Int,                         //Screendagi indexi
    val idEnteredByUser: String = "",       //operator qoshishlik uchun id
    val content: String,                    //ekranda ko'rinadigan matn
    val textFieldType: TextFieldType,       //input bosa uning tipi
    val maxLines: Int,                      //tipi text bogan input bosa maximal qatorla soni
    val maxLength: Int,                     //tipi text bogan input bosa maximal uzunligi
    val minLength: Int,                     //tipi text bogan input bosa minimal uzunligi
    val maxValue: Int,                      //tipi number bogan inputga qiymatni maximal chegarasi
    val minValue: Int,                      //tipi number bogan inputga qiymatni minimal chegarasi,
    val isMulti: Int,                       //selelctor kop qiymat qabul qilish qimasligi
    val variants: List<String>,             //spinner bilan selectordagi variantla
    val selected: List<Boolean>,            //selectorda tanlangala listi
    val conditions: List<Conditions>,       //visibility bergichla
){
    private val converter = Gson()

    fun toRequest(): ComponentRequest = ComponentRequest(
        id = id,
        locId = locId,
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
        conditions = converter.toJson(conditions)
    )
}