package spring;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

public class DeptDao {
	private JdbcTemplate jdbcTemplate;

	public DeptDao(DataSource dataSource) {
		this.jdbcTemplate = new JdbcTemplate(dataSource);
	}

	public List<Dept> selectDept() {
		String sql = "select * from dept";
		List<Dept> results = jdbcTemplate.query(sql, new RowMapper<Dept>() {
			@Override
			public Dept mapRow(ResultSet rs, int rowNum) throws SQLException {
				Dept dept = new Dept(
						rs.getString("dname"),
						rs.getString("loc"));
				dept.setDeptno(rs.getInt("deptno"));
				return dept;
			}
		}); // 쿼리문에서 ?에 해당
		return results;
		// List가 비어있으면 null 리턴, 아니면 0번째를 리턴
	}
}
