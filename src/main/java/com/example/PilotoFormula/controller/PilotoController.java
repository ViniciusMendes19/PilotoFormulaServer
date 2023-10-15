package com.example.PilotoFormula.controller;
import com.example.PilotoFormula.model.entities.PilotoEntity;
import com.example.PilotoFormula.model.service.PilotoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import java.util.List;


@RestController
@RequestMapping("/piloto")
public class PilotoController {

    @Autowired
    private PilotoService pilotoService;

    public PilotoController(PilotoService pilotoService) {
        this.pilotoService=pilotoService;
    }


    @PostMapping("/adicionarPiloto")
    public PilotoEntity adicionarPiloto(@RequestBody PilotoEntity pilotoEntity) {
            return pilotoService.adicionarPiloto(pilotoEntity);
    }

    @GetMapping("/buscarTodosPilotos")
    public List<PilotoEntity> buscarTodosPilotos(){
        return pilotoService.buscarTodosPilotos();
    }

    @GetMapping("{id}")
    public ResponseEntity<PilotoEntity> buscarPilotoID(@PathVariable @Valid Long id) {
        return ResponseEntity.ok(pilotoService.buscarPilotoID(id));
    }

    @DeleteMapping("/{id}")
    public void deletarPilotoID(@PathVariable @Valid Long id){
        pilotoService.deletarPilotoID(id);
    }

}
