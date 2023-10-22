package com.example.springbootrestapi.service;

import com.example.springbootrestapi.domain.Blog;
import com.example.springbootrestapi.dto.BlogDto;
import com.example.springbootrestapi.repository.BlogRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
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

    @Override
    public Optional<BlogDto> blogDetail(Long id) {
        return blogRepository.findById(id).map(BlogDto::new);
    }

    @Override
    public BlogDto createBlog(BlogDto createBlog) {
        Blog blog = new Blog();
        blog.setTitle(createBlog.getTitle());
        blog.setContent(createBlog.getContent());
        blog.setCreateDt(Timestamp.valueOf(LocalDateTime.now()));
        blog.setUpdateDt(Timestamp.valueOf(LocalDateTime.now()));
        Blog savedBlog = blogRepository.save(blog);
        return new BlogDto(savedBlog);
    }

    @Override
    public BlogDto updateBlog(Long id, BlogDto updateBlog) {
        return blogRepository.findById(id)
                .map(blog -> {
                    blog.setTitle(updateBlog.getTitle());
                    blog.setContent(updateBlog.getContent());
                    blog.setUpdateDt(Timestamp.valueOf(LocalDateTime.now()));
                    Blog savedBlog = blogRepository.save(blog);
                    return new BlogDto(savedBlog);
                })
                .orElse(null);
    }

    @Override
    public boolean deleteBlog(Long id) {
        if(blogRepository.existsById(id)) {
            blogRepository.deleteById(id);
            return true;
        }
        return false;
    }
}
