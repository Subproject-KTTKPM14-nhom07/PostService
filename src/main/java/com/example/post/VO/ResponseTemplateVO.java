package com.example.post.VO;

import com.example.post.entity.Post;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ResponseTemplateVO implements Serializable {
    private User user;
    private Post post;
}
