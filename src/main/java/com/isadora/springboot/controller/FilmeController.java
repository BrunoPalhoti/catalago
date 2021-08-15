package com.isadora.springboot.controller;

import com.isadora.springboot.domain.Filme;
import com.isadora.springboot.requests.FilmePostRequestBody;
import com.isadora.springboot.requests.FilmePutRequestBody;
import com.isadora.springboot.service.FilmeService;
import com.isadora.springboot.util.DateUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("filmes")
@Log4j2
@RequiredArgsConstructor
public class FilmeController {
    private final DateUtil      dateUtil;
    private  final FilmeService filmeService;

    @GetMapping
    public ResponseEntity<List<Filme>> listAll(){
        log.info("Hora da Requisição Filme Get All: " + dateUtil.formatLocalDateTimeToDatabaseStyle(LocalDateTime.now()));
        return ResponseEntity.ok(filmeService.listAll());
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<Filme> findById(@PathVariable long id){
        log.info("Hora da Requisição Filme Get One: " + dateUtil.formatLocalDateTimeToDatabaseStyle(LocalDateTime.now()));
        return ResponseEntity.ok(filmeService.findByIdOrThrowBadRequestException(id));
    }

    @PostMapping
    public ResponseEntity<Filme> save(@RequestBody FilmePostRequestBody filmePostRequestBody){
        log.info("Hora da Requisição Filme Post: " + dateUtil.formatLocalDateTimeToDatabaseStyle(LocalDateTime.now()));
        return new ResponseEntity<>(filmeService.save(filmePostRequestBody), HttpStatus.CREATED);
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<Void> delete(@PathVariable long id){
        log.info("Hora da Requisição Filme Delete: " + dateUtil.formatLocalDateTimeToDatabaseStyle(LocalDateTime.now()));
        filmeService.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PutMapping
    public ResponseEntity<Void> replace(@RequestBody FilmePutRequestBody filmePutRequestBody){
        log.info("Hora da Requisição Filme Delete: " + dateUtil.formatLocalDateTimeToDatabaseStyle(LocalDateTime.now()));
        filmeService.replace(filmePutRequestBody);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
