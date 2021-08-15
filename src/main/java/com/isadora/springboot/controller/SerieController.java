package com.isadora.springboot.controller;

import com.isadora.springboot.domain.Serie;
import com.isadora.springboot.requests.SeriePostRequestBody;
import com.isadora.springboot.requests.SeriePutRequestBody;
import com.isadora.springboot.service.SerieService;
import com.isadora.springboot.util.DateUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("series")
@Log4j2
@RequiredArgsConstructor
public class SerieController {
    private final DateUtil      dateUtil;
    private final SerieService  serieService;

    @GetMapping
    public ResponseEntity<List<Serie>> list(){
        log.info("Hora da Requisição Serie Get All: " + dateUtil.formatLocalDateTimeToDatabaseStyle(LocalDateTime.now()));
        return ResponseEntity.ok(serieService.listAll());
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<Serie> findById(@PathVariable long id){
        log.info("Hora da Requisição Serie Get One: " + dateUtil.formatLocalDateTimeToDatabaseStyle(LocalDateTime.now()));
        return ResponseEntity.ok(serieService.findByIdOrThrowBadRequestException(id));
    }

    @PostMapping
    public ResponseEntity<Serie> save(@RequestBody SeriePostRequestBody seriePostRequestBody){
        log.info("Hora da Requisição Serie Post: " + dateUtil.formatLocalDateTimeToDatabaseStyle(LocalDateTime.now()));
        return  new ResponseEntity<>(serieService.save(seriePostRequestBody), HttpStatus.CREATED);
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<Void> delete(@PathVariable long id){
        log.info("Hora da Requisição Serie Delete: " + dateUtil.formatLocalDateTimeToDatabaseStyle(LocalDateTime.now()));
        serieService.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PutMapping
    public ResponseEntity<Void> replece(@RequestBody SeriePutRequestBody seriePutRequestBody){
        log.info("Hora da Requisição Serie Put: " + dateUtil.formatLocalDateTimeToDatabaseStyle(LocalDateTime.now()));
        serieService.replace(seriePutRequestBody);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
