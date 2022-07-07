package com.posco.pcommentService.service;

import com.posco.pcommentService.model.PcommentDto;
import com.posco.pcommentService.model.PcommentUserDto;
import com.posco.pcommentService.model.UserResponseDto;
import com.posco.pcommentService.repository.PcommentRepository;
import com.posco.pcommentService.repository.PcommentUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.client.DefaultResponseErrorHandler;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.util.UriComponentsBuilder;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.lang.reflect.Type;
import java.net.URI;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class PcommentService {

    private final RestTemplate restTemplate;
    @Autowired
    PcommentUserRepository pcommentUserRepository;

    @Autowired
   public PcommentService(RestTemplate restTemplate){
        this.restTemplate = restTemplate;
        restTemplate.setRequestFactory(new HttpComponentsClientHttpRequestFactory());
        restTemplate.setErrorHandler(new DefaultResponseErrorHandler() {
            public boolean hasError(ClientHttpResponse response) throws IOException {
                HttpStatus statusCode = response.getStatusCode();
                return statusCode.series() == HttpStatus.Series.SERVER_ERROR;
            }
        });
    }

    public List<PcommentUserDto> getCommentByPid(PcommentDto pcommentDto){
        return pcommentUserRepository.getByPid(pcommentDto.getPid());
    }

    public Integer delteById(PcommentDto pcommentDto){
        return pcommentUserRepository.deleteByIdAndUserid(pcommentDto.getId(), pcommentDto.getUserid());
    }

    public PcommentUserDto insertPcomment(PcommentDto pcommentDto){
        PcommentUserDto pcommentUserDto = new PcommentUserDto();
        pcommentUserDto.setPid(pcommentDto.getPid());
        pcommentUserDto.setContent(pcommentDto.getContent());
        pcommentUserDto.setUserid(pcommentDto.getUserid());

        Map<String, Integer> urlVariables = new HashMap<>();
        urlVariables.put("userid", pcommentDto.getUserid());

        ServletRequestAttributes requestAttributes =
                (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
        HttpServletRequest request = requestAttributes.getRequest();
        String tokenBearer = request.getHeader("Authorization");
        HttpHeaders httpHeaders = new HttpHeaders();

        httpHeaders.set("Authorization", tokenBearer);
        HttpEntity requests = new HttpEntity(httpHeaders);

        URI url = UriComponentsBuilder
                .fromHttpUrl("http://localhost:9007/member/{userid}")
                .build(urlVariables);
        ResponseEntity<UserResponseDto> userResponse =
                restTemplate.exchange(url, HttpMethod.GET, requests, new ParameterizedTypeReference<UserResponseDto>() {
                });
        System.out.println("userResponse + "+userResponse);
        System.out.println("userResponse dto name + "+userResponse.getBody().getName());
        pcommentUserDto.setName(userResponse.getBody().getName());
        return pcommentUserRepository.save(pcommentUserDto);
    }

}
