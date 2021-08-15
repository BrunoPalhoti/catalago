package com.isadora.springboot.requests;

import lombok.Data;

@Data
public class FilmePutRequestBody {
    private Long id;
    private String nome;
}
