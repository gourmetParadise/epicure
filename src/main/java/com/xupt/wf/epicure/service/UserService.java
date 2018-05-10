package com.xupt.wf.epicure.service;

import com.xupt.wf.epicure.entity.User;
import com.xupt.wf.epicure.mapper.UserMapper;
import com.xupt.wf.epicure.vo.UserReq;
import org.omg.CORBA.OBJ_ADAPTER;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.ListableBeanFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * Description:
 * User: WangFeng
 * Date: 2018-04-17
 * Time: 0:02
 */
@Service
public class UserService {

    private static Logger LOGGER = LoggerFactory.getLogger(UserService.class);

    @Autowired
    private UserMapper userMapper;

    public boolean isExist(String nickName){
        int count = userMapper.isExist(nickName);
        LOGGER.debug("==================================count= " + count);
        if(1 == count){
            return true;
        } else {
            return false;
        }
    }

    @Transactional(rollbackFor=Exception.class)
    public int registerUser(Map<String,Object> map){
        return userMapper.registerUser(map);
    }

    public List<User> getUserList(){
        return userMapper.queryAll();
    }

    public User getUserByName(String name){
        User user = userMapper.queryByName(name);
        LOGGER.debug("==================================user= " + user);
        return user;
    }

    @Transactional(rollbackFor = Exception.class)
    public int updateUser(User user){
        return userMapper.updateUser(user);
    }

    public boolean Login(String niceName, String password){
        String pass = userMapper.queryPassByName(niceName).trim();
        if(null != pass && pass.equals(password)){
            return true;
        } else {
            return false;
        }
    }


    public int uploadPhoto(String nickName, String filePath) {
        return userMapper.uploadPhoto(nickName, filePath);
    }
}
