package com.grupo2.plusorder

import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import androidx.appcompat.app.AlertDialog
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.WindowInsetsControllerCompat
import androidx.fragment.app.Fragment
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.grupo2.plusorder.databinding.ActivityMainBinding
import com.grupo2.plusorder.utils.uiutils.AlertDialogUtils
import kotlinx.android.synthetic.main.fragment_ementa.*
import kotlinx.android.synthetic.main.fragment_ementa.view.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        //Variaveis
        val binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root // view. = findviewbyid
        setContentView(view)
        val ementaFragment = EmentaFragment()
        val qrcodeFragment = MaisFragment()
        val pedidosFragment = PedidosFragment()
        val perfilFragment = PerfilFragment()
        val bottomNavigationView = findViewById<BottomNavigationView>(R.id.bottom_navigation)

        //Barra de navegação, troca entre fragments
        replaceFragment(ementaFragment)
        bottomNavigationView.setOnItemSelectedListener {
            when (it.itemId) {
                R.id.page_1 -> replaceFragment(ementaFragment)
                R.id.page_2 -> replaceFragment(pedidosFragment)
                R.id.page_3 -> if (Constants.idCliente != null) replaceFragment(perfilFragment) else { AlertDialogUtils.ShowOkAlertBox("", "Realize o Login para aceder",this) }
                R.id.page_4 -> if (Constants.idCliente != null) replaceFragment(qrcodeFragment) else { AlertDialogUtils.ShowOkAlertBox("", "Realize o Login para aceder",this) }
            }
            true
        }
    }

    //Funcao que realiza a troca dos fragments
    private fun replaceFragment(fragment: Fragment)
    {
        if(fragment != null) {
            val transaction = supportFragmentManager.beginTransaction()
            transaction.replace(R.id.frame_layout, fragment)
            transaction.commit()
        }
    }
}

