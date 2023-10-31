package com.ws.cars.service

import com.ws.cars.dto.CarroDTO
import com.ws.cars.dto.CarsDTO
import com.ws.cars.entity.Carro
import com.ws.cars.entity.Modelo
import com.ws.cars.repository.CarroRepository
import com.ws.cars.repository.ModeloRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import org.springframework.web.server.ResponseStatusException

@Service
class CarroService {
    @Autowired
    private lateinit var carroRepository: CarroRepository

    @Autowired
    private lateinit var modeloRepository: ModeloRepository

    @Transactional
    fun createCarro(carroDTO: CarroDTO): Int? {
        val carro = Carro()
        mapToEntity(carroDTO, carro)
        val modelo = modeloRepository.findById(carroDTO.modeloId!!)
            .orElseThrow { ResponseStatusException(HttpStatus.NOT_FOUND, "Carro não encontrado para ID: ${carroDTO.modeloId}") }
        carro.modelo = modelo
        return carroRepository.save(carro).id
    }

    fun getAllCars(): List<CarsDTO> {
        val cars = carroRepository.findAll()
        return cars.stream().map(this::mapCarsToDTO).toList()
    }

    @Transactional(readOnly = true)
    fun getAllCarros(): List<CarroDTO> {
        val cars: List<Carro> = carroRepository.findAll()
        return cars.map { carro -> mapToDTO(carro, CarroDTO()) }
    }


    @Transactional(readOnly = true)
    fun getCarroById(id: Int): CarroDTO {
        return carroRepository.findById(id).map { carro -> mapToDTO(carro, CarroDTO()) }
            .orElseThrow { ResponseStatusException(HttpStatus.NOT_FOUND, "Carro não encontrado.") }
    }

    @Transactional
    fun updateCarro(id: Int, carroDTO: CarroDTO): CarroDTO {
        val existingCarro = carroRepository.findById(id)
            .orElseThrow { RuntimeException("Carro não encontrado.") }
        val updatedCarro = mapToEntity(carroDTO, existingCarro)
        carroRepository.save(updatedCarro)
        return mapToDTO(updatedCarro, carroDTO)
    }

    fun deleteCarros(ids: List<Int>) {
        carroRepository.deleteAllById(ids)
    }

    private fun mapToEntity(carroDTO: CarroDTO, carro: Carro): Carro {
        carro.id = carroDTO.id
        carro.timestampCadastro = carroDTO.timestampCadastro
        carro.ano = carroDTO.ano
        carro.combustivel = carroDTO.combustivel
        carro.numPortas = carroDTO.numPortas
        carro.cor = carroDTO.cor
        val modelo = Modelo()
        modelo.id = carroDTO.modeloId
        carro.modelo = modelo
        return carro
    }

    private fun mapToDTO(carro: Carro, carroDTO: CarroDTO): CarroDTO {
        carroDTO.id = carro.id
        carroDTO.timestampCadastro = carro.timestampCadastro
        carroDTO.modeloId = carro.modelo?.id!!
        carroDTO.ano = carro.ano
        carroDTO.combustivel = carro.combustivel
        carroDTO.numPortas = carro.numPortas
        carroDTO.cor = carro.cor

        return carroDTO
    }


    private fun mapCarsToDTO(carro: Carro): CarsDTO {
        val carsDTO = CarsDTO()
        carsDTO.id = carro.id
        carsDTO.timestampCadastro = carro.timestampCadastro
        carsDTO.modeloId = carro.modelo?.id
        carsDTO.ano = carro.ano
        carsDTO.combustivel = carro.combustivel
        carsDTO.numPortas = carro.numPortas
        carsDTO.cor = carro.cor
        carsDTO.nomeModelo = carro.modelo?.nome
        carsDTO.valor = carro.modelo?.valorFipe
        carsDTO.brand = carro.modelo?.marca?.id
        return carsDTO
    }
}
