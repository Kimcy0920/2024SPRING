package config;

import org.apache.tomcat.jdbc.pool.DataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import spring.ItemListPrinter;
import spring.MemberListPrinter;
import spring.OrderDao;

@Configuration
@ComponentScan(basePackages = {"spring"})
public class AppCtx {
	@Bean(destroyMethod = "close")
	public DataSource dataSource() {
		DataSource ds = new DataSource();
		ds.setDriverClassName("com.mysql.cj.jdbc.Driver");
		ds.setUrl("jdbc:mysql://localhost:3307/spring5db2?characterEncoding=utf8");
		ds.setUsername("spring5");
		ds.setPassword("spring5");
		ds.setInitialSize(2);
		ds.setMaxActive(10);
		return ds;
	}
	
	@Bean // 빈 등록하기
	public OrderDao orderDao() {
		return new OrderDao(dataSource());
	}
	
	@Bean
	public MemberListPrinter memberListPrinter() {
		return new MemberListPrinter(orderDao());
	}
	
	@Bean
	public ItemListPrinter itemListPrinter() {
		return new ItemListPrinter(orderDao());
	}
	
}
