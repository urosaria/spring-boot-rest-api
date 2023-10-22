package com.example.springbootrestapi.dto;

import com.example.springbootrestapi.domain.Blog;
import com.example.springbootrestapi.domain.User;
import com.fasterxml.jackson.annotation.JsonCreator;

public class BlogDto {
    private Long id;
    private String title;
    private String content;
    private User user;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @JsonCreator
    public BlogDto(Blog blog) {
        this.id = blog.getId();
        this.title = blog.getTitle();
        this.content = blog.getContent();
        this.user = blog.getUser();
        //this.userName = blog.getUser().getGivenName() +" "+ blog.getUser().getFamilyName();
    }
}
