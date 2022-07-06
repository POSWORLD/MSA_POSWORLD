package com.posco.pcommentService.service;

import com.posco.pcommentService.model.PcommentDto;
import com.posco.pcommentService.model.PcommentUserDto;
import com.posco.pcommentService.model.UserResponseDto;
import com.posco.pcommentService.repository.PcommentRepository;
import com.posco.pcommentService.repository.PcommentUserRepository;
import org.bouncycastle.util.encoders.UrlBase64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.lang.reflect.ParameterizedType;
import java.net.URI;
import java.net.URL;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class PcommentService {

    private final RestTemplate restTemplate;
    @Autowired
    PcommentRepository pcommentRepository;

    @Autowired
    PcommentUserRepository pcommentUserRepository;

     @Autowired
     public PcommentService(RestTemplate restTemplate){
         this.restTemplate = restTemplate;
     }

    public List<PcommentDto> getCommentByPid(PcommentDto pcommentDto){
        return pcommentRepository.getPcommentDtoByPid(pcommentDto.getPid());
    }

    public Integer delteById(PcommentDto pcommentDto){
        return pcommentRepository.deletePcommentDtoByIdAndUserid(pcommentDto.getId(), pcommentDto.getUserid());
    }

    public PcommentUserDto insertPcomment(PcommentDto pcommentDto){
        PcommentUserDto pcommentUserDto = new PcommentUserDto();
        pcommentUserDto.setPid(pcommentDto.getPid());
        pcommentUserDto.setContent(pcommentDto.getContent());
        Map<String, Integer> urlVariables = new HashMap<>();
        urlVariables.put("userid", pcommentDto.getUserid());
        URI url = UriComponentsBuilder
                .fromHttpUrl("http://loalhost:9001/user/{id}")
                .build(urlVariables);
        ResponseEntity<UserResponseDto> userListResponse =
                restTemplate.exchange(url, HttpMethod.GET, null, new ParameterizedTypeReference<UserResponseDto>(){
                });
        pcommentUserDto.setName(userListResponse.getBody().getName());
        return pcommentUserRepository.save(pcommentUserDto);
    }

    public PcommentDto updatePcomment(PcommentDto pcommentDto){
        return pcommentRepository.save(pcommentDto);
    }
}
