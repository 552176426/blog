package com.lhhh.bean;

import java.util.Arrays;

/**
 * @author: lhhh
 * @date: Created in 2020/7/29
 * @description:
 * @version:1.0
 */
public class ResultLog {
    private String url;
    private String ip;
    private String classMethod;
    private Object[] args;

    public ResultLog(String url, String ip, String classMethod, Object[] args) {
        this.url = url;
        this.ip = ip;
        this.classMethod = classMethod;
        this.args = args;
    }

    @Override
    public String toString() {
        return "ResultLog{" +
                "url='" + url + '\'' +
                ", ip='" + ip + '\'' +
                ", classMethod='" + classMethod + '\'' +
                ", args=" + Arrays.toString(args) +
                '}';
    }
}
