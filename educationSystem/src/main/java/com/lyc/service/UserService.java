package com.lyc.service;

import com.lyc.bean.Menu;
import com.lyc.bean.User;

import java.util.List;


public interface UserService {
    public User queryUserByName(String u);
    public  int register(User user);
    public List<User> login(User user);
    public List<Menu> queryMenuByRole(int role_id);
    public List<User> searchAllUser();
    public String deleteUser(User user);
    public String updateUser(User user);
    public List<User> getUserByPage(int page, int size);
}
