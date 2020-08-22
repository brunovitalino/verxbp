package br.com.verx.bp.config.datasource;

import java.util.Properties;

import javax.sql.DataSource;

import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Configuration
public class DataSourceTestConfiguration {
	
//	@Bean
//	public LocalContainerEntityManagerFactoryBean entityManagerFactoryBean(DataSource dataSource) {
//		LocalContainerEntityManagerFactoryBean factoryBean = new LocalContainerEntityManagerFactoryBean();
//		factoryBean.setPackagesToScan("br.com.verx.bp.model");
//		factoryBean.setDataSource(dataSource);
//		
//		JpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
//		factoryBean.setJpaVendorAdapter(vendorAdapter);
//		
//		Properties additionalProperties = new Properties();
//		additionalProperties.setProperty("hibernate.dialect", "org.hibernate.dialect.PostgreSQL94Dialect");
//		additionalProperties.setProperty("hibernate.ddl-auto", "update");
//		factoryBean.setJpaProperties(additionalProperties);
//		
//		return factoryBean;
//	}
	
	@Bean
	@Profile("dev")
	public DataSource dataSource() {
		return DataSourceBuilder.create()
				.driverClassName("org.postgresql.Driver")
				.url("jdbc:postgresql://localhost:5432/verxbp")
				.username("postgres")
				.password("123")
				.build();
	}
//	@Bean
//	public DataSource datasource() {
//		return DataSourceBuilder.create()
//				.driverClassName("org.postgresql.Driver")
//				//postgres://hceuvznkgvmxqz:0a5f95676dd49f73a3c92c89d295e7a7312d0600ce557ac65b5d7f277150833a@ec2-3-208-50-226.compute-1.amazonaws.com:5432/d5kcl2qu0o0ck9
//				.url("jdbc:postgresql://ec2-3-208-50-226.compute-1.amazonaws.com:5432/verxbp?sslmode=require")
//				.username("hceuvznkgvmxqz")
//				.password("0a5f95676dd49f73a3c92c89d295e7a7312d0600ce557ac65b5d7f277150833a")
//				.build();
//	}

	@Bean
	public Properties additionalProperties() {
		Properties additionalProperties = new Properties();
		additionalProperties.setProperty("hibernate.dialect", "org.hibernate.dialect.PostgreSQL94Dialect");
//		additionalProperties.setProperty("hibernate.ddl-auto", "update");
		additionalProperties.setProperty("hibernate.ddl-auto", "create-drop");
		return additionalProperties;
	}
	
}
