package com.posworld.boardService.board.repository;

import com.posworld.boardService.board.model.BoardDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BoardRepository extends JpaRepository<BoardDto, Integer> {


}

