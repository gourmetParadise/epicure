package com.xupt.wf.epicure.entity;

import com.xupt.wf.epicure.vo.Ingredient;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * Description:
 * User: WangFeng
 * Date: 2018-05-05
 * Time: 11:43
 * CREATE TABLE `cookbook` (
 `cookbook_id` INT(11) NOT NULL AUTO_INCREMENT,
 `cookbook_name` VARCHAR(64) NOT NULL DEFAULT '',
 `cookbook_type` VARCHAR(64) NOT NULL DEFAULT '' COMMENT '菜式',
 `cookbook_occasion` VARCHAR(64) NOT NULL DEFAULT '' COMMENT '场合',
 `cookbook_crowd` VARCHAR(64) NOT NULL DEFAULT '' COMMENT '适合的人群',
 `cookbook_effect` VARCHAR(64) NOT NULL DEFAULT '' COMMENT '功效',
 `cookboot_material` VARCHAR(2000) NOT NULL DEFAULT '' COMMENT '原材料',
 `cook_step` VARCHAR(2048) NOT NULL DEFAULT '' COMMENT '步骤',
 `cookbook_picture` VARCHAR(128) DEFAULT '' COMMENT '照片',
 `birth_time` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
 `update_time` TIMESTAMP NOT NULL DEFAULT '0000-00-00 00:00:00',
 `cookbook_desc` VARCHAR(2000) DEFAULT '' COMMENT '其他描述',
 `user_name` VARCHAR(64) NOT NULL DEFAULT '',
 `collect_num` INT(11) DEFAULT '0' COMMENT '被收藏数量',
 `read_num` INT(11) DEFAULT '0' COMMENT '访问量',
 `cookbook_tips` VARCHAR(512) DEFAULT '' COMMENT '提示',
 `status` INT(11) DEFAULT '0' COMMENT '0 正常, -1 被删除',
 PRIMARY KEY (`cookbook_id`)
 ) ENGINE=INNODB DEFAULT CHARSET=utf8 COMMENT='菜谱表'

 */
public class CookBook {

    private Integer cookbookId;
    private String cookbookName;
    private String cookbookType;
    private String cookbookOccasion;
    private String cookbookCrowd;
    private String cookbookEffect;
    private String materials;
    private String cookbookSteps;
    private String cookImage;
    private String birthTime;
    private String updateTime;
    private String cookbookDesc;
    private String userName;
    private String userPhoto;
    private String cookbookTips;
    private Integer collectNum;
    private Integer readNum;
    private List<Ingredient> ingreList;

    public Integer getCookbookId() {
        return cookbookId;
    }

    public void setCookbookId(Integer cookbookId) {
        this.cookbookId = cookbookId;
    }

    public String getCookbookName() {
        return cookbookName;
    }

    public void setCookbookName(String cookbookName) {
        this.cookbookName = cookbookName;
    }

    public String getCookbookType() {
        return cookbookType;
    }

    public void setCookbookType(String cookbookType) {
        this.cookbookType = cookbookType;
    }

    public String getCookbookOccasion() {
        return cookbookOccasion;
    }

    public void setCookbookOccasion(String cookbookOccasion) {
        this.cookbookOccasion = cookbookOccasion;
    }

    public String getCookbookCrowd() {
        return cookbookCrowd;
    }

    public void setCookbookCrowd(String cookbookCrowd) {
        this.cookbookCrowd = cookbookCrowd;
    }

    public String getCookbookEffect() {
        return cookbookEffect;
    }

    public void setCookbookEffect(String cookbookEffect) {
        this.cookbookEffect = cookbookEffect;
    }

    public String getMaterials() {
        return materials;
    }

    public void setMaterials(String materials) {
        this.materials = materials;
    }

    public String getCookbookSteps() {
        return cookbookSteps;
    }

    public void setCookbookSteps(String cookbookSteps) {
        this.cookbookSteps = cookbookSteps;
    }

    public String getCookImage() {
        return cookImage;
    }

    public void setCookImage(String cookImage) {
        this.cookImage = cookImage;
    }

    public String getBirthTime() {
        return birthTime;
    }

    public void setBirthTime(String birthTime) {
        this.birthTime = birthTime;
    }

    public String getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(String updateTime) {
        this.updateTime = updateTime;
    }
    public String getCookbookDesc() {
        return cookbookDesc;
    }

    public void setCookbookDesc(String cookbookDesc) {
        this.cookbookDesc = cookbookDesc;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getCookbookTips() {
        return cookbookTips;
    }

    public void setCookbookTips(String cookbookTips) {
        this.cookbookTips = cookbookTips;
    }

    public Integer getCollectNum() {
        return collectNum;
    }

    public void setCollectNum(Integer collectNum) {
        this.collectNum = collectNum;
    }

    public Integer getReadNum() {
        return readNum;
    }

    public void setReadNum(Integer readNum) {
        this.readNum = readNum;
    }

    public String getUserPhoto() {
        return userPhoto;
    }

    public void setUserPhoto(String userPhoto) {
        this.userPhoto = userPhoto;
    }

    public List<Ingredient> getIngreList() {
        return ingreList;
    }

    public void setIngreList(List<Ingredient> ingreList) {
        this.ingreList = ingreList;
    }
}
