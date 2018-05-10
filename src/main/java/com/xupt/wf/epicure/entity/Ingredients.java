package com.xupt.wf.epicure.entity;

import java.io.Serializable;

/**
 * Created with IntelliJ IDEA.
 * Description:
 * User: WangFeng
 * Date: 2018-05-06
 * Time: 21:54
 * CREATE TABLE `ingredients` (
 `Ingre_id` INT(11) NOT NULL AUTO_INCREMENT COMMENT '食材主键',
 `Ingre_name` VARCHAR(64) NOT NULL DEFAULT '' COMMENT '食材名称',
 `Ingre_num` VARCHAR(16) NOT NULL DEFAULT '' COMMENT '食材的质量/数量',
 `cookbook_id` INT(11) NOT NULL COMMENT '菜谱的id',
 `cookbook_name` VARCHAR(64) NOT NULL DEFAULT '' COMMENT '菜谱的名称',
 `create_time` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建的时间',
 `update_time` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '修改的时间',
 `status` INT(1) DEFAULT '0' COMMENT '该记录的装态：0:正常,-1:被删除',
 PRIMARY KEY (`Ingre_id`)
 ) ENGINE=INNODB DEFAULT CHARSET=utf8 COMMENT='食材表'
 */
public class Ingredients implements Serializable{

    private Integer ingreId;
    private String source;
    private String amount;
    private Integer cookbookId;
    private String cookbookName;
    private String createTime;
    private String updateTime;

    public Integer getIngreId() {
        return ingreId;
    }

    public void setIngreId(Integer ingreId) {
        this.ingreId = ingreId;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

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

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(String updateTime) {
        this.updateTime = updateTime;
    }

    @Override
    public String toString() {
        return "Ingredients{" +
                "ingreId=" + ingreId +
                ", source='" + source + '\'' +
                ", amount='" + amount + '\'' +
                ", cookbookId=" + cookbookId +
                ", cookbookName='" + cookbookName + '\'' +
                ", createTime='" + createTime + '\'' +
                ", updateTime='" + updateTime + '\'' +
                '}';
    }
}
