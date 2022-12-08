package com.grupo2.plusorder

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.FragmentActivity
import androidx.fragment.app.FragmentTransaction
import androidx.recyclerview.widget.RecyclerView
import com.grupo2.plusorder.backend.models.Prato
import com.squareup.picasso.Picasso


class EmentaRecyclerAdapter(val pratos: List<Prato>, val context: Context) :
    RecyclerView.Adapter<EmentaRecyclerAdapter.MyViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {

        //Declarar ItemView como RecyclerView
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.ementa_prato,
            parent,false)
        return MyViewHolder(itemView)

    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {

        //Variaveis
        val currentitem = pratos[position]
        var imageURL = currentitem.imagem

        //Popular RecyclerView
        Picasso.get().load(imageURL).resize(800, 800).into(holder.imagemPrato)
        holder.nomePrato.text = currentitem.nome
        holder.preco.text = currentitem.preco.toString().plus(" Eur")
        holder.tempo.text = currentitem.tempoEstimado.toString().plus(" Min")

        //Clicar numa row da RecyclerView
        holder.itemView.setOnClickListener{
            Constants.idPrato = currentitem.id
            val fragmentManager = (context as FragmentActivity).supportFragmentManager
            val fragmentTransaction: FragmentTransaction = fragmentManager.beginTransaction()
            fragmentTransaction.replace(R.id.frame_layout2, DetalheFragment())
            fragmentTransaction.addToBackStack(null).commit()
        }
    }

    override fun getItemCount(): Int {

        //Tamanho
        return pratos.size
    }

    class MyViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView){

        //Declarar Variaveis de Views
        val nomePrato : TextView = itemView.findViewById(R.id.NomePrato)
        val imagemPrato : ImageView = itemView.findViewById(R.id.ImagemPrato)
        val tempo : TextView = itemView.findViewById(R.id.Tempo)
        val preco : TextView = itemView.findViewById(R.id.preco)
    }
}