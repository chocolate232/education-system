package com.lyc.es.dao;

import com.lyc.es.bean.Menu;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;


import java.util.List;

@Mapper
@Repository
public interface MenuMapper {
    /**
     * 按角色读取菜单
     * @param role_id
     * @return
     */
    public List<Menu> queryMenuByRole(int role_id);
}
