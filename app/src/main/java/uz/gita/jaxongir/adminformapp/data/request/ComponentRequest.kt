package uz.gita.jaxongir.adminformapp.data.request



data class ComponentRequest (
    val userId: String,
    val locId: Int,
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
    val conditions: String,
    val type: String,
)