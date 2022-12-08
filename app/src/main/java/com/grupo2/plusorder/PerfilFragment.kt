package com.grupo2.plusorder

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.grupo2.plusorder.backend.models.Conta
import com.grupo2.plusorder.backend.tables.BackendCidade
import com.grupo2.plusorder.backend.tables.BackendConta
import com.grupo2.plusorder.backend.tables.BackendPrato
import com.grupo2.plusorder.databinding.FragmentPerfilBinding
import kotlinx.android.synthetic.main.fragment_perfil.view.*

class PerfilFragment : Fragment() {

    private var _binding: FragmentPerfilBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view: View = inflater.inflate(R.layout.fragment_perfil, container, false)


        var itens = BackendPrato.GetAllFavoritosByIdCliente(Constants.idCliente!!).toMutableList()
        val adapta = FavoritosRecyclerAdapter(itens, requireActivity())
        val recicla : RecyclerView = view.findViewById(R.id.recyclerView2)
        recicla.layoutManager = LinearLayoutManager(requireActivity(), RecyclerView.HORIZONTAL , false)
        recicla.adapter = adapta

        var CONTA : Conta? = BackendConta.GetConta(Constants.idCliente!!)

        view.textNome.text = CONTA!!.nome_pp
        view.textEmail.text = CONTA!!.email
        view.textCidade.text = BackendCidade.GetCidade(CONTA!!.idCidade!!)!!.cidade

        return view
    }
}