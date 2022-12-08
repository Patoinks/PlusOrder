package com.grupo2.plusorder

import android.content.Context
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.WindowInsetsControllerCompat
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.grupo2.plusorder.backend.tables.BackendPedido
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class HistoricoActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_historico)

       // val adapta = HistoricoRecyclerAdapter(BackendPedido.GetPedidosByIdCliente(Constants.idCliente))
        val recicla : RecyclerView = findViewById(R.id.EmentaRecycler)
        recicla.layoutManager = GridLayoutManager(this,2)
       // recicla.adapter = adapta


    }
}