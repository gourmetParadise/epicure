package com.xupt.wf.epicure.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.xupt.wf.epicure.entity.CookBook;
import com.xupt.wf.epicure.entity.UserHistory;
import com.xupt.wf.epicure.errorCode.ErrorCode;
import com.xupt.wf.epicure.service.CookBookService;
import com.xupt.wf.epicure.service.UserHistoryService;
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
 * Date: 2018-05-05
 * Time: 15:19
 */
@RestController
@RequestMapping(value = "/cookbook")
public class CookBookController {
    private static final Logger LOGGER = LoggerFactory.getLogger(CookBookController.class);

    @Autowired
    private CookBookService cookBookService;

    @Autowired
    private UserHistoryService userHistoryService;

    @RequestMapping(value = "/uploadImage", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
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
                CookBook cookBook = new CookBook();
                cookBook.setCookImage("/cookbookPath/" + fileName);
                Map<String, Object> resultMap = cookBookService.addCookImage(cookBook);
                return JSONObject.toJSONString(new ResultEntity<>(10000, "", resultMap));
            } catch (Exception e){
                e.printStackTrace();
                return JSONObject.toJSONString(new ResultEntity<>(ErrorCode.FILE_UPLOAD_FAIL.getStatus(), e.getMessage(), ErrorCode.FILE_UPLOAD_FAIL.getMsg()));
            }
        } else {
            return JSONObject.toJSONString(new ResultEntity<>(ErrorCode.FILE_UPLOAD_DAMAGE.getStatus(), "", ErrorCode.FILE_UPLOAD_DAMAGE.getMsg()));
        }
    }

    @RequestMapping(value = "/{cookbookId}/updateImage", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public String updatecookImage(@RequestParam("file") MultipartFile file ,  @PathVariable("cookbookId") Integer cookbookId, HttpServletRequest request){
        if(null != file){

            String contentType = file.getContentType();
            String fileName = file.getOriginalFilename();
            String filePath = request.getSession().getServletContext().getRealPath("/cookbookPath/");

//            Integer cookbookId = Integer.parseInt(request.getParameter("cookbookId"));
            fileName = TimeTools.formatFileName(new Date()) + fileName.substring(fileName.indexOf("."));
            LOGGER.info("============cookbookId=" + cookbookId);
            LOGGER.info("===========contentType= "+ contentType);
            LOGGER.info("===========fileName= "+ fileName);
            LOGGER.info("============filePath= " + filePath);
            try{
                FileUtils.uploadFile(file.getBytes(), filePath, fileName);
                String imagePath = "/cookbookPath/" + fileName;
                cookBookService.updateImage(cookbookId, imagePath);
                return JSONObject.toJSONString(new ResultEntity<>(10000, "", imagePath));
            } catch (Exception e){
                e.printStackTrace();
                return JSONObject.toJSONString(new ResultEntity<>(ErrorCode.FILE_UPLOAD_FAIL.getStatus(), e.getMessage(), ErrorCode.FILE_UPLOAD_FAIL.getMsg()));
            }
        } else {
            return JSONObject.toJSONString(new ResultEntity<>(ErrorCode.FILE_UPLOAD_DAMAGE.getStatus(), "", ErrorCode.FILE_UPLOAD_DAMAGE.getMsg()));
        }
    }

    @RequestMapping(value = "/uploadMessage", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public String addCookbook(HttpServletRequest request, HttpSession session){
        String cookRequest = RequestUtils.getRequestBody(request);
        JSONObject jsonCook = JSONObject.parseObject(cookRequest);
        CookBook cookBook = new CookBook();
        cookBook.setCookbookId(jsonCook.getInteger("cookbookId"));
        cookBook.setCookbookName(jsonCook.getString("cookbookName"));
        cookBook.setMaterials(jsonCook.getString("materials"));
        cookBook.setTypeId(jsonCook.getInteger("typeId"));
        LOGGER.info("=====================typeId=" + jsonCook.getInteger("typeId"));
        cookBook.setCookbookSteps(jsonCook.getString("cookbookSteps"));
        cookBook.setCookbookDesc(jsonCook.getString("cookbookDesc"));
        cookBook.setUserName(jsonCook.getString("userName"));
        cookBook.setCookbookTips(jsonCook.getString("cookbookTips"));
        if(cookBookService.addCookBook(cookBook)){
            return JSONObject.toJSONString(new ResultEntity<>(ErrorCode.COOKBOOK_UPLOAD_SUCC.getStatus(),"",ErrorCode.COOKBOOK_UPLOAD_SUCC.getMsg()));
        } else {
            return JSONObject.toJSONString(new ResultEntity<>(ErrorCode.COOKBOOK_UPLOAD_FAIL.getStatus(),"",ErrorCode.COOKBOOK_UPLOAD_FAIL.getMsg()));
        }
    }

    @RequestMapping(value = "/updateMessage", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public String updateCookbook(HttpServletRequest request, HttpSession session) {
        String cookRequest = RequestUtils.getRequestBody(request);
        JSONObject jsonCook = JSONObject.parseObject(cookRequest);
        CookBook cookBook = new CookBook();
        cookBook.setCookbookId(jsonCook.getInteger("cookbookId"));
        cookBook.setCookbookName(jsonCook.getString("cookbookName"));
        cookBook.setMaterials(jsonCook.getString("materials"));
        cookBook.setTypeId(jsonCook.getInteger("typeId"));
        LOGGER.info("=====================typeId=" + jsonCook.getInteger("typeId"));
        cookBook.setCookbookSteps(jsonCook.getString("cookbookSteps"));
        cookBook.setCookbookDesc(jsonCook.getString("cookbookDesc"));
        cookBook.setCookbookTips(jsonCook.getString("cookbookTips"));
        if (cookBookService.updateCookBook(cookBook)) {
            return JSONObject.toJSONString(new ResultEntity<>(ErrorCode.COOKBOOK_UPDATE_SUCC.getStatus(), "", ErrorCode.COOKBOOK_UPDATE_SUCC.getMsg()));
        } else {
            return JSONObject.toJSONString(new ResultEntity<>(ErrorCode.COOKBOOK_UPDATE_FAIL.getStatus(), "", ErrorCode.COOKBOOK_UPDATE_SUCC.getMsg()));
        }
    }
    
    @RequestMapping(value = "/{userName}/list", method = RequestMethod.GET, produces = "application/json;charset=UTF-8")
    public String cookbookList(@PathVariable("userName") String userName){
        return JSONObject.toJSONString(new ResultEntity<>(ErrorCode.QUERY_SUCCESS.getStatus(),"", cookBookService.queryListByName(userName)));
    }

    @RequestMapping(value = "/community", method = RequestMethod.GET, produces = "application/json;charset=UTF-8")
    public String cookbookList(){
        return JSONObject.toJSONString(new ResultEntity<>(ErrorCode.QUERY_SUCCESS.getStatus(),"", cookBookService.queryList()));
    }

    @RequestMapping(value = "/{cookbookId}/info", method = RequestMethod.GET, produces = "application/json;charset=UTF-8")
    public String cookbookInfo(@PathVariable("cookbookId") Integer cookbookId, HttpSession session){
        String userName = (String) session.getAttribute("nickName");
        CookBook cookbook = cookBookService.queryInfoById(cookbookId);
        if(cookbook != null ) {
            cookBookService.increReadCount(cookbookId);
            if(userName != null) {
                userHistoryService.addHistory(cookbook, userName);
            }
            return JSONObject.toJSONString(new ResultEntity<>(ErrorCode.QUERY_SUCCESS.getStatus(),"", cookbook));
        } else {
            return JSONObject.toJSONString(new ResultEntity<>(ErrorCode.QUERY_FAIL.getStatus(),"", ErrorCode.QUERY_FAIL.getMsg()));
        }

    }

    @RequestMapping(value = "/{cookbookId}/{userName}/delete", method = RequestMethod.GET, produces = "application/json;charset=UTF-8")
    public String deleteCookbook(@PathVariable("userName") String userName, @PathVariable("cookbookId") Integer cookbookId){
        if(cookBookService.deleteCookBook(cookbookId, userName)){
            return JSONObject.toJSONString(new ResultEntity<>(ErrorCode.COOKBOOK_DEL_SUCC.getStatus(),"",ErrorCode.COOKBOOK_DEL_SUCC.getMsg()));
        } else {
            return JSONObject.toJSONString(new ResultEntity<>(ErrorCode.COOKBOOK_DEL_FAIL.getStatus(),"",ErrorCode.COOKBOOK_DEL_FAIL.getMsg()));
        }
    }
}
