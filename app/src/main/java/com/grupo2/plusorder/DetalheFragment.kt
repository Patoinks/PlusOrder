package com.grupo2.plusorder

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.view.isGone
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.fragment.app.FragmentTransaction
import com.grupo2.plusorder.Constants.idPrato
import com.grupo2.plusorder.backend.models.Favorito
import com.grupo2.plusorder.backend.tables.BackendFavorito
import com.grupo2.plusorder.backend.tables.BackendPrato
import com.grupo2.plusorder.utils.backendutils.PedidoUtils
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_main.view.*
import kotlinx.android.synthetic.main.details.*
import kotlinx.android.synthetic.main.details.view.*
import kotlinx.android.synthetic.main.ementa_prato.*
import kotlinx.android.synthetic.main.ementa_prato.view.*
import kotlinx.android.synthetic.main.fragment_perfil.view.*
import java.util.*


class DetalheFragment() : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {

        //Variaveis
        val view: View = inflater.inflate(R.layout.details, container, false)
        var prato = BackendPrato.GetPrato(Constants.idPrato!!)
        var imageURL = prato!!.imagem

        //Popular Detalhes
        Picasso.get().load(imageURL).resize(600, 600).into(view.imageDetail)
        view.nomeDetalhe.text = prato!!.nome
        view.precoPrato.text = prato!!.preco.toString().plus(" Eur")
        view.descricao.text = prato!!.descricao

        //Se não tiver logado, não ha opção de favoritar
        if (Constants.idCliente == null)
        {
            view.favoritado.isGone = true
            view.favoritar.isGone = true
        }
        else
        {
            var favorito = BackendFavorito.GetFavoritoByIdClienteAndIdPrato(Constants.idCliente!!, idPrato!!)
            if (favorito != null)
            {
                view.favoritado.isGone = false
                view.favoritar.isGone = true
            }
        }


        //Favoritar Prato
        view.favoritar.setOnClickListener{
            view.favoritado.isGone = false
            view.favoritar.isGone = true
            BackendFavorito.AddFavorito(Favorito(UUID.randomUUID(), idPrato, Constants.idCliente ))
        }
        view.favoritado.setOnClickListener{
            view.favoritado.isGone = true
            view.favoritar.isGone = false
            var favoritoid = BackendFavorito.GetFavoritoByIdClienteAndIdPrato(Constants.idCliente!!, idPrato!!)
            BackendFavorito.DeleteFavorito(favoritoid!!.id!!)
        }

        view.adicionarPedido.setOnClickListener{
            // if doesnt contain prato already add
            if (!PedidoUtils.PratoExists(prato!!.id!!))
            {
                PedidoUtils.AddPratoToPedidoTemp(prato)
                Toast.makeText(context,"Prato " + prato.nome + " adicionado ao pedido!", Toast.LENGTH_LONG).show();
                view.adicionarPedido.text = "Eliminar do pedido"
            }
            else
            {
                PedidoUtils.DeletePratoFromPedidoTemp(prato)
                Toast.makeText(context,"Prato " + prato.nome + " eliminado do pedido!", Toast.LENGTH_LONG).show();
                view.adicionarPedido.text = "Adicionar ao pedido"
            }

        }

        return view
    }
}