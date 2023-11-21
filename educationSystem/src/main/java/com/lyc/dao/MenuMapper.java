package com.lyc.dao;

import com.lyc.bean.Menu;
import org.apache.ibatis.annotations.Param;


import java.util.List;

public interface MenuMapper {
    /**
     * 按角色读取菜单
     * @param role_id
     * @return
     */
    public List<Menu> queryMenuByRole(int role_id);
    public int queryRoleById(@Param("id") int u_id);
}
