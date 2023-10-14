package com.example.PilotoFormula.service;

import com.example.PilotoFormula.exceptions.ErroNaoEncontrado;
import com.example.PilotoFormula.exceptions.ErroSolicitacao;
import com.example.PilotoFormula.model.entities.PilotoEntity;
import com.example.PilotoFormula.model.repository.PilotoRepository;
import com.example.PilotoFormula.model.service.PilotoService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class PilotoServiceTests {

    @Mock
    private PilotoRepository pilotoRepository;

    @InjectMocks
    private PilotoService pilotoService;

    private PilotoEntity pilotoEntity;

    @BeforeEach
    public void setup() {
        pilotoEntity = new PilotoEntity();
        pilotoEntity.setId(1L);
        pilotoEntity.setNome("Teste");
        pilotoEntity.setNacionalidade("Teste");
        pilotoEntity.setEquipe("Teste");
    }

    @Test
    public void testeAdicionarPilotoSucesso() {
        when(pilotoRepository.save(pilotoEntity)).thenReturn(pilotoEntity);
        PilotoEntity result = pilotoService.adicionarPiloto(pilotoEntity);
        assertEquals(pilotoEntity, result);
    }

    @Test
    public void testeAdicionarPilotoErroSolicitacao() {
        PilotoEntity invalidPilotoEntity = new PilotoEntity();
        invalidPilotoEntity.setNome("");
        invalidPilotoEntity.setNacionalidade("");
        invalidPilotoEntity.setEquipe("");

        assertThrows(ErroSolicitacao.class, () -> pilotoService.adicionarPiloto(invalidPilotoEntity));
    }

    @Test
    public void testeBuscarTodosPilotosSucesso() {
        List<PilotoEntity> pilotos = Arrays.asList(pilotoEntity);
        when(pilotoRepository.findAll()).thenReturn(pilotos);
        List<PilotoEntity> result = pilotoService.buscarTodosPilotos();
        assertEquals(pilotos, result);
    }

    @Test
    public void testeBuscarTodosPilotosErroNaoEncontrado() {
        when(pilotoRepository.findAll()).thenReturn(Arrays.asList());
        assertThrows(ErroNaoEncontrado.class, () -> pilotoService.buscarTodosPilotos());
    }

    @Test
    public void testeBuscarPilotoIDSucesso() {
        when(pilotoRepository.findById(1L)).thenReturn(Optional.of(pilotoEntity));
        PilotoEntity result = pilotoService.buscarPilotoID(1L);
        assertEquals(pilotoEntity, result);
    }

    @Test
    public void testeBuscarPilotoIDErroNaoEncontrado() {
        when(pilotoRepository.findById(1L)).thenReturn(Optional.empty());
        assertThrows(ErroNaoEncontrado.class, () -> pilotoService.buscarPilotoID(1L));
    }

    @Test
    public void testeDeletarPilotoIDSucesso() {
        when(pilotoRepository.existsById(1L)).thenReturn(true);
        pilotoService.deletarPilotoID(1L);
        verify(pilotoRepository, times(1)).deleteById(1L);
    }

    @Test
    public void testeDeletarPilotoIDErroNaoEncontrado() {
        when(pilotoRepository.existsById(1L)).thenReturn(false);
        assertThrows(ErroNaoEncontrado.class, () -> pilotoService.deletarPilotoID(1L));
    }
}