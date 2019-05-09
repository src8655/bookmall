package bookmall.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import bookmall.vo.OrdersBookVo;
import bookmall.vo.OrdersVo;


public class OrdersDao {
	
	//주문 입력(입력 후 주문번호를 리턴)
	public Long insertOrders(OrdersVo vo) {
		Long ordersNo = -1L;
		
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {
			con = getConnection();
			
			String sql = "insert into orders values(null, " + 
					" (select concat(DATE_FORMAT(now(),\'%Y%m%d\'), \'_\', LPAD(count(a.orders_date)+1, 5, 0))" + 
					" from orders a" + 
					" where DATE_FORMAT(a.orders_date,\'%Y%m%d\') = DATE_FORMAT(now(),\'%Y%m%d\'))" + 
					" , ?, ?, now(), ?)";
			pstmt = con.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
			pstmt.setLong(1, vo.getMoney());
			pstmt.setString(2, vo.getAddr());
			pstmt.setLong(3, vo.getMemberNo());
			
			pstmt.executeUpdate();
			
			//입력된 주문의 주문번호 받기
			ResultSet rs = pstmt.getGeneratedKeys();
			
			if(rs.next()) ordersNo = rs.getLong(1);
			
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
		
		return ordersNo;
	}
	
	//주문서적 입력
	public boolean insertOrderBook(OrdersBookVo vo) {
		boolean result = false;
		
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {
			con = getConnection();
			
			String sql = "insert into orders_book values(?, ?, ?)";
			pstmt = con.prepareStatement(sql);
			pstmt.setLong(1, vo.getOrdersNo());
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
	

	//주문도서 금액 총합계를 리턴
	public Long getOrdersBookMoney(Long ordersNo) {
		Long result = 0L;
		
		
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			con = getConnection();
			
			
			String sql = "select sum(b.price * a.counts)" + 
					" from orders_book a, book b" + 
					" where a.book_no = b.no" + 
					" and a.orders_no = ?";
			pstmt = con.prepareStatement(sql);
			pstmt.setLong(1, ordersNo);
			
			rs = pstmt.executeQuery();
			
			if(rs.next()) result = rs.getLong(1);
			
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
	
	//주문의 금액 갱신
	public boolean updateOrdersMoney(Long ordersNo, Long money) {
		boolean result = false;
		
		
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {
			con = getConnection();
			
			
			String sql = "update orders set money=? where no=?";
			pstmt = con.prepareStatement(sql);
			pstmt.setLong(1, money);
			pstmt.setLong(2, ordersNo);
			
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
	
	//주문 리스트 출력(전체고객 주문)
	public List<OrdersVo> getOrdersList() {
		List<OrdersVo> result = new ArrayList<OrdersVo>();
		
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			con = getConnection();
			
			String sql = "select a.no, a.orders_code, a.money, a.addr, DATE_FORMAT(a.orders_date,\'%Y년 %m월 %d일\'), a.member_no, b.name, b.email" + 
					" from orders a, member b" + 
					" where a.member_no = b.no"
					+ " order by a.orders_date desc";
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				OrdersVo vo = new OrdersVo();
				vo.setNo(rs.getLong(1));
				vo.setOrdersCode(rs.getString(2));
				vo.setMoney(rs.getLong(3));
				vo.setAddr(rs.getString(4));
				vo.setOrdersDate(rs.getString(5));
				vo.setMemberNo(rs.getLong(6));
				vo.setName(rs.getString(7));
				vo.setEmail(rs.getString(8));
				
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
	//주문 리스트 출력(특정고객 주문)
	public List<OrdersVo> getOrdersList(Long memberNo) {
		List<OrdersVo> result = new ArrayList<OrdersVo>();
		
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			con = getConnection();
			
			String sql = "select a.no, a.orders_code, a.money, a.addr, DATE_FORMAT(a.orders_date,\'%Y년 %m월 %d일\'), a.member_no, b.name, b.email" + 
					" from orders a, member b" + 
					" where a.member_no = b.no"
					+ " and a.member_no = ?"
					+ " order by a.orders_date desc";
			pstmt = con.prepareStatement(sql);
			pstmt.setLong(1, memberNo);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				OrdersVo vo = new OrdersVo();
				vo.setNo(rs.getLong(1));
				vo.setOrdersCode(rs.getString(2));
				vo.setMoney(rs.getLong(3));
				vo.setAddr(rs.getString(4));
				vo.setOrdersDate(rs.getString(5));
				vo.setMemberNo(rs.getLong(6));
				vo.setName(rs.getString(7));
				vo.setEmail(rs.getString(8));
				
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
	

	//주문도서 리스트 출력(전체주문)
	public List<OrdersBookVo> getOrdersBookList() {
		List<OrdersBookVo> result = new ArrayList<OrdersBookVo>();
		
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			con = getConnection();
			
			String sql = "select a.orders_no, a.book_no, a.counts, b.orders_code, c.title" + 
					" from orders_book a, orders b, book c" + 
					" where a.orders_no = b.no" + 
					" and a.book_no = c.no";
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				OrdersBookVo vo = new OrdersBookVo();
				vo.setOrdersNo(rs.getLong(1));
				vo.setBookNo(rs.getLong(2));
				vo.setCounts(rs.getLong(3));
				vo.setOrdersCode(rs.getString(4));
				vo.setTitle(rs.getString(5));
				
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
	
	//주문도서 리스트 출력(특정주문)
	public List<OrdersBookVo> getOrdersBookList(Long ordersNo) {
		List<OrdersBookVo> result = new ArrayList<OrdersBookVo>();
		
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			con = getConnection();
			
			String sql = "select a.orders_no, a.book_no, a.counts, b.orders_code, c.title" + 
					" from orders_book a, orders b, book c" + 
					" where a.orders_no = b.no" + 
					" and a.book_no = c.no"
					+ " and a.orders_no = ?";
			pstmt = con.prepareStatement(sql);
			pstmt.setLong(1, ordersNo);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				OrdersBookVo vo = new OrdersBookVo();
				vo.setOrdersNo(rs.getLong(1));
				vo.setBookNo(rs.getLong(2));
				vo.setCounts(rs.getLong(3));
				vo.setOrdersCode(rs.getString(4));
				vo.setTitle(rs.getString(5));
				
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
