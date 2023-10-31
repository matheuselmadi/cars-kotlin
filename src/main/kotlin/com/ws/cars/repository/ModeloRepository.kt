package com.ws.cars.repository

import com.ws.cars.entity.Modelo
import org.springframework.data.jpa.repository.JpaRepository

interface ModeloRepository : JpaRepository<Modelo, Int>