package main;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import config.DbConfig;
import spring.DeptDao;

public class selectAllDept {
	public static void main(String[] args) {
		AnnotationConfigApplicationContext ctx =
				new AnnotationConfigApplicationContext(DbConfig.class);
		
		DeptDao deptDao = ctx.getBean(DeptDao.class);
		System.out.println(deptDao.selectDept().get(0));
		ctx.close();
	}
}
