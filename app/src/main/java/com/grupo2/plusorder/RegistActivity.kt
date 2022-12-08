package com.grupo2.plusorder

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.grupo2.plusorder.backend.tables.BackendConta
import com.grupo2.plusorder.databinding.ActivityMainBinding
import com.grupo2.plusorder.databinding.RegistActivityBinding
import com.grupo2.plusorder.utils.backendutils.LoginUtils
import kotlinx.android.synthetic.main.regist_activity.view.*

class RegistActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val binding = RegistActivityBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        view.buttonRegister.setOnClickListener {
            var contaId = LoginUtils.TryRegister(binding.editTextNome.text.toString(),
                binding.editTextEmail.text.toString(),
                binding.editTextPassword.text.toString())

            // Check if conta registed
            if (contaId == null) {
                Toast.makeText(applicationContext, "Impossivel registar", Toast.LENGTH_LONG).show();
            } else {
                Toast.makeText(applicationContext,
                    "Bem vindo " + BackendConta.GetConta(contaId)!!.nome_pp,
                    Toast.LENGTH_LONG).show();

                Constants.idCliente = contaId

                val intent = Intent(this, MainActivity::class.java)
                startActivity(intent)
                finish()
            }
        }
    }
}