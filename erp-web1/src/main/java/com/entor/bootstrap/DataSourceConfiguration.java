package com.entor.bootstrap;

import java.util.Properties;

import javax.sql.DataSource;

import org.apache.ibatis.plugin.Interceptor;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import com.github.pagehelper.PageInterceptor;
import com.mchange.v2.c3p0.ComboPooledDataSource;

@Configuration
public class DataSourceConfiguration {
	
	@Bean
	@Primary
	@ConfigurationProperties(prefix="spring.datasource.c3p0")
	public DataSource getDataSource() {
		return DataSourceBuilder.create().
				type(ComboPooledDataSource.class).
				build();
	}
	/** 定义创建Session工厂Bean的方法 */
	@Bean
    public SqlSessionFactoryBean sqlSessionFactoryBean() throws Exception {
		//定义MyBatis的Session工厂对象，用于产生MyBatis全局的会话工厂
        SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
        //设置数据源
        sqlSessionFactoryBean.setDataSource(getDataSource());
        /*
         * 设置分页拦截器参数
         * 	reasonable:分页合理化参数，默认值为false。当该参数设置为 true 时，
         * 		pageNum<=0 时会查询第一页，pageNum>pages（超过总数时），会查询最后一页。
         * 		默认false 时，直接根据参数进行查询。
         * 	supportMethodsArguments:支持通过 Mapper 接口参数来传递分页参数，默认值false，
         * 		分页插件会从查询方法的参数值中，自动根据上面 params 配置的字段中取值，查找到合适的值时就会自动分页。
         * 	pageSizeZero:默认值为 false，当该参数设置为 true 时，如果 pageSize=0 或者 RowBounds.limit = 0 就会查询出全部的结果。
         */
        Properties properties = new Properties();
        properties.setProperty("reasonable", "true");
        properties.setProperty("supportMethodsArguments", "true");
        properties.setProperty("pageSizeZero", "true");
        //创建分页拦截器
        PageInterceptor interceptor = new PageInterceptor();
        interceptor.setProperties(properties);
        //添加分页拦截器
        sqlSessionFactoryBean.setPlugins(new Interceptor[]{interceptor});
        return sqlSessionFactoryBean;
    }
	
}
