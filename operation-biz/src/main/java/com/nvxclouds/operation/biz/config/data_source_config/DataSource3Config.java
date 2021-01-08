package com.nvxclouds.operation.biz.config.data_source_config;

import com.alibaba.druid.pool.DruidDataSource;
import com.nvxclouds.common.core.mapper.Mapper;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import tk.mybatis.spring.annotation.MapperScan;

import javax.sql.DataSource;

/**
 * @Auther: ShouZhi@Duan
 * @Date: 2020/7/1 15:50
 * @Description:postgresql license数据源配置
 */

@Configuration
@MapperScan(basePackages = {"com.nvxclouds.operation.biz.license_mapper","com.nvxclouds.common.core.mapper"}, sqlSessionTemplateRef = "db3SqlSessionTemplate",markerInterface = Mapper.class)
public class DataSource3Config {


    @Bean
    public DataSource db3DataSource() {
        DruidDataSource datasource = new DruidDataSource();
        datasource.setUrl("jdbc:postgresql://central.nvxclouds.net:54321/medusa_license_db");
        datasource.setUsername("medusa_license");
        datasource.setPassword("medusa");
        datasource.setDriverClassName("org.postgresql.Driver");
        datasource.setDbType("com.alibaba.druid.pool.DruidDataSource");
        return datasource;
        //return DataSourceBuilder.create().build();
    }

    @Bean
    public SqlSessionFactory db3SqlSessionFactory(@Qualifier("db3DataSource") DataSource dataSource) throws Exception {
        SqlSessionFactoryBean bean = new SqlSessionFactoryBean();
        bean.setDataSource(dataSource);
        bean.setMapperLocations(new PathMatchingResourcePatternResolver().getResources("classpath*:license_mapper/*.xml"));
        return bean.getObject();
    }

    @Bean
    public DataSourceTransactionManager db3TransactionManager(@Qualifier("db3DataSource") DataSource dataSource) {
        return new DataSourceTransactionManager(dataSource);
    }

    @Bean
    public SqlSessionTemplate db3SqlSessionTemplate(@Qualifier("db3SqlSessionFactory") SqlSessionFactory sqlSessionFactory) throws Exception {
        return new SqlSessionTemplate(sqlSessionFactory);
    }
}
