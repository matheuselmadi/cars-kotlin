package com.ws.cars.repository

import com.ws.cars.entity.Carro
import org.springframework.data.jpa.repository.JpaRepository

interface CarroRepository : JpaRepository<Carro, Int> {
}
