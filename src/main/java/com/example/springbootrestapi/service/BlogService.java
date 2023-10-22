package com.example.springbootrestapi.service;

import com.example.springbootrestapi.dto.BlogDto;

import java.util.List;

public interface BlogService {
    List<BlogDto> blogList();
}
