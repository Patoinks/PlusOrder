package com.grupo2.plusorder

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.textfield.TextInputEditText
import com.grupo2.plusorder.backend.models.Conta
import com.grupo2.plusorder.databinding.LoginActivityBinding
import com.grupo2.plusorder.databinding.QrcodeBinding
import com.grupo2.plusorder.databinding.RegistActivityBinding
import com.grupo2.plusorder.utils.backendutils.LoginUtils
import kotlinx.android.synthetic.main.login_activity.*


class LoginPage : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val binding = LoginActivityBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        registarbut.setOnClickListener {
            val intent = Intent(this,  RegistActivity::class.java)
            startActivity(intent)
            finish()
        }


        EmentaBotaao.setOnClickListener {
            val intent = Intent(this,  MainActivity::class.java)
            startActivity(intent)
            finish()
        }


        materialButton2.setOnClickListener {
            val intent = Intent(this,  QrCodeAtivity::class.java)
            startActivity(intent)
            finish()
        }

        (findViewById<View>(R.id.buttonLogin) as Button).setOnClickListener {

            var loggedConta : Conta? = LoginUtils.TryLogin(
                findViewById<TextInputEditText>(R.id.editTextEmail).text.toString(),
                findViewById<TextInputEditText>(R.id.editTextPassword).text.toString())

            if (loggedConta == null) {
                Toast.makeText(applicationContext, "Credenciais incorretas", Toast.LENGTH_LONG).show();
            } else {
                Toast.makeText(applicationContext,"Bem vindo "+ loggedConta.nome_pp, Toast.LENGTH_LONG).show();

                Constants.idCliente = loggedConta.id

                val intent = Intent(this,  MainActivity::class.java)
                startActivity(intent)
                finish()
            }
        }
    }
}