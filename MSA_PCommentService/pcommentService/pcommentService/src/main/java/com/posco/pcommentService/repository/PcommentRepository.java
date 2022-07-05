package com.posco.pcommentService.repository;

import com.posco.pcommentService.model.PcommentDto;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PcommentRepository extends JpaRepository<PcommentDto, Integer> {

}
