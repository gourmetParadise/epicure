package com.xupt.wf.epicure.service;

import com.xupt.wf.epicure.entity.UserShip;
import com.xupt.wf.epicure.mapper.UserShipMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * Description:
 * User: WangFeng
 * Date: 2018-05-09
 * Time: 0:01
 */

@Service
public class UserShipService {

    private static final Logger LOGGER = LoggerFactory.getLogger(Logger.class);

    @Autowired
    private UserShipMapper userShipMapper;

    @Transactional
    public boolean addUserShip(UserShip userShip){
        boolean result = false;
        int count = userShipMapper.addShip(userShip);
        if(1 == count){
            //自己的关注数量加一
            userShipMapper.increAddNum(userShip.getSelfName());
            UserShip userShip_ = new UserShip();
            userShip_.setSelfName(userShip.getFriendName());
            userShip_.setFriendName(userShip.getSelfName());
            userShip_.setShipType(-1);
            count = userShipMapper.addShip(userShip_);
            if(1 == count){
                // 你所关注的人的被关注数量加一
                userShipMapper.increAddedNum(userShip.getFriendName());
                result = true;
            }
        }
        return result;
    }

    //获取用户关注列表
    public List<UserShip> addUserShipList(String selfName){
        List<UserShip> shipList = userShipMapper.addshiplist(selfName);
        LOGGER.info("=========================shipList=" + shipList);
        return shipList;
    }

    //获取用户被关注列表
    public List<UserShip> addedUserShipList(String selfName){
        List<UserShip> shipList = userShipMapper.addedshiplist(selfName);
        LOGGER.info("=========================shipList=" + shipList);
        return shipList;
    }

    @Transactional
    public boolean deleteUserShip(UserShip userShip){
        boolean result = false;
        int count = userShipMapper.deleteShip(userShip);
        if(1 == count) {
            //自己关注的人的数目减一
            userShipMapper.decreAddNum(userShip.getSelfName());
            UserShip userShip_ = new UserShip();
            userShip_.setSelfName(userShip.getFriendName());
            userShip_.setFriendName(userShip.getSelfName());
            userShip_.setShipType(-1);
            count = userShipMapper.deleteShip(userShip_);
            if(1 == count){
                // 你所关注的人的被关注数量减一
                userShipMapper.decreAddedNum(userShip.getFriendName());
                result = true;
            }
        }
        return result;
    }

    public boolean isFollow(String selfName, String friendName){
        boolean result = false;
        int count = userShipMapper.isFollow(selfName,friendName);
        if(count == 1) {
            result = true;
        }
        return result;
    }

}
