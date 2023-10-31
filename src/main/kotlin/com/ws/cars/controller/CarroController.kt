package com.ws.cars.controller

import com.ws.cars.dto.CarroDTO
import com.ws.cars.dto.CarsDTO
import com.ws.cars.service.CarroService
import jakarta.validation.Valid
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping(value = ["/cars"])
class CarroController {
    @Autowired
    private lateinit var carroService: CarroService

    @GetMapping("/all")
    fun getAll(): List<CarsDTO> {
        return carroService.getAllCars()
    }

    @GetMapping
    fun getAllCarros(): List<CarroDTO> {
        return carroService.getAllCarros()
    }

    @GetMapping("/{id}")
    fun getById(@PathVariable id: Int): CarroDTO {
        return carroService.getCarroById(id)
    }

    @PostMapping
    fun createCarro(@RequestBody @Valid carroDTO: CarroDTO): ResponseEntity<Int> {
        return ResponseEntity(carroService.createCarro(carroDTO), HttpStatus.CREATED)
    }

    @PutMapping("/{id}")
    fun updateCarro(@PathVariable id: Int, @RequestBody @Valid carroDTO: CarroDTO): ResponseEntity<CarroDTO> {
        val updatedCarro = carroService.updateCarro(id, carroDTO)
        return ResponseEntity.ok(updatedCarro)
    }

    @DeleteMapping("/{ids}")
    fun deleteCarro(@PathVariable ids: List<Int>): ResponseEntity<Void> {
        carroService.deleteCarros(ids)
        return ResponseEntity.noContent().build()
    }
}
