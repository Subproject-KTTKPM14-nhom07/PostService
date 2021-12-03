package com.example.post.controller;

import com.example.post.VO.ResponseTemplateVO;
import com.example.post.entity.Post;
import com.example.post.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/post")
public class PostController {

    @Autowired
    private PostService postService;

    @PostMapping("/")
    public Post savePost(@RequestBody Post post){
        return  postService.savePost(post);
    }

    @GetMapping("/{id}")
    public ResponseTemplateVO getPostWithUser(@PathVariable("id") Long id){
        return  postService.getPostWithUser(id);
    }

    @GetMapping("/")
    public List<Post> getAll(){
        return postService.getAllPosts();
    }

}
