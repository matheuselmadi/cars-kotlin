package com.ws.cars.service

import com.ws.cars.dto.ModeloDTO
import com.ws.cars.entity.Marca
import com.ws.cars.entity.Modelo
import com.ws.cars.repository.MarcaRepository
import com.ws.cars.repository.ModeloRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import org.springframework.web.server.ResponseStatusException

@Service
class ModeloService {
    @Autowired
    private lateinit var modeloRepository: ModeloRepository

    @Autowired
    private lateinit var marcaRepository: MarcaRepository

    @Transactional
    fun createModelo(modeloDTO: ModeloDTO): Int? {
        val modelo = Modelo()
        mapToEntity(modeloDTO, modelo)
        val marca = marcaRepository.findById(modeloDTO.marcaId!!)
            .orElseThrow { ResponseStatusException(HttpStatus.NOT_FOUND, "Marca não encontrada para ID: ${modeloDTO.marcaId}") }
        modelo.marca = marca
        return modeloRepository.save(modelo).id
    }

    @Transactional(readOnly = true)
    fun getAllModelos(): List<ModeloDTO> {
        val modelos = modeloRepository.findAll()
        return modelos.stream().map { modelo -> mapToDTO(modelo, ModeloDTO()) }
            .toList()
    }

    @Transactional(readOnly = true)
    fun getModeloById(id: Int): ModeloDTO {
        return modeloRepository.findById(id).map { modelo -> mapToDTO(modelo, ModeloDTO()) }
            .orElseThrow { ResponseStatusException(HttpStatus.NOT_FOUND, "Modelo não encontrado.") }
    }

    @Transactional
    fun updateModelo(id: Int, modeloDTO: ModeloDTO): ModeloDTO {
        val existingModelo = modeloRepository.findById(id)
            .orElseThrow { RuntimeException("Modelo não encontrado") }
        val updatedModelo = mapToEntity(modeloDTO, existingModelo)
        modeloRepository.save(updatedModelo)
        return mapToDTO(updatedModelo, modeloDTO)
    }

    fun deleteModelos(ids: List<Int>) {
        modeloRepository.deleteAllById(ids)
    }

    private fun mapToEntity(modeloDTO: ModeloDTO, modelo: Modelo): Modelo {
        modelo.id = modeloDTO.id
        modelo.nome = modeloDTO.nome
        modelo.valorFipe = modeloDTO.valorFipe
        val marca = Marca()
        marca.id = modeloDTO.marcaId
        modelo.marca = marca
        return modelo
    }

    private fun mapToDTO(modelo: Modelo, modeloDTO: ModeloDTO): ModeloDTO {
        modeloDTO.id = modelo.id
        modeloDTO.marcaId = modelo.marca?.id
        modeloDTO.nome = modelo.nome
        modeloDTO.valorFipe = modelo.valorFipe
        return modeloDTO
    }
}
