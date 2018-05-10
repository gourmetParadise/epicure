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
    private Integer cookbookName;
    private String userName;
    private String workName;
    private String workImage;
    private String createTime;
    private String updateTime;
    private String wordDesc;
    private Integer likeNum;
    private Integer readNum;
    private Integer workScore;

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

    public Integer getCookbookName() {
        return cookbookName;
    }

    public void setCookbookName(Integer cookbookName) {
        this.cookbookName = cookbookName;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getWorkName() {
        return workName;
    }

    public void setWorkName(String workName) {
        this.workName = workName;
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

    public String getWordDesc() {
        return wordDesc;
    }

    public void setWordDesc(String wordDesc) {
        this.wordDesc = wordDesc;
    }

    public Integer getLikeNum() {
        return likeNum;
    }

    public void setLikeNum(Integer likeNum) {
        this.likeNum = likeNum;
    }

    public Integer getReadNum() {
        return readNum;
    }

    public void setReadNum(Integer readNum) {
        this.readNum = readNum;
    }

    public Integer getWorkScore() {
        return workScore;
    }

    public void setWorkScore(Integer workScore) {
        this.workScore = workScore;
    }

    @Override
    public String toString() {
        return "Work{" +
                "workId=" + workId +
                ", cookbookId=" + cookbookId +
                ", cookbookName=" + cookbookName +
                ", userName='" + userName + '\'' +
                ", workName='" + workName + '\'' +
                ", workImage='" + workImage + '\'' +
                ", createTime='" + createTime + '\'' +
                ", updateTime='" + updateTime + '\'' +
                ", wordDesc='" + wordDesc + '\'' +
                ", likeNum=" + likeNum +
                ", readNum=" + readNum +
                ", workScore=" + workScore +
                '}';
    }
}
