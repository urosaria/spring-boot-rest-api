package com.example.springbootrestapi.controller;

import com.example.springbootrestapi.dto.BlogDto;
import com.example.springbootrestapi.service.BlogService;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.Optional;

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
            List<BlogDto> blogs = blogService.blogList();
            return ResponseEntity.ok(blogs);
        } catch (IllegalArgumentException ile){
            return ResponseEntity.badRequest().body("An error occurred: " + ile.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Unexpected error: " + e.getMessage());
        }
    }

    @GetMapping(value = {BLOG_BASE_PATH + "/detail/{id}"}, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> blogDetail(@PathVariable Long id) {
        try {
            Optional<BlogDto> blogDetail = blogService.blogDetail(id);
            if (!blogDetail.isPresent()) {
                return ResponseEntity.notFound().build();
            }

            return ResponseEntity.ok(blogDetail.get());
        } catch(IllegalArgumentException ile){
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping(value = BLOG_BASE_PATH + "/create", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> createBlog(@RequestBody BlogDto newBlog) {
        try {
            BlogDto result = blogService.createBlog(newBlog);
            if (result != null) {
                URI location = ServletUriComponentsBuilder
                        .fromCurrentRequest()
                        .path("/{id}")
                        .buildAndExpand(result.getId())
                        .toUri();
                return ResponseEntity.created(location).body(result);
            } else {
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to create the blog");
            }
        } catch (IllegalArgumentException ile) {
            return ResponseEntity.badRequest().body("Invalid input: " + ile.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Unexpected error: " + e.getMessage());
        }
    }

    @PutMapping(value = {BLOG_BASE_PATH + "/update/{id}"}, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> updateBlog(@PathVariable Long id, @RequestBody BlogDto updatedBlog) {
        try {
            BlogDto result = blogService.updateBlog(id, updatedBlog);
            if (result != null) {
                return ResponseEntity.ok(result);
            } else {
                return ResponseEntity.notFound().build();
            }
        } catch (IllegalArgumentException ile) {
            return ResponseEntity.badRequest().body("Invalid input: " + ile.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Unexpected error: " + e.getMessage());
        }
    }

    @DeleteMapping(value = {BLOG_BASE_PATH + "/delete/{id}"})
    public ResponseEntity<?> deleteBlog(@PathVariable Long id) {
        try {
            boolean isDeleted = blogService.deleteBlog(id);
            if (isDeleted) {
                return ResponseEntity.ok().build();
            } else {
                return ResponseEntity.notFound().build();
            }
        } catch (IllegalArgumentException ile) {
            return ResponseEntity.badRequest().body("Invalid input: " + ile.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Unexpected error: " + e.getMessage());
        }
    }
}
