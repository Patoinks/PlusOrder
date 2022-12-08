package com.grupo2.plusorder

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.grupo2.plusorder.backend.models.Conta
import com.grupo2.plusorder.backend.models.Prato
import com.grupo2.plusorder.backend.tables.BackendCidade
import com.grupo2.plusorder.backend.tables.BackendConta
import com.grupo2.plusorder.backend.tables.BackendPrato
import com.grupo2.plusorder.databinding.ActivityDefinicoesBinding
import com.grupo2.plusorder.databinding.FragmentPerfilBinding
import com.grupo2.plusorder.databinding.LoginActivityBinding
import com.grupo2.plusorder.databinding.RegistActivityBinding
import com.grupo2.plusorder.utils.PerfilUtils
import com.grupo2.plusorder.utils.backendutils.LoginUtils
import kotlinx.android.synthetic.main.activity_definicoes.view.*
import kotlinx.android.synthetic.main.fragment_perfil.view.*
import kotlinx.android.synthetic.main.regist_activity.view.*
import java.util.*

class DefinicoesActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val binding = ActivityDefinicoesBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        var CONTA : Conta? = BackendConta.GetConta(Constants.idCliente!!)


        view.btAtualizar.setOnClickListener{
            var contaId = PerfilUtils.TryUpdate(CONTA!!.id!!, binding.textNome.text.toString(), CONTA!!.pass!!, binding.textEmail.text.toString(), CONTA!!.NIF!!, CONTA!!.dataNasc!!, CONTA!!.idCidade!!, CONTA!!.imagem!!)

            // Check if conta
            if (contaId == null) {
                Toast.makeText(applicationContext, "Impossivel atualizar", Toast.LENGTH_LONG).show();
            } else {
                Toast.makeText(applicationContext,"Utilizador(a) "+ BackendConta.GetConta(CONTA!!.id!!)!!.nome_pp+ "atualizado com sucesso", Toast.LENGTH_LONG).show();

                Constants.idCliente = CONTA!!.id!!

                val intent = Intent(this,  DefinicoesActivity::class.java)
                startActivity(intent)
                finish()
            }

        }
    }
}