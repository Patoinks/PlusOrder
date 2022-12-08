package com.grupo2.plusorder.backend.models

import org.json.JSONObject
import java.util.*

class PedidoPrato {

    // Attributes
    var idPrato: UUID? = null
    var idPedido: UUID? = null
    var quantidade: Integer? = null
    var precoUnidade: Double? = null

    // Constructors
    constructor(idPrato: UUID?, idPedido: UUID?, quantidade: Integer?, precoUnidade: Double?) {
        this.idPrato = idPrato
        this.idPedido = idPedido
        this.quantidade = quantidade
        this.precoUnidade = precoUnidade
    }

    // Functions
    fun toJSON(): JSONObject {
        return JSONObject()
            .put("idPrato", idPrato)
            .put("idPedido", idPedido)
            .put("quantidade", quantidade)
            .put("precoUnidade", precoUnidade);
    }

    companion object {
        fun fromJSON(jsonObject: JSONObject): PedidoPrato {
            return PedidoPrato(
                UUID.fromString(jsonObject["idPrato"] as? String),
                UUID.fromString(jsonObject["idPedido"] as? String),
                jsonObject["quantidade"] as? Integer,
                jsonObject["precoUnidade"] as? Double
            )
        }
    }
}