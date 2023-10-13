package com.example.PilotoFormula.service;

import com.example.PilotoFormula.model.entities.PilotoEntity;
import com.example.PilotoFormula.model.repository.PilotoRepository;
import com.example.PilotoFormula.model.service.PilotoService;
import org.bouncycastle.util.MemoableResetException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;

import javax.el.MethodNotFoundException;

@SpringBootTest
public class PilotoServiceTests {

    @InjectMocks
    private PilotoService pilotoService;

    @Mock
    private PilotoRepository pilotoRepository;

    PilotoEntity pilotoEntity = PilotoEntity.builder()
            .id((long)2)
            .nome("Nome")
            .nacionalidade("Brasileiro")
            .equipe("Ferrari")
            .build();

    PilotoEntity pilotoEntityNomeNull = PilotoEntity.builder()
            .id((long)2)
            .nome(null)
            .nacionalidade("Brasileiro")
            .equipe("Ferrari")
            .build();

    PilotoEntity pilotoEntityNacionalidadeNull = PilotoEntity.builder()
            .id((long)2)
            .nome("João")
            .nacionalidade(null)
            .equipe("Ferrari")
            .build();

    PilotoEntity pilotoEntityEquipeNull = PilotoEntity.builder()
            .id((long)2)
            .nome("João")
            .nacionalidade("Brasileiro")
            .equipe(null)
            .build();

    PilotoEntity pilotoEntityTodosNull = PilotoEntity.builder()
            .id(null)
            .nome(null)
            .nacionalidade(null)
            .equipe(null)
            .build();


    @Test
    void quandoAdicionarPilotoRetornarSucesso() {
        Mockito.when(pilotoService.adicionarPiloto(pilotoEntity)).thenReturn(pilotoEntity);
        PilotoEntity resposta = pilotoService.adicionarPiloto(pilotoEntity);
        Assertions.assertNotNull(resposta);
    }

    @Test
    void quandoAdicionarPilotoRetornarErroNomeNull() {
        Mockito.when(pilotoService.adicionarPiloto(pilotoEntityNomeNull)).thenReturn(pilotoEntityNomeNull);
        Assertions.assertThrows(MethodNotFoundException.class, () -> pilotoService.adicionarPiloto(pilotoEntityNomeNull));
    }

    @Test
    void quandoAdicionarPilotoRetornarErroNacionalidadeNull() {
        Mockito.when(pilotoService.adicionarPiloto(pilotoEntityNacionalidadeNull)).thenReturn(pilotoEntityNacionalidadeNull);
        Assertions.assertThrows(MethodNotFoundException.class, () -> pilotoService.adicionarPiloto(pilotoEntityNacionalidadeNull));
    }

    @Test
    void quandoAdicionarPilotoRetornarErroEquipeNull() {
        Mockito.when(pilotoService.adicionarPiloto(pilotoEntityEquipeNull)).thenReturn(pilotoEntityEquipeNull);
        Assertions.assertThrows(MethodNotFoundException.class, () -> pilotoService.adicionarPiloto(pilotoEntityEquipeNull));
    }


    @Test
    void quandoAdicionarPilotoRetornarErroTodosNull() {
        Mockito.when(pilotoService.adicionarPiloto(pilotoEntityTodosNull)).thenReturn(pilotoEntityTodosNull);
        Assertions.assertThrows(MethodNotFoundException.class, () -> pilotoService.adicionarPiloto(pilotoEntityTodosNull));
    }
}
