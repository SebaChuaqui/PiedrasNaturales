package com.example.piedrasnaturales.model

import android.util.Log
import androidx.lifecycle.LiveData
import com.example.piedrasnaturales.Retrofit.RetrofitClient
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class Repository(private val mJoyasDAO: JoyasDAO) {

    private val service = RetrofitClient.getRetroJoyas()

    val mPiedras = mJoyasDAO.getAllJoyasFromDB()

    val mDataPiedrasDBList = mutableListOf<Piedras>()

    fun getPiedrasFromServer(){

        Log.d("hola", "hi")
        val mCall = service.getJoyasFromApi()
        mCall.enqueue(object: Callback<List<PiedrasItem>>{
            override fun onResponse(
                call: Call<List<PiedrasItem>>,
                response: Response<List<PiedrasItem>>
            ) {
                Log.d("otro", response.body().toString())
                when (response.code()){
                    in 200..299 -> CoroutineScope(Dispatchers.IO).launch {
                        response.body()?.let {
                            Log.d("joyas", it.toString())
                            mJoyasDAO.insertAllJoyas(it)
                        }
                    }
                    in 300..399 -> Log.d("ERROR 300", response.errorBody().toString())

                }
            }

            override fun onFailure(call: Call<List<PiedrasItem>>, t: Throwable) {
                Log.d("ERROR", t.message.toString())
            }

        })
    }

    fun getDataFromNetWorkCoroutines() = CoroutineScope(Dispatchers.IO).launch {
        val service = kotlin.runCatching { service.getDataFromApiCoroutines() }
        service.onSuccess {
            when(it.code()) {
                in 200..299 -> CoroutineScope(Dispatchers.IO).launch {
                    it.body()?.let {
                    mJoyasDAO.insertAllJoyas(it)
                    }
                }
            }
        }
        service.onFailure {
            Log.d("ERROR", it.message.toString())
        }
    }

    fun getOneByCodigo(codigo: String): LiveData<PiedrasItem>{
        return mJoyasDAO.getCodigoByID(codigo)
    }

    fun getOneByImage(image: String): LiveData<PiedrasItem>{
        return mJoyasDAO.getImageByID(image)
    }

    fun getOneByName(name: String): LiveData<PiedrasItem>{
        return mJoyasDAO.getProductoByID(name)
    }

    fun getOneByPrecio(precio: String): LiveData<PiedrasItem>{
        return mJoyasDAO.getPrecioByID(precio)
    }

    fun getOneByID(id: String?): LiveData<PiedrasItem>{
        return mJoyasDAO.getByID(id)
    }

    fun getOneDetalles(detalleDelProducto: String): LiveData<PiedrasItem>{
        return mJoyasDAO.getDetalleByID(detalleDelProducto)
    }

}