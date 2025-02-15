package com.study.springboot.jdbc;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class MyUserDAO {
    @Autowired
    private JdbcTemplate jdbcTemplate;
 
    public int countDept() {
    	String query = "select count(*) from dept";
    	return jdbcTemplate.queryForObject(query, Integer.class);
    	// queryForObject(): 하나의 객체를 결과값으로 출력할 경우에만 사용
    }
    
    public void saveUser(MyUserDTO userDTO) {
    	String query = "insert into dept(deptno, dname, loc) values(?, ?, ?)";
    	jdbcTemplate.update(query, userDTO.getDeptno(), userDTO.getDname(), userDTO.getLoc());
    }
    
    public void deleteUser(MyUserDTO userDTO) {
    	String query = "delete from dept where deptno = ?";
    	jdbcTemplate.update(query, userDTO.getDeptno());
    }
    public void updateUser(MyUserDTO userDTO) {
    	String query = "update dept set dname=?, loc=? where deptno = ?";
    	jdbcTemplate.update(query, userDTO.getDname(), userDTO.getLoc(), userDTO.getDeptno());
    }
    
    public List<MyUserDTO> list() {
        String query = "select * from dept";
        List<MyUserDTO> list = jdbcTemplate.query(
                query,
//                new BeanPropertyRowMapper<MyUserDTO>(MyUserDTO.class) // 아래 26-32라인이 25번 RowMapper와 동일한 역할을 함
                (rs, rowNum)->{
                	MyUserDTO userDTO = new MyUserDTO(
                			rs.getInt("deptno"),
                			rs.getString("dname"),
                			rs.getString("loc"));
                	return userDTO;
                });
        
        for(MyUserDTO my : list) {
            System.out.println(my);  
        }   
        
        return list;
    }

}
