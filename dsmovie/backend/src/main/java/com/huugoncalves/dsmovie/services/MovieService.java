package com.huugoncalves.dsmovie.services;

import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.huugoncalves.dsmovie.dto.MovieDTO;
import com.huugoncalves.dsmovie.entities.Movie;
import com.huugoncalves.dsmovie.repositories.MovieRepository;

@Service
public class MovieService {

	@Autowired
	private MovieRepository repository;

	@Transactional(readOnly = true)
	public Page<MovieDTO> findAll(Pageable pageable) {
		Page<Movie> result = repository.findAll(pageable);
		Page<MovieDTO> page = result.map(x -> new MovieDTO(x));
		return page;
	}

	@Transactional(readOnly = true)
	public MovieDTO findById(Long id) {
		try {
			Movie result = repository.findById(id).get();
			MovieDTO dto = new MovieDTO(result);
			return dto;
		} catch (NoSuchElementException e) {
			MovieDTO dto = new MovieDTO();
			dto.setStatus("ID não encontrado.");
			return dto;
		}
	}

}
