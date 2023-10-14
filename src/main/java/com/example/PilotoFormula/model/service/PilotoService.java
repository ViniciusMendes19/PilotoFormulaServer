package com.example.PilotoFormula.model.service;
import com.example.PilotoFormula.exceptions.ErroNaoEncontrado;
import com.example.PilotoFormula.exceptions.ErroSolicitacao;
import com.example.PilotoFormula.model.entities.PilotoEntity;
import com.example.PilotoFormula.model.repository.PilotoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class PilotoService {

    @Autowired
    private PilotoRepository pilotoRepository;

    public PilotoEntity adicionarPiloto( PilotoEntity pilotoEntity) {
        if (pilotoEntity.getNome().isEmpty() || pilotoEntity.getNacionalidade().isEmpty() || pilotoEntity.getEquipe().isEmpty()) {
            throw new ErroSolicitacao("Os seguintes campos não podem ser vazios: Nome, Nacionalidade, Equipe");
        }
        return pilotoRepository.save(pilotoEntity);
    }

    public List<PilotoEntity> buscarTodosPilotos() {
        List<PilotoEntity> pilotos = pilotoRepository.findAll();
        if (pilotos.isEmpty()) {
            throw new ErroNaoEncontrado("Nenhum piloto encontrado");
        }
        return pilotos;
    }

    public PilotoEntity buscarPilotoID(Long id) {
        return pilotoRepository.findById(id)
                .orElseThrow(() -> new ErroNaoEncontrado("Piloto não encontrado pelo id: " + id));
    }

    public void deletarPilotoID(Long id) {
        if (!pilotoRepository.existsById(id)) {
            throw new ErroNaoEncontrado("Piloto não encontrado pelo id: " + id);
        }
        pilotoRepository.deleteById(id);
    }

}