package cn.nju.user.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import cn.nju.user.domain.User;

public class JdbcUserDaoImpl implements UserDao {

	@Override
	public User findByUsername(String username) {
		Connection con=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		try {
			con=JdbcUtils.getConnection();
			String sql="SELECT * FROM t_user WHERE username=?";
			pstmt=con.prepareStatement(sql);
			pstmt.setString(1, username);
			
			rs=pstmt.executeQuery();
			
			if(rs==null)return null;
			
			if(rs.next()){
				User user=new User();
				user.setUsername(rs.getString(1));
				user.setPassword(rs.getString(2));
				user.setAge(rs.getInt(3));
				user.setGender(rs.getString(4));
				
				return user;
			}else{
				return null;
			}
			
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}finally{
			try{
			if(rs!=null)rs.close();
			if(pstmt!=null)pstmt.close();
			if(con!=null)con.close();
			}catch(SQLException e){
				
			}
		}
		
		
	}

	@Override
	public void add(User user)  {

		Connection con=null;
		PreparedStatement pstmt=null;
		
		try {
			con=JdbcUtils.getConnection();
			String sql="INSERT INTO t_user VALUES(?,?,?,?)";
			pstmt=con.prepareStatement(sql);
			pstmt.setString(1, user.getUsername());
			pstmt.setString(2, user.getPassword());
			pstmt.setInt(3, user.getAge());
			pstmt.setString(4, user.getGender());
			
			pstmt.executeUpdate();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}finally{
			try {
			if(pstmt!=null)
				
					pstmt.close();
			if(con!=null)con.close();
			}catch(SQLException e){}
		}
	}

}
