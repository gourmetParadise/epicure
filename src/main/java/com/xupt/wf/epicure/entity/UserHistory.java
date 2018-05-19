package com.xupt.wf.epicure.entity;

/**
 * Created with IntelliJ IDEA.
 * Description:
 * User: WangFeng
 * Date: 2018-05-15
 * Time: 22:23
 */
public class UserHistory {

    private Integer recordId;
    private String userName;
    private Integer typeId;
    private Integer cookbookId;
    private String cookbookName;
    private String cookbookType;
    private String materials;
    private Integer count;
    private String createTime;
    private String updateTime;

    public Integer getTypeId() {
        return typeId;
    }

    public void setTypeId(Integer typeId) {
        this.typeId = typeId;
    }

    public Integer getRecordId() {
        return recordId;
    }

    public void setRecordId(Integer recordId) {
        this.recordId = recordId;
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

    public String getCookbookType() {
        return cookbookType;
    }

    public void setCookbookType(String cookbookType) {
        this.cookbookType = cookbookType;
    }

    public String getMaterials() {
        return materials;
    }

    public void setMaterials(String materials) {
        this.materials = materials;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
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
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof UserHistory)) return false;

        UserHistory that = (UserHistory) o;

        if (getRecordId() != null ? !getRecordId().equals(that.getRecordId()) : that.getRecordId() != null)
            return false;
        if (getUserName() != null ? !getUserName().equals(that.getUserName()) : that.getUserName() != null)
            return false;
        if (getCookbookId() != null ? !getCookbookId().equals(that.getCookbookId()) : that.getCookbookId() != null)
            return false;
        if (getCookbookName() != null ? !getCookbookName().equals(that.getCookbookName()) : that.getCookbookName() != null)
            return false;
        if (getCookbookType() != null ? !getCookbookType().equals(that.getCookbookType()) : that.getCookbookType() != null)
            return false;
        if (getMaterials() != null ? !getMaterials().equals(that.getMaterials()) : that.getMaterials() != null)
            return false;
        if (getCount() != null ? !getCount().equals(that.getCount()) : that.getCount() != null) return false;
        if (getCreateTime() != null ? !getCreateTime().equals(that.getCreateTime()) : that.getCreateTime() != null)
            return false;
        return getUpdateTime() != null ? getUpdateTime().equals(that.getUpdateTime()) : that.getUpdateTime() == null;
    }

    @Override
    public int hashCode() {
        int result = getRecordId() != null ? getRecordId().hashCode() : 0;
        result = 31 * result + (getUserName() != null ? getUserName().hashCode() : 0);
        result = 31 * result + (getCookbookId() != null ? getCookbookId().hashCode() : 0);
        result = 31 * result + (getCookbookName() != null ? getCookbookName().hashCode() : 0);
        result = 31 * result + (getCookbookType() != null ? getCookbookType().hashCode() : 0);
        result = 31 * result + (getMaterials() != null ? getMaterials().hashCode() : 0);
        result = 31 * result + (getCount() != null ? getCount().hashCode() : 0);
        result = 31 * result + (getCreateTime() != null ? getCreateTime().hashCode() : 0);
        result = 31 * result + (getUpdateTime() != null ? getUpdateTime().hashCode() : 0);
        return result;
    }
}
