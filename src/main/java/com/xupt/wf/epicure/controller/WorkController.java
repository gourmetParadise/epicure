package com.xupt.wf.epicure.controller;

import com.alibaba.fastjson.JSONObject;
import com.xupt.wf.epicure.entity.CookBook;
import com.xupt.wf.epicure.errorCode.ErrorCode;
import com.xupt.wf.epicure.service.WorkService;
import com.xupt.wf.epicure.tools.FileUtils;
import com.xupt.wf.epicure.tools.ResultEntity;
import com.xupt.wf.epicure.tools.TimeTools;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
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

    @RequestMapping(value = "/upload", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public String uploadcookImage(@RequestParam("file") MultipartFile file , HttpServletRequest request){
        if(null != file){
            String contentType = file.getContentType();
            String fileName = file.getOriginalFilename();
            String filePath = request.getSession().getServletContext().getRealPath("/cookbookPath/");
            fileName = TimeTools.formatFileName(new Date()) + fileName.substring(fileName.indexOf("."));
            LOGGER.info("===========contentType= "+ contentType);
            LOGGER.info("===========fileName= "+ fileName);
            LOGGER.info("============filePath= " + filePath);
            try{
                FileUtils.uploadFile(file.getBytes(), filePath, fileName);
                String imagePath = "/cookbookPath/" + fileName;
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

}
