package com.xupt.wf.epicure.controller;

import com.alibaba.fastjson.JSONObject;
import com.xupt.wf.epicure.entity.CookBook;
import com.xupt.wf.epicure.entity.Topic;
import com.xupt.wf.epicure.errorCode.ErrorCode;
import com.xupt.wf.epicure.service.TopicService;
import com.xupt.wf.epicure.tools.ResultEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * Description:
 * User: WangFeng
 * Date: 2018-05-17
 * Time: 10:57
 */
@RestController
@RequestMapping(value = "/category")
public class TopicController {

    @Autowired
    private TopicService topicService;

    @RequestMapping(value = "/list" , method = RequestMethod.GET, produces = "application/json;charset=UTF-8")
    public String getTopicList(){
        List<Topic> list = topicService.getList();
        if(list != null && list.size() > 0) {
            return JSONObject.toJSONString(new ResultEntity<>(ErrorCode.QUERY_SUCCESS.getStatus(),"", list));
        } else {
            return JSONObject.toJSONString(new ResultEntity<>(ErrorCode.QUERY_FAIL.getStatus(),"", ErrorCode.QUERY_FAIL.getStatus()));
        }
    }

    /**
     * 按类别查询
     * @param typeId
     * @return
     */
    @RequestMapping(value = "/{typeId}/list" , method = RequestMethod.GET, produces = "application/json;charset=UTF-8")
    public String getTopicList(@PathVariable("typeId") Integer typeId) {
        List<CookBook> list = topicService.getListByType(typeId);
        if(list != null && list.size() > 0) {
            return JSONObject.toJSONString(new ResultEntity<>(ErrorCode.QUERY_SUCCESS.getStatus(),"", list));
        } else {
            return JSONObject.toJSONString(new ResultEntity<>(ErrorCode.QUERY_FAIL.getStatus(),"", ErrorCode.QUERY_FAIL.getStatus()));
        }
    }


    @RequestMapping(value = "/limit" , method = RequestMethod.GET, produces = "application/json;charset=UTF-8")
    public String getTopicListByLimit(HttpServletRequest request) {

        List<CookBook> readList = topicService.getListByReadNumLimit();
        List<CookBook> countList = topicService.getListByCountLimit();
        List<CookBook> scoreList = topicService.getListByScoreLimit();

        HashMap<String, Object> map = new HashMap<>();
        if(readList != null && countList != null && scoreList != null) {
            map.put("readNum", readList);
            map.put("cookNum", countList);
            map.put("score", scoreList);
            return JSONObject.toJSONString(new ResultEntity<>(ErrorCode.QUERY_SUCCESS.getStatus(),"", map));
        } else {
            return JSONObject.toJSONString(new ResultEntity<>(ErrorCode.QUERY_FAIL.getStatus(),"", ErrorCode.QUERY_FAIL.getStatus()));
        }
    }

    /**
     * 按评分推荐
     * @return
     */
    @RequestMapping(value = "/score" , method = RequestMethod.GET, produces = "application/json;charset=UTF-8")
    public String getTopicListByScore(HttpServletRequest request){
        List<CookBook> list = topicService.getListByScore();
        HashMap<String, Object> map = new HashMap<>();
        if(list != null && list.size() > 0) {
            return JSONObject.toJSONString(new ResultEntity<>(ErrorCode.QUERY_SUCCESS.getStatus(),"", list));
        } else {
            return JSONObject.toJSONString(new ResultEntity<>(ErrorCode.QUERY_FAIL.getStatus(),"", ErrorCode.QUERY_FAIL.getStatus()));
        }
    }

    /**
     * 按评分推荐
     * @return
     */
    @RequestMapping(value = "/cookNum" , method = RequestMethod.GET, produces = "application/json;charset=UTF-8")
    public String getTopicListByCount(HttpServletRequest request){
        List<CookBook> list = topicService.getListByCount();
        HashMap<String, Object> map = new HashMap<>();
        if(list != null && list.size() > 0) {
            return JSONObject.toJSONString(new ResultEntity<>(ErrorCode.QUERY_SUCCESS.getStatus(),"", list));
        } else {
            return JSONObject.toJSONString(new ResultEntity<>(ErrorCode.QUERY_FAIL.getStatus(),"", ErrorCode.QUERY_FAIL.getStatus()));
        }
    }

    /**
     *
     * @param request
     * @return
     */
    @RequestMapping(value = "/readNum" , method = RequestMethod.GET, produces = "application/json;charset=UTF-8")
    public String getTopicListByNum(HttpServletRequest request){
        List<CookBook> list = topicService.getListByReadNum();
        HashMap<String, Object> map = new HashMap<>();
        if(list != null && list.size() > 0) {
            return JSONObject.toJSONString(new ResultEntity<>(ErrorCode.QUERY_SUCCESS.getStatus(),"", list));
        } else {
            return JSONObject.toJSONString(new ResultEntity<>(ErrorCode.QUERY_FAIL.getStatus(),"", ErrorCode.QUERY_FAIL.getStatus()));
        }
    }

}
