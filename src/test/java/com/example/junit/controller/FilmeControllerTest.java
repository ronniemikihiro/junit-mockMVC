package com.example.junit.controller;

import com.example.junit.entity.Filme;
import com.example.junit.service.FilmeService;
import io.restassured.http.ContentType;
import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;

import static io.restassured.module.mockmvc.RestAssuredMockMvc.given;
import static io.restassured.module.mockmvc.RestAssuredMockMvc.standaloneSetup;

@WebMvcTest
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class FilmeControllerTest {

    private final FilmeController filmeController;

    @MockBean
    private FilmeService filmeService;

    @BeforeEach
    public void setup() {
        standaloneSetup(this.filmeController);
    }

    @Test
    public void retornarOk_obterPorCodigo() {
        Mockito.when(this.filmeService.obterPorCodigo(1L))
                .thenReturn(Filme.builder().codigo(1L).titulo("Batman").descricao("O homem morcego").build());

        given()
            .accept(ContentType.JSON)
        .when()
            .get("/filmes/{codigo}", 1L)
        .then()
            .statusCode(HttpStatus.OK.value());
    }

    @Test
    public void retornarNotFound_obterPorCodigo() {
        Mockito.when(this.filmeService.obterPorCodigo(6L))
                .thenReturn(null);

        given()
            .accept(ContentType.JSON)
        .when()
            .get("/filmes/{codigo}", 6L)
        .then()
            .statusCode(HttpStatus.NOT_FOUND.value());
    }

    @Test
    public void retornarBadRequest_obterPorCodigo() {
        given()
            .accept(ContentType.JSON)
        .when()
            .get("/filmes/{codigo}", -1L)
        .then()
            .statusCode(HttpStatus.BAD_REQUEST.value());

        Mockito.verify(this.filmeService, Mockito.never()).obterPorCodigo(-1L);
    }
}
