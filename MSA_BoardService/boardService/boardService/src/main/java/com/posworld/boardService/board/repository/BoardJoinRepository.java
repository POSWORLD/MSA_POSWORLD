package com.posworld.boardService.board.repository;

import com.posworld.boardService.board.model.SelectJoinDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface BoardJoinRepository extends JpaRepository<SelectJoinDto, Integer> {
    @Query(value = " SELECT b.num, b.homeid, b.content, b.wdate, b.friendid, u.name As friendname, u.prophoto As friendimg FROM boardtbl " +
            "as b INNER JOIN usertbl as u ON b.friendid = u.id WHERE homeid= ?", nativeQuery = true)
    List<SelectJoinDto> getBoards(Integer homeid);

    List<SelectJoinDto> getByHomeid(Integer homeid);
}
