package spring;

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

public class OrderDao {
private JdbcTemplate jdbcTemplate;
	
	public OrderDao(DataSource dataSource) {
		this.jdbcTemplate = new JdbcTemplate(dataSource);
	}
	
	public Long insertOrderItemRequest(OrderItemRequest orderItemRequest) {
        KeyHolder keyHolder = new GeneratedKeyHolder();
        String sql = "INSERT INTO order_item(item_id, order_id, orderprice, count) VALUES (?, ?, ?, ?)";

        jdbcTemplate.update(connection -> {
            PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setLong(1, orderItemRequest.getItemId());
            ps.setLong(2, orderItemRequest.getOrderId());
            ps.setInt(3, orderItemRequest.getOrderPrice());
            ps.setInt(4, orderItemRequest.getCount());
            return ps;
        }, keyHolder);

        return keyHolder.getKey().longValue();
    }
	
	
	public Long insertOrder(Order order) {
        KeyHolder keyHolder = new GeneratedKeyHolder();
        String sql = "insert into orders(member_id, city, street, zipcode, order_date)\r\n"
        		+ "values (?, ?, ?, ?, now())";

        jdbcTemplate.update(connection -> {
            PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setLong(1, order.getMemberId());
            ps.setString(2, order.getCity());
            ps.setString(3, order.getStreet());
            ps.setString(4, order.getZipcode());
            return ps;
        }, keyHolder);

        return keyHolder.getKey().longValue();
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
								rs.getInt("price"),
								rs.getInt("orderprice"),
								rs.getString("order_date"));
						
						return orderResult;
					}
				}, name);

		return results;
	}
	
	public List<Member> selectAllMember() {
		List<Member> results = jdbcTemplate.query(
				"select * from member",
				
				new RowMapper<Member>() {
					@Override
					public Member mapRow(ResultSet rs, int rowNum) throws SQLException {
						Member member = new Member(
								rs.getLong("id"),
								rs.getString("name"),
								rs.getString("city"),
								rs.getString("street"),
								rs.getString("zipcode")
								);
						
						return member;
					}
				});

		return results;
	}
	
	public List<Item> selectAllItem() {
		List<Item> results = jdbcTemplate.query(
				"select * from item",
				
				new RowMapper<Item>() {
					@Override
					public Item mapRow(ResultSet rs, int rowNum) throws SQLException {
						Item item = new Item(
								rs.getLong("id"),
								rs.getString("name"),
								rs.getInt("price"),
								rs.getInt("stockquantity")
								);
						
						return item;
					}
				});

		return results;
	}
	
	public List<OrderItemResult> selectByOrderItem(Long orderId) {
		List<OrderItemResult> results = jdbcTemplate.query(
				"SELECT oi.id, i.name, item_id, order_id, orderprice, count\n"
				+ "  FROM order_item oi, item i\n"
				+ " where oi.item_id = i.id\n"
				+ "    and oi.order_id = ?",
				
				new RowMapper<OrderItemResult>() {
					@Override
					public OrderItemResult mapRow(ResultSet rs, int rowNum) throws SQLException {
						OrderItemResult orderItemResult = new OrderItemResult(
								rs.getLong("id"),
								rs.getString("name"),
								rs.getLong("item_id"),
								rs.getLong("order_id"),
								rs.getInt("orderprice"),
								rs.getInt("count")
								);
						
						return orderItemResult;
					}
				}, orderId);

		return results;
	}
	
	public void cancelOrderItem(Long id) {
		jdbcTemplate.update("delete from order_item where id = ?", id);
	}
	
	public void cancelOrder(Long id) {
		jdbcTemplate.update("delete from orders where id = ?", id);
	}
	
	public int countOrderItemByOrderId(Long id) {
		return jdbcTemplate.queryForObject("select count(*) from order_item where order_id = ?", Integer.class, id);
	}
}
