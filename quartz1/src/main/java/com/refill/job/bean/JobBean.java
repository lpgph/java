package com.refill.job.bean;

import java.io.Serializable;
import java.util.Date;

/**
 * 任务计划 <br/>
 * Create Date: 3/21/18 5:02 PM <br/>
 *
 * @author Peter Guo
 * @version 0.1
 **/
public class JobBean implements Serializable {

    /**
     * 任务名称(不可重复)
     */
    private String jobName;


    /**
     * 任务调用类名，包名+类名
     */
    private String jobClassName;

    /**
     * 任务描述
     */
    private String jobDescription;

    /**
     * 触发器状态
     */
    private String triggerState;

    /**
     * cron表达式
     */
    private String triggerCronEx;

    /**
     * 启动时间
     */
    private Date triggerStartTime;

    /**
     * 前一次运行时间
     */
    private Date triggerPreviousTime;

    /**
     * 下次运行时间
     */
    private Date triggerNextTime;


    public String getJobName() {
        return jobName;
    }

    public void setJobName(String jobName) {
        this.jobName = jobName;
    }

    public String getJobClassName() {
        return jobClassName;
    }

    public void setJobClassName(String jobClassName) {
        this.jobClassName = jobClassName;
    }


    public String getTriggerState() {
        return triggerState;
    }

    public void setTriggerState(String triggerState) {
        this.triggerState = triggerState;
    }

    public String getTriggerCronEx() {
        return triggerCronEx;
    }

    public void setTriggerCronEx(String triggerCronEx) {
        this.triggerCronEx = triggerCronEx;
    }

    public String getJobDescription() {
        return jobDescription;
    }

    public void setJobDescription(String jobDescription) {
        this.jobDescription = jobDescription;
    }

    public Date getTriggerStartTime() {
        return triggerStartTime;
    }

    public void setTriggerStartTime(Date triggerStartTime) {
        this.triggerStartTime = triggerStartTime;
    }

    public Date getTriggerPreviousTime() {
        return triggerPreviousTime;
    }

    public void setTriggerPreviousTime(Date triggerPreviousTime) {
        this.triggerPreviousTime = triggerPreviousTime;
    }

    public Date getTriggerNextTime() {
        return triggerNextTime;
    }

    public void setTriggerNextTime(Date triggerNextTime) {
        this.triggerNextTime = triggerNextTime;
    }
}
