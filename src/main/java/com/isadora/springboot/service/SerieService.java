package com.isadora.springboot.service;

import com.isadora.springboot.domain.Serie;
import com.isadora.springboot.repository.SerieRepository;
import com.isadora.springboot.requests.SeriePostRequestBody;
import com.isadora.springboot.requests.SeriePutRequestBody;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
@RequiredArgsConstructor
public class SerieService {

    private final SerieRepository serieRepository;
    public List<Serie> listAll(){
        return serieRepository.findAll();
    }

    public Serie findByIdOrThrowBadRequestException(long id){
        return serieRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST, "Serie ID not fund"));
    }

    public Serie save(SeriePostRequestBody seriePostRequestBody){
        return serieRepository.save(Serie.builder().nome(seriePostRequestBody.getNome()).build());
    }

    public void delete(long id){
        serieRepository.delete(findByIdOrThrowBadRequestException(id));
    }

    public void replace(SeriePutRequestBody seriePutRequestBody){
        findByIdOrThrowBadRequestException(seriePutRequestBody.getId());
        Serie serie = Serie.builder()
                .id(seriePutRequestBody.getId())
                .nome(seriePutRequestBody.getNome())
                .build();
        serieRepository.save(serie);
    }

}
