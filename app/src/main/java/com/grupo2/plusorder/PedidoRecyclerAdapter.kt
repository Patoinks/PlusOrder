package com.grupo2.plusorder

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
    import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import androidx.recyclerview.widget.RecyclerView
import com.grupo2.plusorder.backend.models.Prato
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.fragment_ementa.*


class PedidoRecyclerAdapter(val pratos: List<Prato>, val context: Context) :
    RecyclerView.Adapter<PedidoRecyclerAdapter.MyViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {

        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.row_pedido,
            parent,false)
        return MyViewHolder(itemView)

    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {

        val currentitem = pratos[position]
        var imageURL = currentitem.imagem
        Picasso.get().load(imageURL).resize(600, 600).into(holder.imagemPrato)
        holder.nomePrato.text = currentitem.nome
        holder.preco.text = currentitem.preco.toString().plus(" €")


    }



    override fun getItemCount(): Int {

        return pratos.size
    }


    class MyViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView){

        val nomePrato : TextView = itemView.findViewById(R.id.NomePedido)
        val imagemPrato : ImageView = itemView.findViewById(R.id.ImagemPedido)
        val preco : TextView = itemView.findViewById(R.id.tPrecoPedido)

    }

}