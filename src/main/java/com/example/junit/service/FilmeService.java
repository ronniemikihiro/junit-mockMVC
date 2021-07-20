package com.example.junit.service;

import com.example.junit.entity.Filme;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FilmeService {

    private static final List<Filme> listaFilmes = List.of(
            Filme.builder().codigo(1L).titulo("Batman").descricao("O homem morcego").build(),
            Filme.builder().codigo(2L).titulo("Superman").descricao("O super homem").build(),
            Filme.builder().codigo(3L).titulo("Spiderman").descricao("O homem aranha").build(),
            Filme.builder().codigo(4L).titulo("Demolidor").descricao("O homem sem medo").build(),
            Filme.builder().codigo(5L).titulo("Arrow").descricao("O arqueiro verde").build()
    );

    public Filme obterPorCodigo(Long codigo) {
        return listaFilmes.stream().filter(f -> f.getCodigo().equals(codigo)).findFirst().orElse(null);
    }
}
