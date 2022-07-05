package com.posco.pcommentService.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("pComment")
public class PcommentController {

    @GetMapping("/")
    public Boolean test(){
        return true;
    }
}
