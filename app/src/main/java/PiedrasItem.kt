
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(tableName = "joyas_table")
data class PiedrasItem(
    @PrimaryKey
    @SerializedName("codigo")
    val codigo: String,
    @SerializedName("Detalle del Producto")
    val detalleDelProducto: String,
    @SerializedName("id")
    val id: Int,
    @SerializedName("image")
    val image: String,
    @SerializedName("Nombre Producto")
    val nombreProducto: String,
    @SerializedName("Precio")
    val precio: Int
)