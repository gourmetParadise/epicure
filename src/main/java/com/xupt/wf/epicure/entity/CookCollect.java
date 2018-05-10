package com.xupt.wf.epicure.entity;

/**
 * Created with IntelliJ IDEA.
 * Description:
 * User: WangFeng
 * Date: 2018-05-08
 * Time: 18:58
 */
public class CookCollect {

    private Integer collectId;
    private String userName;
    private Integer cookbookId;
    private String cookbookName;
    private String cookImage;
    private String collectReason;
    private String collectTime;

    public Integer getCollectId() {
        return collectId;
    }

    public void setCollectId(Integer collectId) {
        this.collectId = collectId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
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

    public String getCollectReason() {
        return collectReason;
    }

    public void setCollectReason(String collectReason) {
        this.collectReason = collectReason;
    }

    public String getCollectTime() {
        return collectTime;
    }

    public void setCollectTime(String collectTime) {
        this.collectTime = collectTime;
    }

    public String getCookImage() {
        return cookImage;
    }

    public void setCookImage(String cookImage) {
        this.cookImage = cookImage;
    }

    @Override
    public String toString() {
        return "CookCollect{" +
                "collectId=" + collectId +
                ", userName='" + userName + '\'' +
                ", cookbookId=" + cookbookId +
                ", cookbookName='" + cookbookName + '\'' +
                ", cookImage='" + cookImage + '\'' +
                ", collectReason='" + collectReason + '\'' +
                ", collectTime='" + collectTime + '\'' +
                '}';
    }
}
