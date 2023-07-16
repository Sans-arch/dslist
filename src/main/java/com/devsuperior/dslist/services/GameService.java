package com.devsuperior.dslist.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.devsuperior.dslist.dto.GameDTO;
import com.devsuperior.dslist.dto.GameMinDTO;
import com.devsuperior.dslist.entities.Game;
import com.devsuperior.dslist.projections.GameMinProjection;
import com.devsuperior.dslist.repositories.GameListRepository;
import com.devsuperior.dslist.repositories.GameRepository;


@Service
public class GameService {
	
	@Autowired
	private GameRepository gameRepository;
	
	@Autowired
	private GameListRepository gameListRepository;
	
	@Transactional(readOnly = true)
	public GameDTO findById(Long id) {
		Game gameFromEntity = gameRepository.findById(id).get();
		return new GameDTO(gameFromEntity);
	}

	@Transactional(readOnly = true)
	public List<GameMinDTO> findAllMin() {
		List<Game> gamesFromEntity = gameRepository.findAll();
		return gamesFromEntity.stream().map(game -> new GameMinDTO(game)).toList();
	}
	
	@Transactional(readOnly = true)
	public List<GameMinDTO> findByList(Long listId) {
		List<GameMinProjection> projection = gameListRepository.searchByList(listId);
		return projection.stream().map(gameProjection -> new GameMinDTO(gameProjection)).toList();
	}
	
}
