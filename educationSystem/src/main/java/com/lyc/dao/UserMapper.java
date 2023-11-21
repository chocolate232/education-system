package com.lyc.dao;

import com.lyc.bean.Menu;
import com.lyc.bean.User;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;


@Repository
public interface UserMapper {
    /**
     * 檢查用戶是否存在
     * @param user_name
     * @return
     */
    public User queryUserByName(String user_name);

    /**
     * 註冊用戶
     * @param user
     * @return
     */
    public int register(User user);
    public int insertUserRole(@Param("u_id") int u_id,@Param("r_id") int r_id);

    /**
     * 登錄
     * @param map
     * @return
     */
    public List<User> login(Map<String, Object> map);
    public List<User> searchAllUser();
    public int deleteUser(int uid);
    public int updateUser(User user);
    public User queryUserById(int id);
}
