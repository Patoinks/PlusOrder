package com.grupo2.plusorder

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.grupo2.plusorder.backend.models.Conta
import com.grupo2.plusorder.backend.tables.BackendConta
import com.grupo2.plusorder.databinding.TrocarContaBinding
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.fragment_perfil.view.*
import kotlinx.android.synthetic.main.trocar_conta.view.*

class TrocarContaActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val binding = TrocarContaBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        var CONTA : Conta? = BackendConta.GetConta(Constants.idCliente!!)

        view.textView5.text = CONTA!!.nome_pp
        var imgUrl = CONTA!!.imagem!!
        Picasso.get().load(imgUrl).resize(600, 600).into(view.shapeableImageView)

        view.button2.setOnClickListener {
            Constants.idCliente = null
            val intent = Intent(this,  LoginPage::class.java)
            startActivity(intent)
            finish()
        }
    }
}