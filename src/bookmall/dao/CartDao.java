package bookmall.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import bookmall.vo.CartVo;


public class CartDao {
	//카트 입력
	public boolean insert(CartVo vo) {
		boolean result = false;
		
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {
			con = getConnection();
			
			String sql = "insert into cart values(?, ?, ?)";
			pstmt = con.prepareStatement(sql);
			pstmt.setLong(1, vo.getMemberNo());
			pstmt.setLong(2, vo.getBookNo());
			pstmt.setLong(3, vo.getCounts());

			int count = pstmt.executeUpdate();
			
			if(count == 1) result = true;
			
		} catch(SQLException e) {
			System.out.println("error" + e);
		}finally {
			try {
				if(pstmt != null) pstmt.close();
				if(con != null) con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		return result;
	}
	
	//카트 리스트 출력(모든고객)
	public List<CartVo> getList() {
		List<CartVo> result = new ArrayList<CartVo>();
		
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			con = getConnection();
			
			String sql = "select a.member_no, a.book_no, a.counts, c.price, c.title, b.name" + 
					" from cart a, member b, book c" + 
					" where a.member_no = b.no" + 
					" and a.book_no = c.no";
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				CartVo vo = new CartVo();
				vo.setMemberNo(rs.getLong(1));
				vo.setBookNo(rs.getLong(2));
				vo.setCounts(rs.getLong(3));
				vo.setPrice(rs.getLong(4));
				vo.setTitle(rs.getString(5));
				vo.setName(rs.getString(6));
				
				result.add(vo);
			}
			
			
		} catch(SQLException e) {
			System.out.println("error" + e);
		}finally {
			try {
				if(rs != null) rs.close();
				if(pstmt != null) pstmt.close();
				if(con != null) con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		return result;
	}
	
	//카트 리스트 출력(특정고객)
	public List<CartVo> getList(Long memberNo) {
		List<CartVo> result = new ArrayList<CartVo>();
		
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			con = getConnection();
			
			String sql = "select a.member_no, a.book_no, a.counts, c.price, c.title, b.name" + 
					" from cart a, member b, book c" + 
					" where a.member_no = b.no" + 
					" and a.book_no = c.no" +
					" and a.member_no = ?";
			pstmt = con.prepareStatement(sql);
			pstmt.setLong(1, memberNo);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				CartVo vo = new CartVo();
				vo.setMemberNo(rs.getLong(1));
				vo.setBookNo(rs.getLong(2));
				vo.setCounts(rs.getLong(3));
				vo.setPrice(rs.getLong(4));
				vo.setTitle(rs.getString(5));
				vo.setName(rs.getString(6));
				
				result.add(vo);
			}
			
			
		} catch(SQLException e) {
			System.out.println("error" + e);
		}finally {
			try {
				if(rs != null) rs.close();
				if(pstmt != null) pstmt.close();
				if(con != null) con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		return result;
	}
	
	
	//커넥션 받는 함수
	private Connection getConnection() throws SQLException {
		Connection con = null;
		
		try {
			Class.forName("org.mariadb.jdbc.Driver");
			
			String url = "jdbc:mariadb://192.168.0.10:3307/bookmall";
			con = DriverManager.getConnection(url, "bookmall", "bookmall");
			
		} catch (ClassNotFoundException e) {
			System.out.println("드라이버 로딩 실패 : " + e);
		}
		
		
		return con;
	}
}
