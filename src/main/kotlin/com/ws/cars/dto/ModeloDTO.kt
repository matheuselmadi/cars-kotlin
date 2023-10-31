package com.ws.cars.dto

import com.fasterxml.jackson.annotation.JsonProperty
import lombok.Getter
import lombok.Setter

@Getter
@Setter
class ModeloDTO {

    var id: Int? = null

    @JsonProperty("marca_id")
    var marcaId: Int? = null

    var nome: String? = null

    @JsonProperty("valor_fipe")
    var valorFipe: Double? = null
}