package com.example.PilotoFormula.controller;

import com.example.PilotoFormula.model.entities.PilotoEntity;
import com.example.PilotoFormula.model.service.PilotoService;
import org.assertj.core.condition.AnyOf;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
public class PilotoControllerTests {

    @InjectMocks
    private PilotoController pilotoController;

    @Mock
    private PilotoService pilotoService;

    PilotoEntity pilotoEntity = PilotoEntity.builder()
            .id((long) 20)
            .nome("João")
            .nacionalidade("Brasileiro")
            .equipe("Ferrari")
            .build();

    PilotoEntity pilotoEntityNomeNull = PilotoEntity.builder()
            .id((long) 20)
            .nome(null)
            .nacionalidade("Brasileiro")
            .equipe("Ferrari")
            .build();

    PilotoEntity pilotoEntityNacionalidadeNull = PilotoEntity.builder()
            .id((long) 20)
            .nome("João")
            .nacionalidade(null)
            .equipe("Ferrari")
            .build();

    PilotoEntity pilotoEntityEquipeNull = PilotoEntity.builder()
            .id((long) 20)
            .nome("João")
            .nacionalidade("Brasileiro")
            .equipe(null)
            .build();




    @Test
    void testeQuandoAdicionarPilotoRetornarSucesso() {
        Mockito.when(pilotoService.adicionarPiloto(pilotoEntity)).thenReturn(pilotoEntity);
        PilotoEntity resposta = pilotoController.adicionarPiloto(pilotoEntity);
        Assertions.assertNotNull(resposta);
        Assertions.assertEquals(pilotoEntity.getNome(),resposta.getNome());
        Assertions.assertEquals(pilotoEntity.getNacionalidade(),resposta.getNacionalidade());
        Assertions.assertEquals(pilotoEntity.getEquipe(),resposta.getEquipe());

    }

    @Test
    void testeQuandoAdicionarPilotoRetornarErroNomeNull() {
        Mockito.when(pilotoService.adicionarPiloto(pilotoEntityNomeNull)).thenReturn(pilotoEntityNomeNull);
        Assertions.assertThrows(MethodArgumentNotValidException.class,() -> pilotoController.adicionarPiloto(pilotoEntityNomeNull));

    }
    @Test
    void testequandoAdicionarPilotoRetornarErroNacionalidadeNull() {
        Mockito.when(pilotoService.adicionarPiloto(pilotoEntityNacionalidadeNull)).thenReturn(pilotoEntityNacionalidadeNull);
        Assertions.assertThrows(MethodArgumentNotValidException.class, () -> pilotoController.adicionarPiloto(pilotoEntityNacionalidadeNull));

    }
    @Test
    void testeQuandoAdicionarPilotoRetornarErroEquipeNull() {
        Mockito.when(pilotoService.adicionarPiloto(pilotoEntityEquipeNull)).thenReturn(pilotoEntityEquipeNull);
        Assertions.assertThrows(MethodArgumentNotValidException.class, () -> pilotoController.adicionarPiloto(pilotoEntityEquipeNull));

    }

    @Test
    public void testeBuscarTodosPilotosSucesso() {
        // dados de exemplo que espera do serviço
        PilotoEntity piloto1 = new PilotoEntity();
        PilotoEntity piloto2 = new PilotoEntity();
        List<PilotoEntity> pilotos = new ArrayList<>();
        pilotos.add(piloto1);
        pilotos.add(piloto2);

        // simula comportamento do metodo
        Mockito.when(pilotoService.buscarTodosPilotos()).thenReturn(pilotos);

        // chamada do meu metodo que quero testar
        List<PilotoEntity> resposta = pilotoController.buscarTodosPilotos();

        // testar se o resultado é o que eu espero
        Assertions.assertEquals(pilotos, resposta);
    }
    @Test
    public void testeBuscarTodosPilotosErro() {

        // simula um erro na chamada(retornando uma lista vazia)
        Mockito.when(pilotoService.buscarTodosPilotos()).thenReturn(new ArrayList<>());
        // chama o meu metodo controller
        List<PilotoEntity> resposta = pilotoController.buscarTodosPilotos();
        // verifica se o resultado deu uma lista vazia
        Assertions.assertEquals(0, resposta.size());
    }

    @Test
    public void testeBuscarPilotosIDSucesso(){
        // objeto de exemplo que eu espero
        PilotoEntity piloto = new PilotoEntity();
        piloto.setId(10L);

        // comportanmento simulado de sucesso
        Mockito.when(pilotoService.buscarPilotoID(10L)).thenReturn(piloto);

        // chamada do metodo controller
        ResponseEntity<PilotoEntity> resposta = pilotoController.buscarPilotoID(10L);

        // verifica se a resposta esta ok e compara com o piloto simulado
        Assertions.assertEquals(HttpStatus.OK, resposta.getStatusCode());
        Assertions.assertEquals(piloto, resposta.getBody());
    }

    @Test
    public void testeBuscarPilotosIDErro(){
        // simula que o objeto venha nullo
        Mockito.when(pilotoService.buscarPilotoID(Mockito.any(Long.class))).thenReturn(null);

        // chamada do metodo controller que busca ID
        ResponseEntity<PilotoEntity> resposta = pilotoController.buscarPilotoID(10L);

        // verifica se a resposta é NOT FOUND e se a resposta é nulla
        Assertions.assertEquals(HttpStatus.NOT_FOUND, resposta.getStatusCodeValue());
        Assertions.assertEquals(null, resposta.getBody());
    }

    @Test
    public void testeDeletarPilotoID(){
        // chama o meu metodo de deletar
        pilotoController.deletarPilotoID(10L);

        // verifica se o metodo chamou com o ID corretamente
        Mockito.verify(pilotoService).deletarPilotoID(10L);
    }

    @Test
    public void testeDeletarPilotoIDErro(){
        Mockito.doThrow(new RuntimeException("Erro ao deletar piloto, ID não existe")).when(pilotoService).deletarPilotoID(10L);
        try{
            pilotoController.deletarPilotoID(10L);
        }catch (ResponseStatusException ex) {

        }
    }
}
