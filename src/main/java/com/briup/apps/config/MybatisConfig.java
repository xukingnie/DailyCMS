package com.briup.apps.config;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@MapperScan("com.briup.apps.dao")
public class MybatisConfig {

}
