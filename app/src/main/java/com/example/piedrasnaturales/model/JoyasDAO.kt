package com.example.piedrasnaturales.model

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface JoyasDAO {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insertAllJoyas(mJoyasList: List<PiedrasItem>)

    @Query("SELECT * FROM joyas_table")
    fun getAllJoyasFromDB(): LiveData<List<PiedrasItem>>

    @Query("SELECT * FROM joyas_table WHERE id=:mId")
    fun getByID(mId: Int): LiveData<PiedrasItem>

    @Query("SELECT * FROM joyas_table WHERE codigo=:mCodigo")
    fun getCodigoByID(mCodigo: String): LiveData<PiedrasItem>

    @Query("SELECT * FROM joyas_table WHERE image=:mImage")
    fun getImageByID(mImage: String): LiveData<PiedrasItem>

    @Query("SELECT * FROM joyas_table WHERE nombreProducto=:mNombreProducto")
    fun getProductoByID(mNombreProducto: String): LiveData<PiedrasItem>

    @Query("SELECT * FROM joyas_table WHERE precio=:mPrecio")
    fun getPrecioByID(mPrecio: String): LiveData<PiedrasItem>

    @Query("SELECT * FROM joyas_table WHERE detalleDelProducto=:mDetalleDelProducto")
    fun getDetalleByID(mDetalleDelProducto: String): LiveData<PiedrasItem>


}