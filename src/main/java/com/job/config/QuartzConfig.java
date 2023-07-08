package com.job.config;

import org.quartz.spi.JobFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;
import org.springframework.scheduling.quartz.SpringBeanJobFactory;

import javax.annotation.Resource;
import javax.sql.DataSource;

/**
 * @author hexin
 */
@Configuration
public class QuartzConfig {
    @Resource
    private DataSource dataSource;

    @Bean
    public JobFactory jobFactory() {
        return new SpringBeanJobFactory();
    }

    @Bean
    public SchedulerFactoryBean schedulerFactoryBean(JobFactory jobFactory) {
        SchedulerFactoryBean schedulerFactoryBean = new SchedulerFactoryBean();

        // 配置数据源
        schedulerFactoryBean.setDataSource(dataSource);
        // 配置自定义的Job工厂
        schedulerFactoryBean.setJobFactory(jobFactory);

        return schedulerFactoryBean;
    }
}