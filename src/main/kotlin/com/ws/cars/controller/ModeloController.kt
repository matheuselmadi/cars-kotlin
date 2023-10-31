package com.ws.cars.controller

import com.ws.cars.dto.ModeloDTO
import com.ws.cars.service.ModeloService
import jakarta.validation.Valid
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping(value = ["/modelo"])
class ModeloController {
    @Autowired
    private lateinit var modeloService: ModeloService

    @GetMapping
    fun getAll(): List<ModeloDTO> {
        return modeloService.getAllModelos()
    }

    @GetMapping("/{id}")
    fun getById(@PathVariable id: Int): ModeloDTO {
        return modeloService.getModeloById(id)
    }

    @PostMapping
    fun createModelo(@RequestBody @Valid modeloDTO: ModeloDTO): ResponseEntity<Int> {
        return ResponseEntity(modeloService.createModelo(modeloDTO), HttpStatus.CREATED)
    }

    @PutMapping("/{id}")
    fun updateModelo(@PathVariable id: Int, @RequestBody modeloDTO: ModeloDTO): ResponseEntity<ModeloDTO> {
        val updatedModelo = modeloService.updateModelo(id, modeloDTO)
        return ResponseEntity.ok(updatedModelo)
    }

    @DeleteMapping("/{ids}")
    fun deleteModelo(@PathVariable ids: List<Int>): ResponseEntity<Void> {
        modeloService.deleteModelos(ids)
        return ResponseEntity.noContent().build()
    }
}
