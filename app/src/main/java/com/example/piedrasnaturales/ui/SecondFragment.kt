package com.example.piedrasnaturales.ui

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.bumptech.glide.Glide
import com.example.piedrasnaturales.MyViewModel.JoyasViewModel
import com.example.piedrasnaturales.R
import kotlinx.android.synthetic.main.fragment_second.*


class SecondFragment : Fragment() {

    lateinit var mJoyasViewModel: JoyasViewModel
    private var JoyasID = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mJoyasViewModel = ViewModelProvider(this).get(JoyasViewModel::class.java)
        arguments?.let {
                JoyasID = it.getInt("id")
        }

    }

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_second, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

            mJoyasViewModel.getOneID(JoyasID.toString()).observe(viewLifecycleOwner, Observer {
                if (it != null) {
                    Glide.with(this).load(it.image).into(imageView)
                    producto.text = it.nombreProducto
                    detalle.text = it.detalleDelProducto
                    Log.d("OBSERVADO", it.toString())
                }
            })

            compra.setOnClickListener {

                findNavController().navigate(R.id.action_SecondFragment_to_FirstFragment)
            }
        }
    }

