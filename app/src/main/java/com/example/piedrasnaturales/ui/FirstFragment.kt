package com.example.piedrasnaturales.ui

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.piedrasnaturales.Adapter.JoyasAdapter
import com.example.piedrasnaturales.MyViewModel.JoyasViewModel
import com.example.piedrasnaturales.R
import com.example.piedrasnaturales.model.PiedrasItem
import kotlinx.android.synthetic.main.fragment_first.*


class FirstFragment : Fragment(), JoyasAdapter.Joyas {

    lateinit var  mJoyasViewModel: JoyasViewModel
    var mId: Int? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mJoyasViewModel = ViewModelProvider(this).get(JoyasViewModel::class.java)
    }

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_first, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val mRecyclerView = recyclerView
        val mAdapter = JoyasAdapter(this)

        mRecyclerView.adapter = mAdapter
        mRecyclerView.layoutManager = GridLayoutManager(context, 2)

        mJoyasViewModel.mAllJoyitas.observe(viewLifecycleOwner, Observer{
            mAdapter.updateListJoyas(it)
            Log.d("funciona", it.toString())
        })

        }

    override fun passJoyas(mJoyas: PiedrasItem) {
        val mBundle = Bundle()
        mBundle.putString("imagen", mJoyas.image)
        mBundle.putString("Producto", mJoyas.nombreProducto)
        mBundle.putString("Detalle", mJoyas.detalleDelProducto)
        mBundle.putString("Codigo", mJoyas.codigo)
        mBundle.putString("Precio", mJoyas.precio)
        findNavController().navigate(R.id.action_FirstFragment_to_SecondFragment, mBundle)
    }
}
