package com.ws.cars.dto

import com.fasterxml.jackson.annotation.JsonProperty
import java.sql.Timestamp

data class CarroDTO(

    var id: Int? = null,

    @JsonProperty("timestamp_cadastro")
    var timestampCadastro: Timestamp? = null,

    @JsonProperty("modelo_id")
    var modeloId: Int? = null,

    var ano: Int? = null,

    var combustivel: String? = null,

    @JsonProperty("num_portas")
    var numPortas: Int? = null,

    var cor: String? = null
)
