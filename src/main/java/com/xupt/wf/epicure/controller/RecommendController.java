package com.xupt.wf.epicure.controller;

import com.alibaba.fastjson.JSONObject;
import com.xupt.wf.epicure.entity.CookBook;
import com.xupt.wf.epicure.errorCode.ErrorCode;
import com.xupt.wf.epicure.service.UserHistoryService;
import com.xupt.wf.epicure.tools.ResultEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * Description:
 * User: WangFeng
 * Date: 2018-05-20
 * Time: 1:50
 */
@RestController
@RequestMapping(value = "/recommend")
public class RecommendController {

    @Autowired
    UserHistoryService userHistoryService;

    @RequestMapping(value = "/list", method = RequestMethod.GET, produces = "application/json;charset=UTF-8")
    public String recommend(){
        List<CookBook> recommendList = userHistoryService.recommend("admin3");
        return JSONObject.toJSONString(new ResultEntity<>(ErrorCode.RECOMMEND_SUCC.getStatus(), "", recommendList));
    }
}
