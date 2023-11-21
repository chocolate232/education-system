package com.lyc.service;

import com.lyc.bean.Menu;
import com.lyc.bean.User;
import com.lyc.dao.MenuMapper;
import com.lyc.dao.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service //告诉spring应用，我要把这个类创建对象放置spring容器
public class UserServiceImpl implements  UserService{
    @Autowired// 依赖注入,我从容器里面取东西，对象
    UserMapper userMapper;//=new UserMapperImpl()

    @Autowired
    MenuMapper menuMapper;

    public User queryUserByName(String user_name){
        User user = userMapper.queryUserByName(user_name);
        return  user;
    }

    /**
     * 注册，首先校验用户名是否存在
     * 判断
     * @param user
     * @return
     */

    public  int register(User user){
//        取数据
        User rUser= userMapper.queryUserByName(user.getUsername());
        //校验用户名是否存在
        if(rUser !=null&&  rUser.getUsername().equals(user.getUsername())){
            return  1;//1代表用户已存在
        }
//        注册插入数据
        int r = userMapper.register(user);
        System.out.println(r+"插入数据");
        if(r ==1){
            User rUser2= userMapper.queryUserByName(user.getUsername());
            System.out.println(rUser2.getId()+"插入角色");
            int i = userMapper.insertUserRole(rUser2.getId(),3);
            if(i!=1){
                System.out.println("插入角色失败");
                deleteUser(rUser2);
                return 3;//插入角色失败
            }
            return 0;//成功
        }else {
            return 2;//插入不成功
        }

    }


    public  List<User>  login(User user){

        Map map =  new HashMap<String,Object>();
        map.put("username",user.getUsername());
        map.put("password",user.getPassword());
        List<User> a = userMapper.login(map);
        System.out.println(a);
        return a;
    }
    public List<Menu> queryMenuByRole(int id){
        int role_id=menuMapper.queryRoleById(id);
        return menuMapper.queryMenuByRole(role_id);
    }

    public List<User> searchAllUser(){
        return userMapper.searchAllUser();
    }
    public String deleteUser(User user){
        if (user.getId()==0){
            return "id不能为空";
        }
        if (userMapper.deleteUser(user.getId())==0){
            System.out.println("删除失败");
            return "删除失败";
        }
//        System.out.println(user.toString());
        System.out.println("删除成功");
        return "删除成功";
    }
    public String updateUser(User user){
        if (user.getId()==0){
            return "id不能为空";
        }
        if (userMapper.updateUser(user)==0){
            System.out.println("更新失败");
            return "更新失败";
        }
        System.out.println(user.toString());
        System.out.println("更新成功");
        return "更新成功";
    }
    public List<User> getUserByPage(int page,int size){
        List<User> getAllUser = userMapper.searchAllUser();
        int totalRecords=getAllUser.size();
        int totalPage=totalRecords/size;
        int offset = (page-1)*size;
        int endIndex=offset+size;
        List<User>currentPage = getAllUser.subList(offset,endIndex);
        return currentPage;

    }
        //


}
