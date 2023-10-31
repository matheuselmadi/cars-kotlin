package com.ws.cars.service

import com.ws.cars.dto.MarcaDTO
import com.ws.cars.entity.Marca
import com.ws.cars.repository.MarcaRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import org.springframework.web.server.ResponseStatusException

@Service
class MarcaService {
    @Autowired
    private lateinit var marcaRepository: MarcaRepository

    fun createMarca(marcaDTO: MarcaDTO): MarcaDTO {
        return mapToDTO(marcaRepository.save(mapToEntity(marcaDTO)))
    }

    fun getAllMarcas(): List<MarcaDTO> {
        val marcas: List<Marca> = marcaRepository.findAll()
        return marcas.map { mapToDTO(it) }
    }

    fun getMarcaById(id: Int): MarcaDTO {
        return mapToDTO(marcaRepository.findById(id).orElseThrow { ResponseStatusException(HttpStatus.NOT_FOUND, "Marca não encontrada.") })
    }

    @Transactional
    fun updateMarca(id: Int, marcaDTO: MarcaDTO): MarcaDTO {
        val existingMarca = marcaRepository.findById(id)
            .orElseThrow { ResponseStatusException(HttpStatus.NOT_FOUND) }

        existingMarca.id = marcaDTO.id
        existingMarca.nomeMarca = marcaDTO.nomeMarca

        marcaRepository.save(existingMarca)

        return mapToDTO(existingMarca)
    }


    fun deleteMarcas(ids: List<Int>) {
        marcaRepository.deleteAllById(ids)
    }

    private fun mapToEntity(marcaDTO: MarcaDTO): Marca {
        val marca = Marca()
        marca.id = marcaDTO.id
        marca.nomeMarca = marcaDTO.nomeMarca
        return marca
    }

    private fun mapToDTO(marca: Marca): MarcaDTO {
        val marcaDTO = MarcaDTO()
        marcaDTO.id = marca.id
        marcaDTO.nomeMarca = marca.nomeMarca
        return marcaDTO
    }
}
