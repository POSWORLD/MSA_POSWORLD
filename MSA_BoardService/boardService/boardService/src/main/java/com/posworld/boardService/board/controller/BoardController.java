package com.posworld.boardService.board.controller;

import com.posworld.boardService.board.model.BoardDto;
import com.posworld.boardService.board.model.SelectJoinDto;
import com.posworld.boardService.board.service.BoardService;
import com.posworld.boardService.config.SecurityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@CrossOrigin(origins="http://localhost:3001")
@RequestMapping("board")
@RestController
public class BoardController {
    @Autowired
    private BoardService boardService;

    @Autowired
    SecurityService securityService;
    @Autowired
    BoardDto boardDto;

//    @GetMapping("/{homeid}")
//    public List<SelectJoinDto> getBoards(@PathVariable Integer homeid){
//        boardDto.setHomeid(Integer.valueOf(homeid))  ;
//        return boardService.getBoards(homeid);
//    }

    @PostMapping("/{homeid}/{userid}")
    public BoardDto insertBoard(@RequestBody BoardDto boardDto,@PathVariable String userid, @PathVariable String homeid){
        try{
            boardDto.setContent(boardDto.getContent());
            boardDto.setFriendid(Integer.valueOf(userid));
            boardDto.setHomeid(Integer.valueOf(homeid));
        }
        catch (Exception e){
            System.out.println("[ERROR] " + e.getMessage());
        }
        return boardService.insertBoard(boardDto);
    }
//    @PostMapping("/")
//    public BoardDto insertBoard(@RequestBody BoardDto boardDto){
//        try{
//            boardDto.setContent(boardDto.getContent());
//            boardDto.setFriendid(securityService.getIdAtToken());
//            boardDto.setHomeid(boardDto.getHomeid());
//        }
//        catch (Exception e){
//            System.out.println("[ERROR] " + e.getMessage());
//        }
//        return boardService.insertBoard(boardDto);
//    }
//    @PutMapping("/{num}")
////    public Integer updateBoard(@PathVariable Integer num, @RequestBody BoardDto boardDto){
////        BoardDto result;
////        try{
////            boardDto.setFriendid(securityService.getIdAtToken());
////            boardDto.setNum(Integer.valueOf(num));
////            result = boardService.updateBoard(num ,boardDto);
////            return 1 ;
////        }
////        catch (Exception e){
////            return 0;
////        }
       // return boardService.updateBoard(num, boardDto);
  //  }

 @PutMapping("/{num}")
    public BoardDto updateBoard(@PathVariable Integer num, @RequestBody BoardDto boardDto) {
    return boardService.updateBoard(num, boardDto);
    }
    @DeleteMapping("/{num}")
    public ResponseEntity<Map<String,Boolean>> deleteBoard(@PathVariable Integer num) {
        System.out.println(num);
        return boardService.deleteBoard(num );
    }



}
