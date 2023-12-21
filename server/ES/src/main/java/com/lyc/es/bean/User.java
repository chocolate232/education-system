package com.lyc.es.bean;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
public class User  {
    private int id;
    private String name;
    private String username;
    private String password;
    private List<Role>roles;
}
