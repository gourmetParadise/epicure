package com.xupt.wf.epicure.entity;

/**
 * Created with IntelliJ IDEA.
 * Description: 用户类
 * User: WangFeng
 * Date: 2018-04-05
 * Time: 15:31
 */

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Date;

/**
 * CREATE TABLE `user` (
 `user_id` INT(11) NOT NULL AUTO_INCREMENT,
 `user_nickname` VARCHAR(64) NOT NULL,
 `user_sex` VARCHAR(8) NOT NULL,
 `birth_date` DATE DEFAULT NULL,
 `native_place` VARCHAR(64) DEFAULT NULL COMMENT '用户籍贯',
 `now_place` VARCHAR(64) DEFAULT NULL COMMENT '现在居住地',
 `user_sign` VARCHAR(64) DEFAULT NULL COMMENT '用户签名',
 `user_photo` VARCHAR(64) DEFAULT NULL COMMENT '用户头像',
 `add_num` INT(11) NOT NULL DEFAULT '0' COMMENT '关注数量',
 `added_num` INT(11) NOT NULL DEFAULT '0' COMMENT '被关注数量',
 `user_email` VARCHAR(32) NOT NULL,
 `password` VARCHAR(16) NOT NULL,
 PRIMARY KEY (`user_id`)
 ) ENGINE=INNODB DEFAULT CHARSET=utf8 COMMENT='用户表'
 *
 */

public class User implements Serializable {
    private Integer userId;
    private String  userNickName;
    private String  userSex;
    private String birthDate;
    private String nativePlace;
    private String nowPlace;
    private String userSign;
    private String userPhoto;
    private Integer addNum;
    private Integer addedNum;
    private String userEmail;
    private String password;
    private String createTime;
    private String userCareer;

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getUserNickName() {
        return userNickName;
    }

    public void setUserNickName(String userNickName) {
        this.userNickName = userNickName;
    }

    public String getUserSex() {
        return userSex;
    }

    public void setUserSex(String userSex) {
        this.userSex = userSex;
    }

    public String getNativePlace() {
        return nativePlace;
    }

    public void setNativePlace(String nativePlace) {
        this.nativePlace = nativePlace;
    }

    public String getNowPlace() {
        return nowPlace;
    }

    public void setNowPlace(String nowPlace) {
        this.nowPlace = nowPlace;
    }

    public String getUserSign() {
        return userSign;
    }

    public void setUserSign(String userSign) {
        this.userSign = userSign;
    }

    public String getUserPhoto() {
        return userPhoto;
    }

    public void setUserPhoto(String userPhoto) {
        this.userPhoto = userPhoto;
    }

    public Integer getAddNum() {
        return addNum;
    }

    public void setAddNum(Integer addNum) {
        this.addNum = addNum;
    }

    public Integer getAddedNum() {
        return addedNum;
    }

    public void setAddedNum(Integer addedNum) {
        this.addedNum = addedNum;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUserCareer() {
        return userCareer;
    }

    public void setUserCareer(String userCareer) {
        this.userCareer = userCareer;
    }

    public String getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(String birthDate) {
        this.birthDate = birthDate;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }
}
