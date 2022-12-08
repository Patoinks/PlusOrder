package com.grupo2.plusorder

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.fragment.app.FragmentActivity
import androidx.fragment.app.FragmentTransaction
import androidx.recyclerview.widget.RecyclerView
import com.grupo2.plusorder.backend.models.Pedido
import com.grupo2.plusorder.backend.models.Prato
import com.squareup.picasso.Picasso


class HistoricoRecyclerAdapter(val pratos: List<Pedido>, val context: Context) :
    RecyclerView.Adapter<HistoricoRecyclerAdapter.MyViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {

        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.row_favoritos,
            parent,false)
        return MyViewHolder(itemView)

    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {

        val currentitem = pratos[position]

     /*   currentitem.id
        Picasso.get().load(imageURL).resize(600, 600).into(holder.imagemPrato)*/

    }



    override fun getItemCount(): Int {

        return pratos.size
    }


    class MyViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView){

        val imagemPrato : ImageView = itemView.findViewById(R.id.FavoritarImagem)

    }

}