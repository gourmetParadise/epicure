package com.xupt.wf.epicure.mapper;

import com.xupt.wf.epicure.entity.UserShip;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * Description:
 * User: WangFeng
 * Date: 2018-05-08
 * Time: 22:42
 */
public interface UserShipMapper {

    int addShip(UserShip userShip);

    int deleteShip(UserShip userShip);

    int increAddNum(String userName);

    int increAddedNum(String userName);

    int decreAddNum(String userName);

    int decreAddedNum(String userName);

    List<UserShip> addshiplist(String selfName);

    List<UserShip> addedshiplist(String selfName);

    int isFollow(@Param("selfName") String selfName, @Param("friendName") String friendName);
}
