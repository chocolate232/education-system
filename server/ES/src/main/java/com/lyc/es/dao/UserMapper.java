package com.lyc.es.dao;

import com.lyc.es.bean.Menu;
import com.lyc.es.bean.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;


@Mapper
@Repository
public interface UserMapper {
    /**
     * 檢查用戶是否存在
     * @param user_name
     * @return
     */
    public User queryUserByName(@Param("username") String user_name);

    /**
     * 註冊用戶
     * @param user
     * @return
     */
    public int register(User user);
    public int insertUserRole(@Param("u_id") int u_id, @Param("r_id") int r_id);
    public int queryRole(@Param("Role") String name);
    public String[] getAllColumnNames(@Param("tableName") String name);
    public List<User> selectUsersByKeyword(Map<String,Object>map);

    /**
     * 登錄
     * @param map
     * @return
     */
    public List<User> login(Map<String, Object> map);
    public List<User> searchAllUser();
    public int deleteUser(int uid);
    public int updateUser(User user);
    public int addUser(Map<String ,Object> map);
    public User queryUserById(int id);
}
