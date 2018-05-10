package com.xupt.wf.epicure.controller;

import com.alibaba.fastjson.JSONObject;
import com.sun.org.apache.regexp.internal.RE;
import com.xupt.wf.epicure.entity.CookCollect;
import com.xupt.wf.epicure.errorCode.ErrorCode;
import com.xupt.wf.epicure.service.CookCollectService;
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
import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * Description:
 * User: WangFeng
 * Date: 2018-05-08
 * Time: 21:29
 */

@RestController
@RequestMapping(value = "/collect")
public class CookCollectController {

    private static final Logger LOGGER = LoggerFactory.getLogger(CookCollectController.class);

    @Autowired
    private CookCollectService collectService;

    @RequestMapping(value = "/add", method = RequestMethod.POST, produces = "application/json")
    public String addCollect(HttpServletRequest request, HttpSession session){
        String reqStr = RequestUtils.getRequestBody(request);
        JSONObject jsonObject = JSONObject.parseObject(reqStr);

        CookCollect cookCollect = new CookCollect();
        cookCollect.setUserName(jsonObject.getString("userName"));
        cookCollect.setCookbookId(jsonObject.getInteger("cookbookId"));
        cookCollect.setCookbookName(jsonObject.getString("cookbookName"));
        cookCollect.setCookImage(jsonObject.getString("cookImage"));
        cookCollect.setCollectReason(jsonObject.getString("collectReason"));
        if(collectService.addCollect(cookCollect)){
            return JSONObject.toJSONString(new ResultEntity<>(ErrorCode.COLLECT_ADD_SUCC.getStatus(), "", ErrorCode.COLLECT_ADD_SUCC.getMsg()));
        } else {
            return JSONObject.toJSONString(new ResultEntity<>(ErrorCode.COLLECT_ADD_FAIL.getStatus(), "", ErrorCode.COLLECT_ADD_FAIL.getMsg()));
        }
    }

    @RequestMapping(value = "/{userName}/list", method = RequestMethod.GET, produces = "application/json")
    public String collectList(@PathVariable("userName") String userName){
        List<CookCollect> collectList = collectService.queryByName(userName);
        return JSONObject.toJSONString(new ResultEntity<>(ErrorCode.QUERY_SUCCESS.getStatus(), "", collectList));
    }

    @RequestMapping(value = "/{collectId}/{userName}/delete", method = RequestMethod.GET, produces = "application/json")
    public String deleteCollect(@PathVariable("userName") String userName, @PathVariable("collectId") Integer collectId){
        if(collectService.deleteCollect(userName, collectId)){
            return JSONObject.toJSONString(new ResultEntity<>(ErrorCode.DEL_SUCC.getStatus(), "", ErrorCode.DEL_SUCC.getMsg()));
        } else {
            return JSONObject.toJSONString(new ResultEntity<>(ErrorCode.DEL_FAIL.getStatus(), "", ErrorCode.DEL_FAIL.getMsg()));
        }
    }

    @RequestMapping(value = "/{collectId}/{userName}/iscollect", method = RequestMethod.GET, produces = "application/json")
    public String isCollect(@PathVariable("userName") String userName, @PathVariable("collectId") Integer collectId){
        if(collectService.isCollect(userName, collectId)){
            return JSONObject.toJSONString(new ResultEntity<>(ErrorCode.IS_COLLECT.getStatus(), "", ErrorCode.IS_COLLECT.getMsg()));
        } else {
            return JSONObject.toJSONString(new ResultEntity<>(ErrorCode.NOT_COLLECT.getStatus(), "", ErrorCode.NOT_COLLECT.getMsg()));
        }
    }
}
