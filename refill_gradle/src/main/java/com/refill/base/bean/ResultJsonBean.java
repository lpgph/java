package com.refill.base.bean;

import com.refill.base.constant.e.BaseEnum;
import com.refill.util.StringUtil;

/**
 * @author Peter Guo
 * @version 0.1
 * @ClassName ResultJsonBean
 * @Description json数据返回数据格式
 * @date 17/04/25
 */
public class ResultJsonBean extends BaseBean {

    public ResultJsonBean(BaseEnum code, Object data, boolean flag, String msg){
        if (code != null) {
            this.code=code.toString();
        }
        this.result = flag;
        if (!StringUtil.isBlank(msg)) {
            this.resultMsg = msg;
        }
        if (!StringUtil.isBlank(data)) {
            this.resultData = data;
        }
    }


    public ResultJsonBean(BaseEnum code, Object data, boolean flag){
        this(code,data,flag,null);
    }
    public ResultJsonBean(BaseEnum code, boolean flag, String msg){
        this(code,null,flag,msg);
    }

    public ResultJsonBean(BaseEnum code, boolean flag){
        this(code,null,flag,null);
    }




    /**
     * 模块编号
     */
    private String code;

    /**
     * 返回状态:true成功 false:失败
     */
    private boolean result;

    /**
     * 返回信息提示
     */
    private String resultMsg;

    /**
     * 返回数据
     */
    private Object resultData;

    /**
     * 返回后跳的地址
     */
    private String url;

    /**
     * 获取后跳的地址
     *
     * @return 返回后跳的地址
     */
    public String getUrl() {
        return url;
    }

    /**
     * 设置后跳的地址
     *
     * @param url 后跳地址
     */
    public void setUrl(String url) {
        this.url = url;
    }

    /**
     * 获取模块编号
     *
     * @return 返回模块编号
     */
    public String getCode() {
        return code;
    }

    /**
     * 设置模块编号
     *
     * @param code 模块编号
     */
    public void setCode(String code) {
        this.code = code;
    }

    /**
     * 获取返回状态
     *
     * @return 返回状态
     */
    public boolean isResult() {
        return result;
    }

    /**
     * 设置返回状态
     *
     * @param result 返回状态
     */
    public void setResult(boolean result) {
        this.result = result;
    }

    /**
     * 设置返回信息提示
     *
     * @return 返回提示信息
     */
    public String getResultMsg() {
        return resultMsg;
    }

    /**
     * 设置返回信息提示
     *
     * @param resultMsg 返回提示信息
     */
    public void setResultMsg(String resultMsg) {
        this.resultMsg = resultMsg;
    }

    /**
     * 获取返回数据
     *
     * @return 返回数据
     */
    public Object getResultData() {
        return resultData;
    }

    /**
     * 设置返回数据
     *
     * @param resultData 返回数据
     */
    public void setResultData(Object resultData) {
        this.resultData = resultData;
    }

}
