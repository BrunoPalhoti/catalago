package com.isadora.springboot.service;

import java.util.List;

import com.isadora.springboot.repository.AnimeRepository;
import com.isadora.springboot.requests.AnimePostRequestBody;
import com.isadora.springboot.requests.AnimePutRequestBody;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.isadora.springboot.domain.Anime;

@Service
@RequiredArgsConstructor
public class AnimeService {

	private final AnimeRepository animeRepository;
	public List<Anime> listAll() {
		return animeRepository.findAll();
	}

	public Anime findByIdOrThrowBadRequestException(long id) {
		return animeRepository.findById(id)
				.orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST, "Anime ID not fund"));
	}

	public Anime save(AnimePostRequestBody animePostRequestBody) {
		return animeRepository.save(Anime.builder().nome(animePostRequestBody.getNome()).build());
	}
	
	public void delete(long id) {
		animeRepository.delete(findByIdOrThrowBadRequestException(id));
	}

	public void replace(AnimePutRequestBody animePutRequestBody) {
		findByIdOrThrowBadRequestException(animePutRequestBody.getId());
		Anime anime = Anime.builder()
				.id(animePutRequestBody.getId())
				.nome(animePutRequestBody.getNome())
				.build();
		animeRepository.save(anime);
	}
}
