package config;

import org.apache.tomcat.jdbc.pool.DataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import dbquery.DbQuery;
import spring.DeptDao;

@Configuration
public class DbConfig {

	@Bean(destroyMethod = "close") // 접속끊기면 close시킴
	public DataSource dataSource() {
		DataSource ds = new DataSource(); // ds - 커넥션 풀
		ds.setDriverClassName("com.mysql.jdbc.Driver");
		ds.setUrl("jdbc:mysql://localhost:3307/spring5fs?characterEncoding=utf8");
		ds.setUsername("root");
		ds.setPassword("mysql");
		ds.setInitialSize(2);
		ds.setMaxActive(10);
		return ds;
	}
	
	@Bean
	public DbQuery dbQuery() {
		return new DbQuery(dataSource()); // dataSource 사용
	}
	
	@Bean
	public DeptDao deptDao() {
		return new DeptDao(dataSource());
	}
}
