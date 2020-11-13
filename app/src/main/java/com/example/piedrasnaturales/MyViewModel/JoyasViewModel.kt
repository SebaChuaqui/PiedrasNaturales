package com.example.piedrasnaturales.MyViewModel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import com.example.piedrasnaturales.model.JoyasDB
import com.example.piedrasnaturales.model.PiedrasItem
import com.example.piedrasnaturales.model.Repository

class JoyasViewModel(application: Application): AndroidViewModel(application) {

    private val mRepository: Repository
    val mAllJoyitas: LiveData<List<PiedrasItem>>

    init {

        val mJoyasDAO = JoyasDB.getDataBase(application).getJoyasDAO()
        mRepository = Repository(mJoyasDAO)
        mAllJoyitas = mRepository.mPiedras
        mRepository.getPiedrasFromServer()
    }

    fun getOneImage(image : String): LiveData<PiedrasItem>{
        return mRepository.getOneByImage(image)
    }

    fun getOneCodigo(codigo : String): LiveData<PiedrasItem>{
        return mRepository.getOneByCodigo(codigo)

    }

    fun getOneName(name: String): LiveData<PiedrasItem>{
        return mRepository.getOneByName(name)
    }

    fun getOnePrecio(precio: String): LiveData<PiedrasItem>{
        return mRepository.getOneByPrecio(precio)
    }

    fun getOneID(id: Int): LiveData<PiedrasItem>{
        return mRepository.getOneByID(id)
    }

    fun getOneDetalle(Detalle: String): LiveData<PiedrasItem>{
        return mRepository.getOneDetalles(Detalle)
    }
}