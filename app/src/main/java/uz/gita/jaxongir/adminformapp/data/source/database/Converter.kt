package uz.gita.jaxongir.adminformapp.data.source.database

import androidx.compose.ui.graphics.Color
import androidx.room.TypeConverter
import com.google.gson.Gson
import uz.gita.jaxongir.adminformapp.data.enums.ComponentEnum

class Converter {
    private val converter = Gson()
    @TypeConverter
    fun stringConverter(data: List<String>): String = converter.toJson(data)

    @TypeConverter
    fun booleanConverter(data: List<Boolean>): String = converter.toJson(data)

    @TypeConverter
    fun typeConverter(data: ComponentEnum): String = converter.toJson(data)

    @TypeConverter
    fun colorConverter(data: Color): String = converter.toJson(data)

    @TypeConverter
    fun stringConverter(data: String): List<String> =
        converter.fromJson(data, Array<String>::class.java).asList()

    @TypeConverter
    fun booleanConverter(data: String): List<Boolean> =
        converter.fromJson(data, Array<Boolean>::class.java).asList()

    @TypeConverter
    fun typeConverter(data: String): ComponentEnum = converter.fromJson(data, ComponentEnum::class.java)

     @TypeConverter
     fun colorConverter(data: String): Color = converter.fromJson(data, Color::class.java)
}