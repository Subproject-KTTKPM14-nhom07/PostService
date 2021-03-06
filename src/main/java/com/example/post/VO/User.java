package com.example.post.VO;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class User implements Serializable {

    private Long id;

    private String name;

    private int yearOfBirth;

    private String password;

    private String phone;
}
