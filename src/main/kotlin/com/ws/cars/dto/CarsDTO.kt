package com.ws.cars.dto

import com.fasterxml.jackson.annotation.JsonProperty
import lombok.Getter
import lombok.Setter
import java.sql.Timestamp

@Getter
@Setter
class CarsDTO {

    var id: Int? = null

    @JsonProperty("timestamp_cadastro")
    var timestampCadastro: Timestamp? = null

    @JsonProperty("modelo_id")
    var modeloId: Int? = null

    var ano: Int? = null

    var combustivel: String? = null

    @JsonProperty("num_portas")
    var numPortas: Int? = null

    var cor: String? = null

    @JsonProperty("nome_modelo")
    var nomeModelo: String? = null

    var valor: Double? = null

    var brand: Int? = null
}