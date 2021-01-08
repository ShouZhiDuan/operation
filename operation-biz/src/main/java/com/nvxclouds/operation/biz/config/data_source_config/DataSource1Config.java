package com.nvxclouds.operation.biz.config.data_source_config;

import com.alibaba.druid.pool.DruidDataSource;
import com.nvxclouds.common.core.mapper.Mapper;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import tk.mybatis.spring.annotation.MapperScan;

import javax.sql.DataSource;

/**
 * @Auther: ShouZhi@Duan
 * @Date: 2020/7/1 15:49
 * @Description:mysql 运营本地数据源配置
 */

@Configuration
@MapperScan(basePackages = {"com.nvxclouds.*.biz.mapper","com.nvxclouds.common.core.mapper"}, sqlSessionTemplateRef = "db1SqlSessionTemplate",markerInterface = Mapper.class)
public class DataSource1Config {

    @Bean
    @Primary
    public DataSource db1DataSource() {
        //to see https://blog.csdn.net/weixin_43334140/article/details/106060505
        DruidDataSource datasource = new DruidDataSource();
        datasource.setUrl("jdbc:mysql://192.168.10.10:3306/sp?useUnicode=true&characterEncoding=UTF-8&zeroDateTimeBehavior=CONVERT_TO_NULL&serverTimezone=CTT");
        datasource.setUsername("sp_dev");
        datasource.setPassword("Wentiliangkaihua@2020dev");
        datasource.setDriverClassName("com.mysql.cj.jdbc.Driver");
        datasource.setDbType("com.alibaba.druid.pool.DruidDataSource");
        return datasource;
    }

    @Bean
    @Primary
    public SqlSessionFactory db1SqlSessionFactory(@Qualifier("db1DataSource") DataSource dataSource) throws Exception {
        SqlSessionFactoryBean bean = new SqlSessionFactoryBean();
        bean.setDataSource(dataSource);
        bean.setMapperLocations(new PathMatchingResourcePatternResolver().getResources("classpath*:mapper/*.xml"));
        return bean.getObject();
    }

    @Bean
    @Primary
    public DataSourceTransactionManager db1TransactionManager(@Qualifier("db1DataSource") DataSource dataSource) {
        return new DataSourceTransactionManager(dataSource);
    }

    @Bean
    @Primary
    public SqlSessionTemplate db1SqlSessionTemplate(@Qualifier("db1SqlSessionFactory") SqlSessionFactory sqlSessionFactory) throws Exception {
        return new SqlSessionTemplate(sqlSessionFactory);
    }
}
