package com.refill.job.bean;

import org.quartz.*;
import org.quartz.impl.matchers.GroupMatcher;
import org.quartz.impl.triggers.CronTriggerImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.text.ParseException;
import java.util.*;

/**
 * Quartz工具类  支持quartz自身调度和spring等调度 <br/>
 * Create Date: 3/21/18 9:36 AM <br/>
 *
 * @author Peter Guo
 * @version 0.1
 **/
public class JobBeanUtil {

    /**
     * slf4j日志记录
     */
    protected static final Logger logger = LoggerFactory.getLogger(JobBeanUtil.class);


    private static Scheduler scheduler;

    @Autowired
    public void setScheduler(Scheduler scheduler) {
        JobBeanUtil.scheduler = scheduler;
    }


    /**
     * 默认任务组名
     */
    private static String JOB_GROUP_NAME = "DEFAULT_JOBGROUP_NAME";

    /**
     * 默认触发器组名
     */
    private static String TRIGGER_GROUP_NAME = "DEFAULT_TRIGGERGROUP_NAME";

    /**
     * 传入事务执行类JSON数据的参数名
     */
    public final static String JOB_TASK_DATA_JSON_NAME = "jobTaskDataJson";


    /**
     * 添加一个定时任务
     *
     * @param jobName    任务名
     * @param jobClass   任务
     * @param cron       cron表达式
     * @param jobDataMap 需要传送的数据 Map格式
     */
    public static boolean addJob(String jobName, String jobClass, String cron, JobDataMap jobDataMap) {
        try {

            TriggerKey triggerKey = TriggerKey.triggerKey(jobName, TRIGGER_GROUP_NAME);
            CronTrigger trigger = (CronTrigger) scheduler.getTrigger(triggerKey);
            //一个触发器只能存在一个定时任务!!
            if (null != trigger) return false;

            trigger = TriggerBuilder
                    .newTrigger()
                    .withIdentity(jobName, TRIGGER_GROUP_NAME)
                    .withSchedule(CronScheduleBuilder.cronSchedule(cron))
                    .build();

            JobKey jobKey = JobKey.jobKey(jobName, JOB_GROUP_NAME);
            JobDetail jobDetail = scheduler.getJobDetail(jobKey);
            if (jobDetail != null) return false;
            //任务
            jobDetail = JobBuilder
                    .newJob((Class<? extends Job>) Class.forName(jobClass))
                    .withIdentity(jobName, JOB_GROUP_NAME)
                    .build();

            //需要传送的数据 Map格式
            if (jobDataMap != null && jobDataMap.size() > 0) {
                jobDetail.getJobDataMap().putAll(jobDataMap);
            }
            scheduler.scheduleJob(jobDetail, trigger);
            return true;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }


    /**
     * 根据 任务名修改时间 重启触发器
     *
     * @param jobName 任务名
     * @param cron    cron表达式
     */
    public static void modifyTriggerTime(String jobName, String cron) {
        try {

            TriggerKey triggerKey = TriggerKey.triggerKey(jobName, TRIGGER_GROUP_NAME);
            CronTrigger trigger = (CronTrigger) scheduler.getTrigger(triggerKey);

            //不存在,不修改
            if (trigger == null) {
                return;
            }
            String oldTime = trigger.getCronExpression();
            if (!oldTime.equalsIgnoreCase(cron)) {
                //按新的cronExpression表达式重新构建trigge
                trigger.getTriggerBuilder()
                        .withSchedule(CronScheduleBuilder.cronSchedule(cron))
                        .build();
                // 恢复启动触发器
                scheduler.resumeTrigger(triggerKey);
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }


    /**
     * 根据 任务名和任务组 以及 触发器名和触发器组来修改时间(重新开始任务)
     *
     * @param jobName 任务名
     * @param cron    cron表达式
     */
    public static void modifyJobTime(String jobName, String cron, JobDataMap jobDataMap) {
        TriggerKey triggerKey = TriggerKey.triggerKey(jobName, TRIGGER_GROUP_NAME);
        try {
            CronTrigger trigger = (CronTrigger) scheduler.getTrigger(triggerKey);
            if (trigger == null) {
                return;
            }
            String oldTime = trigger.getCronExpression();
            if (!oldTime.equalsIgnoreCase(cron)) {
                /* 方式二 先删除任务 重新再添加 */
                //根据任务名和任务组来重新创建工作
                JobKey job = JobKey.jobKey(jobName, JOB_GROUP_NAME);
                //获取任务执行的class路径
                JobDetail jobDetail = scheduler.getJobDetail(job);
                //删除任务重新添加
                deleteJob(jobName);
                addJob(jobName, jobDetail.getJobClass().toString(), cron, jobDataMap);
            }
        } catch (SchedulerException e) {
            e.printStackTrace();
        }
    }


    /**
     * 根据 触发器名和触发器组来修改时间 重启任务
     *
     * @param jobName 任务名
     * @param cron    cron表达式
     */
    public static boolean modifyJob(String jobName, String cron) {
        TriggerKey triggerKey = TriggerKey.triggerKey(jobName, TRIGGER_GROUP_NAME);
        try {
            CronTrigger trigger = (CronTrigger) scheduler.getTrigger(triggerKey);
            if (trigger == null) return false;
            String oldTime = trigger.getCronExpression();
            if (!oldTime.equalsIgnoreCase(cron)) {
                /*方式一 恢复触发器相关的job */
                trigger = trigger
                        .getTriggerBuilder()
                        .withIdentity(triggerKey)
                        .withSchedule(CronScheduleBuilder.cronSchedule(cron))
                        .build();
                /*
                 * 替换一个指定的Trigger, 即解除指定Trigger与任务的绑定，并将新的Trigger与任务绑定，
                 * Quartz会自动调整新Trigger的JobName与JobGroup，而旧的Trigger将被移除
                 */
                scheduler.rescheduleJob(triggerKey, trigger);
                return true;
            }
        } catch (SchedulerException e) {
            e.printStackTrace();
        }
        return false;
    }


    /**
     * 立即执行一个任务
     *
     * @param jobName 任务名
     */
    public boolean runJob(String jobName) {
        JobKey jobKey = JobKey.jobKey(jobName, JOB_GROUP_NAME);
        try {
            //创建一个立即触发的Trigger，并将其与name与group指定的任务绑定
            scheduler.triggerJob(jobKey);
            return true;
        } catch (SchedulerException e) {
            e.printStackTrace();
        }
        return false;
    }

    /**
     * 暂停任务
     *
     * @param jobName 任务名
     */
    public static boolean pauseJob(String jobName) {
        JobKey jobKey = JobKey.jobKey(jobName, JOB_GROUP_NAME);
//        TriggerKey triggerKey = TriggerKey.triggerKey(jobName, TRIGGER_GROUP_NAME);
        try {
            // 暂停触发器
//            scheduler.pauseTrigger(triggerKey);
            scheduler.pauseJob(jobKey);
            return true;
        } catch (SchedulerException e) {
            logger.debug("{}", e);
        }
        return false;
    }

    /**
     * 恢复任务
     *
     * @param jobName 任务名
     */
    public static boolean resumeJob(String jobName) {
        JobKey jobKey = JobKey.jobKey(jobName, JOB_GROUP_NAME);
//        TriggerKey triggerKey = TriggerKey.triggerKey(jobName, TRIGGER_GROUP_NAME);
        try {
            scheduler.resumeJob(jobKey);
//            scheduler.resumeTrigger(triggerKey);
            return true;
        } catch (SchedulerException e) {
            logger.debug("{}", e);
        }
        return false;
    }



    /**
     * 删除一个任务以及触发器
     *
     * @param jobName 任务名
     */
    public static boolean deleteJob(String jobName) {
        //获取触发器key
        TriggerKey triggerKey = TriggerKey.triggerKey(jobName, TRIGGER_GROUP_NAME);
        try {
            scheduler.pauseTrigger(triggerKey);// 暂停触发器
            scheduler.unscheduleJob(triggerKey);// 移除触发器
            //获取任务
            JobKey job = JobKey.jobKey(jobName, JOB_GROUP_NAME);
            // 删除任务
            scheduler.deleteJob(job);
            return true;
        } catch (SchedulerException e) {
            logger.debug("{}", e);
        }
        return false;
    }


    /**
     * 移除所有任务以及触发器
     */
    public static void deleteAllJobs() throws SchedulerException {
        GroupMatcher<JobKey> matcher = GroupMatcher.anyJobGroup();
        GroupMatcher<TriggerKey> triggerGroupEquals = GroupMatcher.anyTriggerGroup();
        scheduler.pauseTriggers(triggerGroupEquals);
        scheduler.unscheduleJobs(new ArrayList<>(scheduler.getTriggerKeys(triggerGroupEquals)));
        scheduler.deleteJobs(new ArrayList<>(scheduler.getJobKeys(matcher)));
    }


    /**
     * 启动所有触发器线程
     */
    public static void startJobs() throws SchedulerException {
        if (!scheduler.isStarted()) {
            scheduler.start();
        }
    }

    /**
     * 关闭所有触发器线程
     */
    public static void shutdownJobs() throws SchedulerException {
        if (!scheduler.isShutdown()) {
            //等待调度job完成
            scheduler.shutdown();
        }
    }


    /**
     * 判断cron时间表达式正确性
     *
     * @param cronExpression cron表达式
     * @return boolean
     */
    public static boolean isCronEx(final String cronExpression) {
        CronTriggerImpl trigger = new CronTriggerImpl();
        try {
            trigger.setCronExpression(cronExpression);
            Date date = trigger.computeFirstFireTime(null);
            return date != null && date.after(new Date());
        } catch (ParseException e) {
            logger.debug("{}", e);
        }
        return false;
    }


    /**
     * 获取单个任务
     *
     * @param jobName
     * @return
     */
    public static JobBean getEntity(String jobName) throws SchedulerException {
        JobKey jobKey = JobKey.jobKey(jobName, JOB_GROUP_NAME);
        TriggerKey triggerKey = TriggerKey.triggerKey(jobName, TRIGGER_GROUP_NAME);
        return getJobBean(jobKey, triggerKey);
    }

    /**
     * 获取 job bean
     *
     * @param jobKey
     * @param triggerKey
     * @return
     * @throws SchedulerException
     */
    private static JobBean getJobBean(JobKey jobKey, TriggerKey triggerKey) throws SchedulerException {
        JobBean job = new JobBean();
        JobDetail jobDetail = scheduler.getJobDetail(jobKey);
        if (jobDetail != null) {
            //任务名
            job.setJobName(jobKey.getName());
            //class路径
            job.setJobClassName(jobDetail.getJobClass().getName());
            //job描述
            job.setJobDescription(jobDetail.getDescription());
        }
        CronTrigger trigger = (CronTrigger) scheduler.getTrigger(triggerKey);
        if (trigger != null) {
            //触发器状态
            Trigger.TriggerState triggerState = scheduler.getTriggerState(trigger.getKey());
            //触发器状态
            job.setTriggerState(triggerState.name());
            //获取cron表达式
            job.setTriggerCronEx(trigger.getCronExpression());
            //触发器开始时间
            job.setTriggerStartTime(trigger.getStartTime());
            //上次触发时间
            job.setTriggerPreviousTime(trigger.getPreviousFireTime());
            //下次触发时间
            job.setTriggerNextTime(trigger.getNextFireTime());
        }
        return job;
    }

    /**
     * 获取所有任务
     *
     * @return list
     * @throws SchedulerException
     */
    public static List<JobBean> getAllJobs() {
        try {

            GroupMatcher<JobKey> matcher = GroupMatcher.anyJobGroup();
            Set<JobKey> jobKeys = scheduler.getJobKeys(matcher);
            List<JobBean> jobList = new ArrayList<>();
            //一个任务可以有多个触发器
            for (JobKey jobKey : jobKeys) {
                List<? extends Trigger> triggers = scheduler.getTriggersOfJob(jobKey);
                for (Trigger trigger : triggers) {
                    jobList.add(getJobBean(jobKey, trigger.getKey()));
                }
            }
            return jobList;
        } catch (SchedulerException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 所有正在运行的job
     *
     * @return
     * @throws SchedulerException
     */
    public static List<JobBean> getRunningJob() {
        try {
            List<JobExecutionContext> executingJobs = scheduler.getCurrentlyExecutingJobs();
            List<JobBean> jobList = new ArrayList<>(executingJobs.size());
            for (JobExecutionContext executingJob : executingJobs) {
                JobBean job = new JobBean();
                JobDetail jobDetail = executingJob.getJobDetail();
                JobKey jobKey = jobDetail.getKey();
                Trigger trigger = executingJob.getTrigger();
                jobList.add(getJobBean(jobKey, trigger.getKey()));
                jobList.add(job);
            }
            return jobList;
        } catch (SchedulerException e) {
            e.printStackTrace();
        }
        return null;
    }

}
