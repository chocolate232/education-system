package com.lyc.es.service;

import com.lyc.es.bean.Menu;
import com.lyc.es.bean.User;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;


public interface UserService {

    public int addUser(Map<String,Object> map);
    public User queryUserByName(String u);
    public  int register(User user);
    public List<User> login(User user);
    public List<Menu> queryMenuByRole(int role_id);
    public List<User> searchAllUser();
    public String deleteUser(User user);
    public String updateUser(User user);
    public List<User> getUserByPage(int page, int size);
    public List<User> searchUser(Map<String,Object> map);
}
