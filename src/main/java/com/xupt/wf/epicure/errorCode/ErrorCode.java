package com.xupt.wf.epicure.errorCode;

/**
 * Created with IntelliJ IDEA.
 * Description:
 * User: WangFeng
 * Date: 2018-04-25
 * Time: 17:37
 */
public enum ErrorCode {
    NICKNAME_IS_NORMAL(10000, "此用户名合适"),
    NICKNAME_IS_EXIST(10001, "此用户已经存在"),

    REGISTER_SUCCESS(10000, "注册成功"),
    REGISTER_FAIL(10001, "注册失败"),

    LOGIN_SUCCESS(10000, "登录成功"),
    LOGIN_FAIL(10001, "用户名或者密码错误"),

    UPDATE_USER_SUCCESS(10000, "修改成功"),
    UPDATE_USER_FAIL(10001, "修改失败"),

    FILE_UPLOAD_SUCCESS(10000, "图片上传成功"),
    FILE_UPLOAD_FAIL(10001, "图片上传失败"),
    FILE_UPLOAD_DAMAGE(10002, "图片为空或已损坏"),

    COOKBOOK_UPLOAD_SUCC(10000, "菜谱上传成功"),
    COOKBOOK_UPLOAD_FAIL(10001, "菜谱上传失败"),

    WORK_UPLOAD_SUCC(10000, "作品上传成功"),
    WORK_UPLOAD_FAIL(10001, "作品上传失败"),

    SCORE_SUCC(10000, "评分成功"),
    SCORE_FAIL(10001, "评分失败"),

    COOKBOOK_UPDATE_SUCC(10000, "菜谱修改成功"),
    COOKBOOK_UPDATE_FAIL(10001, "菜谱修改失败"),

    COOKBOOK_DEL_SUCC(10000, "菜谱删除成功"),
    COOKBOOK_DEL_FAIL(10001, "菜谱删除失败"),

    COLLECT_ADD_SUCC(10000, "菜谱收藏成功"),
    COLLECT_ADD_FAIL(10001, "菜谱收藏失败"),

    IS_COLLECT(10000, "已收藏"),
    NOT_COLLECT(10001, "没收藏"),

    DEL_SUCC(10000, "取消收藏成功"),
    DEL_FAIL(10001, "取消收藏失败"),

    ADD_SUCC(10000, "关注成功"),
    ADD_FAIL(10000, "关注失败"),

    CANCEL_SUCC(10000, "取消关注成功"),
    CANCEL_FAIL(10001, "取消关注失败"),

    IS_FOLLOW(10000, "已关注"),
    NOT_FOLLOW(10001, "没关注"),

    QUERY_SUCCESS(10000, "查询成功"),
    QUERY_FAIL(10001, "查询失败"),

    SERVER_INTERNAL_FAIL(9999, "服务内部错误");

    private int status;
    private String msg;

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    ErrorCode(int status, String msg) {
        this.status = status;
        this.msg = msg;
    }

}
