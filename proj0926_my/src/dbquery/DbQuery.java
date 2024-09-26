package dbquery;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;

import domain.entity.Item;
import domain.entity.Member;
import domain.entity.OrderResult;
import domain.entity.Orders;

public class DbQuery {
	private JdbcTemplate jdbcTemplate;
	private DataSource dataSource;

	public DbQuery(DataSource dataSource) {
		this.dataSource = dataSource;
		this.jdbcTemplate = new JdbcTemplate(dataSource);
	}
	
	public List<Item> findAllItem() {
		String sql = "select * from item";
		return jdbcTemplate.query(sql, (rs, rn)->{
			Item item = new Item(
					rs.getLong("id"),
					rs.getString("name"),
					rs.getInt("price"),
					rs.getInt("stockquantity")
			);
			return item;
		});
	}
	
	public int count() {
		Connection conn = null;
		try {
			conn = dataSource.getConnection();
			try (Statement stmt = conn.createStatement();
					ResultSet rs = stmt.executeQuery("select count(*) from MEMBER")) {
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
	
	public List<Member> selectAllMember() {
        String query = "select * from member";
        List<Member> list = jdbcTemplate.query(
                query, (rs, rowNum)->{
                	Member member = new Member(
                			rs.getLong("id"),
                			rs.getString("name"),
                			rs.getString("city"),
                			rs.getString("street"),
                			rs.getString("zipcode"));
                	return member;
                });     
        
        return list;
    }
	
	public List<OrderResult> selectByOrder(String name) {
		List<OrderResult> results = jdbcTemplate.query(
				"select m.name name, i.name item, i.price price, oi.count count, oi.orderprice orderprice, o.order_date order_date\r\n"
				+ "  from member m, orders o, order_item oi, item i\r\n"
				+ " where m.id = o.member_id\r\n"
				+ "   and o.id = oi.order_id\r\n"
				+ "   and oi.item_id = i.id\r\n"
				+ "   and m.name = ?",
				
				new RowMapper<OrderResult>() {
					@Override
					public OrderResult mapRow(ResultSet rs, int rowNum) throws SQLException {
						OrderResult orderResult = new OrderResult(
								rs.getString("name"),
								rs.getString("item"),
								rs.getInt("count"),
								rs.getInt("orderprice"),
								rs.getString("order_date"));
						
						return orderResult;
					}
				}, name);

		return results;
	}
	
	public void ordersUpdate(Orders orders) {
		jdbcTemplate.update("update orders set order_item_id = ? where id = ?",
				orders.getOrderItemId(), orders.getId());
	}
	
	public Long insertOrders(Orders orders) {
        KeyHolder keyHolder = new GeneratedKeyHolder();
        String sql = "insert into orders(member_id, order_item_id, city, street, zipcode, order_date)\r\n"
        		+ "values (?, ?, ?, ?, ?, now());";

        jdbcTemplate.update(connection -> {
            PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setLong(1, orders.getMemberId());
            ps.setLong(2, orders.getOrderItemId());
            ps.setString(3, orders.getCity());
            ps.setString(4, orders.getStreet());
            ps.setString(5, orders.getZipcode());
            return ps;
        }, keyHolder);

        return keyHolder.getKey().longValue();
    }
	
	public long insertMember(Member member) {
		KeyHolder keyHolder = new GeneratedKeyHolder();
		String sql = "insert into member(name, city, street, zipcode) values(?, ?, ?, ?)";
		
		jdbcTemplate.update(connection -> {
			PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			ps.setString(1, member.getName());
            ps.setString(2, member.getCity());
            ps.setString(3, member.getStreet());
            ps.setString(4, member.getZipcode());
            return ps;
		}, keyHolder);
		
		return keyHolder.getKey().longValue();
	}
}
