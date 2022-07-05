package com.posco.pcommentService.controller;

import com.posco.pcommentService.model.PcommentDto;
import com.posco.pcommentService.service.PcommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import java.util.List;

@RestController
@RequestMapping("pComment")
public class PcommentController {

    @Autowired
    PcommentService pcommentService;

    @Autowired
    PcommentDto pcommentDto;

    @GetMapping("/")
    public Boolean test(){
        return true;
    }

    @GetMapping("/{pid}")
    public List<PcommentDto> getPcommentsByPid(@PathVariable String pid){
        pcommentDto.setPid(Integer.valueOf(pid));
        return pcommentService.getCommentByPid(pcommentDto);

    }
    @PostMapping("/")
    public PcommentDto insertPcomment(@RequestBody PcommentDto pcommentDto){
        try {
            pcommentDto.setUserid(pcommentDto.getUserid());
            pcommentDto.setContent(pcommentDto.getContent());
            pcommentDto.setPid(pcommentDto.getPid());
        }
        catch (Exception e){
            System.out.println("[ERROR] " + e.getMessage());
        }
        return pcommentService.insertPcomment(pcommentDto);
    }
    @PutMapping("/")
    public PcommentDto updatePcomment(@RequestBody PcommentDto pcommentDto){
        try {
            pcommentDto.setPid(pcommentDto.getId());
            pcommentDto.setUserid(pcommentDto.getUserid());
            pcommentDto.setContent(pcommentDto.getContent());
            pcommentDto.setPid(pcommentDto.getPid());
        }
        catch (Exception e){
            System.out.println("[ERROR] " + e.getMessage());
        }
        return pcommentService.updatePcomment(pcommentDto);
    }


    @Transactional
    @DeleteMapping("/{id}/{userid}")
    public Integer deletePcommentById(@PathVariable String id, @PathVariable String userid){
        pcommentDto.setId(Integer.valueOf(id));
        pcommentDto.setUserid(Integer.valueOf(userid));
        return pcommentService.delteById(pcommentDto);
    }
}
