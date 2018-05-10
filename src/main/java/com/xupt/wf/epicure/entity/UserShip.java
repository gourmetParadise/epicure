package com.xupt.wf.epicure.entity;

/**
 * Created with IntelliJ IDEA.
 * Description:
 * User: WangFeng
 * Date: 2018-05-08
 * Time: 22:29
 */
public class UserShip {

    private Integer usershipId;
    private String selfName;
    private String friendName;
    private String createTime;
    private Integer shipType;
    private String friendPhoto;
    private Integer cookCount;

    public Integer getUsershipId() {
        return usershipId;
    }

    public void setUsershipId(Integer usershipId) {
        this.usershipId = usershipId;
    }

    public String getSelfName() {
        return selfName;
    }

    public void setSelfName(String selfName) {
        this.selfName = selfName;
    }

    public String getFriendName() {
        return friendName;
    }

    public void setFriendName(String friendName) {
        this.friendName = friendName;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public Integer getShipType() {
        return shipType;
    }

    public void setShipType(Integer shipType) {
        this.shipType = shipType;
    }

    public String getFriendPhoto() {
        return friendPhoto;
    }

    public void setFriendPhoto(String friendPhoto) {
        this.friendPhoto = friendPhoto;
    }

    public Integer getCookCount() {
        return cookCount;
    }

    public void setCookCount(Integer cookCount) {
        this.cookCount = cookCount;
    }

    @Override
    public String toString() {
        return "UserShip{" +
                "usershipId=" + usershipId +
                ", selfName='" + selfName + '\'' +
                ", friendName='" + friendName + '\'' +
                ", createTime='" + createTime + '\'' +
                ", shipType=" + shipType +
                ", friendPhoto='" + friendPhoto + '\'' +
                ", cookCount=" + cookCount +
                '}';
    }
}
