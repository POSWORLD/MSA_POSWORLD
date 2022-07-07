package com.posworld.userService.service;

import com.posworld.userService.dto.HomeDto;
import com.posworld.userService.dto.TokenDto;
import com.posworld.userService.dto.UserRequestDto;
import com.posworld.userService.dto.UserResponseDto;
import com.posworld.userService.entity.Member;
import com.posworld.userService.jwt.TokenProvider;
import com.posworld.userService.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

import java.lang.reflect.ParameterizedType;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class AuthService {

    private final AuthenticationManagerBuilder managerBuilder;
    private final MemberRepository memberRepository;
    private final PasswordEncoder passwordEncoder;
    private final TokenProvider tokenProvider;

    private final Environment env;
    private final RestTemplate restTemplate;

    @Autowired
    HomeDto homeDto;


    public UserResponseDto signup(UserRequestDto requestDto) {
        if (memberRepository.existsByUserid(requestDto.getUserid())) {
            throw new RuntimeException("이미 가입되어 있는 유저입니다");
        }

        if (requestDto.getPw()==""){
            throw new RuntimeException("비밀번호를 입력해주세요.");
        }

        if (requestDto.getName()==""){
            throw new RuntimeException("이름을 입력해주세요.");
        }

        Member member = requestDto.toUser(passwordEncoder);
        member.setProphoto("/img/minimi.png");

        UserResponseDto newUser = UserResponseDto.of(memberRepository.save(member));
        Integer id = newUser.getId().intValue();
        System.out.println(">>>>>>>>>>>>>>>>>id"+id);

        homeDto.setUserid(id);
        homeDto.setTitle("미니룸");
        homeDto.setPhoto("/img/miniroom.png");
        homeDto.setContent("㉠ㅣ억나ㄴ1 그 때 그 시절 ♡");
        homeDto.setBgm("좋은음악");
        homeDto.setTotal("1");

        String url = "http://127.0.0.1:8000/home/";
        ResponseEntity<Integer> res = new RestTemplate().postForEntity(url, homeDto, Integer.class);

        return newUser;
    }

    public TokenDto login(UserRequestDto requestDto) {
        UsernamePasswordAuthenticationToken authenticationToken = requestDto.toAuthentication();

        Authentication authentication = managerBuilder.getObject().authenticate(authenticationToken);

        return tokenProvider.generateTokenDto(authentication);
    }

    public Boolean checkId(UserRequestDto requestDto) {
        return memberRepository.findByUserid(requestDto.getUserid()).isPresent();
    }
}
