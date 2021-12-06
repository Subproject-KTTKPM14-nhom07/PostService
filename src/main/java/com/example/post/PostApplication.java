package com.example.post;

import com.example.post.entity.Post;
import com.example.post.service.PostService;
import com.example.post.service.PostServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@SpringBootApplication
public class PostApplication {



    public static void main(String[] args) {
        SpringApplication.run(PostApplication.class, args);

//        PostService postService = new PostServiceImpl();
//
//        for (int i = 0; i < 3; i++) {
////            List<Post>
//            Post post = postService.getPostById(1l);
//            System.out.println(LocalDateTime.now());
//        }
    }

    @Bean
    //@LoadBalanced
    public RestTemplate restTemplate(){
        return new RestTemplate();
    }

}
