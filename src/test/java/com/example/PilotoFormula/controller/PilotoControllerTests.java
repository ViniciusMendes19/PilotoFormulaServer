package com.example.PilotoFormula.controller;

import com.example.PilotoFormula.model.entities.PilotoEntity;
import com.example.PilotoFormula.model.service.PilotoService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class PilotoControllerTests {

    @InjectMocks
    private PilotoController pilotoController;

    @Mock
    private PilotoService pilotoService;

    private PilotoEntity pilotoEntity;
    private List<PilotoEntity> pilotoEntities;

    @BeforeEach
    public void setup() {
        pilotoController = new PilotoController(pilotoService);

        pilotoEntity = new PilotoEntity();
        pilotoEntity.setId(1L);
        pilotoEntity.setNome("Teste");
        pilotoEntity.setNacionalidade("Brasil");
        pilotoEntity.setEquipe("Teste Equipe");

        pilotoEntities = Arrays.asList(pilotoEntity);
    }

    @Test
    public void testeAdicionarPilotoSucesso() {
        when(pilotoService.adicionarPiloto(any(PilotoEntity.class))).thenReturn(pilotoEntity);

        PilotoEntity result = pilotoController.adicionarPiloto(pilotoEntity);

        Assertions.assertEquals(pilotoEntity, result);
        Mockito.verify(pilotoService, times(1)).adicionarPiloto(any(PilotoEntity.class));
    }

    @Test
    public void testeBuscarTodosPilotosSucesso() {
        when(pilotoService.buscarTodosPilotos()).thenReturn(pilotoEntities);

        List<PilotoEntity> result = pilotoController.buscarTodosPilotos();

        Assertions.assertEquals(pilotoEntities, result);
        Mockito.verify(pilotoService, times(1)).buscarTodosPilotos();
    }

    @Test
    public void testeBuscarPilotoIDSucesso() {
        when(pilotoService.buscarPilotoID(anyLong())).thenReturn(pilotoEntity);

        PilotoEntity result = pilotoController.buscarPilotoID(1L).getBody();

        Assertions.assertEquals(pilotoEntity, result);
        Mockito.verify(pilotoService, times(1)).buscarPilotoID(anyLong());
    }

    @Test
    public void testeDeletarPilotoIDSucesso() {
        Mockito.doNothing().when(pilotoService).deletarPilotoID(anyLong());

        pilotoController.deletarPilotoID(1L);

        Mockito.verify(pilotoService, times(1)).deletarPilotoID(anyLong());
    }
}