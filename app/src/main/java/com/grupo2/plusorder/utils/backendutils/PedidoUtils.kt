package com.grupo2.plusorder.utils.backendutils

import com.grupo2.plusorder.Constants
import com.grupo2.plusorder.backend.models.Pedido
import com.grupo2.plusorder.backend.models.PedidoPrato
import com.grupo2.plusorder.backend.models.Prato
import com.grupo2.plusorder.backend.tables.BackendPedido
import com.grupo2.plusorder.backend.tables.BackendPedidoPrato
import java.lang.Exception
import java.util.*

object PedidoUtils {

    var pratosFromPedidoTemp = arrayListOf<Prato>()

    fun AddPratoToPedidoTemp(prato: Prato){
        pratosFromPedidoTemp.add(prato)
    }

    fun DeletePratoFromPedidoTemp(prato: Prato){
        pratosFromPedidoTemp.remove(prato)
    }

    fun LimparPedidoTemp(){
        pratosFromPedidoTemp.clear()
    }

    fun PratoExists(idPrato: UUID): Boolean{
        var pratoExists = false

        for (prato in pratosFromPedidoTemp)
            if (prato.id == idPrato)
                pratoExists = true

        return pratoExists
    }

    fun FinishPedidoTemp() : Pedido?{
        // Check if idMesa and idCliente exist
        if (Constants.idMesa == null || Constants.idCliente == null)
            throw Exception("Cliente ou Mesa nÃ£o registado")

        // Create and store
        var pedido: Pedido? = BackendPedido.AddPedido(Pedido(
            Constants.EMPTY_GUID, Constants.idMesa, Constants.DEFAULT_FUNCIONARIO, Constants.idCliente, Integer(1)))
            ?: throw Exception("Impossivel adicionar pedido")

        // Add Pratos to Pedido
        for (prato in pratosFromPedidoTemp){
            AddPratoToPedido(pedido!!, prato, Integer(1))
        }

        // Clear Pedido
        LimparPedidoTemp()

        // return pedido
        return pedido
    }

    fun AddPratoToPedido(pedido: Pedido, prato: Prato, qtd: Integer) : Boolean {
        return BackendPedidoPrato.AddPedidoPrato(PedidoPrato(
            prato.id, pedido.id, qtd, prato.preco
        ))
    }
}