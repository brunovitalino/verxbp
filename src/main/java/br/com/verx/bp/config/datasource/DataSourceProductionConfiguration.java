package br.com.verx.bp.config.datasource;

import java.net.URI;
import java.net.URISyntaxException;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.core.env.Environment;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

@Configuration
@Profile("prod")
public class DataSourceProductionConfiguration {
	
//	@Autowired
//	private Environment environment;
//	
//	@Bean
//	public DataSource dataSource() throws URISyntaxException {
//		// usr:pw@hst:prt/pth
//		URI dbUri = new URI(environment.getProperty("DATABASE_URL"));
//		
//		return DataSourceBuilder.create()
//				.driverClassName("org.postgresql.Driver")
//				.url("jdbc:postgresql://"+dbUri.getHost()+":"+dbUri.getPort()+dbUri.getPath())
//				.username(dbUri.getUserInfo().split(":")[0])
//				.password(dbUri.getUserInfo().split(":")[1])
//				.build();
//	}

	@Value("${spring.datasource.url}")
	private String dbUrl;
	
	@Bean
	public DataSource dataSource() throws URISyntaxException {		
		HikariConfig config = new HikariConfig();
//		config.setDriverClassName("org.postgresql.Driver");
		config.setJdbcUrl(dbUrl);
		return new HikariDataSource(config);
	}
	
}
