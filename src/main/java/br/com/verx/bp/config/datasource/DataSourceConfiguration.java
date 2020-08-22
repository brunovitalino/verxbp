package br.com.verx.bp.config.datasource;

import javax.sql.DataSource;

import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Configuration
public class DataSourceConfiguration {
	
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
//	public Properties additionalProperties() {
//		Properties additionalProperties = new Properties();
//		additionalProperties.setProperty("hibernate.dialect", "org.hibernate.dialect.PostgreSQL94Dialect");
//		additionalProperties.setProperty("hibernate.ddl-auto", "create-drop");
//		return additionalProperties;
//	}
	
}
