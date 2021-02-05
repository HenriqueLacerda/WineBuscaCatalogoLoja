package br.com.wine.catalogo.controller;

import br.com.wine.catalogo.dto.LojaRequestDto;
import br.com.wine.catalogo.dto.LojaResponseDto;
import br.com.wine.catalogo.service.LojaService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;

@RestController
@AllArgsConstructor
public class LojaController {

    private final LojaService lojaService;

    @GetMapping("/{cep}")
    public ResponseEntity<LojaResponseDto> buscar(@PathVariable Long cep) {
        return new ResponseEntity<>(lojaService.buscarPorCep(cep), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<LojaResponseDto> salvar(@RequestBody @Valid LojaRequestDto lojaRequestDto) {
        return new ResponseEntity<>(lojaService.salvar(lojaRequestDto), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> alterar(@PathVariable Long id, @RequestBody @Valid LojaResponseDto lojaResponseDto) {
        lojaService.alterar(lojaResponseDto);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @DeleteMapping
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        lojaService.deletar(id);
        return new ResponseEntity<Void>( HttpStatus.NO_CONTENT);
    }


}
