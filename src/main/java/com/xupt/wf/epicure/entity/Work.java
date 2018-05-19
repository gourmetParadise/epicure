package com.xupt.wf.epicure.entity;

/**
 * Created with IntelliJ IDEA.
 * Description:
 * User: WangFeng
 * Date: 2018-05-08
 * Time: 15:46
 */
public class Work {
    private Integer workId;
    private Integer cookbookId;
    private String userName;
    private String workImage;
    private String createTime;
    private String updateTime;
    private String workDesc;
    private Float workScore;

    public Integer getWorkId() {
        return workId;
    }

    public void setWorkId(Integer workId) {
        this.workId = workId;
    }

    public Integer getCookbookId() {
        return cookbookId;
    }

    public void setCookbookId(Integer cookbookId) {
        this.cookbookId = cookbookId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getWorkImage() {
        return workImage;
    }

    public void setWorkImage(String workImage) {
        this.workImage = workImage;
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

    public String getWorkDesc() {
        return workDesc;
    }

    public void setWorkDesc(String workDesc) {
        this.workDesc = workDesc;
    }

    public Float getWorkScore() {
        return workScore;
    }

    public void setWorkScore(Float workScore) {
        this.workScore = workScore;
    }
}
