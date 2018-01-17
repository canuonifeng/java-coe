package com.codeages.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Date;

import org.junit.Test;



public class JdbcTest {
	
	@Test
	public void testJdbc() throws Exception {
		Class.forName("com.mysql.jdbc.Driver");
		Connection con = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/edusoho_install","root","");
		
		con.setAutoCommit(false);
		
		PreparedStatement stat=con.prepareStatement("select * from biz_order where id = ? ");
		stat.setLong(1, 2L);
		ResultSet rs = stat.executeQuery();
		while(rs.next()) {
			System.out.println(rs.getString("sn"));
			System.out.println(new Date(rs.getLong("created_time")*1000));
		}
		
		rs.close();
		stat.close();
		con.commit();
		con.close();
	}
}
