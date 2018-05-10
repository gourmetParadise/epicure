package com.xupt.wf.epicure.controller;

import com.alibaba.fastjson.JSONObject;
import com.xupt.wf.epicure.entity.UserShip;
import com.xupt.wf.epicure.errorCode.ErrorCode;
import com.xupt.wf.epicure.service.UserShipService;
import com.xupt.wf.epicure.tools.RequestUtils;
import com.xupt.wf.epicure.tools.ResultEntity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * Description:
 * User: WangFeng
 * Date: 2018-05-09
 * Time: 8:08
 */
@RestController
@RequestMapping(value = "/usership")
public class UserShipController {
    private static final Logger LOGGER = LoggerFactory.getLogger(UserShipController.class);

    @Autowired
    private UserShipService userShipService;

    @RequestMapping(value = "/add", method = RequestMethod.POST, produces = "application/json; charset=UTF-8")
    public String addUserShip(HttpServletRequest request){
        String reqStr = RequestUtils.getRequestBody(request);
        JSONObject jsonObject = JSONObject.parseObject(reqStr);
        UserShip userShip = new UserShip();
        userShip.setSelfName(jsonObject.getString("selfName"));
        userShip.setFriendName(jsonObject.getString("friendName"));
        userShip.setShipType(jsonObject.getInteger("shipType"));
        ResultEntity<String> resultEntity;
        if(userShipService.addUserShip(userShip)){
            resultEntity = new ResultEntity<>(ErrorCode.ADD_SUCC.getStatus(), "", ErrorCode.ADD_SUCC.getMsg());
        } else {
            resultEntity = new ResultEntity<>(ErrorCode.ADD_FAIL.getStatus(), "", ErrorCode.ADD_FAIL.getMsg());
        }
        return JSONObject.toJSONString(resultEntity);
    }

    @RequestMapping(value = "/delete", method = RequestMethod.POST, produces = "application/json; charset=UTF-8")
    public String deleteUserShip(HttpServletRequest request){
        String reqStr = RequestUtils.getRequestBody(request);
        JSONObject jsonObject = JSONObject.parseObject(reqStr);
        UserShip userShip = new UserShip();
        userShip.setSelfName(jsonObject.getString("selfName"));
        userShip.setFriendName(jsonObject.getString("friendName"));
        userShip.setShipType(jsonObject.getInteger("shipType"));
        ResultEntity<String> resultEntity;
        if(userShipService.deleteUserShip(userShip)){
            resultEntity = new ResultEntity<>(ErrorCode.CANCEL_SUCC.getStatus(), "", ErrorCode.CANCEL_SUCC.getMsg());
        } else {
            resultEntity = new ResultEntity<>(ErrorCode.CANCEL_FAIL.getStatus(), "", ErrorCode.CANCEL_FAIL.getMsg());
        }
        return JSONObject.toJSONString(resultEntity);
    }

    @RequestMapping(value = "/{selfName}/addshiplist", method = RequestMethod.GET, produces = "application/json; charset=UTF-8")
    public String addUserShipList(@PathVariable("selfName") String selfName){
        List<UserShip> addlist = userShipService.addUserShipList(selfName);
        return JSONObject.toJSONString(new ResultEntity<>(ErrorCode.QUERY_SUCCESS.getStatus(), "", addlist));
    }

    @RequestMapping(value = "/{selfName}/addedshiplist", method = RequestMethod.GET, produces = "application/json; charset=UTF-8")
    public String addedUserShipList(@PathVariable("selfName") String selfName){
        List<UserShip> addedlist = userShipService.addedUserShipList(selfName);
        return JSONObject.toJSONString(new ResultEntity<>(ErrorCode.QUERY_SUCCESS.getStatus(), "", addedlist));
    }

    @RequestMapping(value = "/isfollow", method = RequestMethod.POST, produces = "application/json; charset=UTF-8")
    public String isFollow(HttpServletRequest request){
        String reqStr = RequestUtils.getRequestBody(request);
        JSONObject jsonObject = JSONObject.parseObject(reqStr);
        String selfName = jsonObject.getString("selfName");
        String friendName = jsonObject.getString("friendName");
        ResultEntity<String> resultEntity;
        if(userShipService.isFollow(selfName, friendName)){
            resultEntity = new ResultEntity<>(ErrorCode.IS_FOLLOW.getStatus(), "", ErrorCode.IS_FOLLOW.getMsg());
        } else {
            resultEntity = new ResultEntity<>(ErrorCode.NOT_FOLLOW.getStatus(), "", ErrorCode.NOT_FOLLOW.getMsg());
        }
        return JSONObject.toJSONString(resultEntity);
    }
}
