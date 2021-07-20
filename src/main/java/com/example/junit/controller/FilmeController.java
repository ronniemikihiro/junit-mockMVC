package com.example.junit.controller;

import com.example.junit.entity.Filme;
import com.example.junit.service.FilmeService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/filmes")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class FilmeController {

    private final FilmeService filmeService;

    @GetMapping("/{codigo}")
    public ResponseEntity<Filme> obterPorCodigo(@PathVariable Long codigo) {
        if (codigo < 0) {
            return ResponseEntity.badRequest().build();
        }
        final Filme filme = filmeService.obterPorCodigo(codigo);
        return filme == null ? ResponseEntity.notFound().build() : ResponseEntity.ok(filme);
    }
}
