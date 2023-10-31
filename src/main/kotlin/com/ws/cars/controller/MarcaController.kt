package com.ws.cars.controller

import com.ws.cars.dto.MarcaDTO
import com.ws.cars.service.MarcaService
import jakarta.validation.Valid
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping(value = ["/marca"])
class MarcaController {
    @Autowired
    private lateinit var marcaService: MarcaService

    @GetMapping
    fun getAll(): List<MarcaDTO> {
        return marcaService.getAllMarcas()
    }

    @GetMapping("/{id}")
    fun getById(@PathVariable id: Int): MarcaDTO {
        return marcaService.getMarcaById(id)
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    fun createMarca(@RequestBody @Valid marcaDTO: MarcaDTO): MarcaDTO {
        return marcaService.createMarca(marcaDTO)
    }

    @PutMapping("/{id}")
    fun updateMarca(
        @PathVariable id: Int,
        @RequestBody @Valid marcaDTO: MarcaDTO
    ): ResponseEntity<MarcaDTO> {
        val updateMarca = marcaService.updateMarca(id, marcaDTO)
        return ResponseEntity.ok(updateMarca)
    }

    @DeleteMapping("/{ids}")
    fun deleteMarca(@PathVariable ids: List<Int>): ResponseEntity<Void> {
        marcaService.deleteMarcas(ids)
        return ResponseEntity.noContent().build()
    }
}
