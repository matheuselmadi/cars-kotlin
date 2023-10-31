package com.ws.cars.entity

import jakarta.persistence.*
import lombok.Getter
import lombok.Setter

@Entity
@Table(name = "marca")
@Getter
@Setter
class Marca {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "marca_id_seq")
    var id: Int? = null

    @Column(name = "nome_marca")
    var nomeMarca: String? = null
}