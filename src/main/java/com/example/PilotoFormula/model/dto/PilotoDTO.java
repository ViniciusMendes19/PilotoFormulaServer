package com.example.PilotoFormula.model.dto;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PilotoDTO {

    private Long id;
    @JsonProperty("nome")
    private String nome;

    @JsonProperty("nacionalidade")
    private String nacionalidade;

    @JsonProperty("equipe")
    private String equipe;
}
