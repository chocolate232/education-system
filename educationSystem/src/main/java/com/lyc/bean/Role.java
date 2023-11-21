package com.lyc.bean;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class Role {
    private int id;
    private String rolename;
    private List<Menu> menus;
}
