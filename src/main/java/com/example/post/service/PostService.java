package com.example.post.service;

import com.example.post.VO.ResponseTemplateVO;
import com.example.post.entity.Post;

import java.util.List;

public interface PostService {
    public Post savePost(Post post);
    public List<ResponseTemplateVO> getAllPosts();
    public ResponseTemplateVO getPostWithUser(Long id);
    public Post getPostById(Long id);
    public List<ResponseTemplateVO> getPostByUserId(Long userId);

}
