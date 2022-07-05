package com.posco.posworld.board.service;

import com.posco.posworld.board.exception.ResourceNotFoundException;
import com.posco.posworld.board.model.BoardDto;
import com.posco.posworld.board.model.SelectJoinDto;
import com.posco.posworld.board.repository.BoardJoinRepository;
import com.posco.posworld.board.repository.BoardRepository;
import com.posco.posworld.config.SecurityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class BoardService {
    @Autowired
    private BoardRepository boardRepository;

    @Autowired
    private BoardJoinRepository boardJoinRepository;


    //방명록 리스트 처리
    public List<BoardDto> boardList(){
        return boardRepository.findAll();
    }

    //방명록 작성
//    public BoardDto insertBoard(@RequestBody BoardDto boardDto){
//        boardDto.setContent(boardDto.getContent());
//        boardDto.setFriendid(securityService.getIdAtToken());
//        boardDto.setHomeid(boardDto.getHomeid()); //홈아이디 받기
//        return boardRepository.save(boardDto);
//    }
    public BoardDto insertBoard(@RequestBody BoardDto boardDto){
        return boardRepository.save(boardDto);
    }

    //특정 방명록 불러오기
//   public BoardDto getBoardById(@PathVariable Integer num){
//        BoardDto boardDto = boardRepository.findById(num)
//                .orElseThrow(()-> new ResourceNotFoundException("Board not exist with id :" +num));
//        return boardRepository.save(boardDto);
//   }

    //getboards
    public List<SelectJoinDto> getBoards(@PathVariable Integer homeid){
        return boardJoinRepository.getBoards(homeid);
    }

    //특정 방명록 수정
    public BoardDto updateBoard(@PathVariable Integer num, @RequestBody BoardDto boardDto){
        BoardDto updateBoard = boardRepository.findById(num)
                .orElseThrow(()->new ResourceNotFoundException("Board not exist with id:"+num));
        updateBoard.setContent(boardDto.getContent());
        updateBoard.setWdate(boardDto.getWdate());

        return boardRepository.save(updateBoard);
    }

    //특정 방명록 삭제
    public ResponseEntity<Map<String, Boolean>> deleteBoard(Integer num){
        BoardDto boardDto = boardRepository.findById(num)
                .orElseThrow(()-> new ResourceNotFoundException("Board not exist with id : "+num));
        boardRepository.delete(boardDto);
        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);
        return ResponseEntity.ok(response);
    }
}

