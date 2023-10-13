package com.example.PilotoFormula.model.service;
import com.example.PilotoFormula.model.entities.PilotoEntity;
import com.example.PilotoFormula.model.repository.PilotoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class PilotoService {

    @Autowired
    private PilotoRepository pilotoRepository;

    public PilotoEntity adicionarPiloto(PilotoEntity pilotoEntity) {
        return pilotoRepository.save(pilotoEntity);
    }

    public List<PilotoEntity> buscarTodosPilotos() {
        return pilotoRepository.findAll();
    }

    public PilotoEntity buscarPilotoID(Long id) {
        return pilotoRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND , "Piloto não encontrado pelo id: " + id));
    }

    public void deletarPilotoID(Long id) {
        pilotoRepository.deleteById(id);
    }

}
