package com.job;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import tk.mybatis.spring.annotation.MapperScan;

/**
 * 系统启动入口类，负责启动整个 Spring Boot 应用。
 * 通过 {@link MapperScan} 注解扫描 MyBatis 的 Mapper 接口，
 * 完成数据库映射文件的加载。
 *
 * <p>该类只包含一个 {@code main} 方法，用于启动应用。</p>
 */
@SpringBootApplication
@MapperScan("com.job.dao")
public class Application {

    /**
     * 程序的入口方法，执行该方法即可启动应用。
     *
     * @param args 启动参数
     */
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

}
