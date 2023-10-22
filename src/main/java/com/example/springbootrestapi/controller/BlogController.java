package com.example.springbootrestapi.controller;

import com.example.springbootrestapi.service.BlogService;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BlogController {

    public static final String BLOG_BASE_PATH = "/blog";

    private final BlogService blogService;

    public BlogController(BlogService blogService) {
        this.blogService = blogService;
    }

    @GetMapping(value = {BLOG_BASE_PATH + "/list"}, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> blogList() {
        try {
            return new ResponseEntity<>(blogService.blogList(), HttpStatus.OK);
        } catch(IllegalArgumentException ile){
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }
}
