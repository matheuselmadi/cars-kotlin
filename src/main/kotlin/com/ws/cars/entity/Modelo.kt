package com.ws.cars.entity

import jakarta.persistence.*
import lombok.Getter
import lombok.Setter

@Entity
@Table(name = "modelo")
@Getter
@Setter
class Modelo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "modelo_id_seq")
    var id: Int? = null

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "marca_id")
    var marca: Marca? = null

    var nome: String? = null

    @Column(name = "valor_fipe")
    var valorFipe: Double? = null
}