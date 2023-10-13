package com.example.PilotoFormula.model.entities;
import lombok.*;
import javax.persistence.*;
import javax.validation.constraints.NotBlank;


@AllArgsConstructor
@NoArgsConstructor
@Data
@Table(name = "piloto")
@Builder
@Entity
@Setter
public class PilotoEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    @NotBlank(message = "Nome não pode ser vazio")
    @Column(name = "Nome" , nullable = false)
    private String nome;

    @NotBlank(message = "Nacionalidade não pode ser vazio")
    @Column(name = "Nacionalidade")
    private String nacionalidade;

    @NotBlank(message = "Equipe não pode ser vazio")
    @Column(name = "Equipe")
    private String equipe;


}
