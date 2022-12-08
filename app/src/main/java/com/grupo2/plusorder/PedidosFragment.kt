package com.grupo2.plusorder

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout.VERTICAL
import androidx.core.view.isGone
import androidx.fragment.app.FragmentActivity
import androidx.fragment.app.FragmentTransaction
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.grupo2.plusorder.R
import com.grupo2.plusorder.backend.models.Prato
import com.grupo2.plusorder.backend.tables.BackendCategoria
import com.grupo2.plusorder.backend.tables.BackendPrato
import com.grupo2.plusorder.databinding.CartaoFragmentBinding
import com.grupo2.plusorder.databinding.FragmentPedidosBinding
import com.grupo2.plusorder.databinding.FragmentPerfilBinding
import com.grupo2.plusorder.utils.backendutils.PedidoUtils
import kotlinx.android.synthetic.main.fragment_ementa.*
import kotlinx.android.synthetic.main.fragment_ementa.view.*
import kotlinx.android.synthetic.main.fragment_pedidos.*
import kotlinx.android.synthetic.main.fragment_pedidos.view.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import java.util.*


class PedidosFragment : Fragment() {

    var prato : Prato? = null
    private var _binding: FragmentPedidosBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view: View = inflater.inflate(R.layout.fragment_pedidos, container, false)
        var total : Double = 0.00
        var total2 : Double = 0.00
        var itens : MutableList<Prato> = arrayListOf()
        var itens2 : MutableList<Prato> = arrayListOf()



        val adapta = PedidoRecyclerAdapter(PedidoUtils.pratosFromPedidoTemp.toMutableList(), requireActivity())
        val recicla : RecyclerView = view.findViewById(R.id.recyclerView)
        recicla.layoutManager = LinearLayoutManager(requireActivity(), RecyclerView.VERTICAL, false)
        recicla.adapter = adapta

        val adapta2 = AdicionadoRecyclerAdapter(itens2, requireActivity())
        val recicla2 : RecyclerView = view.findViewById(R.id.recyclerView2)
        recicla2.layoutManager = LinearLayoutManager(requireActivity(), RecyclerView.VERTICAL, false)
        recicla2.adapter = adapta2


        view.limpar.setOnClickListener{

            PedidoUtils.LimparPedidoTemp()
            recicla.adapter!!.notifyDataSetChanged()
        }

        view.FinalizarRefeicao.setOnClickListener{
            PedidoUtils.FinishPedidoTemp()

            val fragmentManager = (context as FragmentActivity).supportFragmentManager
            val fragmentTransaction: FragmentTransaction = fragmentManager.beginTransaction()
            fragmentTransaction.replace(R.id.frame_layout2, CartaoFragment())
            fragmentTransaction.addToBackStack(null).commit()
        }

        view.IniciarPedido.setOnClickListener{
            for(prato in PedidoUtils.pratosFromPedidoTemp) {
                itens2.add(prato)

                total2 += prato.preco!!.toDouble()
            }
            PedidoUtils.LimparPedidoTemp()
            recicla.adapter!!.notifyDataSetChanged()

        }
        for (prato in PedidoUtils.pratosFromPedidoTemp)
            total += prato.preco!!.toDouble()




        view.TotalPedido.text = total.toString().plus(" Eur")

        view.textView12.text = total2.toString().plus(" Eur")


        return view
    }

}