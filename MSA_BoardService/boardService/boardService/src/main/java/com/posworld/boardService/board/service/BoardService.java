package com.posworld.boardService.board.service;

import com.posworld.boardService.board.exception.ResourceNotFoundException;
import com.posworld.boardService.board.model.BoardDto;
import com.posworld.boardService.board.model.SelectJoinDto;
import com.posworld.boardService.board.model.UserResponseDto;
import com.posworld.boardService.board.repository.BoardJoinRepository;
import com.posworld.boardService.board.repository.BoardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.client.DefaultResponseErrorHandler;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.util.UriComponentsBuilder;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.net.URI;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class BoardService {
    @Autowired
    RestTemplate restTemplate;
    @Autowired
    private BoardRepository boardRepository;

    @Autowired
    private BoardJoinRepository boardJoinRepository;

    public BoardService(RestTemplate restTemplate){
        this.restTemplate = restTemplate;
        restTemplate.setRequestFactory(new HttpComponentsClientHttpRequestFactory());
        restTemplate.setErrorHandler(new DefaultResponseErrorHandler() {
            public boolean hasError(ClientHttpResponse response) throws IOException {
                HttpStatus statusCode = response.getStatusCode();
                return statusCode.series() == HttpStatus.Series.SERVER_ERROR;
            }
        });
    }

    //????????? ????????? ??????
    public List<BoardDto> boardList(){
        return boardRepository.findAll();
    }

    //????????? ??????
//    public BoardDto insertBoard(@RequestBody BoardDto boardDto){
//        boardDto.setContent(boardDto.getContent());
//        boardDto.setFriendid(securityService.getIdAtToken());
//        boardDto.setHomeid(boardDto.getHomeid()); //???????????? ??????
//        return boardRepository.save(boardDto);
//    }
    /* (MSA ???) insert
    public BoardDto insertBoard(@RequestBody BoardDto boardDto){
        return boardRepository.save(boardDto);
    }*/
    public SelectJoinDto insertBoard(BoardDto boardDto){
        SelectJoinDto selectJoinDto = new SelectJoinDto();
        selectJoinDto.setFriendid(boardDto.getFriendid());
        selectJoinDto.setContent(boardDto.getContent());
        selectJoinDto.setHomeid(boardDto.getHomeid());

        Map<String, Integer> urlVariables = new HashMap<>();
        urlVariables.put("userid", boardDto.getFriendid());

        ServletRequestAttributes requestAttributes =
                (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
        HttpServletRequest request = requestAttributes.getRequest();
        String tokenBearer = request.getHeader("Authorization");
        HttpHeaders httpHeaders = new HttpHeaders();

        httpHeaders.set("Authorization", tokenBearer);
        HttpEntity requests = new HttpEntity(httpHeaders);

        URI url = UriComponentsBuilder
                .fromHttpUrl("http://127.0.0.1:9007/member/{userid}")
                .build(urlVariables);
        ResponseEntity<UserResponseDto> userResponse =
                restTemplate.exchange(url, HttpMethod.GET, requests, new ParameterizedTypeReference<UserResponseDto>() {
                });
        System.out.println("??????"+userResponse);
        selectJoinDto.setFriendname(userResponse.getBody().getName());
        selectJoinDto.setFriendimg(userResponse.getBody().getProphoto());
        return boardJoinRepository.save(selectJoinDto);

    }



    //?????? ????????? ????????????
//   public BoardDto getBoardById(@PathVariable Integer num){
//        BoardDto boardDto = boardRepository.findById(num)
//                .orElseThrow(()-> new ResourceNotFoundException("Board not exist with id :" +num));
//        return boardRepository.save(boardDto);
//   }

    //getboards
    public List<SelectJoinDto> getByHomeid(@PathVariable Integer homeid){
        return boardJoinRepository.getByHomeid(homeid);
    }

    //?????? ????????? ??????
    public SelectJoinDto updateBoard(@PathVariable Integer num, @RequestBody SelectJoinDto selectJoinDto){
        SelectJoinDto updateBoard = boardJoinRepository.findById(num)
                .orElseThrow(()->new ResourceNotFoundException("Board not exist with id:"+num));
        updateBoard.setContent(selectJoinDto.getContent());
        updateBoard.setWdate(selectJoinDto.getWdate());

        return boardJoinRepository.save(updateBoard);
    }

    //?????? ????????? ??????
    public ResponseEntity<Map<String, Boolean>> deleteBoard(Integer num){
        SelectJoinDto selectJoinDto = boardJoinRepository.findById(num)
                .orElseThrow(()-> new ResourceNotFoundException("Board not exist with id : "+num));
        boardJoinRepository.delete(selectJoinDto);
        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);
        return ResponseEntity.ok(response);
    }
}

