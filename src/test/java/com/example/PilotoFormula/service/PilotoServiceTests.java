package com.example.PilotoFormula.service;

import com.example.PilotoFormula.model.entities.PilotoEntity;
import com.example.PilotoFormula.model.repository.PilotoRepository;
import com.example.PilotoFormula.model.service.PilotoService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;


import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@SpringBootTest
public class PilotoServiceTests {

    @InjectMocks
    private PilotoService pilotoService;

    @Mock
    private PilotoRepository pilotoRepository;

    PilotoEntity pilotoEntity = PilotoEntity.builder()
            .id((long) 2)
            .nome("Nome")
            .nacionalidade("Brasileiro")
            .equipe("Ferrari")
            .build();

    PilotoEntity pilotoEntityNomeNull = PilotoEntity.builder()
            .id((long) 2)
            .nome(null)
            .nacionalidade("Brasileiro")
            .equipe("Ferrari")
            .build();

    PilotoEntity pilotoEntityNacionalidadeNull = PilotoEntity.builder()
            .id((long) 2)
            .nome("João")
            .nacionalidade(null)
            .equipe("Ferrari")
            .build();

    PilotoEntity pilotoEntityEquipeNull = PilotoEntity.builder()
            .id((long) 2)
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
        Assertions.assertThrows(NullPointerException.class, () -> pilotoService.adicionarPiloto(pilotoEntityNomeNull));
    }

    @Test
    void quandoAdicionarPilotoRetornarErroNacionalidadeNull() {
        Mockito.when(pilotoService.adicionarPiloto(pilotoEntityNacionalidadeNull)).thenReturn(pilotoEntityNacionalidadeNull);
        Assertions.assertThrows(NullPointerException.class, () -> pilotoService.adicionarPiloto(pilotoEntityNacionalidadeNull));
    }

    @Test
    void quandoAdicionarPilotoRetornarErroEquipeNull() {
        Mockito.when(pilotoService.adicionarPiloto(pilotoEntityEquipeNull)).thenReturn(pilotoEntityEquipeNull);
        Assertions.assertThrows(NullPointerException.class, () -> pilotoService.adicionarPiloto(pilotoEntityEquipeNull));
    }


    @Test
    void quandoAdicionarPilotoRetornarErroTodosNull() {
        Mockito.when(pilotoService.adicionarPiloto(pilotoEntityTodosNull)).thenReturn(pilotoEntityTodosNull);
        Assertions.assertThrows(NullPointerException.class, () -> pilotoService.adicionarPiloto(pilotoEntityTodosNull));
    }

    @Test//talvez ruim
    public void testeBuscarTodosPilotosSucesso() {
        // dados de exemplo que espera do serviço
        PilotoEntity piloto1 = new PilotoEntity();
        PilotoEntity piloto2 = new PilotoEntity();
        List<PilotoEntity> pilotos = new ArrayList<>();
        pilotos.add(piloto1);
        pilotos.add(piloto2);

        // simula comportamento do metodo
        Mockito.when(pilotoRepository.findAll()).thenReturn(pilotos);

        // chamada do meu metodo que quero testar
        List<PilotoEntity> resposta = pilotoService.buscarTodosPilotos();

        // testar se o resultado é o que eu espero
        Assertions.assertEquals(pilotos, resposta);
    }

    @Test//talvez ruim
    public void testeBuscarTodosPilotosErro() {
        // simula um erro quando chama o findall
        Mockito.when(pilotoRepository.findAll()).thenThrow(new RuntimeException("Erro no repositório"));

        // chama o meu metodo e captura o erro
        try {
            pilotoService.buscarTodosPilotos();
        } catch (RuntimeException ex) {
            // verifica se o erro foi lançado corretamente
            Assertions.assertEquals("Erro no repositório", ex.getMessage());
        }
    }

    @Test//talvez ruim
    public void testeBuscarPilotosIDSucesso() {
        // objeto de exemplo que eu espero
        PilotoEntity piloto = new PilotoEntity();
        piloto.setId(10L);

        // comportanmento simulado de sucesso
        Mockito.when(pilotoService.buscarPilotoID(10L)).thenReturn(piloto);

        // chamada do metodo controller
        PilotoEntity resposta = pilotoService.buscarPilotoID(10L);

        // verifica se a resposta esta ok e compara com o piloto simulado
        Assertions.assertEquals(HttpStatus.OK, resposta.getId());
        Assertions.assertEquals(piloto, resposta.getId());
    }

    @Test//talvez ruim
    public void testeBuscarPilotoIDNaoEncontrado() {
        Long id = 2L;

        Mockito.when(pilotoRepository.findById(id)).thenReturn(Optional.empty());

        pilotoService.buscarPilotoID(id);
    }

    @Test//talvez ruim
    public void testeDeletarPilotoIDComSucesso() {
        Long id = 1L;
        pilotoService.deletarPilotoID(id);

        Mockito.verify(pilotoRepository).deleteById(id);
    }

    @Test//talvez ruim
    public void testeDeletarPilotoIDNaoEncontrado() {
        Long id = 2L;

        Mockito.doThrow(EmptyResultDataAccessException.class).when(pilotoRepository).deleteById(id);
        pilotoService.deletarPilotoID(id);
    }
}