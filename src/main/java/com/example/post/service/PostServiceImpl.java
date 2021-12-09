package com.example.post.service;

import com.example.post.VO.ResponseTemplateVO;
import com.example.post.VO.User;
import com.example.post.entity.Post;
import com.example.post.repository.PostRepository;
import io.github.resilience4j.ratelimiter.annotation.RateLimiter;
import io.github.resilience4j.retry.annotation.Retry;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
public class PostServiceImpl implements PostService{
    //redis

    private HashOperations hashOperations;//crud hash

    private RedisTemplate redisTemplate;
    @Autowired
    public PostServiceImpl(RedisTemplate redisTemplate) {

        this.hashOperations = redisTemplate.opsForHash();
        this.redisTemplate = redisTemplate;

    }
    @Autowired
    private PostRepository postRepository;

    @Autowired
    private RestTemplate restTemplate;

    @Override
    @Retry(name="basic")
    @RateLimiter(name="basic")
    public Post savePost(Post post) {
        post.setDayPost(LocalDateTime.now());
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
//        Post p= new Post();
//        p.setContent("AAAAAAAAAAAAAAAAA");
//        hashOperations.put("EMPLOYEE", "xxxxx", p);
        return voList;
    }

    @Override
    @Retry(name="basic")
    @RateLimiter(name="basic")
    /**
     * get post and user info with post id
     */
    public ResponseTemplateVO getPostWithUser(Long id) {
        ResponseTemplateVO vox=null;
        ResponseTemplateVO vo=null;
        vox= (ResponseTemplateVO) hashOperations.get("POSTID", id);
        if(vox==null){
            vo = new ResponseTemplateVO();
            Post post = postRepository.findById(id).get();
            vo.setPost(post);

            User user = restTemplate.getForObject("http://18.136.126.140:8000/user/" + post.getUserId(),User.class);
//        log.info(LocalDateTime.now()+"");
            vo.setUser(user);
            hashOperations.put("POSTID", id, vo);
            return vo;
        }
        else return vox;

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
