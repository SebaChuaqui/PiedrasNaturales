package com.example.piedrasnaturales.model
import androidx.annotation.NonNull
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(tableName = "joyas_table")
data class PiedrasItem(
    @PrimaryKey
    @SerializedName("codigo")
    val codigo: String,
    @SerializedName("detalle del producto")
    val detalleDelProducto: String,
    @SerializedName("id")
    val id: Int,
    @SerializedName("image")
    val image: String,
    @SerializedName("nombre producto")
    val nombreProducto: String,
    @SerializedName("precio")
    val precio: String
)