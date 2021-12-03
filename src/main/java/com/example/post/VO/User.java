package com.example.post.VO;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class User{

    private Long id;

    private String name;

    private int yearOfBirth;

    private String password;

    private String phone;
}
