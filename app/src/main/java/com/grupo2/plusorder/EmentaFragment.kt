package com.grupo2.plusorder

import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.grupo2.plusorder.backend.models.Mesa
import com.grupo2.plusorder.backend.models.Prato
import com.grupo2.plusorder.backend.tables.BackendCategoria
import com.grupo2.plusorder.backend.tables.BackendMesa
import com.grupo2.plusorder.backend.tables.BackendPrato
import kotlinx.android.synthetic.main.fragment_ementa.*
import kotlinx.android.synthetic.main.fragment_ementa.view.*
import java.util.*


class EmentaFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        //Variaveis
        val view: View = inflater.inflate(R.layout.fragment_ementa, container, false)
        var itens : MutableList<Prato> = arrayListOf()
        var idCat : UUID? = BackendCategoria.GetCategoriaIdByName(view.CategoriaEntrada.text.toString())
        var pratos = BackendPrato.GetAllPratos().toMutableList()
        var mesas : Mesa? = null

        //Popular a reciclerview
        val adapta = EmentaRecyclerAdapter(itens, requireActivity())
        val recicla : RecyclerView = view.findViewById(R.id.EmentaRecycler)
        recicla.layoutManager = GridLayoutManager(requireActivity(),1)
        recicla.adapter = adapta
        for (prato in pratos) {
            itens.add(prato)
        }

        //Verificar se a mesa está conectada
        if (Constants.idMesa != null)
        {
            mesas = BackendMesa.GetMesa(Constants.idMesa!!)
            view.numeroMesa!!.text = mesas!!.nrMesa.toString()
        }

        //Barra de Pesquisas
        view.pesquisinha.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, p1: Int, p2: Int, p3: Int) {}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                if (s!!.isNotEmpty()) {
                    itens.clear()
                    val search = s
                    pratos = BackendPrato.SearchPratosByNameCategoria(search.toString(), idCat!!).toMutableList()
                    for (prato in pratos) {
                        if (prato.nome!!.contains(search)) {
                            itens.add(prato)
                        }
                    }
                    recicla.adapter!!.notifyDataSetChanged()
                    CategoriaEscolhida.text = "Pesquisar"
                }
            }
            override fun afterTextChanged(s: Editable?) {}
        })

        //Botões de Categorias
        view.CategoriaEscolhida.text = "Ementa"
        view.CategoriaEntrada.setOnClickListener {
            itens.clear()
             idCat = BackendCategoria.GetCategoriaIdByName(view.CategoriaEntrada.text.toString())
            pratos = BackendPrato.GetAllPratosByCategoria(idCat!!).toMutableList()
            for (prato in pratos)
            {
                itens.add(prato)
            }
            CategoriaEscolhida.text = CategoriaEntrada.text
            recicla.adapter!!.notifyDataSetChanged()
        }

        view.CategoriaPratos.setOnClickListener {
            itens.clear()
            idCat  = BackendCategoria.GetCategoriaIdByName(view.CategoriaPratos.text.toString())
             pratos = BackendPrato.GetAllPratosByCategoria(idCat!!).toMutableList()
            for (prato in pratos)
            {
                itens.add(prato)
            }
            CategoriaEscolhida.text = CategoriaPratos.text
            recicla.adapter!!.notifyDataSetChanged()
        }

        view.CategoriaBebidas.setOnClickListener {
            itens.clear()
            idCat = BackendCategoria.GetCategoriaIdByName(view.CategoriaBebidas.text.toString())
            pratos = BackendPrato.GetAllPratosByCategoria(idCat!!).toMutableList()
            for (prato in pratos)
            {
                itens.add(prato)
            }
            CategoriaEscolhida.text = CategoriaBebidas.text
            recicla.adapter!!.notifyDataSetChanged()
        }

        view.CategoriaSobremesa.setOnClickListener {
            itens.clear()
            idCat = BackendCategoria.GetCategoriaIdByName(view.CategoriaSobremesa.text.toString())
            pratos = BackendPrato.GetAllPratosByCategoria(idCat!!).toMutableList()
            for (prato in pratos)
            {
                itens.add(prato)
            }
            CategoriaEscolhida.text = CategoriaSobremesa.text
            recicla.adapter!!.notifyDataSetChanged()
        }

        //QrCode
        view.numeroMesa.setOnClickListener{
            val intent = Intent(requireActivity(),  QrCodeAtivity::class.java)
            startActivity(intent)
        }

        return view
    }
}