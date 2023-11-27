package uz.gita.jaxongir.adminformapp.data.request


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
    val connectedValues: String,      //visibitily boyicha berilgan qiymatlar
    val connectedIds: String,         //boglangan id lar
    val operators: String,
    val type: String,
)