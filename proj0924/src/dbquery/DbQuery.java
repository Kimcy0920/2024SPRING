package dbquery;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import javax.sql.DataSource;
import javax.swing.tree.RowMapper;

import org.springframework.jdbc.core.JdbcTemplate;

public class DbQuery {
	private JdbcTemplate jdbcTemplate;
	private DataSource dataSource;

	public DbQuery(DataSource dataSource) {
		this.dataSource = dataSource;
		this.jdbcTemplate = new JdbcTemplate(dataSource);
	}

	public int count() {
		Connection conn = null;
		try {
			conn = dataSource.getConnection();
			try (Statement stmt = conn.createStatement();
					ResultSet rs = stmt.executeQuery("select count(*) from item")) {
				rs.next();
				return rs.getInt(1);
			}
		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			if (conn != null)
				try {
					conn.close();
				} catch (SQLException e) {
				}
		}
	}
	
	public List<Item> list() {
        String query = "select * from item";
        List<Item> list = jdbcTemplate.query(
                query, (rs, rowNum)->{
                	Item item = new Item(
                			rs.getInt("id"),
                			rs.getString("name"),
                			rs.getInt("price"),
                			rs.getInt("stockquantity"));
                	return item;
                });     
        
        return list;
    }

}
