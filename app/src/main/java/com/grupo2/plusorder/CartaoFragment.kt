package com.grupo2.plusorder

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment

class CartaoFragment() : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {

        //Variaveis
        val view: View = inflater.inflate(R.layout.cartao_fragment, container, false)

        return  view
    }
}