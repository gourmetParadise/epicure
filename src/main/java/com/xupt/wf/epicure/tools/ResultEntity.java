package com.xupt.wf.epicure.tools;

/**
 * Created with IntelliJ IDEA.
 * Description: 结果类
 * User: WangFeng
 * Date: 2018-04-17
 * Time: 3:05
 */
public class ResultEntity<T> {
    private int status;
    private String error = "";
    private T value;

    public ResultEntity(int status, String error, T value){
        this.status = status;
        this.error = error;
        this.value = value;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public void setError(String error) {
        this.error = error;
    }

    public int getStatus() {
        return status;
    }

    public String getError() {
        return error;
    }

    public T getValue() {
        return value;
    }

    public void setValue(T value) {
        this.value = value;
    }
}
