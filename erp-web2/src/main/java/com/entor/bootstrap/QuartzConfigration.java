package com.entor.bootstrap;

import org.quartz.Trigger;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.quartz.CronTriggerFactoryBean;
import org.springframework.scheduling.quartz.MethodInvokingJobDetailFactoryBean;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;

import com.entor.quartz.StoreAlertJob;


@Configuration
public class QuartzConfigration {
    @Bean(name = "storeAlertJobBean")
    public MethodInvokingJobDetailFactoryBean storeAlertJobBean(StoreAlertJob storeAlertJob) {
        MethodInvokingJobDetailFactoryBean jobDetail = new MethodInvokingJobDetailFactoryBean();
        jobDetail.setTargetObject(storeAlertJob); // 被执行的对象
        jobDetail.setTargetMethod("sendStoreAlertMsg"); // 被执行的方法
        return jobDetail;
    }
    @Bean(name = "storeAlertJobTrigger")
    public CronTriggerFactoryBean storeAlertJobTrigger(
    		@Qualifier("storeAlertJobBean") MethodInvokingJobDetailFactoryBean storeAlertJobBean) {
        CronTriggerFactoryBean tigger = new CronTriggerFactoryBean();
        tigger.setJobDetail(storeAlertJobBean.getObject());
        tigger.setCronExpression("0/30 * * * * ?"); //Cron表达式
        return tigger;
    }
    @Bean(name = "schedulerFactory")
    public SchedulerFactoryBean schedulerFactory(
    		@Qualifier("storeAlertJobTrigger") Trigger storeAlertJobTrigger) {
        SchedulerFactoryBean bean = new SchedulerFactoryBean();
        // 覆盖已存在的任务
        bean.setOverwriteExistingJobs(true);
        //延时启动定时任务，避免系统未完全启动却开始执行定时任务的情况
        bean.setStartupDelay(5);
        //注册触发器
        bean.setTriggers(storeAlertJobTrigger);
        return bean;
}

}
