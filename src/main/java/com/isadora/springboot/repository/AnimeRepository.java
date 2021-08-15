package com.isadora.springboot.repository;

import java.util.List;

import com.isadora.springboot.domain.Anime;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AnimeRepository extends JpaRepository<Anime, Long> {

}
