package com.example.springbootrestapi.service;

import com.example.springbootrestapi.dto.BlogDto;
import com.example.springbootrestapi.repository.BlogRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class BlogServiceImpl implements BlogService{

    private final BlogRepository blogRepository;

    public BlogServiceImpl(BlogRepository blogRepository) {
        this.blogRepository = blogRepository;
    }

    @Override
    public List<BlogDto> blogList() {
        return blogRepository.findAll().stream()
                .map(BlogDto::new)
                .collect(Collectors.toList());
    }
}
