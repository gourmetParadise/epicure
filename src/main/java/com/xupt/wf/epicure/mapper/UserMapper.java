package com.xupt.wf.epicure.mapper;

import com.xupt.wf.epicure.entity.User;
import com.xupt.wf.epicure.vo.UserReq;
import org.apache.ibatis.annotations.Param;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * Description:
 * User: WangFeng
 * Date: 2018-04-06
 * Time: 13:50
 */

public interface UserMapper extends MyMapper<User>{

    int isExist(String nickName);

    int registerUser(Map<String, Object> map);

    List<User> queryAll();

    String queryPhoto(String nickName);

    User queryByName(String nickName);

    int updateUser(User user);

    int updatePassWord(@Param("newPassword") String newPassWord,@Param("nickName") String nickName);

    String queryPassByName(@Param("nickName")String name);

    int uploadPhoto(@Param("nickName") String nickName, @Param("filePath") String filePath);
}

