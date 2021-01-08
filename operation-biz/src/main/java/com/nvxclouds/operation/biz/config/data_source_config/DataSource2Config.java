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
 * @Description:postgresql study-server数据源配置
 */

@Configuration
@MapperScan(basePackages = {"com.nvxclouds.operation.biz.pg_mapper","com.nvxclouds.common.core.mapper"}, sqlSessionTemplateRef = "db2SqlSessionTemplate",markerInterface = Mapper.class)
public class DataSource2Config {


//    @Bean   ======AWS服务数据库======
//    public DataSource db2DataSource() {
//        DruidDataSource datasource = new DruidDataSource();
//        datasource.setUrl("jdbc:postgresql://central.nvxclouds.net:54320/medusa_study_db");
//        datasource.setUsername("medusa_study");
//        datasource.setPassword("medusa");
//        datasource.setDriverClassName("org.postgresql.Driver");
//        datasource.setDbType("com.alibaba.druid.pool.DruidDataSource");
//        return datasource;
//    }


//    @Bean  //======demo服务数据库======
//    public DataSource db2DataSource() {
//        DruidDataSource datasource = new DruidDataSource();
//        datasource.setUrl("jdbc:postgresql://home.nvxclouds.net:54320/medusa_study_db");
//        datasource.setUsername("medusa_study");
//        datasource.setPassword("medusa");
//        datasource.setDriverClassName("org.postgresql.Driver");
//        datasource.setDbType("com.alibaba.druid.pool.DruidDataSource");
//        return datasource;
//    }

    @Bean  //======运营测试服务数据库======
    public DataSource db2DataSource() {
        DruidDataSource datasource = new DruidDataSource();
        datasource.setUrl("jdbc:postgresql://central.nvxclouds.net:54320/medusa_study_db");
        datasource.setUsername("medusa_study");
        datasource.setPassword("medusa");
        datasource.setDriverClassName("org.postgresql.Driver");
        datasource.setDbType("com.alibaba.druid.pool.DruidDataSource");
        return datasource;
    }



    @Bean
    public SqlSessionFactory db2SqlSessionFactory(@Qualifier("db2DataSource") DataSource dataSource) throws Exception {
        SqlSessionFactoryBean bean = new SqlSessionFactoryBean();
        bean.setDataSource(dataSource);
        bean.setMapperLocations(new PathMatchingResourcePatternResolver().getResources("classpath*:pg_mapper/*.xml"));
        return bean.getObject();
    }

    @Bean
    public DataSourceTransactionManager db2TransactionManager(@Qualifier("db2DataSource") DataSource dataSource) {
        return new DataSourceTransactionManager(dataSource);
    }

    @Bean
    public SqlSessionTemplate db2SqlSessionTemplate(@Qualifier("db2SqlSessionFactory") SqlSessionFactory sqlSessionFactory) throws Exception {
        return new SqlSessionTemplate(sqlSessionFactory);
    }
}
