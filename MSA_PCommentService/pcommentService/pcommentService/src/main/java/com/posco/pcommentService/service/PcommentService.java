package com.posco.pcommentService.service;

import com.posco.pcommentService.model.PcommentDto;
import com.posco.pcommentService.repository.PcommentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PcommentService {

    @Autowired
    PcommentRepository pcommentRepository;

    public List<PcommentDto> getCommentByPid(PcommentDto pcommentDto){
        return pcommentRepository.getPcommentDtoByPid(pcommentDto.getPid());
    }

    public Integer delteById(PcommentDto pcommentDto){
        return pcommentRepository.deletePcommentDtoById(pcommentDto);
    }

    public PcommentDto insertPcomment(PcommentDto pcommentDto){
        return pcommentRepository.save(pcommentDto);
    }

    public PcommentDto updatePcomment(PcommentDto pcommentDto){
        return pcommentRepository.save(pcommentDto);
    }
}
