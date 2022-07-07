package com.posco.pcommentService.repository;

import com.posco.pcommentService.model.PcommentUserDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import java.util.List;

@EnableJpaRepositories
public interface PcommentUserRepository  extends JpaRepository<PcommentUserDto, Integer> {
    PcommentUserDto save(PcommentUserDto pcommentUserDto);

    List<PcommentUserDto> getByPid(Integer pid);

    Integer deleteByIdAndUserid(Integer id, Integer userid);
}
