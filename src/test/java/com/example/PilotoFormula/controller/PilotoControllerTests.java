package com.example.PilotoFormula.controller;

import com.example.PilotoFormula.model.entities.PilotoEntity;
import com.example.PilotoFormula.model.service.PilotoService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.Arrays;
import java.util.List;

@ExtendWith(MockitoExtension.class)
@WebMvcTest(PilotoController.class)
public class PilotoControllerTests {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private PilotoService pilotoService;

    private PilotoEntity pilotoEntity;
    private List<PilotoEntity> pilotoEntityList;

    @BeforeEach
    public void setup() {
        pilotoEntity = new PilotoEntity();
        pilotoEntity.setId(1L);
        pilotoEntity.setNome("Teste");
        pilotoEntity.setNacionalidade("Brasil");
        pilotoEntity.setEquipe("Teste Equipe");

        pilotoEntityList = Arrays.asList(pilotoEntity);
    }

    @Test
    public void testeAdicionarPilotoSucesso() throws Exception {
        Mockito.when(pilotoService.adicionarPiloto(Mockito.any(PilotoEntity.class))).thenReturn(pilotoEntity);

        mockMvc.perform(MockMvcRequestBuilders.post("/piloto/adicionarPiloto")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(pilotoEntity)))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().json(new ObjectMapper().writeValueAsString(pilotoEntity)));
    }

    @Test
    public void testeBuscarTodosPilotosSucesso() throws Exception {
        Mockito.when(pilotoService.buscarTodosPilotos()).thenReturn(pilotoEntityList);

        mockMvc.perform(MockMvcRequestBuilders.get("/piloto/buscarTodosPilotos"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().json(new ObjectMapper().writeValueAsString(pilotoEntityList)));
    }

    @Test
    public void testeBuscarPilotoIDSucesso() throws Exception {
        Mockito.when(pilotoService.buscarPilotoID(Mockito.anyLong())).thenReturn(pilotoEntity);

        mockMvc.perform(MockMvcRequestBuilders.get("/piloto/1"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().json(new ObjectMapper().writeValueAsString(pilotoEntity)));
    }

    @Test
    public void testeDeletarPilotoIDSucesso() throws Exception {
        Mockito.doNothing().when(pilotoService).deletarPilotoID(Mockito.anyLong());

        mockMvc.perform(MockMvcRequestBuilders.delete("/piloto/1"))
                .andExpect(MockMvcResultMatchers.status().isOk());

        Mockito.verify(pilotoService, Mockito.times(1)).deletarPilotoID(Mockito.anyLong());
    }
}