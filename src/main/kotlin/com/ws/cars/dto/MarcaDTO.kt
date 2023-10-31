package com.ws.cars.dto

import com.fasterxml.jackson.annotation.JsonProperty
import lombok.Getter
import lombok.Setter

@Getter
@Setter
class MarcaDTO {

    var id: Int? = null

    @JsonProperty("nome_marca")
    var nomeMarca: String? = null
}