package com.posworld.photoService.repository;

import com.posworld.photoService.model.PhotoDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Repository
public interface PhotoRepository extends JpaRepository<PhotoDto, Integer>  {

    @Modifying
    @Transactional
    @Query("Delete from PhotoDto p where p.id =?1 and p.userid = ?2")
    Integer deleteByIds(@Param("id") Integer id, @Param("userid") Integer userid);
}
