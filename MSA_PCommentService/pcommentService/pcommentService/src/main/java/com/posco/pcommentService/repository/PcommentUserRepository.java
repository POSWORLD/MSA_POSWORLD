package com.posco.pcommentService.repository;

import com.posco.pcommentService.model.PcommentUserDto;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PcommentUserRepository  extends JpaRepository<PcommentUserDto, Integer> {
    PcommentUserDto save(PcommentUserDto pcommentUserDto);
}
