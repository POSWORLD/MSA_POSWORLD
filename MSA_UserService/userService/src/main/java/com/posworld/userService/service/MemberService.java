package com.posworld.userService.service;


import com.posworld.userService.config.SecurityUtil;
import com.posworld.userService.dto.UserResponseDto;
import com.posworld.userService.entity.Member;
import com.posworld.userService.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class MemberService {
    private final MemberRepository memberRepository;
    private final PasswordEncoder passwordEncoder;

    public UserResponseDto getMyInfoBySecurity() {
        return memberRepository.findById(SecurityUtil.getCurrentemberId())
                .map(UserResponseDto::of)
                .orElseThrow(() -> new RuntimeException("로그인 유저 정보가 없습니다"));
    }

    @Transactional
    public UserResponseDto changeMemberNickname(String userId, String name, String prophoto) {
        Member member = memberRepository.findByUserid(userId).orElseThrow(() -> new RuntimeException("로그인 유저 정보가 없습니다"));
        member.setName(name);
        member.setProphoto("/img/"+prophoto);
        return UserResponseDto.of(memberRepository.save(member));
    }

    @Transactional
    public UserResponseDto changeMemberPassword(String exPassword, String newPassword) {
        Member member = memberRepository.findById(SecurityUtil.getCurrentemberId()).orElseThrow(() -> new RuntimeException("로그인 유저 정보가 없습니다"));
        if (!passwordEncoder.matches(exPassword, member.getPw())) {
            throw new RuntimeException("비밀번호가 맞지 않습니다");
        }
        member.setPw(passwordEncoder.encode((newPassword)));
        return UserResponseDto.of(memberRepository.save(member));
    }


    public Long getUserCount() {
        return memberRepository.count();
    }

    public Optional<Member> getUser(Long id){
        return memberRepository.findById(id);
    }

    @Transactional
    public void deleteUser(Long id) {
        memberRepository.deleteById(id);
    }
}
