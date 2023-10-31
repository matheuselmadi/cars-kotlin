package com.ws.cars.entity

import jakarta.persistence.*
import lombok.Getter
import lombok.Setter
import java.sql.Timestamp

@Entity
@Table(name = "carro")
@Getter
@Setter
data class Carro(

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "carro_id_seq")
    var id: Int? = null,

    @Column(name = "timestamp_cadastro")
    var timestampCadastro: Timestamp? = null,

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "modelo_id")
    var modelo: Modelo? = null,

    var ano: Int? = null,

    var combustivel: String? = null,

    @Column(name = "num_portas")
    var numPortas: Int? = null,

    var cor: String? = null
)