package com.example.post.controller;

import com.example.post.VO.ResponseTemplateVO;
import com.example.post.entity.Post;
import com.example.post.service.PostService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/post/retry")
@Slf4j
public class PostRetryController {

    @Autowired
    private PostService postService;

    @PostMapping("/")
    public Post savePost(@RequestBody Post post){
        return  postService.savePost(post);
    }

    @GetMapping("/{id}")
    public ResponseTemplateVO getPostWithUserRedis(@PathVariable("id") Long id){
//        log.info(LocalDateTime.now()+"aaaaaa");
        return  postService.getPostWithUserRT(id);
    }

    @GetMapping("/user/{id}")
    public List<ResponseTemplateVO> getPostByUserId(@PathVariable("id") Long id){
        return  postService.getPostByUserId(id);
    }

    @GetMapping("/")
    public List<ResponseTemplateVO> getAll(){
        return postService.getAllPostsRT();
    }

}
