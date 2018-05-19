package com.xupt.wf.epicure.controller;

import com.alibaba.fastjson.JSONObject;
import com.xupt.wf.epicure.entity.CookBook;
import com.xupt.wf.epicure.entity.Work;
import com.xupt.wf.epicure.errorCode.ErrorCode;
import com.xupt.wf.epicure.service.WorkService;
import com.xupt.wf.epicure.tools.FileUtils;
import com.xupt.wf.epicure.tools.RequestUtils;
import com.xupt.wf.epicure.tools.ResultEntity;
import com.xupt.wf.epicure.tools.TimeTools;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Date;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * Description:
 * User: WangFeng
 * Date: 2018-05-08
 * Time: 16:11
 */


@RestController
@RequestMapping(value = "/work")
public class WorkController {

    private static final Logger LOGGER = LoggerFactory.getLogger(WorkController.class);

    @Autowired
    private WorkService workService;

    @RequestMapping(value = "/uploadImg", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public String uploadWorkImg(@RequestParam("file") MultipartFile file , HttpServletRequest request){
        if(null != file){
            String contentType = file.getContentType();
            String fileName = file.getOriginalFilename();
            String filePath = request.getSession().getServletContext().getRealPath("/workPath/");
            fileName = TimeTools.formatFileName(new Date()) + fileName.substring(fileName.indexOf("."));
            LOGGER.info("===========contentType= "+ contentType);
            LOGGER.info("===========fileName= "+ fileName);
            LOGGER.info("============filePath= " + filePath);
            try{
                FileUtils.uploadFile(file.getBytes(), filePath, fileName);
                String imagePath = "/workPath/" + fileName;
                Map<String, Object> resultMap = workService.addWorkImage(imagePath);
                return JSONObject.toJSONString(new ResultEntity<>(ErrorCode.QUERY_SUCCESS.getStatus(), "", resultMap));
            } catch (Exception e){
                e.printStackTrace();
                return JSONObject.toJSONString(new ResultEntity<>(ErrorCode.FILE_UPLOAD_FAIL.getStatus(), e.getMessage(), ErrorCode.FILE_UPLOAD_FAIL.getMsg()));
            }
        } else {
            return JSONObject.toJSONString(new ResultEntity<>(ErrorCode.FILE_UPLOAD_DAMAGE.getStatus(), "", ErrorCode.FILE_UPLOAD_DAMAGE.getMsg()));
        }
    }

    @RequestMapping(value = "/uploadMessage", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public String uploadWork(HttpServletRequest request){
        String reqStr = RequestUtils.getRequestBody(request);
        JSONObject jsonCook = JSONObject.parseObject(reqStr);
        Work work = new Work();
        work.setWorkId(jsonCook.getInteger("workId"));
        work.setCookbookId(jsonCook.getInteger("cookbookId"));
        work.setUserName(jsonCook.getString("userName"));
        work.setWorkDesc(jsonCook.getString("workDesc"));
        if(workService.updateWork(work)){
            HttpSession session = request.getSession();
            session.setAttribute("cookbookId", work.getCookbookId());
            return JSONObject.toJSONString(new ResultEntity<>(ErrorCode.WORK_UPLOAD_SUCC.getStatus(), "", ErrorCode.WORK_UPLOAD_SUCC.getMsg()));
        } else {
            return JSONObject.toJSONString(new ResultEntity<>(ErrorCode.WORK_UPLOAD_FAIL.getStatus(), "", ErrorCode.WORK_UPLOAD_FAIL.getMsg()));
        }
    }

    @RequestMapping(value = "/{workId}/info", method = RequestMethod.GET, produces = "application/json;charset=UTF-8")
    public String getWorkById(@PathVariable("workId") Integer workId){
        Work work = workService.getWorkById(workId);
        return JSONObject.toJSONString(new ResultEntity<>(ErrorCode.QUERY_SUCCESS.getStatus(),"", work));
    }

    @RequestMapping(value = "/workScore", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public String uploadScore(HttpServletRequest request){
        String reqStr = RequestUtils.getRequestBody(request);
        JSONObject jsonCook = JSONObject.parseObject(reqStr);
        Integer workId = jsonCook.getInteger("workId");
        Float score = jsonCook.getInteger("workScore").floatValue();
        if(workService.addScore(request, workId, score)){
            return JSONObject.toJSONString(new ResultEntity<>(ErrorCode.SCORE_SUCC.getStatus(), "", ErrorCode.SCORE_SUCC.getMsg()));
        } else {
            return JSONObject.toJSONString(new ResultEntity<>(ErrorCode.SCORE_FAIL.getStatus(), "", ErrorCode.SCORE_FAIL.getMsg()));
        }
    }

}

