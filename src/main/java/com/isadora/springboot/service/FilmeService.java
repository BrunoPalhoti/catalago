package com.isadora.springboot.service;

import com.isadora.springboot.domain.Filme;
import com.isadora.springboot.repository.FilmeRepository;
import com.isadora.springboot.requests.FilmePostRequestBody;
import com.isadora.springboot.requests.FilmePutRequestBody;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
@RequiredArgsConstructor
public class FilmeService {

    private final FilmeRepository filmeRepository;

    public List<Filme> listAll(){
        return filmeRepository.findAll();
    }

    public Filme findByIdOrThrowBadRequestException(long id){
        return filmeRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST, "Anime ID not fund"));
    }

    public Filme save(FilmePostRequestBody filmePostRequestBody){
        return filmeRepository.save(Filme.builder()
                .nome(filmePostRequestBody.getNome())
                .build());
    }

    public void delete(long id){
       filmeRepository.delete(findByIdOrThrowBadRequestException(id));
    }

    public void replace(FilmePutRequestBody filmePutRequestBody){
        findByIdOrThrowBadRequestException(filmePutRequestBody.getId());
        Filme filme = Filme.builder()
                .id(filmePutRequestBody.getId())
                .nome(filmePutRequestBody.getNome())
                .build();
        filmeRepository.save(filme);
    }
}
