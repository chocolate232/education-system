package com.lyc.bean;

import lombok.Data;

import java.util.List;

@Data
public class User {
    private int id;
    private String name;
    private String username;
    private String password;
    private List<Role>roles;
}
