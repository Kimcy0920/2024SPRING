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
	
	public List<Item> selectAllItem() {
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
	
	public Member selectById(Long id) {
		String sql = "select * from member where id = ?";
		List<Member> results = jdbcTemplate.query(sql, new RowMapper<Member>() {
			@Override
			public Member mapRow(ResultSet rs, int rowNum) throws SQLException {
				Member member = new Member(
						rs.getLong("id"),
						rs.getString("name"),
						rs.getString("City"),
						rs.getString("Street"),
						rs.getString("Zipcode"));
				member.setId(rs.getLong("id"));
				return member;
			}
		}, id); // 쿼리문에서 ?에 해당
		return results.isEmpty() ? null : results.get(0);
		// List가 비어있으면 null 리턴, 아니면 0번째를 리턴
	}
}
