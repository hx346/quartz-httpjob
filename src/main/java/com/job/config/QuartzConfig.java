package com.job.config;

import org.quartz.Trigger;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;


import javax.annotation.Resource;
import javax.sql.DataSource;

@Configuration
public class QuartzConfig {

    @Resource
    private DataSource dataSource; // 注入数据源

    @Bean
    public SchedulerFactoryBean schedulerFactory(Trigger... triggers) {
        SchedulerFactoryBean schedulerFactoryBean = new SchedulerFactoryBean();
        schedulerFactoryBean.setDataSource(dataSource); // 设置数据源
        schedulerFactoryBean.setTriggers(triggers);
        // 其他配置...
        return schedulerFactoryBean;
    }
}