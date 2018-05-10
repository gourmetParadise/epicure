package com.xupt.wf.epicure.controller;

import com.alibaba.fastjson.JSONObject;
import com.xupt.wf.epicure.entity.CookBook;
import com.xupt.wf.epicure.entity.User;
import com.xupt.wf.epicure.errorCode.ErrorCode;
import com.xupt.wf.epicure.service.CookBookService;
import com.xupt.wf.epicure.service.UserService;
import com.xupt.wf.epicure.tools.*;
import com.xupt.wf.epicure.vo.UserReq;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * Created with IntelliJ IDEA.
 * Description:
 * User: WangFeng
 * Date: 2018-04-17
 * Time: 2:49
 */
@RestController
@RequestMapping(value = "/user")
public class UserController {

    private static Logger LOGGER = LoggerFactory.getLogger(UserController.class);

    @Autowired
    private UserService userService;

    @Autowired
    private CookBookService cookBookService;

    @RequestMapping(value = "/login", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public String userLogin(HttpServletRequest request, HttpSession session){
        String userReq = RequestUtils.getRequestBody(request);
        System.out.println("UserController.userLogin==" + userReq);
        JSONObject req = JSONObject.parseObject(userReq);
        String nickName = req.getString("nickName");
        String password = req.getString("password");
        boolean result = userService.Login(nickName, password);
        String msg = null;
        if(result){
            msg = "恭喜" + nickName + ErrorCode.LOGIN_SUCCESS.getMsg();
            session.setAttribute("nickName", nickName);
            return JSONObject.toJSONString(new ResultEntity<>(ErrorCode.LOGIN_SUCCESS.getStatus(), "" ,msg));
        } else {
            return JSONObject.toJSONString(new ResultEntity<>(ErrorCode.LOGIN_FAIL.getStatus(), "", ErrorCode.LOGIN_FAIL.getMsg()));
        }
    }

    @RequestMapping(value = "/register", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public String userRegister(HttpServletRequest request, HttpSession session){
        String user = RequestUtils.getRequestBody(request);
        JSONObject req = JSONObject.parseObject(user);
        Map<String, Object> map = new HashMap<>();
        map.put("userNickName", req.getString("nickName"));
        map.put("userEmail", req.getString("email"));
        map.put("password", req.getString("password"));
        int result = userService.registerUser(map);
        if(1 == result ){
            return JSONObject.toJSONString(new ResultEntity<>(ErrorCode.REGISTER_SUCCESS.getStatus(), "", ErrorCode.REGISTER_SUCCESS.getMsg()));
        } else {
            return JSONObject.toJSONString(new ResultEntity<>(ErrorCode.REGISTER_FAIL.getStatus(), "", ErrorCode.REGISTER_FAIL.getMsg()));
        }
    }

    @RequestMapping(value = "/{nickName}/judge", method = RequestMethod.GET, produces = "application/json;charset=UTF-8")
    public String judgeNickName(@PathVariable("nickName") String nickName){
        boolean result = userService.isExist(nickName);
        if(result){
            return JSONObject.toJSONString(new ResultEntity<>(ErrorCode.NICKNAME_IS_EXIST.getStatus(), "", ErrorCode.NICKNAME_IS_EXIST.getMsg()));
        } else {
            return JSONObject.toJSONString(new ResultEntity<>(ErrorCode.NICKNAME_IS_NORMAL.getStatus(), "", ErrorCode.NICKNAME_IS_NORMAL.getMsg()));
        }
    }

    @RequestMapping(value = "/update", method = RequestMethod.POST,produces = "application/json;charset=UTF-8")
    public String updateUserInfo(@RequestBody UserReq userReq, HttpServletRequest request){
        User user = new User();
        user.setUserNickName(userReq.getUserNickName());
        user.setUserSex(userReq.getUserSex());
        user.setBirthDate(userReq.getBirthDate());
        user.setNativePlace(userReq.getNativePlace());
        user.setUserSign(userReq.getUserSign());
        user.setUserCareer(userReq.getUserCareer());
        int result = userService.updateUser(user);
        if(1 == result){
            return JSONObject.toJSONString(new ResultEntity<>(ErrorCode.UPDATE_USER_SUCCESS.getStatus(), "", ErrorCode.UPDATE_USER_SUCCESS.getMsg()));
        } else {
            return JSONObject.toJSONString(new ResultEntity<>(ErrorCode.UPDATE_USER_FAIL.getStatus(), "", ErrorCode.UPDATE_USER_FAIL.getMsg()));
        }
    }

    @RequestMapping(value = "/{nickName}/info", method = RequestMethod.GET, produces = "application/json;charset=UTF-8")
    public String queryUser(@PathVariable("nickName") String nickName){
        Map<String, Object> resultMap = new HashMap<>();
        User user = userService.getUserByName(nickName);
        List<CookBook> cookBookList = cookBookService.queryListByName(nickName);
        resultMap.put("user", user);
        resultMap.put("cookBookList", cookBookList);
        return JSONObject.toJSONString(new ResultEntity<>(ErrorCode.QUERY_SUCCESS.getStatus(), "", resultMap));
    }

    @RequestMapping(value = "/getNowTime", method = RequestMethod.GET, produces = "application/json;charset=UTF-8")
    public String getNowTime() {
        Date nowTime = new Date();
        String currentTime = TimeTools.formatDate(nowTime);
        Map<String, String> map = new HashMap<>();
        map.put("nowTime", currentTime);
        return JSONObject.toJSONString(map);
    }

    @RequestMapping(value = "/uploadPhoto", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public String ImageUpload(@RequestParam("file") MultipartFile file, HttpServletRequest request){
        if(null != file){
            String contentType = file.getContentType();
            String fileName = file.getOriginalFilename();
            LOGGER.info("===========contentType= "+ contentType);
            LOGGER.info("===========fileName= "+ fileName);
            String filePath = request.getSession().getServletContext().getRealPath("/imgPath/");
            LOGGER.info("============filePath= " + filePath);
            fileName = TimeTools.formatFileName(new Date()) + fileName.substring(fileName.indexOf("."));
            try{
                FileUtils.uploadFile(file.getBytes(), filePath, fileName);
                String nickName = request.getSession().getAttribute("nickName").toString();
                //存储数据库
                if(null != nickName){
                    String imagePath = "/imgPath/" + fileName;
                    int result = userService.uploadPhoto(nickName, imagePath);
                    if(1 == result){
                        return JSONObject.toJSONString(new ResultEntity<>(ErrorCode.QUERY_SUCCESS.getStatus(), "", imagePath));
                    }
                }
            } catch (Exception e){
                e.printStackTrace();
                return JSONObject.toJSONString(new ResultEntity<>(ErrorCode.FILE_UPLOAD_FAIL.getStatus(), e.getMessage(), ErrorCode.FILE_UPLOAD_FAIL.getMsg()));
            }
        } else {
            return JSONObject.toJSONString(new ResultEntity<>(ErrorCode.FILE_UPLOAD_DAMAGE.getStatus(), "", ErrorCode.FILE_UPLOAD_DAMAGE.getMsg()));
        }
        return JSONObject.toJSONString(new ResultEntity<>(ErrorCode.FILE_UPLOAD_SUCCESS.getStatus(), "", ErrorCode.FILE_UPLOAD_SUCCESS.getMsg()));
    }
    
}
