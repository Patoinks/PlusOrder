package com.grupo2.plusorder

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.grupo2.plusorder.databinding.ActivityDefinicoesBinding
import com.grupo2.plusorder.databinding.ContactosBinding

class ContactsActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ContactosBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)




    }
}