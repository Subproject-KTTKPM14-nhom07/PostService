package com.example.post.service;

import com.example.post.VO.ResponseTemplateVO;
import com.example.post.VO.User;
import com.example.post.entity.Post;
import com.example.post.repository.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
public class PostServiceImpl implements PostService{

    @Autowired
    private PostRepository postRepository;

    @Autowired
    private RestTemplate restTemplate;
    @Override
    public Post savePost(Post post) {
        return postRepository.saveAndFlush(post);
    }

    @Override
    public List<Post> getAllPosts() {
        return postRepository.findAll();
    }

    @Override
    public ResponseTemplateVO getPostWithUser(Long id) {
        ResponseTemplateVO vo = new ResponseTemplateVO();
        Post post = postRepository.findById(id).get();
        vo.setPost(post);

        User user = restTemplate.getForObject("http://localhost:8000/user/" + post.getUserId(),User.class);

        vo.setUser(user);
        return vo;
    }

    @Override
    public Post getPostById(Long id) {
        return postRepository.findById(id).get();
    }

    @Override
    public List<Post> getPostByUserId(Long userId) {
        return postRepository.getPostByUserId(userId);
    }
}
