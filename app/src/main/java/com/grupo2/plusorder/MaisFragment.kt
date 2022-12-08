package com.grupo2.plusorder

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.grupo2.plusorder.backend.models.Conta
import com.grupo2.plusorder.backend.tables.BackendConta
import com.grupo2.plusorder.databinding.FragmentMaisBinding
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.fragment_mais.*
import kotlinx.android.synthetic.main.fragment_mais.view.*
import kotlinx.android.synthetic.main.fragment_pedidos.view.*
import kotlinx.android.synthetic.main.fragment_perfil.view.*
import java.security.AccessController.getContext

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER

/**
 * A simple [Fragment] subclass.
 * Use the [MaisFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class MaisFragment : Fragment() {
    private var _binding: FragmentMaisBinding? = null
    private val binding get() = _binding!!
    // TODO: Rename and change types of parameters

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        // Inflate the layout for this fragment
        val view: View = inflater.inflate(R.layout.fragment_mais, container, false)

        // initialize account
        var CONTA : Conta? = BackendConta.GetConta(Constants.idCliente!!)

        // user
        view.UserName.text = CONTA!!.nome_pp
        var imgUrl = CONTA!!.imagem!!
        Picasso.get().load(imgUrl).resize(600, 600).into(view.UserImage)

        //
        view.btQrCode.setOnClickListener {
            val intent = Intent (this@MaisFragment.requireContext(), QrCodeAtivity::class.java)
            startActivity(intent)
        }

        view.btDefinicoes.setOnClickListener {
            val intent = Intent (this@MaisFragment.requireContext(), DefinicoesActivity::class.java)
            startActivity(intent)
        }

        view.btFaturas.setOnClickListener {
            val intent = Intent (this@MaisFragment.requireContext(), FaturasActivity::class.java)
            startActivity(intent)
        }

        view.btContactos.setOnClickListener {
            val intent = Intent (this@MaisFragment.requireContext(), ContactsActivity::class.java)
            startActivity(intent)
        }

        view.btSairConta.setOnClickListener {
            val intent = Intent (this@MaisFragment.requireContext(), TrocarContaActivity::class.java)
            startActivity(intent)
        }

        // Inflate the layout for this fragment
        return view
    }
}