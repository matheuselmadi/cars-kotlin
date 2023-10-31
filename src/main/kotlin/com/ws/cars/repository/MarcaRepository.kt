package com.ws.cars.repository

import com.ws.cars.entity.Marca
import org.springframework.data.jpa.repository.JpaRepository

interface MarcaRepository : JpaRepository<Marca, Int>