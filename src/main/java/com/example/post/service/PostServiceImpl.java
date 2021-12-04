package com.example.post.service;

import com.example.post.VO.ResponseTemplateVO;
import com.example.post.VO.User;
import com.example.post.entity.Post;
import com.example.post.repository.PostRepository;
import io.github.resilience4j.ratelimiter.annotation.RateLimiter;
import io.github.resilience4j.retry.annotation.Retry;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@Service
public class PostServiceImpl implements PostService{

    @Autowired
    private PostRepository postRepository;

    @Autowired
    private RestTemplate restTemplate;

    @Override
    @Retry(name="basic")
    @RateLimiter(name="basic")
    public Post savePost(Post post) {
        return postRepository.saveAndFlush(post);
    }

    @Override
    @Retry(name="basic")
    @RateLimiter(name="basic")
    public List<ResponseTemplateVO> getAllPosts() {
        List<Post> postList=postRepository.findAll();
        List<ResponseTemplateVO> voList=new ArrayList<>();
        ResponseTemplateVO templateVO;
        for (Post post:postList) {
            templateVO=getPostWithUser(post.getId());
            voList.add(templateVO);
        }
        return voList;
    }

    @Override
    @Retry(name="basic")
    @RateLimiter(name="basic")
    /**
     * get post and user info with post id
     */
    public ResponseTemplateVO getPostWithUser(Long id) {
        ResponseTemplateVO vo = new ResponseTemplateVO();
        Post post = postRepository.findById(id).get();
        vo.setPost(post);

        User user = restTemplate.getForObject("http://localhost:8000/user/" + post.getUserId(),User.class);

        vo.setUser(user);
        return vo;
    }

    @Override
    @Retry(name="basic")
    @RateLimiter(name="basic")
    public Post getPostById(Long id) {
        return postRepository.findById(id).get();
    }

    @Override
    @Retry(name="basic")
    @RateLimiter(name="basic")
    public List<ResponseTemplateVO> getPostByUserId(Long userId) {
        List<Post> postList=postRepository.getPostByUserId(userId);
        List<ResponseTemplateVO> voList=new ArrayList<>();
        ResponseTemplateVO templateVO;
        for (Post post:postList) {
            templateVO=getPostWithUser(post.getId());
            voList.add(templateVO);
        }
        return voList;
    }
}
