package com.example.springbootrestapi.service;

import com.example.springbootrestapi.dto.BlogDto;

import java.util.List;
import java.util.Optional;

public interface BlogService {
    List<BlogDto> blogList();

    Optional<BlogDto> blogDetail(Long id);

    BlogDto createBlog(BlogDto createBlog);

    BlogDto updateBlog(Long id, BlogDto updatedBlog);

    boolean deleteBlog(Long id);
}
