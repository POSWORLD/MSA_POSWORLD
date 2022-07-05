package com.posco.pcommentService.repository;

import com.posco.pcommentService.model.PcommentDto;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PcommentRepository extends JpaRepository<PcommentDto, Integer> {
    List<PcommentDto> getPcommentDtoByPid(Integer pid);

    Integer deletePcommentDtoById (PcommentDto pcommentDto);


}
