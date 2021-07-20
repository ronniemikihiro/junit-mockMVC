package com.example.junit.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@AllArgsConstructor
@Builder
public class Filme {

    private Long codigo;
    private String titulo;
    private String descricao;

}
